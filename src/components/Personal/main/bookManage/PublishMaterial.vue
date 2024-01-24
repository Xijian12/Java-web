<template>
    <div class="form_box">
      <h4 style="color:#424242">发布资料</h4>
    <el-form :model="materialForm" :rules="rules" label-width="150px">
      <el-form-item label="学校" style="width:350px" prop="school">
        <el-input v-model="materialForm.school"></el-input>
      </el-form-item>
      <el-form-item label="专业" style="width:350px" prop="major">
        <el-input v-model="materialForm.major"></el-input>
      </el-form-item>
      <el-form-item label="学科" style="width:350px" prop="subject">
        <el-input v-model="materialForm.subject"></el-input>
      </el-form-item>
      <el-form-item label="资料简介" prop="materialProfile">
        <el-input type="textarea" v-model="materialForm.materialProfile"></el-input>
      </el-form-item>
      <el-form-item label="资料封面上传" prop="materialCoverUrl">
        <el-upload class="box_upload"  action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
          accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程电子书上传" prop="elecBookUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadElecBook"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程电子书下载所需积分:" label-width="200px" style="width:350px" prop="elecBookPoints">
         <el-input v-model="materialForm.elecBookPoints"></el-input>
      </el-form-item>
      <el-form-item label="教学计划上传" prop="teachingPlanUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadTeachingPlan"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="教学计划下载所需积分:" label-width="200px"  style="width:350px" prop="teachingPlanPoints">
         <el-input v-model="materialForm.teachingPlanPoints"></el-input>
      </el-form-item>
      <el-form-item label="课程PPT上传" prop="classPptUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadClassPpt"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程PPT下载所需积分:" label-width="200px"  style="width:350px" prop="classPptPoints">
         <el-input v-model="materialForm.classPptPoints"></el-input>
      </el-form-item>
      <el-form-item label="历年卷上传" prop="calendarVolumeUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadCalendarVolume"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="历年卷下载所需积分:" label-width="200px"  style="width:350px" prop="calendarVolumePoints">
         <el-input v-model="materialForm.calendarVolumePoints"></el-input>
      </el-form-item>
      <el-form-item label="其他资料上传" prop="anotherMaterialUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadAnotherMaterial"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="其他资料下载所需积分:" label-width="200px"    style="width:350px" prop="anotherMaterialPoints">
         <el-input v-model="materialForm.anotherMaterialPoints"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()"  :loading="isLoading"  style="margin-left:185px">发布资料</el-button>
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
  const materialForm = reactive<any>(
    {
     school:'',
     major:'',
     subject:'',
     materialUploader: usereamil,
     materialProfile:'',
     materialCoverUrl:'',
     materialCoverUuid:'',
     elecBookUuid:'',
     elecBookPoints:'',
     teachingPlanUuid:'',
     teachingPlanPoints:'',
     classPptUuid:'',
     classPptPoints:'',
     calendarVolumeUuid:'',
     calendarVolumePoints:'',
     anotherMaterialUuid:'',
     anotherMaterialPoints:'',
    })
const rules = ref(
  {
    school: [{ required: true, message: '请输入学校名！', trigger: 'blur' },],
     major: [{ required: true, message: '请输入专业名！', trigger: 'blur' },],
     subject: [{ required: true, message: '请输入学科名！', trigger: 'blur' },],
  })
function uploadFile(param) {
    const uploadForm1 = new FormData();
    uploadForm1.append('materialCover', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterialcover", uploadForm1)
        .then(response => {
          // 处理成功响应
          materialForm.materialCoverUrl = response.data.data[0]
          materialForm.materialCoverUuid = response.data.data[1]
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
}
function uploadElecBook(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          materialForm.elecBookUuid = response.data.data
        })
}
function uploadTeachingPlan(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          materialForm.teachingPlanUuid = response.data.data
        })
}
function uploadClassPpt(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          materialForm.classPptUuid = response.data.data
        })
}
function uploadCalendarVolume(param) {

  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          materialForm.calendarVolumeUuid = response.data.data
        })
}
function uploadAnotherMaterial(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          materialForm.anotherMaterialUuid = response.data.data
        })
}
function exceed(files, fileList) {
    return ElMessage.error("最多上传4张图片！");
}
function imgPreview(file, fileList) {
              let fileName = file.name;
              let regex = /(.jpg|.jpeg|.png)$/;
              if (regex.test(fileName.toLowerCase())) {
                  let picUrl = URL.createObjectURL(file.raw);
                  materialForm.files = fileList;
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
    console.log("上传", materialForm)
    // 发送上传请求
    axios.post("/material", materialForm)
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
          if (code == 0) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",

          })
        }
      }).catch(error => {
          // 处理错误
          console.error(error);
        });

}    
</script>
  
<style lang="less" scoped>
  .form_box {
      width: 50%;
      margin-left: 20%;
      background-color: #fff;
      padding: 30px;
  }
</style>
  