<template>
  <div id="Header_content">
    <span class="Header_content_left">欢迎！
      <span style="color:red;">{{arr.username}}</span>
      <!-- <img class="Header_content_left_image" :src="arr" alt=""> -->
    </span>
    <!-- <span v-if="arr">我的积分：￥<span style="color:red;font-size:20px;">{{arr.points}}</span></span> -->
    <span class="Header_content_right" @click="toPersonal">个人中心</span>
    <div>
    <el-button @click="userLogout" style="width: 100px" type="success" plain>退出登录</el-button>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {logout} from "@/net"
import router from "@/router"
import {useStore} from 'vuex';

const store = useStore();
const arr = ref(store.state.personalID[0])
function userLogout(){
  logout(() => router.push("/"))
}
function toPersonal(){
  router.push("/personal/personalMyinfo")
}

</script>

<style scoped>
  #Header_content{
    display: flex;
    height: 50px;
    background-color:  #f7fbfd;
    justify-content: space-around;
    line-height: 50px;
  }
  #Header_content > span:nth-child(1),
  #Header_content > span:nth-child(3)
  {
    cursor: pointer;
  }
  .Header_content_left_image{
    position: relative;
    width: 30px;
    height: 30px;
    border-radius: 30px;
    border: 1px solid #000;
    margin-left: 10px;
  }
</style>