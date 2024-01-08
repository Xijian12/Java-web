<template>
    <div>
        <div>
            <el-table :data="recordsList" style="width: 100%">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="table-expand">
                            <el-form-item label="图书ID：">
                                <span>{{ props.row.bookId }}</span>
                            </el-form-item>
                            <el-form-item label="书名：">
                                <span>{{ props.row.bookName }}</span>
                            </el-form-item>
                            <el-form-item label="ISBN号：">
                                <span>{{ props.row.bookIsbn }}</span>
                            </el-form-item>
                            <el-form-item label="分类：">
                                <span>{{ props.row.bookCategory }}</span>
                            </el-form-item>
                            <el-form-item label="价格：">
                                <span>{{ props.row.bookPrice }}</span>
                            </el-form-item>
                            <el-form-item label="描述：">
                                <span>{{ props.row.bookOutline }}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column label="图书ID" prop="bookId">
                </el-table-column>
                <el-table-column label="图书名称" prop="bookName">
                </el-table-column>
                <el-table-column label="图书状态" prop="bookRelease">
                    <!-- <template slot-scope="scope">{{ scope.row.bookRelease === 1 ? '男' : '女' }}</template> -->
                    <template slot-scope="scope">
                        <!-- <p v-if="scope.row.bookRelease == 0">待审核</p>
                <p v-if="scope.row.bookRelease == 1">审核通过</p>
                <p v-if="scope.row.bookRelease == 2">审核不通过</p> -->
                        <el-tag v-if="scope.row.bookRelease == 0" type="info" effect="dark">待审核</el-tag>
                        <el-tag v-if="scope.row.bookRelease == 1" type="success" effect="dark">审核通过</el-tag>
                        <el-tag v-if="scope.row.bookRelease == 2" type="danger" effect="dark">审核不通过</el-tag>
                        <el-tag v-if="scope.row.bookRelease == 3" type="warning" effect="dark">已出售</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <span v-if="scope.row.bookRelease == 0 || scope.row.bookRelease == 2">
                            <el-button type="danger" @click="handleEdit(scope.row.bookId)">重新上传</el-button>
                        </span>
                        <span v-else>
                            <el-button type="info" disabled>不可用</el-button>
                        </span>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div>
            <el-dialog title="编辑图书" v-model="dialogFormVisible" width="700px" @close="editDialogClosed">
                <el-form ref="bookFormRef" :model="bookForm" :rules="rules" label-width="80px">
                    <el-form-item label="图书ID" style="width:150px" prop="bookId">
                        <el-input v-model="bookForm.bookId" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="图书名" style="width:500px" prop="bookName">
                        <el-input v-model="bookForm.bookName"></el-input>
                    </el-form-item>
                    <el-form-item label="ISBN号" style="width:350px" prop="bookIsbn">
                        <el-input v-model.number="bookForm.bookIsbn"></el-input>
                    </el-form-item>
                    <el-form-item label="价格" style="width:180px" prop="bookPrice">
                        <el-input v-model="bookForm.bookPrice"></el-input>
                    </el-form-item>
                    <el-form-item label="图书简介" prop="bookOutline">
                        <el-input type="textarea" v-model="bookForm.bookOutline"
                            :autosize="{ minRows: 2, maxRows: 4 }"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <span class="dialog_btn">
                            <el-button type="primary" @click="onSubmit()">重新上传</el-button>
                        </span>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
    </div>
  </template>
    
  <script>
   import {ref} from 'vue'
  const userId = ref('');
  const recordsList = ref([]);
  const dialogFormVisible = ref(false);
  const bookForm = ref( {
                bookId: '',
                bookName: '',
                bookIsbn: '',
                bookPrice: '',
                bookOutline: '',
            });
  const rules = ref({
                bookName: [
                    { required: true, message: '请输入图书名！', trigger: 'blur' },
                ],
                bookIsbn: [
                    { required: true, message: '请输入ISBN号！', trigger: 'blur' },
                ],
                bookPrice: [
                    { required: true, message: '请输入价格！', trigger: 'blur' },
                ],
                bookOutline: [
                    { required: true, message: '请输入图书简介！', trigger: 'blur' },
                ],
            });
  
        
    // created() {
    //     this.userId = this.cookie.getCookie("LoginId");
    //     this.getRecordsList();
    // },
       function getRecordsList() {
            this.$http({
                method: 'get',
                url: '/publishRecord',
                params: {
                    sellerId: this.userId
                }
            })
                .then(res => {
                    this.recordsList = res.data.result;
                    if (res.data.errcode != "0") {
                        return this.$notify({
                            title: '警告',
                            message: res.data.errmsg,
                            type: 'warning'
                        });
                    } else {
                        this.$notify({
                            title: '成功',
                            message: res.data.errmsg,
                            type: 'success'
                        });
                    }
                    console.log(this.recordsList);
                })
        }
        function handleEdit(id) {
            this.$http({
                method: 'get',
                url: 'getBook',
                params: {
                    bookId: id
                }
            })
                .then(res => {
                    console.log("res.data:", res.data);
                    if (res.data.errcode != "0") {
                        return this.$notify({
                            title: '警告',
                            message: res.data.errmsg,
                            type: 'warning'
                        });
                    } else {
                        this.bookForm = res.data.result;
                    }
                })
            this.dialogFormVisible = true
        }
        function onSubmit() {
            this.$refs.bookFormRef.validate(async valid => {
                if (!valid) return;
                //发起修改请求
                const { data: res } = await this.$http.put("changeBook", this.bookForm);
                if (res.errcode != "0") {
                    return this.$message.error("重新上传失败！");
                } else {
                    this.$message.success("重新上传成功！");
                    this.dialogFormVisible = false;
                    this.getRecordsList();
                }
            })
        }
        // 监听修改用户对话框的关闭
        function editDialogClosed() {
            this.$refs.bookFormRef.resetFields()
        }
  </script>
    
  <style lang="less" scoped>
  .table-expand {
    font-size: 0;
  }
  
  .table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  
  .table-expand .el-form-item {
    padding-left: 50px;
    margin-right: 0;
    margin-bottom: 0;
    width: 30%;
  }
  
  .dialog_btn {
    margin-left: 25%;
  }
  </style>