<template>
  <div class="contentcenter">
    <div class="newleft toplist">
    <div class="hd">
      <a href="/SearchDisplay/1" class="mydoc"><i class="keke_iconfont"></i>全部文档</a>
      <h2 :class="{ 'active': activeTab === 'recommend' }" @click="changeTab('recommend')">推荐文档</h2>
      <h2 :class="{ 'active': activeTab === 'latest' }" @click="changeTab('latest')">最新上传</h2>
      <span class="active-line" :style="{ transform: `translateX(${activeTab === 'recommend' ? '0' : '120'}px)`, width: '40px' }"></span>
    </div>
    <div class="bd bd1" v-show="activeTab === 'recommend'">
      <ul class="newdoclist">
        <!-- 推荐文档列表内容 -->
        <el-row>
        <el-col :span="12"> 
        <div v-if="books && books.length > 0" v-for="(book, index) in books.slice(0,6)" :key="index" class="ranking_num1" >
        <span @click="searchMaterial(book.materialId)"><el-icon><Folder /></el-icon>{{ book.school }}{{ book.major }}{{ book.subject }}</span>
        <span>{{formatDate(book.updateTime)  }}</span>
        </div>
        </el-col>
        <el-col :span="12"> 
        <div v-if="books && books.length > 0" v-for="(book, index) in books.slice(6,12)" :key="index" class="ranking_num2">
        <span @click="searchMaterial(book.materialId)"><el-icon><Folder /></el-icon>{{ book.school }}{{ book.major }}{{ book.subject }}</span>
        <span>{{formatDate(book.updateTime)  }}</span>
        </div>
        </el-col>
        </el-row>
      </ul>
    </div>
    <div class="bd bd2" v-show="activeTab === 'latest'">
      <ul class="newdoclist">
        <!-- 最新上传文档列表内容 -->
        <el-row>
        <el-col :span="12"> 
        <div v-if="books2 && books.length > 0" v-for="(book, index) in books2.slice(0,6)" :key="index" class="ranking_num1" >
        <span @click="searchMaterial(book.materialId)"><el-icon><Folder /></el-icon>{{ book.school }}{{ book.major }}{{ book.subject }}</span>
        <span>{{formatDate2(book.updateTime)  }}</span>
        </div>
        </el-col>
        <el-col :span="12"> 
        <div v-if="books2 && books.length > 0" v-for="(book, index) in books2.slice(6,12)" :key="index" class="ranking_num2">
        <span @click="searchMaterial(book.materialId)"><el-icon><Folder /></el-icon>{{ book.school }}{{ book.major }}{{ book.subject }}</span>
        <span>{{formatDate2(book.updateTime)  }}</span>
        </div>
        </el-col>
        </el-row>
      </ul>
    </div>
  </div>
  <div class="right_userlist">
    <div class="hd">
      <h2>用户排行</h2>
      <div class="ranking_num3"  v-if="Users && Users.length > 0" v-for="(User, index) in Users.slice(0,7)">
        <el-row style="margin-top: 15px;">
          <el-col :span="4"> 
            <img :src="User.avatarUrl" class="rounded-avatar" />
          </el-col> 
        <el-col :span="8">       
          <span  style="margin-left: 10px;">{{ User.username}}</span>
         </el-col>
         <el-col :span="12"> 
        <span style="text-align: right; margin-right: 20px;">¥{{ User.points}}</span>
        </el-col>
        </el-row>
        </div>
    </div>
  </div>
  </div>
</template>

<script setup>
import {ref, reactive, onMounted} from 'vue';
import axios from "axios";
import router from "@/router"
const activeTab = ref('recommend');
function changeTab(select){
  activeTab.value = select;
}
const books = ref()
// 初始化页面数据
function initData() {
  // 发起简单的 GET 请求
  axios.get("/material/highest/12")
  .then(response => {
    // 处理成功的响应
    books.value = response.data.data
    console.log('成功：', books.value);
  })
  .catch(error => {
    // 处理请求错误
    console.error('请求失败：', error);
  });    
}
const books2 = ref()
// 初始化页面数据
function initData3() {
  // 发起简单的 GET 请求
  axios.get("/material?page=1&pageSize=12")
  .then(response => {
    // 处理成功的响应
    books2.value = response.data.data.items
    console.log('成功：', books2.value);
  })
  .catch(error => {
    // 处理请求错误
    console.error('请求失败：', error);
  });    
}
const Users = ref()
function initData2() {
  // 发起简单的 GET 请求
  axios.get("/admin/showTopNAccounts")
  .then(response => {
    // 处理成功的响应
    Users.value = response.data.data
    console.log('成功：', Users.value);
  })
  .catch(error => {
    // 处理请求错误
    console.error('请求失败：', error);
  });    
}
// 跳转资料细节
function searchMaterial(id){
  router.push({name: 'MaterialDetail', params: {id: id}});
}
// 格式化日期
function formatDate(time){
      let date = new Date(time);
      let year = date.getFullYear();
      let month = date.getMonth()+1;
      let day = date.getDate();
      return year+"-"+month;
}
// 格式化日期
function formatDate2(time){
      let date = new Date(time);
      let year = date.getFullYear();
      let month = date.getMonth()+1;
      let day = date.getDate();
      return year+"-"+month+"-"+day;
}
onMounted(() => {
  initData();
  initData2();
  initData3();
});
</script>

