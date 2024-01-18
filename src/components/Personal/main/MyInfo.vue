<template>
    <div>
        <div class="main_box">
            <div class="image">
                <img class="logo_img" :src="userInfo.avatarUrl" >
            </div>      
            <el-descriptions class="margin-top" title="我的信息" :column="1" size="medium" border>
                <template #extra>
                    <el-button type="primary" @click="editDialogVisible=true">修改信息</el-button>
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
            <el-dialog title="修改信息" v-model="editDialogVisible" width="500px">
                <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
                    <el-form-item label="用户名" prop="userName">
                        <el-input v-model="editForm.userName" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="userPhone">
                        <el-input v-model="editForm.userPhone"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="userEmail">
                        <el-input v-model="editForm.userEmail"></el-input>
                    </el-form-item>
                    <el-form-item label="收货地址" prop="userAddress">
                        <el-input v-model="editForm.userAddress" type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4 }"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog_footer">
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="editUserInfo">保存</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
  
    <!-- 弹窗表单区域 -->
</template>
    
<script setup>  
import {ref, onMounted} from 'vue'
import axios from "axios";
import {useStore} from 'vuex';
const store = useStore();
const username = ref(store.state.personalID[0].username)
const userInfo = ref([]);
const editDialogVisible = ref(false);
const editForm = ref({
                userName: "",
                userEmail: "",
                userPhone: "",
                userAddress: "",
            });
const editFormRules = ref({
                userEmail: [
                    { required: true, message: "请输入正确的邮箱！", trigger: 'blur', pattern: /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/ }
                ],
                userPhone: [
                    { required: true, message: "请输入正确的手机号！", trigger: 'blur', pattern: /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/ }
                ],
                userAddress: [
                    { required: true, message: "请输入正确的地址！", trigger: 'blur', pattern: /^[\u0391-\uFFE5A-Za-z]+$/ }
                ]
            }
  );

function initData() {
    console.log("cacac",username.value)
    axios.get('/user/userInfo',{params: {username: username.value,}}).then(response => {
    // 处理成功的响应
    userInfo.value = response.data.data; 
    console.log('成功：', userInfo.value);
  })
   
};

  
        //保存修改后的表单信息
 function editUserInfo() {
            this.$refs.editFormRef.validate(async valid => {
                if (!valid) return;
                //发起修改请求
              const { data: res } = await this.$http.put("updateuser", this.editForm);
                if (res.errcode != "0") {
                    return this.$message.error("修改失败！");
                } else {
                    this.$message.success("修改成功！");
                    this.editDialogVisible = false;
                    this.userInfo = res.result;
                }
            })
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