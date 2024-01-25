<template>
  <div class="contentCenter">
    <div class="contentCenter_Left">
      <div class="contentCenter_Left_menu">
          <div class="contentCenter_Left_menu_title1" @mouseenter="yiru" @mouseleave="yichu"> 
            <div class="left_item">
              <div class="navTitleBox">
        <img src="@/assets/images/category-area.png" alt="">
        <div class="navTitle">按分类看</div>
        <div class="navDescribe">分门别类，找你所想</div>
        <i class="keke_iconfont"></i>
              </div>
            </div>
          </div>
          <div class="contentCenter_Left_menu_title2" @mouseenter="yiru2" @mouseleave="yichu2">
            <div class="left_item">
              <div class="navTitleBox">
        <img src="@/assets/images/category-specialty.png" alt="">
      <div class="navTitle">图书搜索</div>
      <div class="navDescribe">精确搜索，直达所需</div>
      <i class="keke_iconfont"></i>
              </div>
            </div>
          </div>
          <div class="contentCenter_Left_menu_title3" @mouseenter="yiru3" @mouseleave="yichu3">
            <div class="left_item">
                <div class="navTitleBox">
                <img src="@/assets/images/category-ask.png" alt="">
                <div class="navTitle">资料搜索</div>
                <div class="navDescribe">更多资料在这里</div>
                <i class="keke_iconfont"></i>
                </div>
            </div>
          </div>
        </div>
      <div class="contentCenter_Left_item">
        <div v-show="guanbi1" class="fugai" @mouseenter="yiru" @mouseleave="yichu">
          <div class="fugai_left">
            <div class="popup">
              <div class="cate_list_box">
                <li>
                  <div class="firstcatename">
                    <a href="/SearchDisplay/经济管理" title="经济管理"   >经济管理</a>
                  </div>
                  <span class="catelist">
                  <a href="/SearchDisplay/管理学" title=""   >管理学</a>
                  <a href="/SearchDisplay/金融投资" title=""   >金融投资</a>
                  <a href="/SearchDisplay/市场营销" title=""   >市场营销</a>
                  <a href="/SearchDisplay/保险" title=""   >保险</a>
                  <a href="/SearchDisplay性格情绪" title=""   >性格情绪</a>
                  <a href="/SearchDisplay/财会专业" title=""   >财会专业</a></span>
                </li>
        <li>
          <div class="firstcatename">
            <a href="pdf-class-2-0-0.html" title="成功励志"   >成功励志</a>
          </div>
          <span class="catelist ">
            <a href="/SearchDisplay/成功学" title=""   >成功学</a>
            <a href="/SearchDisplay/职场" title=""   >职场</a>
            <a href="/SearchDisplay/演讲口才" title=""   >演讲口才</a>
            <a href="/SearchDisplay/人际交往" title=""   >人际交往</a>
            <a href="/SearchDisplay/个人修养" title=""   >个人修养</a> 
            <a href="/SearchDisplay/励志书籍" title=""   >励志书籍</a></span>
        </li>
        <li>
          <div class="firstcatename">
            <a href="/SearchDisplay人物传记" title="人物传记"   >人物传记</a>
          </div>
          <span class="catelist ">
            <a href="/SearchDisplay/人物传记" title=""   >人物传记</a>
            <a href="/SearchDisplay/世界各国史" title=""   >世界各国史</a>
            <a href="/SearchDisplay/世界各国史" title=""   >中国文人史</a>
            <a href="/SearchDisplay/中国史" title=""   >中国史</a></span>
        </li>
        <li>
          <div class="firstcatename">
            <a href="/SearchDisplay/计算机书籍" title="计算机书籍"   >计算机书籍</a>
          </div>
          <span class="catelist ">
            <a href="/SearchDisplay/操作系统" title=""   >操作系统</a>
            <a href="/SearchDisplay/数据库<" title=""   >数据库</a>
            <a href="/SearchDisplay/信息安全" title=""   >信息安全</a>
            <a href="/SearchDisplay/程序设计" title=""   >程序设计</a>
            <a href="/SearchDisplay/软件开发" title=""   >软件开发</a>
            <a href="/SearchDisplay/人工智能" title=""   >人工智能</a>
            <a href="/SearchDisplay/面设计" title=""   >平面设计</a>
            <a href="/SearchDisplay/网络通信" title=""   >网络通信</a>
            <a href="/SearchDisplay/家庭办公" title=""   >家庭办公</a>
            <a href="/SearchDisplay/计算机语言" title=""   >计算机语言</a></span>
        </li>
        <li>
          <div class="firstcatename">
            <a href="/SearchDisplay/人文社科类<" title="人文社科类"   >人文社科类</a>
          </div>
          <span class="catelist keke_doc_clearfix">
            <a href="/SearchDisplay/社会科学" title=""   >社会科学</a>
            <a href="/SearchDisplay/心理学" title=""   >心理学</a>
            <a href="/SearchDisplay/宗教" title=""   >宗教</a>
            <a href="/SearchDisplay/法律" title=""   >法律</a>
            <a href="/SearchDisplay/政治军事" title=""   >政治军事</a>
            <a href="/SearchDisplay/文化" title=""   >文化</a>
            <a href="/SearchDisplay/教育考试" title=""   >教育考试</a>
            <a href="/SearchDisplay/古籍<" title=""   >古籍</a></span>
        </li>
        <div class="openmore">
          <a href="/SearchDisplay/1"   >查看全部文档</a>
        </div>
              </div>
            </div>
          </div>
        </div>
        <div v-show="guanbi2" class="fugai" @mouseenter="yiru2" @mouseleave="yichu2">
          <div class="fugai_left">
            <div class="popup">
        <h1 class="searchtitle">图书搜索</h1>
          <div class="search_bar">
            <form @submit.prevent="SearchDisplayVue()">
              <input v-model="searchData" type="text" name="keyword" class="tx" autocomplete="off" placeholder="请输入您要搜索的图书类别...">
              <el-button type="submit" @click="SearchDisplayVue()">搜索</el-button>
            </form>
          </div>
          <div class="hotkeyword">
          热门搜索：

          <el-button
            v-for="button in buttons"
            :key="button.categoryName"
            text
            bg
            class="custom-button"
            @click="searchBook(button.categoryName)"
            >{{ button.categoryName }}</el-button>
            <div style="margin-top: 20px;margin-left: 85px;">
              <el-button
            v-for="button in buttons2"
            :key="button.categoryName"
            text
            bg
            class="custom-button"
            @click="searchBook(button.categoryName)"
            >{{ button.categoryName }}</el-button>
            </div>
        </div>
            </div>
          </div>
        </div>   
        <div v-show="guanbi3" class="fugai" @mouseenter="yiru3" @mouseleave="yichu3">
          <div class="fugai_left">
            <div class="popup">
        <h1 class="searchtitle">资料搜索</h1>
          <div class="search_bar">
            <form @submit.prevent="SearchDisplayMaterialVue()">
              <input v-model="searchDataMaterial" type="text" name="keyword" class="tx" autocomplete="off" placeholder="请输入您要搜索资料的相关信息...">
              <el-button type="submit" @click="SearchDisplayMaterialVue()">搜索</el-button>
            </form>
          </div>
          <div class="hotkeyword" >
          热门搜索：

          <el-button
            v-if="buttons3 && buttons3.length > 0" 
            v-for="(button, index) in buttons3.slice(0,3)" 
            :key="index"
            text
            bg
            class="custom-button2"
            @click="searchMaterial(button)"
            >{{ button }}</el-button>
            <div style="margin-top: 20px;margin-left: 85px;">
          <el-button
            v-if="buttons3 && buttons3.length > 0"
            v-for="(button, index) in buttons3.slice(3,6)" 
            :key="index"
            text
            bg
            class="custom-button2"
            @click="searchMaterial(button)"
            >{{ button }}</el-button>
          </div>
        </div>
            </div>
          </div>
        </div>  
        <div v-show="!guanbi" class="contentCenter_Left_item_content">
            <div class="huandengpian">
              <el-carousel trigger="click" class="el-carousel" indicator-position="none">
                <el-carousel-item v-for="item in carouselData" :key="item.id">
                  <img v-viewer :src="item.imageUrl" alt="图片丢失了" class="carousel-image"> 
                </el-carousel-item>
              </el-carousel>
            </div>
        </div>
        </div>
    </div>
    <div class="contentCenter_Right" >
          <div class="hd_right_box">
            <div class="hd_right">
                <div class="right_top">
                    <h2>文库累计收录文档</h2>
                    <div class="docnumboxs"><span class="docnum" id="docnum">1145296</span> 份</div>
                </div>
                <a href="javascript:" @click="tipNoPer"  class="updocbtn"><el-icon><UploadFilled /></el-icon>上传文档</a>
                <ul class="advantage">
                    <li>
                        <img src="@/assets/images/s-fast.png" alt="快审"><span>快审</span>
                    </li>
                    <li>
                        <img src="@/assets/images/s-profession.png" alt="高佣">高佣
                    </li>
                    <li>
                        <img src="@/assets/images/s-privacy.png" alt="开放">开放
                    </li>
                </ul>
            </div>
        </div>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import router from "@/router"
