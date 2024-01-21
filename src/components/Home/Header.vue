<template>
  <div id="Header_content">
    <span class="Header_content_left">欢迎！
      <span style="color:red;">{{arr.username}}</span>
      <!-- <img class="Header_content_left_image" :src="arr" alt=""> -->
    
    <span style="margin-left: 200px;" v-if="arr">我的积分：￥<span style="color:red;font-size:18px;">{{arr.points}}</span></span>
    <span style="margin-left: 200px;" @click="toPersonal">{{"个人中心"}}</span>
  </span>
    <div>
    <el-button @click="userLogout" style="width: 100px" type="success" plain>退出登录</el-button>
    </div>
  </div>
</template>

<script setup>
import {ref,onMounted} from 'vue'
import {logout} from "@/net"
import router from "@/router"
import {useStore} from 'vuex';
import axios from "axios";
import {useStorage} from '@vueuse/core';
const sessionStorageDate = useStorage("user", "session")
const sessionStorageDateName = useStorage("username", "游客")
const store = useStore();
const arr = ref(store.state.personalID[0])
const email = ref(store.state.personalID[0].email)
function userLogout(){
  sessionStorageDate.value = null;
  sessionStorageDateName.value = null;
  logout(() => router.push("/"))

}
function toPersonal(){
  router.push("/personal/personalMyinfo")
}
function initData() {
    axios.get('/admin/adminInfoByEmail',{params: {email: email.value,}}).then(response => {
      store.commit('addUserPic',response.data.data.points);
      store.commit('addUserName',response.data.data.username);
  })
   
};
onMounted(() => {
  initData();
});


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