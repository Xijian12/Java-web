<template>
  <Header></Header>
  <div id="wrap">
      <el-page-header @back="goBack" content="搜索页面" title="返回首页"></el-page-header>
  </div>
  <div class="search_bar">
    <form @submit.prevent="SearchDisplayVue()">
        <input v-model="searchData" type="text" name="keyword" class="tx" autocomplete="off" placeholder="请输入您要搜索资料的学校...">
        <el-button type="submit" @click="SearchDisplayVue()">搜索</el-button>
    </form>
  </div>
  <div id="Select" style="display: flex; align-items: center;">
    <span style="font-size:15px">学校分类</span>
    <el-collapse style="margin-left: 60px;" v-model="collapseVisible">
      <el-collapse-item title="展开更多">
        <el-checkbox-group v-model="selectedOptionsRef" :min="0" :max="1">
          <el-checkbox v-for="option in selectedOptions" :label="option" :key="option" @change="searchMajor()">
            {{ option}}
          </el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
  </div>
  <div id="Select" v-if = "majorShow" style="display: flex; align-items: center;">
    <span style="font-size:15px">院系分类</span>
    <el-collapse style="margin-left: 60px;">
      <el-collapse-item title="展开更多">
        <el-checkbox-group v-model="selectedmajorOptionsRef" :min="0" :max="1" >
          <el-checkbox v-for="option in selectedmajorOptions" :label="option" :key="option" @change="searchSubject()">
            {{ option}}
          </el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
  </div>
  <div id="Select" v-if = "subjectShow" style="display: flex; align-items: center;">
    <span style="font-size:15px">专业分类</span>
    <el-collapse style="margin-left: 60px;">
      <el-collapse-item title="展开更多">
        <el-checkbox-group v-model="selectedsubjectOptionsRef" :min="0" :max="1" >
          <el-checkbox v-for="option in selectedsubjectOptions" :label="option" :key="option" @change="searchMaterial()">
            {{ option}}
          </el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
  </div>
  <div  id="wrap">
    <div style="margin-top: 10px">
      <el-row :gutter="24">
        <el-col :span="6" v-for="(book, index) in materials" :key="index">
          <div id="card-container" @click="MaterialDetail(book.materialId)" style="cursor: pointer">
            <el-card :body-style="{ padding: '0px'}" shadow="hover">
              <div id="img">
                <img :src="book.materialCoverUrl">
              </div>
              <div id="content">
                <div id="bookName">
                  <span class="title">学科: </span><br>
                  <div class="content" style="height: 45px;">{{ book.subject }}</div>
                </div>
                <div id="author">
                  <span class="title">上传者: </span><br>
                  <span class="content" style="height: 20px;">{{ book.materialUploader }}</span>
                </div>
                <div>
                  <el-popover
                      placement="right"
                      :title="book.bookName"
                      width="200"
                      trigger="hover"
                      :close-delay="100"
                      :content="book.bookProfile">
                      <template #reference><el-button slot="reference" type="text" >简介</el-button></template>  
                  </el-popover>
                </div>
                <div id="price">
                  <span class="discountPrice"><span style="font-size: 15px">¥ </span>{{decimals(book.anotherMaterialPoints+book.teachingPlanPoints+book.elecBookPoints+book.calendarVolumePoints+book.classPptPoints)}}</span><br>
                </div>
              </div>
              <div id="bottom-btn" >
                <el-button @click.stop="MaterialDetail(book.materialId)" size="medium" type="primary" plain>查看详情</el-button>
                <el-button-group style="margin-left: 10px">
                  <el-button @click.stop="addToCart(index)" size="mini" :icon="ShoppingCart"></el-button>
                  <el-button @click.stop="addToCollection(index)" size="mini" :icon="Star"></el-button>
                  <el-button @click.stop="" size="mini" :icon="More"></el-button>
                </el-button-group>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>

    <!--数据为空时显示-->
    <div id="pageBtn">
      <el-pagination
          background
          layout="prev, pager, next"
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :total="total"
          @current-change="pagechange">
      </el-pagination>
    </div>
  </div>
</template>

<script setup>
import {More, Star, ShoppingCart} from '@element-plus/icons-vue';
import Header from "@/components/Home/Header.vue";
import { ref, onMounted, watch } from 'vue';
import axios from "axios";
import { useRoute, useRouter } from 'vue-router';
// 控制图书和资料
const route = useRoute();
const router = useRouter();
const total = ref(1);
    // 翻页功能
function pagechange(val) {
  pageNum.value = val;
  initDataCategory(route.params.id);
}
    // 保留一位小数
function decimals(value) {
      return value.toFixed(1);
}
let pageNum = ref(1);
let pageSize = ref(12);
// 页面初始化时执行，查询同类
 // 返回首页
function goBack() {
  router.push("/home");
}
 
