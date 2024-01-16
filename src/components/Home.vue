<template>
  <div id="home_content">
    <Header></Header>
    <ContentTitle></ContentTitle>
    <ContentCenter1 ></ContentCenter1>
    <ContentCenter1_1 ></ContentCenter1_1>
    <ContentCenter2></ContentCenter2>
    <ContentCenter3></ContentCenter3>
    <Footer></Footer>
    <div class="comeToHeader">
      <div class="active" @click="toSearch()" ><span >精选推荐</span></div>
      <div class="active"><span>反馈</span></div>
      <div class="active"><span>客服帮助</span></div>
      <div class="active" @click="toTop()"><span>顶部</span></div>
    </div>
  </div>
</template>

<script setup>
import Header from "@/components/Home/Header.vue";
import ContentTitle from "@/components/Home/ContentTitle.vue";
import ContentCenter1 from "@/components/Home/ContentCenter1.vue";
import ContentCenter1_1 from "@/components/Home/ContentCenter1_1.vue";
import ContentCenter2 from "@/components/Home/ContentCenter2.vue";
import ContentCenter3 from "@/components/Home/ContentCenter3.vue";
import Footer from "@/components/Home/Footer.vue";
import router from "@/router"
function toTop() {
      //    document.documentElement.scrollTop = 0;
      let timer = setInterval(() => {
        let ispeed = Math.floor(document.documentElement.scrollTop / 5);
        document.documentElement.scrollTop -= ispeed;
        if (document.documentElement.scrollTop <= 10) {
          document.documentElement.scrollTop === 0;
          clearInterval(timer);
        } else {
          console.log("difit");
        }
      }, 16);
    }
function toSearch() {
  router.push({name:"SearchDisplay", params: {id: 1}})
}
</script>

<style scoped>
#home_content {
  background-color: #f4f5f9;
  /* min-width: 1700px; */
}
.comeToHeader {
  position: fixed;
  right: 5%;
  bottom: 30px;
  width: 50px;
  height: 200px;
  border-radius: 12px;
  background-color: #fff;
  display: flex;
  flex-direction: column;
  
}
.comeToHeader > div {
  flex: 1;
  flex-wrap: wrap;
  text-align: center;
  font-size: 18px;
  cursor: pointer;
  background-color: #f0f9eb;

}
.comeToHeader > div:not(:last-child){
  border-bottom: 1px solid #555353;
}
.comeToHeader > div:nth-child(1) {
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
}
.comeToHeader > div:nth-child(4) {
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}
.comeToHeader > div > span {
  color: #67c23a;
  display: inline-block;
  vertical-align: middle;
}
.comeToHeader > div.active:hover {
  background-color: orangered;
}
</style>