import axios from "axios";
import {useStore} from 'vuex';
const userInfo = ref([]);
const store = useStore();
const username = ref(store.state.personalID[0].username)
const guanbi = ref(false)
const guanbi1 = ref(false)
const guanbi2 = ref(false)
const guanbi3 = ref(false)
const carouselData = ref([
  { id: 1, imageUrl: 'src/assets/images/dragon.png', number: 1 },
  { id: 2, imageUrl: 'src/assets/images/background.jpeg', number: 2 },
  { id: 3, imageUrl: 'src/assets/images/jinxuan.jpg', number: 3 },
]);
const buttons = ref();
const buttons2 = ref();
const buttons3 = ref();

// 搜索图书类别
function searchBookCategory() {
  axios.get("/category",{
      params:{
      page: 1,
      pageSize: 5}
    }).then((resp) => {
      buttons.value = resp.data;
    });
  axios.get("/category",{
      params:{
      page: 2,
      pageSize: 5}
    }).then((resp) => {
      buttons2.value = resp.data;
    });
  axios.get("/material/school").then((resp) => {
    buttons3.value = resp.data.data;      
    });
};
searchBookCategory();
const yiru = () =>  {
      guanbi.value = !guanbi.value
      guanbi1.value = !guanbi1.value
    }
const  yichu = () =>  {
   guanbi.value = !guanbi.value
   guanbi1.value = !guanbi1.value
    }