// 图书资料查询
const collapseVisible = ref(true)
const selectedOptionsRef = ref([])
const selectedOptions = ref("")
function searchSchool(){
  axios.get("/material/school").then((resp) => {
        selectedOptions.value = resp.data.data;      
      });
}
const selectedmajorOptionsRef = ref([])
const selectedmajorOptions = ref("")
const majorShow = ref(false)
function searchMajor(){
  selectedmajorOptionsRef.value = []
  axios.get("/material/school/major", {params:{school: selectedOptionsRef.value[0]}}).then((resp) => {
        selectedmajorOptions.value = resp.data.data;
        majorShow.value = true;
      });
}
const selectedsubjectOptionsRef = ref([])
const selectedsubjectOptions = ref("")
const subjectShow = ref(false)
function searchSubject(){
  selectedsubjectOptionsRef.value = []
  axios.get("/material/school/major/subject", {
    params:{
      school: selectedOptionsRef.value[0],
      major: selectedmajorOptions.value[0],
    }}).then((resp) => {
        selectedsubjectOptions.value = resp.data.data;
        subjectShow.value = true;
      });
}
const materials = ref()
function searchMaterial(){
  axios.get("/material", {
    params:{
      school: selectedOptionsRef.value[0],
      major: selectedmajorOptions.value[0],
      subject: selectedsubjectOptionsRef.value[0]
    }}).then((resp) => {
      materials.value = resp.data.data.items
      total.value = resp.data.data.total
      });
}
function initDataCategory(category){
  axios.get("/material/SMS", {
    params:{
      schMajorSub: category,
      page: pageNum.value,
      pageSize: pageSize.value,
    }}).then((resp) => {
      materials.value = resp.data.data.items
      total.value = resp.data.data.total
      });
}
// 导航栏跳转功能
function MaterialDetail(id){
  router.push({name: 'MaterialDetail', params: {id: id}});
}
// 搜索
const searchData = ref("")
function SearchDisplayVue(){
      selectedOptionsRef.value = []
      selectedmajorOptionsRef.value = []
      selectedsubjectOptionsRef.value = []
      const path = searchData.value
      router.push({name:"SearchDisplayMaterialSMS", params: {id: path}})
}
onMounted(async () => {
  const category = route.params.id;
  router.afterEach((to, from, next) => {
        window.scrollTo(0, 0)
    })
  searchSchool();
  await initDataCategory(category);
});
// 使用 watch 监听路由变化
watch(() => route.params.id,
      (to, from) => {
        console.log(`Route changed from ${from.path} to ${to.path}`);
        window.location.reload()
      }
);
</script>

<style scoped>
[v-cloak] {
  display: none !important;
}
.search_bar{width:700px;height:40px;position:relative;z-index:10;padding-right:60px;border:2px solid #3a3a3a;border-radius:5px;margin:0 auto;margin-top:30px;background:#fff}
.search_bar input{outline:0;border:none;background:0 0;resize:none;border-radius:0;-webkit-appearance:none;-moz-appearance:none;appearance:none;display:block;width:90%;height:40px;padding:0 10px 0 20px;font-size:14px}
.search_bar button{position:absolute;text-align:center;top:-1px;right:0;width:90px;height:42px;border-radius:0 5px 5px 0;cursor:pointer;outline:0;border:none;font-size:16px;resize:none;-webkit-appearance:none;-moz-appearance:none;appearance:none;color:#fff;background:linear-gradient(to bottom,#3b9cec 0,#ec3b3b 100%)}
.search_bar .keke_iconfont{font-size:18px;margin-right:7px}
#Select{
  margin-top: 20px;
  height: 140px;
  margin-left: 270px;
}
#NavigationBarContainer {
  margin-top: 20px;
  margin-left: 250px;
  margin-right: 250px;
}
#wrap {
  margin: 50px auto;
  width: 1200px;
}

#pageBtn {
  margin-right:540px;
}

#card-container {
  margin-top: 25px;
}

#card-container #img {
  float: left;
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

#card-container #img img {
  width: 150px;
  height: 220px;
}

#card-container #content {
  float: left;
  margin-top: 25px;
  margin-left: 15px;
  width: 105px;
  height: 190px;
}

#card-container #content .title {
  color: #a9a6a6;
  font-size: 14px;
}

#card-container #content .content {
  font-size: 16px;
  width: 95px;
  display: -webkit-box;
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

#card-container #content #price .discountPrice {
  color: #ff1f1f;
  font-size: 20px;
}

#card-container #content #price .price {
  text-decoration: line-through;
  color: rgba(151, 151, 151, 0.9);
  font-size: 14px;
}

#card-container #bottom-btn{
  float: none;
  margin: 0 0 10px 10px;
  width: 245px;
  height: 290px;
}
</style>