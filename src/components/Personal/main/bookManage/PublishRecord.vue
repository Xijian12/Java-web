<template>
    <div>
        <div>
            <el-table :data="recordsList" style="width: 100%" empty-text="没有数据">
              <el-table-column prop="bookId" label="图书ID" />
              <el-table-column prop="bookCoverUrl" label="图书封面">
                <template #default="scope">
                  <el-image
                    :src="scope.row.bookCoverUrl"
                    style="width: 70px; height: 70px;"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="bookName" label="图书名称" />
              <el-table-column prop="bookAuthor" label="图书作者"/> 
              <el-table-column prop="bookPublishHouse" label="出版社" />
              <el-table-column prop="bookDownloadNum" label="下载量" />
              <el-table-column prop="bookClickNum" label="点击量" />
              <el-table-column prop="categoryName" label="分类名称" />
              <el-table-column prop="categoryAlias" label="分类别名" />
              <el-table-column fixed="right" label="操作">
                <template #default="books">
                  <el-button
                    @click="editFromButton(editBookFormRef, books.row)"
                    type="text"
                    >编辑</el-button
                  >
                  <el-button @click="deleteBookDialog(books.row)" type="text"
                    >删除</el-button
                  >
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
    
<script setup>
import {ref, onMounted} from 'vue'
import { useStore } from 'vuex';
import axios from "axios";
const store = useStore();
const userEmail = ref(store.state.personalID[0].email)
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
function initData() {
    axios.get(`/book/userUpload/${userEmail.value}`).then(response => {
    // 处理成功的响应
    recordsList.value = response.data.data; 
    console.log("cca", recordsList.value)
  })
   
};
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
onMounted(() => {
  initData();
});
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