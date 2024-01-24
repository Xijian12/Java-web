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
      <el-form-item label="下载所需积分" style="width:350px" prop="bookPoints">
         <el-input v-model="bookForm.bookPoints"></el-input>
      </el-form-item>
      <el-form-item label="图书类别" prop="categoryName" >
        <el-select v-model="bookForm.categoryName"  placeholder="请选择图书分类">
          <el-option v-for="item in categoryList" :key="item.categoryName" :label="item.categoryName"
            :value="item.categoryName"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="图书简介" prop="bookProfile">
        <el-input type="textarea" v-model="bookForm.bookProfile"></el-input>
      </el-form-item>
      <el-form-item label="图书封面上传" prop="pictures">
        <el-upload class="box_upload"  action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
          accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="图书文件上传" prop="files">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadFileBook"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="isLoading"  @click="onSubmit()" style="margin-left:185px">发布图书</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  
<script lang="ts" setup>
  import { ref, reactive, onMounted } from 'vue';
  import {ElMessage, ElMessageBox} from "element-plus";
  import type { UploadInstance } from 'element-plus'
  import axios from "axios";
  import {useStore} from 'vuex';
  const isLoading = ref(false);
  const store = useStore();
  const usereamil = ref(store.state.personalID[0].email)
  const uploadRef = ref<UploadInstance>()
  const uploadBookRef = ref<UploadInstance>()
  const categoryList = ref([{
    categoryId:"",
    categoryName:"",
  }]);
  const bookForm = reactive<any>(
    {
      pictures: [],
      files: [],
      bookName: "",
      bookAuthor: "",
      bookVersion: "",
      bookPublishHouse: "",
      bookCoverUrl: "",
      bookPoints: "",
      bookProfile: "",
      categoryName: "",
      bookUploader: usereamil.value,
      bookCoverUuid: "",
      bookFileUuid: "",
    })
const rules = ref(
  {
    bookName: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookAuthor: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookVersion: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookPublishHouse: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookPoints: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookProfile: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    categoryName: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    files: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    pictures: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
  })
function uploadFile(param) {
  isLoading.value = true;
    const uploadForm1 = new FormData();
    uploadForm1.append('bookCover', param.file);
    // 发送上传请求
    axios.post("/upload/uploadBookcover", uploadForm1)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          bookForm.bookCoverUrl = response.data.data[0]
          bookForm.bookCoverUuid = response.data.data[1]
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
}
function uploadFileBook(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('bookFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadBook", uploadForm2)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          bookForm.bookFileUuid = response.data.data
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
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
              const isLt2M = file.size / 1024 / 1024 < 4;
              if (!isLt2M) {
                ElMessage({
                      message: '上传文件大小不能超过 4MB!',
                      type: 'warning'
                  });
              }
              return isLt2M
}
  //提交方法
function onSubmit() {    
    console.log("上传", bookForm)
    // 发送上传请求
    axios.post("/book", bookForm)
        .then(response => {
          const code = response.data.code
          const message = response.data.message
           // 添加失败
          if (code == 400) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
            });
          }
          // 添加成功
          if (code == 200) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",

          })
        }
      }).catch(error => {
          // 处理错误
          console.error(error);
        });

} 
function initData() {
  // 发起简单的 GET 请求
 axios.get("/category",{
      params:{
      page: 1,
      pageSize: 200}
    })
  .then(response => {
    // 处理成功的响应
    categoryList.value = response.data
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
      width: 50%;
      margin-left: 20%;
      background-color: #fff;
      padding: 30px;
  }
</style>
  