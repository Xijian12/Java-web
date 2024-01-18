<template>
  <div class="home">
    <el-main>
      <!--      仪表盘-->
      <el-row class="dashboard">
        <el-col :xs="24" :sm="12" :lg="6" class="book-num pane"
          ><div>
            <el-icon><Reading /></el-icon>
            <div class="message">
              <p class="title">网站收藏图书资料数量</p>
              <p class="sub-title">{{ sum }}</p>
            </div>
          </div></el-col
        >
        <el-col :xs="24" :sm="12" :lg="6" class="overtime-num pane"
          ><div>
            <el-icon><Failed /></el-icon>
            <div class="message">
              <p class="title">图书资料总下载量</p>
              <p class="sub-title">{{Download }}</p>
            </div>
          </div></el-col
        >
        <el-col :xs="24" :sm="12" :lg="6" class="borrow-num pane"
          ><div>
            <el-icon><List /></el-icon>
            <div class="message">
              <p class="title">图书资料总点击量</p>
              <p class="sub-title">{{ Click }}</p>
            </div>
          </div></el-col
        >
        <el-col :xs="24" :sm="12" :lg="6" class="system-time pane"
          ><div>
            <el-icon><Timer /></el-icon>
            <div class="message">
              <p class="title">系统时间</p>
              <p class="time">{{ times }}</p>
            </div>
          </div></el-col
        >
      </el-row>
      <!--      介绍卡片-->
      <el-card class="introduce">
        <template #header> 开源信息 </template>
        <p>该项目模拟图书资料分享平台</p>
        <p>
          GitHub下载地址：<a
            href="https://github.com/Xijian12/Java-web"
            >GitHub</a
          >
        </p>
        <p>文档地址：<a href="#">文档.md</a></p>
      </el-card>
      <!--      日志卡片-->
      <el-card class="log">
        <template #header>
          <span>操作日志</span>
        </template>
        <el-table
          style="width: 100%"
          height="600"
          :data="logs"
          :default-sort="{ prop: 'date', order: 'descending' }"
        >
          <el-table-column
            prop="time"
            label="日期/时间"
            sortable
            width="220px"
          />
          <el-table-column prop="name" label="名字" width="120px" />
          <el-table-column prop="info" label="操作" />
        </el-table>
      </el-card>
    </el-main>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { Reading, Failed, List, Timer } from "@element-plus/icons-vue";
import axios from "axios";

// 仪表盘数据
let times = ref("");

//系统时间
setInterval(function () {
  let date = new Date();
  let year = date.getFullYear(); //获取当前年份
  let mon = date.getMonth() + 1; //获取当前月份
  let da = date.getDate(); //获取当前日
  let day = date.getDay(); //获取当前星期几
  let h: any = date.getHours(); //获取小时
  let m: any = date.getMinutes(); //获取分钟
  let s: any = date.getSeconds(); //获取秒
  let weeks = ["日", "一", "二", "三", "四", "五", "六"]; //星期
  let d = document.getElementById("Date");
  if (h < 10) {
    h = "0" + h;
  }
  if (m < 10) {
    m = "0" + m;
  }
  if (s < 10) {
    s = "0" + s;
  }
  times.value =
    year +
    "-" +
    mon +
    "-" +
    da +
    " " +
    "星期" +
    weeks[day] +
    " " +
    h +
    ":" +
    m +
    ":" +
    s;
}, 1);
let bookClick = ref(0);
let bookDownload = ref(0);
const Click = ref(0);
const Download = ref(0);
// 获取资料点击数据
const getBookClick = () => {
  axios.get("/book/clickNum").then((resp) => {
    bookClick.value = resp.data.data;
    axios.get("/material/clickNum").then((resp) => {
    materialClick.value = resp.data.data;
    Click.value = bookClick.value+ materialClick.value;
  });
  });
};
getBookClick();
let materialClick = ref(0);
let materialDownload = ref(0);
// 获取资料点击数据
const getMaterialClick = () => {
  axios.get("/material/downloadNum").then((resp) => {
    materialDownload.value = resp.data.data;
    axios.get("/book/downloadNum").then((resp) => {
    bookDownload.value = resp.data.data;  
  Download.value = materialDownload.value + bookDownload.value;
  });
  });
};
getMaterialClick();
//获得图书和资料总数
let sum =ref(0);
let book = ref(0);
let material = ref(0);
const getSum= () => {
  axios.get("/book/find").then((resp) => {
    book.value = resp.data.total;
    axios.get("/material").then((resp) => {
      material.value = resp.data.data.total;  
      sum.value = book.value + material.value;
  });
  });
};
getSum();
// 获取日志数据
let logs = ref();
const getLogs = () => {
  axios.get("http://localhost:8888/nav/logs/").then((resp) => {
    logs.value = resp.data.content;
  });
};
getLogs();
</script>

<style lang="scss">
@import "@/assets/css/home";
</style>
