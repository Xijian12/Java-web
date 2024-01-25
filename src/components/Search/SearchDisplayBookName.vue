<template>
  <Header></Header>
  <div id="wrap">
      <el-page-header @back="goBack" content="搜索页面" title="返回首页"></el-page-header>
  </div>
  <div class="search_bar">
    <form @submit.prevent="SearchDisplayVue()">
        <input v-model="searchData" type="text" name="keyword" class="tx" autocomplete="off" placeholder="请输入您要搜索的文档关键字...">
        <el-button type="submit" @click="SearchDisplayVue()">搜索</el-button>
    </form>
  </div>
  <div  id="NavigationBarContainer">
    <el-menu router class="el-menu-demo" mode="horizontal" :default-active="activeIndex" @select="handleSelect">
      <el-menu-item index="0" style="pointer-events: none;">图书分类</el-menu-item>
      <el-sub-menu index="1">
        <template #title>教育</template>
        <el-sub-menu index="1-1">
          <template #title>教材</template>
          <el-menu-item index="1-1-1" @click="jump('1-1-1')">研究生/本科生/专科生教材</el-menu-item>
          <el-menu-item index="1-1-2" @click="jump('1-1-2')">高职高专教材</el-menu-item>
          <el-menu-item index="1-1-3" @click="jump('1-1-3')">中职教材</el-menu-item>
          <el-menu-item index="1-1-4" @click="jump('1-1-4')">成人教育教材</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="1-2">
          <template #title>外语</template>
          <el-menu-item index="1-2-1" @click="jump('1-2-1')">英语</el-menu-item>
          <el-menu-item index="1-2-2" @click="jump('1-2-2')">CTE-6/CTE-4</el-menu-item>
          <el-menu-item index="1-2-3" @click="jump('1-2-3')">日语</el-menu-item>
          <el-menu-item index="1-2-4" @click="jump('1-2-4')">韩语</el-menu-item>
          <el-menu-item index="1-2-5" @click="jump('1-2-5')">小语种</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="1-3">
          <template #title>考试</template>
          <el-menu-item index="1-3-1" @click="jump('1-3-1')">学历考试</el-menu-item>
          <el-menu-item index="1-3-2" @click="jump('1-3-2')">公务员</el-menu-item>
          <el-menu-item index="1-3-3" @click="jump('1-3-3')">考研</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="1-4">
          <template #title>工具书</template>
          <el-menu-item index="1-4-1" @click="jump('1-4-1')">汉语工具书</el-menu-item>
          <el-menu-item index="1-4-2" @click="jump('1-4-2')">英语工具书</el-menu-item>
          <el-menu-item index="1-4-3" @click="jump('1-4-3')">其他语种工具书</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>小说</template>
        <el-menu-item index="2-1" @click="jump('2-1')">科幻小说</el-menu-item>
        <el-menu-item index="2-2" @click="jump('2-2')">武侠小说</el-menu-item>
        <el-menu-item index="2-3" @click="jump('2-3')">军事小说</el-menu-item>
        <el-menu-item index="2-4" @click="jump('2-4')">情感小说</el-menu-item>
        <el-menu-item index="2-5" @click="jump('2-5')">侦探/悬疑/推理小说</el-menu-item>
        <el-menu-item index="2-6" @click="jump('2-6')">历史小说</el-menu-item>
        <el-menu-item index="2-7" @click="jump('2-7')">社会小说</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>文艺</template>
        <el-sub-menu index="3-1">
          <template #title>文学</template>
          <el-menu-item index="3-1-1" @click="jump('文学')">中国古诗词</el-menu-item>
          <el-menu-item index="3-1-2" @click="jump('文学')">民间文学</el-menu-item>
          <el-menu-item index="3-1-3" @click="jump('文学')">中国现当代随笔</el-menu-item>
          <el-menu-item index="3-1-4" @click="jump('文学')">外国随笔</el-menu-item>
          <el-menu-item index="3-1-5" @click="jump('文学')">名家作品</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3-2">
          <template #title>传记</template>
          <el-menu-item index="3-2-1" @click="jump('3-2-1')">财经人物</el-menu-item>
          <el-menu-item index="3-2-2" @click="jump('3-2-2')">历代帝王</el-menu-item>
          <el-menu-item index="3-2-3" @click="jump('3-2-3')">军事人物</el-menu-item>
          <el-menu-item index="3-2-4" @click="jump('3-2-4')">历史人物</el-menu-item>
          <el-menu-item index="3-2-4" @click="jump('3-2-4')">教育家</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3-3">
          <template #title>艺术</template>
          <el-menu-item index="3-3-1" @click="jump('3-3-1')">艺术理论</el-menu-item>
          <el-menu-item index="3-3-2" @click="jump('3-3-2')">书法/篆刻</el-menu-item>
          <el-menu-item index="3-3-3" @click="jump('3-3-3')">音乐</el-menu-item>
          <el-menu-item index="3-3-4" @click="jump('3-3-4')">雕塑</el-menu-item>
          <el-menu-item index="3-3-5" @click="jump('3-3-5')">绘画</el-menu-item>
          <el-menu-item index="3-3-6" @click="jump('3-3-6')">小人书/连环画</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3-4">
          <template #title>摄影</template>
          <el-menu-item index="3-4-1" @click="jump('3-4-1')">摄影理论</el-menu-item>
          <el-menu-item index="3-4-2" @click="jump('3-4-2')">摄影入门</el-menu-item>
          <el-menu-item index="3-4-3" @click="jump('3-4-3')">摄影进阶</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-sub-menu index="4">
        <template #title>人文社科</template>
        <el-sub-menu index="4-1">
          <template #title>历史</template>
          <el-menu-item index="4-1-1" @click="jump('历史')">中国史</el-menu-item>
          <el-menu-item index="4-1-2" @click="jump('历史')">世界史</el-menu-item>
          <el-menu-item index="4-1-3" @click="jump('历史')">历史地理</el-menu-item>
          <el-menu-item index="4-1-4" @click="jump('历史')">民族史</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="4-2">
          <template #title>文化</template>
          <el-menu-item index="4-2-1" @click="jump('4-2-1')">传统文化</el-menu-item>
          <el-menu-item index="4-2-2" @click="jump('4-2-2')">各国文化</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="4-3">
          <template #title>古籍</template>
          <el-menu-item index="4-3-1" @click="jump('4-3-1')">史类</el-menu-item>
          <el-menu-item index="4-3-2" @click="jump('4-3-2')">经部</el-menu-item>
          <el-menu-item index="4-3-3" @click="jump('4-3-3')">子部</el-menu-item>
          <el-menu-item index="4-3-4" @click="jump('4-3-4')">集部</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="4-4">
          <template #title>心理学</template>
          <el-menu-item index="4-4-1" @click="jump('4-4-1')">心理百科</el-menu-item>
          <el-menu-item index="4-4-2" @click="jump('4-4-2')">心理学著作</el-menu-item>
          <el-menu-item index="4-4-3" @click="jump('4-4-3')">社会心理学</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="4-5">
          <template #title>社会科学</template>
          <el-menu-item index="4-5-1" @click="jump('4-5-1')">社会科学总论</el-menu-item>
          <el-menu-item index="4-5-2" @click="jump('4-5-2')">社会学</el-menu-item>
          <el-menu-item index="4-5-3" @click="jump('4-5-3')">文化人类学</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="4-6">
          <template #title>法律</template>
          <el-menu-item index="4-6-1" @click="jump('4-6-1')">民法</el-menu-item>
          <el-menu-item index="4-6-2" @click="jump('4-6-2')">经济法</el-menu-item>
          <el-menu-item index="4-6-3" @click="jump('4-6-3')">刑法</el-menu-item>
          <el-menu-item index="4-6-4" @click="jump('4-6-4')">理论法学</el-menu-item>
          <el-menu-item index="4-6-5" @click="jump('4-6-5')">法律法规</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>童书</template>
        <el-menu-item index="5-1" @click="jump('5-1')">科普/百科</el-menu-item>
        <el-menu-item index="5-2" @click="jump('5-2')">绘本</el-menu-item>
        <el-menu-item index="5-3" @click="jump('5-3')">文学</el-menu-item>
        <el-menu-item index="5-4" @click="jump('5-4')">少儿英语</el-menu-item>
        <el-menu-item index="5-5" @click="jump('5-5')">幼儿启蒙</el-menu-item>
        <el-menu-item index="5-6" @click="jump('5-6')">动漫卡通</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="6">
        <template #title>经管</template>
        <el-sub-menu index="6-1">
          <template #title>管理</template>
          <el-menu-item index="6-1-1" @click="jump('6-1-1')">一般管理学</el-menu-item>
          <el-menu-item index="6-1-2" @click="jump('6-1-2')">会计</el-menu-item>
          <el-menu-item index="6-1-3" @click="jump('6-1-3')">市场/营销</el-menu-item>
          <el-menu-item index="6-1-4" @click="jump('6-1-4')">管理信息系统</el-menu-item>
          <el-menu-item index="6-1-5" @click="jump('6-1-5')">金融/投资</el-menu-item>
          <el-menu-item index="6-1-6" @click="jump('6-1-6')">MBA</el-menu-item>
          <el-menu-item index="6-1-7" @click="jump('6-1-7')">电子商务</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="6-2">
          <template #title>投资/理财</template>
          <el-menu-item index="6-2-1" @click="jump('6-2-1')">证券/股票</el-menu-item>
          <el-menu-item index="6-2-2" @click="jump('6-2-2')">基金</el-menu-item>
          <el-menu-item index="6-2-3" @click="jump('6-2-3')">期货</el-menu-item>
          <el-menu-item index="6-2-4" @click="jump('6-2-4')">外汇</el-menu-item>
          <el-menu-item index="6-2-5" @click="jump('6-2-5')">保险</el-menu-item>
          <el-menu-item index="6-2-6" @click="jump('6-2-6')">纳税</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="6-3">
          <template #title>经济</template>
          <el-menu-item index="6-3-1" @click="jump('6-3-1')">经济学理论</el-menu-item>
          <el-menu-item index="6-3-2" @click="jump('6-3-2')">区域经济</el-menu-item>
          <el-menu-item index="6-3-3" @click="jump('6-3-3')">通货膨胀</el-menu-item>
          <el-menu-item index="6-3-4" @click="jump('6-3-4')">经济法</el-menu-item>
          <el-menu-item index="6-3-5" @click="jump('6-3-5')">统计/审计</el-menu-item>
          <el-menu-item index="6-3-6" @click="jump('6-3-6')">经济史</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-sub-menu index="7">
        <template #title>期刊</template>
        <el-menu-item index="7-1-1" @click="jump('7-1-1')">娱乐时尚</el-menu-item>
        <el-menu-item index="7-1-2" @click="jump('7-1-2')">旅游/人文</el-menu-item>
        <el-menu-item index="7-1-3" @click="jump('7-1-3')">家庭/美食</el-menu-item>
        <el-menu-item index="7-1-4" @click="jump('7-1-4')">汽车</el-menu-item>
        <el-menu-item index="7-1-5" @click="jump('7-1-5')">IT/科技</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="8">
        <template #title>科技</template>
        <el-sub-menu index="8-1">
          <template #title>科普读物</template>
          <el-menu-item index="8-1-1" @click="jump('8-1-1')">宇宙知识</el-menu-item>
          <el-menu-item index="8-1-2" @click="jump('8-1-2')">人类故事</el-menu-item>
          <el-menu-item index="8-1-3" @click="jump('8-1-3')">生物世界</el-menu-item>
          <el-menu-item index="8-1-4" @click="jump('8-1-4')">科学世界</el-menu-item>
          <el-menu-item index="8-1-5" @click="jump('8-1-5')">百科知识</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="8-2">
          <template #title>建筑</template>
          <el-menu-item index="8-2-1" @click="jump('8-2-1')">建筑科学</el-menu-item>
          <el-menu-item index="8-2-2" @click="jump('8-2-2')">建筑史与建筑文化</el-menu-item>
          <el-menu-item index="8-2-3" @click="jump('8-2-3')">室内设计/装潢装修</el-menu-item>
          <el-menu-item index="8-2-4" @click="jump('8-2-4')">园林景观/环境艺术</el-menu-item>
          <el-menu-item index="8-2-5" @click="jump('8-2-5')">建筑外观设计</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="8-3">
          <template #title>医学</template>
          <el-menu-item index="8-3-1" @click="jump('8-3-1')">预防医学/卫生学</el-menu-item>
          <el-menu-item index="8-3-2" @click="jump('8-3-2')">临床医学理论</el-menu-item>
          <el-menu-item index="8-3-3" @click="jump('8-3-3')">内科学</el-menu-item>
          <el-menu-item index="8-3-4" @click="jump('8-3-4')">外科学</el-menu-item>
          <el-menu-item index="8-3-5" @click="jump('8-3-5')">妇产科学</el-menu-item>
          <el-menu-item index="8-3-6" @click="jump('8-3-6')">儿科学</el-menu-item>
          <el-menu-item index="8-3-7" @click="jump('8-3-7')">护理学</el-menu-item>
          <el-menu-item index="8-3-8" @click="jump('8-3-8')">中医</el-menu-item>
          <el-menu-item index="8-3-9" @click="jump('8-3-9')">药学</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="8-4">
          <template #title>计算机/网络</template>
          <el-menu-item index="8-4-1" @click="jump('8-4-1')">计算机理论</el-menu-item>
          <el-menu-item index="8-4-2" @click="jump('8-4-2')">操作系统</el-menu-item>
          <el-menu-item index="8-4-3" @click="jump('8-4-3')">数据库</el-menu-item>
          <el-menu-item index="8-4-4" @click="jump('8-4-4')">程序设计</el-menu-item>
          <el-menu-item index="8-4-5" @click="jump('8-4-5')">网络与通信技术</el-menu-item>
          <el-menu-item index="8-4-6" @click="jump('8-4-6')">人工智能</el-menu-item>
          <el-menu-item index="8-4-7" @click="jump('8-4-7')">信息安全</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="8-5">
          <template #title>自然科学</template>
          <el-menu-item index="8-5-1" @click="jump('8-5-1')">数学</el-menu-item>
          <el-menu-item index="8-5-2" @click="jump('8-5-2')">力学</el-menu-item>
          <el-menu-item index="8-5-3" @click="jump('8-5-3')">物理学</el-menu-item>
          <el-menu-item index="8-5-4" @click="jump('8-5-4')">化学</el-menu-item>
          <el-menu-item index="8-5-5" @click="jump('8-5-5')">天文学</el-menu-item>
          <el-menu-item index="8-5-6" @click="jump('8-5-6')">地球科学</el-menu-item>
          <el-menu-item index="8-5-7" @click="jump('8-5-7')">生物科学</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="8-6">
          <template #title>工业技术</template>
          <el-menu-item index="8-6-1" @click="jump('8-6-1')">一般工业技术</el-menu-item>
          <el-menu-item index="8-6-2" @click="jump('8-6-2')">机械/仪表工业</el-menu-item>
          <el-menu-item index="8-6-3" @click="jump('8-6-3')">电工技术</el-menu-item>
          <el-menu-item index="8-6-4" @click="jump('8-6-4')">冶金工业</el-menu-item>
          <el-menu-item index="8-6-5" @click="jump('8-6-5')">能源与动力工程</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-sub-menu index="9">
        <template #title>成功/励志</template>
        <el-menu-item index="9-1" @click="jump('9-1')">人生哲学</el-menu-item>
        <el-menu-item index="9-2" @click="jump('9-2')">成功/激励</el-menu-item>
        <el-menu-item index="9-3" @click="jump('9-3')">心理与修养</el-menu-item>
        <el-menu-item index="9-4" @click="jump('9-4')">性格与习惯</el-menu-item>
        <el-menu-item index="9-5" @click="jump('9-5')">人际交往</el-menu-item>
        <el-menu-item index="9-6" @click="jump('9-6')">礼仪</el-menu-item>
        </el-sub-menu>
        </el-menu>
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
                  <el-button @click.stop="" size="mini" :icon="More"></el-button>
                </el-button-group>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>

    <!--数据为空时显示-->
    <div v-show="total===0" v-cloak>
      <el-empty :image-size="200"></el-empty>
    </div>
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
import {useStore} from 'vuex';
// 控制图书和资料
const route = useRoute();
const store = useStore();
const email = ref(store.state.personalID[0].email)
const router = useRouter();
const total = ref(1);
const books = ref({
        id: '',
        bookName: '',
        author: '',
        price: 0,
        discount: 1,
        press: '',
        publicationDate: '',
        brief: '',
        img: ''
      })
    // 翻页功能
