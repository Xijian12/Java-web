<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/img/jizz414vlztmn.jpg" alt="" />
      </div>
      <!-- 登入表单区 -->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
      >
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            prefix-icon="el-icon-s-custom"
            placeholder="请输入手机号或者账号"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="el-icon-search"
            type="password"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <span class="span" @click="zhuce">没有账号，注册一个吧</span>
          <el-button class="button" type="primary" @click="login" 
            >登入按钮</el-button
          >
          <el-button class="button" type="info" @click="resetLoginForm">重置按钮</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 这是登入表单的数据绑定对象
      loginForm: {
        username: "",
        password: "",
      },
      // 这是表单的验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [
          {
            required: true,
            message: "请输入登入名称",
            trigger: "blur",
          },
          {
            min: 6,
            max: 15,
            message: "长度在3到15个字符",
            trigger: "blur",
          },
        ],
        // 验证密码是否合法
        password: [
          {
            require: true,
            message: "请输入登入密码",
            trigger: "blur",
          },
          {
            min: 6,
            max: 15,
            message: "长度在6到15个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    // 点击重置按钮，重置登入表单
    resetLoginForm() {
      // console.log(this)
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return;
        const { data: res } = await this.$http.post("users/login/", this.loginForm);
        if(res){
          this.$message.success("登入成功");
        }else{
          this.$message.error("登入失败");
        }
        // 1. 将登入成功之后的 token,保存到客户端的 sessionStorage 中
        // 1.1 项目中除了登入之外的其他API接口，必须在登入之后才能访问
        // 1.2 token 只应当在当前网站打开期间生效，所以将token保存在sessionStorage 中
        res.img = "https://i03piccdn.sogoucdn.com/1f1dac102a365433"
        this.$store.dispatch('addID',res)
        window.sessionStorage.setItem("token", res.token);
        this.$store.commit("addCookie",res.token);
        // 2. 通过编程式导航跳转到后台主页，路由地址是 /home
        this.$router.push("/home");
      });
    },
    zhuce(){
      this.$router.push({name:"register"})
    }
  },
};
</script>

<style scoped>
.login_container {
  height: 906px;
  width: 100%;
  background-color: skyblue;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: aquamarine;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.avatar_box {
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
}
.avatar_box > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}

.login_form {
  margin-top: 100px;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.span{
  font-size: 12px;
  color: #000;
  cursor: pointer;
}
.span:hover{
  color: red;
}

.button{
  margin-left: 60px;
  margin-top: 20px;
  /* position: relative; */
  /* margin-top: -20px; */
}
</style>
