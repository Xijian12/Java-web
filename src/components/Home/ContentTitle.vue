<template>
  <div class="contentTitle">
    <div class="image">
      <img class="logo_img" src="@/assets/images/logo.jpg" alt="">
      <a class="wenzi" href="https://www.shu.edu.cn/" >
        上海大学
      </a>
    </div>
    <div class="title_search">
      <form @submit.prevent="SearchDisplayVue()">
      <i class="iconfont icon-sousuo sousuo"></i>
      <input v-model="keyword" class="title_seacrh_input"  type="search" placeholder="搜索图书名称" >
      </form>
      <div class="title_seacrh_wenzi">
        <span class="title_wenzi_state">热搜：</span>
        <a  href="/SearchDisplayMaterialSMS/上海" class="title_wenzi">电子书资料</a>
      </div>
    </div>
    <el-button @click="clickme()" style="width: 100px; height: 80px;" type="danger" plain>今日签到</el-button>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue';
import {useStore} from 'vuex';
import router from "@/router";
import axios from "axios";
const store = useStore();
const keyword = ref("");
const username = ref(store.state.personalID[0].username)
function clickme(){
  let obj= {
    username: username.value,
  }
  axios.post("/admin/signIn", obj)
            .then((resp) => {
              if (resp.data.code === 200) {
                ElMessage({
                  showClose: true, type: 'success',
                  message: resp.data.data
                });
              } else {
                ElMessage({
                  showClose: true, type: 'error',
                  message: resp.data.message
                });
              }
            });
  axios.get('/user/userInfo',{params: {username: username.value,}}).then(response => {
    // 处理成功的响应
    store.commit('addUserPic',response.data.data.points);
    setTimeout(function()  {
 
    window.location.reload()

}, 1500);
   
  })   
    }
function SearchDisplayVue(){
      const path = keyword.value
      router.push({name:"SearchDisplayBookName", params: {id: path}})
} 
</script>

<style scoped>
  .contentTitle{
    display: flex;
    flex-flow: wrap;
    justify-content: space-evenly;
  }
  .image{
    height: 70px;
    /* width: 190px; */
    margin-top: 30px;
    display: flex;
    flex-direction: row;
    flex: 2;
    justify-content: flex-end;
  }
  .image > .logo_img{
    border-radius: 25px;
    height: 100%;
    width: 100px;
    padding-right: 5px;
  }
  .image > .wenzi {
    padding-left: 10px;
    border-left: 2px solid rgb(80, 43, 243);
    height: 100%;
    width: 70px;
    font-size: 25px;
    margin-left: 10px;
    margin-top: -5px;
    color: rgb(80, 43, 243);
    text-decoration: none;
  }
  .title_search{
    margin-top: 20px;
    flex: 4;
    margin-left: 40px;
  }
  .title_seacrh_input{
    height: 50px;
    width: 630px;
    border-radius: 25px;
    border: 0px;
    outline:none;
    padding-left: 35px;
    /* flex: 6; */
  }
  input::-webkit-search-cancel-button{
    display: none;
  }
  input[type=search]::-ms-clear{
    display: none;
  }
  .iconfont{
    position: relative;
    /* color: #fff; */
    /* font-size: 15px; */
    left: 30px;
  }
  .title_seacrh_wenzi{
    margin-top: 10px;
  }
  .title_wenzi{
    font-size: small;
    color:	#86cf61;
    cursor: pointer;
    margin-left: 20px;
  }
  .title_wenzi_state{
    font-size: small;
    color:	#000;
    margin-left: 40px;
  }
  .title_wenzi:hover{
    color: orangered;
  }
  .search_content{
    width: 630px;
    /* height: 100%; */
    position: absolute;
    background-color: #fff;
    border-top-left-radius: 25px;
    border-top-right-radius: 25px;
    max-height: 500px;
    overflow: auto;
    z-index: 999;
    padding-top: 20px;
    margin-left: 17px;
  }
  .search_content > ul{
    height: 50px;
    width: 100%;
    /* border: 1px solid #000; */
    line-height: 50px;
  }
  .search_content > ul:hover{
    background-color: #f0f0f0;
    cursor: pointer;
  }
  .search_content > ul > li{
    margin-left: 20px;
  }
</style>