function pagechange(val) {
  pageNum.value = val;
  initDataCategory(route.params.id);
}
    // 保留一位小数
function decimals(value) {
      return value.toFixed(1);
}
  // 跳转商品详情页
function BookDetail(id) {
      router.push({name: 'BookDetail', params: {id: id}});
}
let pageNum = ref(1);
let pageSize = ref(12);
// 页面初始化时执行，查询同类
const initDataCategory = async (categoryName) => {
  if (categoryName == 1){
    try {
      const response = await axios.get('/book/find', {
      params: {
        page: pageNum.value,
        pageSize: pageSize.value
      }
    });
    books.value = response.data.items
    total.value = response.data.total
    // 处理成功的响应
  } catch (error) {
    // 处理请求错误
    console.error('请求失败：', error);
  }
  }else{
    try {
      const response = await axios.get('/book/find', {
      params: {
        bookName: categoryName,
        page: pageNum.value,
        pageSize: pageSize.value
      }
    });
    books.value = response.data.items
    total.value = response.data.total
    // 处理成功的响应
    console.log('成功：', books.value);
  } catch (error) {
    // 处理请求错误
    console.error('请求失败：', error);
  }
  }

};
 // 返回首页
function goBack() {
  router.push("/home");
}
 // 加入收藏
function addToCollection(bookId){
      let obj ={
        userEmail:email.value,
        bookId: bookId,
      }
      axios.post("/book/collectRecord", obj)
            .then((resp) => {
              if (resp.data.code === 0) {
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
// 导航栏跳转功能
function jump(path) {
  router.push({name: 'SearchDisplay', params: {id: path}});
}

// 搜索
const searchData = ref("")
function SearchDisplayVue(){
      const path = searchData.value
      router.push({name:"SearchDisplayBookName", params: {id: path}})
}
onMounted(async () => {
  const category = route.params.id;
  router.afterEach((to, from, next) => {
        window.scrollTo(0, 0)
    })
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