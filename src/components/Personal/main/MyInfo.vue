<template>
    <div>
        <div class="main_box">
            <div class="image">
                <img class="logo_img" :src="userInfo.avatarUrl" >
            </div>      
            <el-descriptions class="margin-top" title="我的信息" :column="1" size="medium" border>
                <template #extra>
                    <el-button type="primary" @click="editFromButton(editUserFormRef)">修改信息</el-button>
                </template>
                <el-descriptions-item>
                    <template #label>
                      <el-icon><User /></el-icon>
                        用户名
                    </template>
                    <p>{{ userInfo.username }}</p>
                </el-descriptions-item>
                <el-descriptions-item>
                    <template #label>
                      <el-icon><Message /></el-icon>
                        邮箱
                    </template>
                    <span>{{ userInfo.email }}</span>
                </el-descriptions-item>
                <el-descriptions-item>
                    <template #label>
                      <el-icon><Briefcase /></el-icon>
                        积分
                    </template>
                    <span>{{ userInfo.points }}</span>
                </el-descriptions-item>
            </el-descriptions>

        </div>
  
        <!-- 弹窗表单区域 -->
        <div>
            <el-dialog
          v-model="editUserFormVisible"
          title="编辑用户"
          class="edit-user-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
          <el-form
            :model="editUserForm"
            ref="editUserFormRef"
            class="edit-user-form"
          >
            <el-form-item
              label="用户名"
              :label-width="formLabelWidth"
              prop="username"
            >
              <el-input
                v-model="editUserForm.username"
                autocomplete="off"
                disabled
              ></el-input>
            </el-form-item>
            <el-form-item
              label="新用户名"
              :label-width="formLabelWidth"
              prop="newUserName"
            >
              <el-input
                v-model="editUserForm.newUserName"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="密码"
              :label-width="formLabelWidth"
              prop="password"
            >
              <el-input
                v-model="editUserForm.newPassword"
                type="password"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item style="margin-left: 70px;" label="头像上传" prop="pictures">
            <el-upload class="box_upload"  :auto-upload="false" action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
            accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
            :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
            <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="editUserFormVisible = false">取消</el-button>
              <el-button
                type="primary"
                @click="editUserButton(editUserFormRef)"
              >
                编辑
              </el-button>
            </span>
          </template>
        </el-dialog>
        </div>
    </div>
  
    <!-- 弹窗表单区域 -->
</template>
    
<script lang="ts" setup>  
import {ref,reactive, onMounted} from 'vue'
import axios from "axios";
import {useStore} from 'vuex';
import type { FormInstance } from "element-plus";
import { ElMessageBox, ElMessage } from "element-plus";
import router from "@/router"
const store = useStore();
const username = ref(store.state.personalID[0].username)
const email = ref(store.state.personalID[0].email)
const userInfo = ref([]);
let formLabelWidth = 120;
function initData() {
  console.log(email.value)
      axios.get('/admin/adminInfoByEmail',{params: {email: email.value,}}).then(response => {
    // 处理成功的响应
    userInfo.value = response.data.data; 
    console.log('成功：', userInfo.value);
  })
};
// 编辑用户对话框
let editUserFormVisible = ref(false);
const editFromButton = (formEl: FormInstance) => {
  editUserFormVisible.value = true;
  editUserForm = reactive(JSON.parse(JSON.stringify(userInfo.value)));
  if (!formEl) return;
  formEl.resetFields();
};

// 编辑用户表单
const editUserFormRef = ref<FormInstance>();
let editUserForm = reactive({
  username: "",
  newUserName: "",
  newPassword: "",
  points: "",
});
const uploadRef = ref()
// 编辑用户按钮
const editUserButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      let obj = {
        oldUserName: editUserForm.username,
        newUserName: editUserForm.newUserName,
        newPassword: editUserForm.newPassword,
      }
      uploadRef.value.submit();
      axios.post("/admin/updateUserInfo", obj).then((resp) => {
          const code = resp.data.code;
          // 编辑失败
          if (code == 400) {
            ElMessageBox.alert("编辑用户失败，请重试", "信息", {
              confirmButtonText: "确认",
            });
          }
          // 编辑成功
          if (code == 200) {
            ElMessageBox.alert("编辑成功", "信息", {
              confirmButtonText: "确认",
              callback: () => {
                editUserFormVisible.value = false;
              },
            });
            initData();
            initData2(); 
          }
        })
      }
    })
};
function initData2() {
    axios.get('/admin/adminInfoByEmail',{params: {email: email.value,}}).then(response => {
      store.commit('addUserPic',response.data.data.points);
      store.commit('addUserName',response.data.data.username);
  })
   
};
const touxiang =reactive<any>({
    bookCoverUrl:"",
    bookFileUuid:"",
});
function uploadFile(param) {
    const uploadForm1 = new FormData();
    uploadForm1.append('avatar', param.file );
    uploadForm1.append('username', username.value );   
    // 发送上传请求
    axios.post("/upload/updateAvatar", uploadForm1)
        .then(response => {
          // 处理成功响应
          touxiang.bookCoverUrl = response.data.data[0]
          touxiang.bookFileUuid = response.data.data[1]
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
                  touxiang.files = fileList;
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
onMounted(() => {
  initData();
});
</script>
    
<style lang="less" scoped>
.main_box {
    margin-left: 0px;
    width: 400px;
}
.image{
    height: 200px;
    width: 190px; 
    margin-top: 30px;
    margin-left: 30px;
    flex-direction: row;
    flex: 2;
    justify-content: flex-end;
}
.image > .logo_img{
    border-radius: 10px;
    height: 100%;
    width: 100%;
}
.margin-top{
    margin-top:20px;
}
</style>