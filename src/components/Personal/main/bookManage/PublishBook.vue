<template>
    <div class="form_box">
      <h4 style="color:#424242">发布图书</h4>
    <el-form :model="bookForm" :rules="rules" label-width="150px">
      <el-form-item label="图书名" style="width:350px" prop="bookName">
         <el-input v-model="bookForm.bookName"></el-input>
      </el-form-item>
      <el-form-item label="图书作者" style="width:350px" prop="bookAuthor">
         <el-input v-model="bookForm.bookAuthor"></el-input>
      </el-form-item>
      <el-form-item label="图书版号" style="width:350px" prop="bookVersion">
         <el-input v-model="bookForm.bookVersion"></el-input>
      </el-form-item>
      <el-form-item label="图书出版社" style="width:350px" prop="bookPublishHouse">
         <el-input v-model="bookForm.bookPublishHouse"></el-input>
      </el-form-item>
      <el-form-item label="下载所需积分" style="width:350px" prop="downloadPoints">
         <el-input v-model="bookForm.downloadPoints"></el-input>
      </el-form-item>
      <el-form-item label="图书类别" prop="bookCategory" >
        <el-select v-model="bookForm.bookCategory"  placeholder="请选择图书分类">
          <el-option v-for="item in categoryList" :key="item.categoryId" :label="item.categoryName"
            :value="item.categoryId"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="图书简介" prop="bookProfile">
        <el-input type="textarea" v-model="bookForm.bookProfile"></el-input>
      </el-form-item>
      <el-form-item label="图书封面上传" prop="files">
        <el-upload class="box_upload" :auto-upload="false" action="" :http-request="uploadFile"
          accept=".jpg, .jpeg, .png" list-type="picture" :limit="4" :on-exceed="exceed" ref="upload" name="File"
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">上传的第一张图片默认为展示主图，只能上传jpg/jpeg/png格式图片，且单个图片不超过2MB</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('bookForm')" style="margin-left:185px">发布图书</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  
<script lang="ts" setup>
  import { ref, reactive, onMounted } from 'vue';
  import {ElMessage} from "element-plus";
  import axios from "axios";
  const uploadForm = new FormData();
  const categoryList = ref([]);
  const bookForm = ref({
                  files: [],
                  bookName: '',
                  bookAuthor: '',
                  bookCategory: '',
                  bookIsbn: '',
                  bookOutline: '',
                  bookPrice: '',
              })
  const rules = ref({
                  bookName: [
                      { required: true, message: '请输入图书名！', trigger: 'blur' },
                  ],
                  bookIsbn: [
                      { required: true, message: '请输入ISBN号！', trigger: 'blur' },
                      { type: 'number', message: 'ISBN号为数字！' }
                  ],
                  bookPrice: [
                      { required: true, message: '请输入价格！', trigger: 'blur' },
                  ],
                  bookOutline: [
                      { required: true, message: '请输入图书简介！', trigger: 'blur' },
                  ],
                  bookCategory: [
                      { required: true, message: '请选择图书种类！', trigger: 'change' },
                  ],
              })
  function uploadFile(param) {
    uploadForm.append('files', param.file);
          }
  function exceed(files, fileList) {
    return ElMessage.error("最多上传4张图片！");
          }
  function imgPreview(file, fileList) {
              let fileName = file.name;
              let regex = /(.jpg|.jpeg|.png)$/;
              if (regex.test(fileName.toLowerCase())) {
                  let picUrl = URL.createObjectURL(file.raw);
                  bookForm.files = fileList;
              } else {
                  //移除最后一个元素
                  fileList.pop();
                  ElMessage.error('请注意图片格式！');
              }
          }
  function beforeAvatarUpload(file) { 
              console.log(file)
              const isLt2M = file.size / 1024 / 1024 < 2;
              if (!isLt2M) {
                ElMessage({
                      message: '上传文件大小不能超过 2MB!',
                      type: 'warning'
                  });
              }
              return isLt2M
          }
  //提交方法
  function onSubmit(bookForm) {
              this.$refs[bookForm].validate((valid) => {
                  if (!valid) return;
                  this.$refs.upload.submit();
                  this.uploadForm.append('bookName', this.bookForm.bookName);
                  this.uploadForm.append('bookCategory', this.bookForm.bookCategory);
                  this.uploadForm.append('bookIsbn', this.bookForm.bookIsbn);
                  this.uploadForm.append('bookOutline', this.bookForm.bookOutline);
                  this.uploadForm.append('bookPrice', this.bookForm.bookPrice);
                  this.uploadForm.append('bookSeller', this.cookie.getCookie("LoginId"));
                  this.uploadForm.append('bookRelease', 0);   //默认审核状态为0，提交由后台管理系统审核
                  this.$http.post('/insertBook', this.uploadForm)
                  .then(res => {
                      if (res.data.errcode != "0") {
                          this.uploadForm = new FormData();   //清空formdata
                          this.clearFiles();
                          return this.$message.error(res.data.errmsg);
                      } else {
                          this.$refs[bookForm].resetFields(); //上传成功清空表单
                          this.clearFiles();  //清空文件列表
                          this.uploadForm = new FormData();   //清空formdata
                          this.$notify({
                              title: '成功',
                              message: res.data.errmsg,
                              type: 'success'
                          })
                      }
                  })
              })
  
          } 
  function initData() {
  // 发起简单的 GET 请求
  axios.get("/category")
  .then(response => {
    // 处理成功的响应
    categoryList.value = response.data
    console.log('成功：', categoryList);
  })
  .catch(error => {
    // 处理请求错误
    console.error('请求失败：', error);
  });
     
    }

onMounted(() => {
  initData();
  // 在这里可以执行其他操作，确保 initData 函数执行完成后再执行
});     
</script>
  
<style lang="less" scoped>
  .form_box {
      width: 40%;
      margin-left: 25%;
      background-color: #fff;
      padding: 30px;
  }
</style>
  