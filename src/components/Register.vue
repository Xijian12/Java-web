<template>
  <div class="register_container">
    <div class="register_box">
      <div class="register_title">注册账号</div>
      <el-form class="inp">
        <el-form-item>
          <el-input v-model="mobile" @blur="checkMobile" type="text" placeholder="手机号码" class="user"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="password" type="password" placeholder="填写密码" class="password"></el-input>
        </el-form-item>
        <el-form-item class="yanzheng">
          <el-input v-model="sms" type="text" placeholder="输入验证码" class="user" style="width: 254px"></el-input>
          <el-button class="yanzhengma" type="info" @click="send_sms" style="width: 150px">{{
              sms_interval_tips
            }}
          </el-button>
        </el-form-item>
        <el-form-item>
          <el-button class="register_btn" style="float: right" @click="registerHandle">注册</el-button>
        </el-form-item>
        <p class="go_login">已有帐号
          <router-link to="/login">直接登入</router-link>
        </p>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      sms: "",
      mobile: "",
      password: "",
      is_send: false,
      sms_interval_tips: "获取验证码",
    }
  },
  methods: {
    checkMobile() {
      if(this.mobile.length<1){
        return false
      }
      //  手机号码格式是否正确
      if (!/^1[3-9]\d{9}$/.test(this.mobile)) {
        this.$message({
          message: "对不起！手机换号格式有误！",
          type: 'warning'
        });
      }
      //  验证手机号码是否已经注册
      this.$http.get(`users/mobile/${this.mobile}/`).then(response => {
        let data = response.data;
        if (!data.result) {
          this.$message({
            message: "对不起！手机号码已经被注册",
            type: 'warning'
          });
          return false;
        }
        console.log(response);
      }).catch(error => {
        this.$message({
          message: error,
          type: 'warning'
        })
      })
    },
    send_sms() {
      if (!/^1[3-9]\d{9}$/.test(this.mobile)) {
        this.$message({
          message: "对不起！手机换号格式有误！",
          type: 'warning'
        });
        return false;
      }
      //  判断是否在60s内发送短信
      if (this.is_send) {
        this.$message({
          message: "对不起，不能频繁发送短信！",
          type: 'warning'
        });
        return false;
      }

      //  请求发送短信
      this.$http.get(`users/sms/${this.mobile}/`).then(response => {
        this.$message({
          message: response.data.result,
          type: 'success'
        });
        //  修改短信的发送状态
        this.is_send = true;
        //  设置间隔时间为60s
        let sms_interval_time = 60;

        //  设置短信发送间隔倒计时
        let timer = setInterval(() => {
          if (sms_interval_time <= 0) {
            clearInterval(timer)
            this.sms_interval_tips = "获取验证码";
          } else {
            sms_interval_time -= 1;
            this.sms_interval_tips = `${sms_interval_time}秒后再申请`;
          }
        }, 1000)
      }).catch(error => {
        this.$message({
          message: "发送验证码失败！",
          type: 'warning'
        })
        console.log(error);
      })
    },
    registerHandle() {
      //  注册信息提交
      if (!/^1[3-9]\d{9}$/.test(this.mobile)) {
        this.$message({
          message: "对不起！手机号码格式错误！",
          type: "warning"
        });
        return false;
      }
      if (this.sms.length < 1) {
        this.$message({
          message: "短信验证码不能为空！",
          type: "warning"
        });
        return false
      }
      if (this.password.length < 6 || this.password.length > 20) {
        this.$message({
          message: "密码长度不能超过6-20位",
          type: "warning"
        });
        return false
      }
      this.$http.post(`users/register/`, {
        sms_code: this.sms,
        mobile: this.mobile,
        password: this.password,
      }).then(response => {
        let _this = this;
        _this.$message({
          message: "注册成功！",
          type: "success",
          onClose() {
            //  保存登入状态
            sessionStorage.token = response.data.token;
            sessionStorage.user_id = response.data.id;
            sessionStorage.user_name = response.data.username;
            // 跳转到用户中心
            _this.$router.push('/login')
          }
        })
      }).catch(error => {
        this.$message({
          message: error,
          type: "warning"
        })
      })
    }
  }
}
</script>

<style scoped>
.register_container {
  height: 100%;
  background-color: antiquewhite;
}

.register_box {
  width: 450px;
  height: 350px;
  background-color: aquamarine;
  border-radius: 3px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.inp {
  margin-top: 30px;
  bottom: 0;
  width: 100%;
  box-sizing: border-box;
  padding: 0 20px;
}

.yanzheng {
  display: flex;
  flex-direction: row;
}

.user {
  flex: 3;
}

.yanzhengma {
  flex: 1;
}
</style>
