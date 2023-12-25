<template>
  <div class="login_body">
    <h4 class="login_tit" style="">图书管理系统</h4>
    <div class="template-login">
      <h3 class="login_form_tit">用户登录</h3>
      <div class="login_form" style="">
        <input type="text" name="un" placeholder="用户名..." class="login-input" id="un">
        <input type="password" name="pw" placeholder="密码..." class="login-input" id="pw">
        <div class="btn-content">
          <button class="btn btn-login btn-lg btn-save" @click="onLogin()">点击登录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { postAction} from '@api/manage';
import app from "../main.js"

export default {
  name: 'Login',
  methods:{
    onLogin:function(){
      if(jQuery('#un').val()===''){
        this.$message.error('用户名不能为空');
        return;
      }
      if(jQuery('#pw').val()===''){
        this.$message.error('密码不能为空');
        return;
      }
      postAction('user/login', {username:jQuery('#un').val(),password:jQuery('#pw').val()}).then((res) => {
        if(res.data.message==='失败'){
          this.$message.error('用户名或密码错误');
        }else{
          app.config.globalProperties.$currentUser=res.data.user;
          this.$router.push('/home')
        }
      });
    }
  }
}
</script>

<style scoped>
.login_tit{
  font-size: 3.5vw;
  letter-spacing: 8px;
  text-align: center;
}
.login_body{
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  background-image: url(../assets/images/login_bgimg.jpg);
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: relative;
  color:#fff;
  padding-top: 70px;
}
.template-login{
  min-width: 400px;
  width: 26vw;
  position: absolute;
  top: 50%;
  transform: translate(-50%,-40% );
  /* margin-left: 30%; */
  display: block !important;
  left: 68%;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px #2971e1;
  box-sizing: border-box;
  padding: 4vh 3vw;
}
.login_form_tit{
  color: #3988eb;
  font-size: 26px;
}
.login_form{
  margin: 3vh 0 0 0;
}
.login-input{
  width: 100%;
  border-radius: 5px;
  border: 1px solid #d8d8d8;
  color: #989ba0;
  font-size: 18px;
  z-index: 2;
  margin-bottom: 3vh;
  display: table-cell;
  /* height: 40px; */
  /* line-height: 40px; */
  padding: 7px 15px;
  outline: none;
}
.login-input:focus {
  -webkit-box-shadow: 0 0 10px rgb(39 83 110 / 40%));
  box-shadow: 0 0 10px rgb(39 83 110 / 40%);
}
input::-webkit-input-placeholder {
  /* WebKit browsers */
  color: #c1c6cf;
}

input:-moz-placeholder {
  /* Mozilla Firefox 4 to 18 */
  color: #c1c6cf;
}

input::-moz-placeholder {
  /* Mozilla Firefox 19+ */
  color: #c1c6cf;
}

input:-ms-input-placeholder{
  /* Internet Explorer 10+ */
  color: #c1c6cf;
}
.btn-login {
  color: #fff;
  background-color: #45a2f2;
  border-color: rgba(0,0,0,0);
  border-radius: 30px;
  padding: 10px 16px;
  font-size: 18px;
  line-height: 1.3333333;
  width: 100%;
  margin: 2vh auto 0 auto;
  cursor: pointer;
}
.btn-login:hover{
  background-color: #11c8f9;
}
</style>