const yiru2 = () =>  {
      guanbi.value = !guanbi.value
      guanbi2.value = !guanbi2.value
    }
const  yichu2 = () =>  {
   guanbi.value = !guanbi.value
   guanbi2.value = !guanbi2.value
    }
const yiru3 = () =>  {
      guanbi.value = !guanbi.value
      guanbi3.value = !guanbi3.value
    }
const  yichu3 = () =>  {
   guanbi.value = !guanbi.value
   guanbi3.value = !guanbi3.value
    }
const searchData = ref("")
function SearchDisplayVue(){
      const path = searchData.value
      router.push({name:"SearchDisplay", params: {id: path}})
}
const searchDataMaterial = ref()
function SearchDisplayMaterialVue(){
      const path = searchDataMaterial.value
      router.push({name:"SearchDisplayMaterialSMS", params: {id: path}})
}
// 上传文档跳转
function tipNoPer(){
  router.push({name:"publish"})
}
function searchBook(path){
  router.push({name:"SearchDisplay", params: {id: path}})
}
function searchMaterial(path){
  router.push({name:"SearchDisplayMaterialSMS", params: {id: path}})
}
function initData() {
    axios.get('/user/userInfo',{params: {username: username.value,}}).then(response => {
    // 处理成功的响应
    userInfo.value = response.data.data; 
  })
   
};
onMounted(() => {
  initData();
});
</script>

