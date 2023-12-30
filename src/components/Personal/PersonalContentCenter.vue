<template>
  <div class="contentCenter">
    <div class="contentCenter_title">
      <span class="contentCenter_title_span"> 全部商品 / 发票 </span>
    </div>
    <div class="contentCenter_center" v-if="this.paid.length === 0">
      <span style="margin: 50px; font-size: 50px">请去购买商品</span>
    </div>
    <div class="contentCenter_center" v-else>
      <ul>
        <li>序号</li>
        <li>商品信息</li>
        <li>单价</li>
        <li>数量</li>
        <li>金额</li>
      </ul>
      <ol v-for="(item, index) in this.paid" :key="index">
        <li>
          <span>{{index+1}}</span>
          <img :src="item.product_image" alt="" />
        </li>
        <li>{{ item.product_name }}</li>
        <li>{{ item.product_price * 10000 }}</li>
        <li>{{ item.product_much }}件</li>
        <li>￥{{ item.product_price * 10000 * item.product_much }}</li>
      </ol>
    </div>
  </div>
</template>

<script>
export default {
    name:"PersonalContentCenter",
    data(){
      return{
        paid:[]
      }
    },
    created(){
      let userid = this.$store.state.personalID[0].user_id;
      this.$http.get(`home/usercartdone/`).then(response => {
        this.paid = response.data.filter(item => item.user_name === userid)
        console.log(response.data);
      })
      // this.paid =  this.$store.state.Product
    }
}
</script>

<style scoped>
.contentCenter {
  width: 1160px;
  height: 100%;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  margin: 20px 20px 20px 0;
}
.contentCenter_title {
  width: 100%;
  height: 40px;
  background-color: paleturquoise;
  line-height: 40px;
}
.contentCenter_title > .contentCenter_title_span {
  height: 100%;
  width: 100px;
  border-bottom: 3px solid red;
  padding: 10px 10px;
}
.contentCenter_center {
  width: 100%;
  height: 600px;
  background-color: #fff;
  overflow: auto;
}
.contentCenter_center > ul {
  display: flex;
  flex-direction: row;
}
.contentCenter_center > ul > li {
  margin-left: 10px;
  flex: 1;
}
.contentCenter_center > ul > li:nth-child(2) {
  flex: 4;
}
.contentCenter_center > ol {
  width: 100%;
  height: 150px;
  border: 1px solid gainsboro;
  margin: 20px 0;
  display: flex;
  flex-direction: row;
}
.contentCenter_center > ol > li {
  margin-left: 10px;
  flex: 1;
  /* line-height: 150px; */
}
.contentCenter_center > ol > li:nth-child(1) {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.contentCenter_center > ol > li:nth-child(1) > span {
  flex: 1;
  margin-top: 70px;
}
.contentCenter_center > ol > li:nth-child(1) > img {
  flex: 1;
  width: 100px;
  height: 80px;
  margin-top: 30px;
}
.contentCenter_center > ol > li:nth-child(2) {
  line-height: 130px;
  margin-top: 10px;
  flex: 4;
  flex-wrap: wrap;
}
.contentCenter_center > ol > li:nth-child(3),
.contentCenter_center > ol > li:nth-child(4),
.contentCenter_center > ol > li:nth-child(5),
.contentCenter_center > ol > li:nth-child(6) {
  line-height: 150px;
}
</style>