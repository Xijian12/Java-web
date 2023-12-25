<template>
  <el-card class="box-card">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="登录密码" prop="oldpassword">
        <el-input show-password v-model="form.oldpassword"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone"></el-input>
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
  name: 'UserPhoneForm',
  props: {
  },
  mounted(){
    this.form={
      id:null,
      oldpassword: '',
      phone: '',
    }
  },
  data() {
    return {
      form: {
        id:null,
        oldpassword: '',
        phone: '',
      },
      rules: {
        oldpassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              postAction('user/getPassword', this.form).then((res) => {
                if(res.data!==this.$currentUser.password){
                  return callback(new Error('密码不正确'));
                }else{
                  callback();
                }
              });
            }, trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系方式', trigger: 'blur' },
          { min: 8, max: 20, message: '长度在 8 到 20 个字符', trigger: 'blur' }
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.form.id=this.$currentUser.id;
          postAction('user/updatePhone', this.form).then((res) => {
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