<style scoped>
.fugai {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  background-color: #fff;
  border-radius: 12px;
}
.fugai > .fugai_left{
  flex: 4;
  padding: 20px;
  margin-right: 1px;
  display: flex;
  flex-direction: column;
  align-content: space-between;
  overflow: auto;
  border-right: 2px solid #d3dce6;
}
.contentCenter {
  position: relative;
  width: 1200px;
  height: 300px;
  /* background-color: orangered; */
  left: 50%;
  transform: translate(-50%);
  margin-top: 20px;
  display: flex;
  flex-direction: row;
}
.contentCenter_Left {
  flex: 3;
  height: 100%;
  margin: 0 20px 0 0;
  /* background-color: palegreen; */
  display: flex;
  flex-direction: row;
}
.contentCenter_Left_menu {
  flex: 1.5;
  max-height: 100%;
  background-color: #f4f5f9;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
}
.contentCenter_Left_menu_title1 {
  height: 100px;
  color: #746868;
  font-size: 20px;
  text-align: center;
  background-color: #fff;
  line-height: 20px;
  /* color: orangered; */
  font-weight: 700;
  border-top-left-radius: 20px;
  /* border-bottom-left-radius: 10px; */
}
.contentCenter_Left_menu_title2 {
  height: 100px;
  color: #746868;
  font-size: 20px;
  text-align: center;
  background-color: #fff;
  line-height: 20px;
  /* color: orangered; */
  font-weight: 700;
  /* border-bottom-left-radius: 10px; */
}
.contentCenter_Left_menu_title3 {
  height: 100px;
  color: #746868;
  font-size: 20px;
  text-align: center;
  background-color: #fff;
  line-height: 20px;
  /* color: orangered; */
  font-weight: 700;
  /* border-bottom-left-radius: 10px; */
  border-bottom-left-radius: 20px;
}

