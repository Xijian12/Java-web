<template>
  <div
    class="contentcenter"
    :style="{ height: `${heightT}` + 'px' }"
    ref="getHeight"
  >
    <div>
      <span class="contentcenter_title">
        猜你喜欢<span class="contentcenter_title_fangkuai">个性推荐</span>
      </span>
    </div>
    <div id="wrap">
    <div style="margin-top: 10px">
      <el-row :gutter="24">
        <el-col :span="6" v-for="(book, index) in books" :key="index">
          <div id="card-container" @click="BookDetail(book.bookId)" style="cursor: pointer">
            <el-card :body-style="{ padding: '0px'}" shadow="hover">
              <div id="img">
                <img :src="book.bookCoverUrl">
              </div>
              <div id="content">
                <div id="bookName">
                  <span class="title">书名: </span><br>
                  <div class="content" style="height: 45px;">{{ book.bookName }}</div>
                </div>
                <div id="author">
                  <span class="title">作者: </span><br>
                  <span class="content" style="height: 20px;">{{ book.bookAuthor }}</span>
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
                  <span class="discountPrice"><span style="font-size: 15px">¥ </span>{{decimals(book.bookPoints*1)}}</span><br>
                  <span v-if="book.discount<1" class="price">定价: ¥{{ book.bookPoints }}</span>
                </div>
              </div>
              <div id="bottom-btn" >
                <el-button @click.stop="BookDetail(book.bookId)" size="medium" type="primary" plain>查看详情</el-button>
                <el-button-group style="margin-left: 10px">
                  <el-button @click.stop="addToCart(index)" size="mini" :icon="ShoppingCart"></el-button>
                  <el-button @click.stop="addToCollection(book.bookId)" size="mini" :icon="Star"></el-button>
                  <el-button @click.stop="copyPageUrl" size="mini" :icon="More"></el-button>
                </el-button-group>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
    </div>
  </div>
  <el-dialog
        v-model="FormVisible">
        您未进行登录请前往登录页面！
        <template #footer>
            <span class="dialog-footer">
              <el-button
                type="primary"
                @click="routerToLogin()"
              >
                确定
              </el-button>
            </span>
          </template>

        </el-dialog>
</template>

<script setup>
import {More, Star, ShoppingCart} from '@element-plus/icons-vue';
import {ref, reactive, onMounted} from 'vue'
import {ElMessage} from "element-plus";
import router from "@/router"
import axios from "axios";
import {useStore} from 'vuex';
// 登录跳转
function routerToLogin(){
  router.push("/")
}
const FormVisible = ref(false);
const store = useStore();
const email = ref(store.state.personalID[0].email)
const total = ref(0)
let books = ref([])
const myArg = ref([])


 // 翻页功能
function page(currentPage){
      const _this = this;
      axios.get("http://localhost:8081/book/selectBook/" + _this.myArg + "/" + currentPage + "/8")
          .then(function (resp) {
            _this.books = resp.data.books;
            _this.total = resp.data.total;
          });
    }
    // 保留一位小数
function decimals(value) {
      return value.toFixed(1);
    }
    // 跳转商品详情页
    function BookDetail(id) {
      router.push({name: 'BookDetail', params: {id: id}});
    }
    // 加入购物车
    function addToCart(index){
      let id = sessionStorage.getItem("id");
      if (id == null) {
        id = sessionStorage.getItem("visitorId");
        this.$message({
          type: 'warning',
          message: '您当前未登录，购物车中的内容会随着会话结束而消失，请尽快登录！',
          duration: 9000,
          showClose: true
        });
      }
      let data = {
        id: id,
        bookId: this.books[index].id,
        quantity: 1
      };
      axios.post("http://localhost:8081/user/addToCart",data)
          .then((resp) => {
            if (resp.data === 1) {
              this.$message({
                showClose: true, type: 'success',
                message: '添加成功！'
              });
            } else if (resp.data === 2) {
              this.$message({
                showClose: true, type: 'error',
                message: '添加失败，库存不足'
              });
            } else {
              this.$message({
                showClose: true, type: 'error',
                message: '操作失败，请重试'
              });
            }
          });
    }
// 加入收藏
function addToCollection(bookId){
  if (email.value == "session"){
      FormVisible.value = true;
    }else{
      let obj ={
        userEmail:email.value,
        bookId: bookId,
      }
      axios.post("/book/collectRecord", obj)
            .then((resp) => {
              if (resp.data.code == 0) {
                ElMessage({
                  showClose: true, type: 'success',
                  message: '添加成功！'
                });
              } else {
                ElMessage({
                  showClose: true, type: 'error',
                  message: resp.data.msg
                });
              }
            });
            }
}
    // 初始化页面数据
function initData() {
  // 发起简单的 GET 请求
  axios.get(`/book/highest/8`)
  .then(response => {
    // 处理成功的响应
    books.value = response.data
    console.log('成功：', books.value);
  })
  .catch(error => {
    // 处理请求错误
    console.error('请求失败：', error);
  });
     
    }

const heightT = ref(748)
//分享
const copyPageUrl = ()=> {
 // 获取当前网页的URL
 const currentPageUrl = window.location.href;
  // 创建一个临时textarea元素
  const textarea = document.createElement('textarea');
  textarea.value = currentPageUrl;
  document.body.appendChild(textarea);

  // 选中并复制文本
  textarea.select();
  document.execCommand('copy');

  // 移除临时元素
  document.body.removeChild(textarea);
  // 这里你可以添加一些反馈，比如提示用户复制成功
  ElMessage({
          message: "复制成功",
          type: "success",
        });
}

onMounted(() => {
  initData();
});


</script>
<style scoped>
.contentcenter {
  width: 1200px;
  /* height: 718px; */
  /* height: 100%; */
  background-color: #fff;
  position: relative;
  margin-top: 20px;
  left: 50%;
  transform: translate(-50%);
  padding: 18px;
  border-radius: 12px;
}
.contentcenter_title {
  height: 30px;
  width: 100%;
  font-size: 27px;
  margin-top: 10px;
}
.contentcenter_title_fangkuai {
  margin-left: 10px;
  background-color: orangered;
  color: white;
  font-size: 17px;
  padding: 0 5px;
  border-radius: 3px;
  position: relative;
  top: -3px;
}

#wrap {
  margin-top: 30px;
  width: 1200px;
}

#card-container {
  margin-top: 10px;
}

#card-container #img {
  float: left;
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

#card-container #img {
  width: 150px;
  height: 220px;
}

#card-container #img img {
  width: 150px;
  height: 200px;
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
  display: flex;
  align-items: center;
  float: none;
  margin: 0 0 30px 10px;
  width: 245px;
  height: 40px;
}

</style>