<style scoped>
.contentcenter {
  position: relative;
  width: 1200px;
  height: 400px;
  background-color: #fff;
  left: 50%;
  transform: translate(-50%);
  display: flex;
  justify-content: space-around;
  border-radius: 12px;
  margin-top: 20px;
}
.bodybox{background:#fff;margin-top:0;padding:0}
.newleft{width:80%;float:left;box-sizing:border-box}
.newleft .hd,.right_userlist .hd{margin-bottom:20px;overflow:hidden;padding-bottom:15px;position:relative;padding-left:20px}
.newleft .hd{position:relative;padding-left:0}
.newleft .hd:after{top:42px;width:100%;height:1px;position:absolute;right:20px;background-color:#eee;content:''}
.right_userlist .hd:after{top:42px;width:100%;height:1px;position:absolute;left:0;background-color:#eee;content:''}
.newleft .hd h2{cursor:pointer;display:inline-block;margin-left:25px;margin-right:25px;font-size:16px;color:#666}
.newleft .hd h2.active,.right_userlist .hd h2{font-weight:800;font-size:20px;color:#333}
.newleft .hd h2 span,.right_userlist .hd h2 span{font-weight:400;font-size:14px;margin-left:15px;color:#999}
.newleft .hd a.mydoc{float:right;margin:5px 20px 0 0;color:#313131;font-size:13px}
.right_userlist .hd:before{border-radius:0 10px 10px 0;top:7px;width:5px;height:15px;position:absolute;left:0;background-color:#0057ff;content:''}
.active-line{position:absolute;left:40px;bottom:2px;width:5px;border-radius:0;height:4px;background:#0057ff;z-index:1;-webkit-transition:all .3s linear;transition:all .3s linear}
.newleft .bd ul li{line-height:35px;float:left;width:50%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
.newleft .bd ul li span{float:right;color:#999;margin:0 20px}
.newleft .bd ul li a{font-size:14px;color:#000;white-space:nowrap;font-weight:400;overflow:hidden;text-overflow:ellipsis}
.newleft .bd .keke_iconfont{font-size:16px;margin-right:7px}
.right_userlist{width:20%;float:right;box-sizing:border-box;padding-left:25px;position:relative}
.right_userlist:before{top:78px;width:1px;height:228px;position:absolute;left:2px;background-color:#f3f3f3;content:''}
.right_userlist .hd .titCell{float:right;margin-top:13px}
.right_userlist .hd .titCell li{font-size:0;display:inline-block;margin:0 3px;border-radius:50%;width:7px;height:7px;text-align:center;background:#ccc;cursor:pointer}
.right_userlist .hd .titCell li.on{background:red;color:#fff;width:15px;border-radius:5px}
.right_userlist .bd{height:260px;overflow:hidden}
.newdoclist .ranking_num1{flex-direction: row; flex:1; margin-right:0px; margin-top: 20px; font-size:18px;cursor: pointer; }
.newdoclist .ranking_num2{flex-direction: row; flex:1; margin-right:0px; margin-top: 20px; font-size:18px;cursor: pointer;}
.newdoclist .ranking_num3{flex-direction: row; margin-right:0px; margin-top: 25px; font-size:18px;cursor: pointer;}
.ranking_num1:hover {
  transform: translateY(-5px); /* 鼠标悬停时向上浮动5像素，你可以根据需要调整浮动的距离 */
  text-decoration: underline; 
}
.ranking_num2:hover {
  text-decoration: underline;
  transform: translateY(-5px); /* 鼠标悬停时向上浮动5像素，你可以根据需要调整浮动的距离 */
}
.ranking_num3:hover {
  text-decoration: underline;
  transform: translateY(-5px); /* 鼠标悬停时向上浮动5像素，你可以根据需要调整浮动的距离 */
}
.newdoclist li a img{width:23px;height:23px;border-radius:30px;display:inline-block;vertical-align:top;margin:5px 0px 0 0}
.newdoclist li{overflow:hidden;line-height:37px;height:37px;display: flex;}
.rounded-avatar {
  width: 25px;
  height: 25px;
  border-radius: 50%; 
  overflow: hidden; 
}
</style>