.contentCenter_Left_item {
  flex: 5;
  height: 100%;
  background-color: #f4f5f9;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}

.contentCenter_Left_item_content {
  width: 100%;
  height: 100%;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}
.contentCenter_Left_item_content > .huandengpian {
  width: 100%;
  height: 100%;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}
.contentCenter_Left_item_content > .huandengpian > .el-carousel {
  text-align: center;
  width: 100%;
  height: 100%;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  
}
  .carousel-image {
  width: 100%;
  height: 100%; 
  object-fit: cover;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}
.contentCenter_Right {
  flex: 1;
  height: 100%;
  margin: auto;
  background-color: #fff;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
}
.hd_right_box{
  width: 25%;
  position: absolute;
  top: 0;
  right: 0;
  padding-left: 12px;
  border-radius: 20px;
  
}
.hd_right {
    box-shadow: 0 0 20px 0 rgba(0,0,0,.08);
    height: 280px;
    text-align: center;
    border-radius: 20px;
    padding: 20px 20px 0;
    background: url(src/assets/images/toprightbg.jpg) center top no-repeat #fff;
}
.docnumboxs {
    font-size: 20px;
    margin-top: 20px;
    font-weight: 400;
}
.docnumboxs .docnum {
    font-size: 24px;
    color: #000;
    font-weight: 800;
}
.updocbtn {
    background: #f54b4b;
    text-align: center;
    display: inline-block;
    margin: 20px auto 0;
    width: 100px;
    padding: 6px 20px;
    color: #fff;
    font-size: 14px;
    border-radius: 30px;
}
.keke_iconfont {
    font-family: "keke_iconfont" !important;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    margin-right: 5px;
}
.hd_right .advantage {
    list-style: none;
    display: flex; /* 将列表项水平排列 */
    justify-content: center; /* 水平居中 */
    padding: 0; /* 去除默认的内边距 */
}

.advantage li {
    margin-right: 20px; /* 为每个列表项添加右边距，根据需要调整间距 */
    text-align: center; /* 文字水平居中 */
    vertical-align: text-bottom; /* 文字在底部对齐 */
}

.advantage img {
    display: block; /* 防止图片底部留白 */
    margin: 0 auto; /* 图片水平居中 */
}
.left_item{z-index:100}
.left_item .navTitleBox{height:80px;border-bottom:1px solid #f5f5f5;position:relative;padding:35px 0 0 60px;cursor:pointer}
.left_item .popup{position:absolute;box-sizing:border-box;left:100%;top:0;display:none;width:760px;min-height:348px;background-color:#fff;box-shadow:0 0 10px 0 rgba(0,0,0,.08);font-size:12px;line-height:30px;overflow:hidden;border:5px solid #fff9f8;z-index: 1000; /* 设置一个较高的层级 */}
.left_item .navTitleBox img{position:absolute;left:15px;top:39px}
.left_item .navTitleBox .navTitle{display:block;font-size:18px;line-height:24px;font-weight:800}
.left_item a .navTitleBox .navTitle{color:#363636}
.left_item .navTitleBox .navDescribe{font-size:12px;color:#bbb;line-height:24px}
.cate_list_box{padding:30px 30px 40px;background:#fff}
.cate_list_box li{position:relative;min-height:30px;margin-bottom:10px;float:left;border-bottom:1px dashed #ececec;padding-bottom:10px;width:100%}
.cate_list_box li:last-child{border-bottom:0}
.firstcatename{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;box-sizing:border-box;position:absolute;width:120px;font-weight:800;left:0;top:0;font-size:15px;color:#333;padding:0 10px 0 17px}
.firstcatename:before{top:10px;width:3px;height:8px;position:absolute;left:0;background-color:#f54b4b;content:''}
.firstcatename a{color:#000}
.catelist{word-break:break-all;display:inline-block;line-height:30px;padding:0 10px 0 100px;top:0;}
.catelist a{color:#777;margin-right:10px;font-size:15px;display:inline-block}
.catelist a:hover{color:#f05759}
.openmore{position:absolute;bottom:0;left:170px;width:750px;height:45px;line-height:40px;background-image:linear-gradient(rgba(255,255,255,.1),#fff);text-align:center;color:#f54b4b;cursor:pointer}
.openmore a{color:#fff;padding:5px 15px 5px;background:#f54b4b}
.searchtitle{text-align:center;font-size:36px;font-weight:800;margin-top:10px}
.search_bar{width:570px;height:40px;position:relative;z-index:10;padding-right:60px;border:2px solid #ec3b3b;border-radius:5px;margin:0 auto;margin-top:30px;background:#fff}
.search_bar input{outline:0;border:none;background:0 0;resize:none;border-radius:0;-webkit-appearance:none;-moz-appearance:none;appearance:none;display:block;width:90%;height:40px;padding:0 10px 0 20px;font-size:14px}
.search_bar button{position:absolute;text-align:center;top:-1px;right:0;width:90px;height:42px;border-radius:0 5px 5px 0;cursor:pointer;outline:0;border:none;font-size:16px;resize:none;-webkit-appearance:none;-moz-appearance:none;appearance:none;color:#fff;background:linear-gradient(to bottom,#ec3b3b 0,#ec3b3b 100%)}
.search_bar .keke_iconfont{font-size:18px;margin-right:7px}
.hotkeyword{text-align:center;margin-top:20px;color:#a5a2a2}
.hotkeyword a{margin:10px 10px 10px 10px;color:#0a0a0a}
.hotkeyword a:hover {transform: translateY(-15px); /* 鼠标悬停时向上浮动5像素，你可以根据需要调整浮动的距离 */}
/* 设置按钮宽度 */
.custom-button {
  width: 100px; /* 替换为你想要的宽度值 */
}
.custom-button2 {
  width: 160px; /* 替换为你想要的宽度值 */
}

</style>