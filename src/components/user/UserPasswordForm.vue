<template>
  <el-card class="box-card">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="原始密码" prop="oldpassword">
        <el-input show-password v-model="form.oldpassword"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newpassword1">
        <el-input show-password v-model="form.newpassword1"></el-input>
      </el-form-item>
      <el-form-item label="确认新密码" prop="newpassword2">
        <el-input show-password v-model="form.newpassword2"></el-input>
      </el-form-item>
      <el-form-item class="d-f j-c-c a-i-c">
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { postAction,getAction} from '@api/manage';
export default {
  name: 'UserPasswordForm',
  props: {
  },
  mounted(){
    this.form={
      id:null,
      oldpassword: '',
      newpassword1: '',
      newpassword2: '',
    }
  },
  data() {
    return {
      form: {
        id:null,
        oldpassword: '',
        newpassword1: '',
        newpassword2: '',
        password:''
      },
      rules: {
        oldpassword: [
          { required: true, message: '请输入原始密码', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              postAction('user/getPassword', this.form).then((res) => {
                if(res.data!==this.$currentUser.password){
                  return callback(new Error('原始密码不正确'));
                }else{
                  callback();
                }
              });
            }, trigger: 'blur' }
        ],
        newpassword1: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        newpassword2: [
          { required: true, message: '请确认新密码', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value!==this.form.newpassword1){
                return callback(new Error('两次输入的密码不一致'));
              }else{
                callback();
              }
            }, trigger: 'blur' }
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.form.password=this.form.newpassword1;
          this.form.id=this.$currentUser.id;
          postAction('user/updatePassword', this.form).then((res) => {
            this.$message.success('修改成功')
          });
        } else {
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
