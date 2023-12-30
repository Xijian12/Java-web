<template>
  <div id="Header_content">
    <span class="Header_content_left" v-if="!this.arr" @click="loginBtn()">亲，请登入 </span>
    <span class="Header_content_left" v-else>欢迎！
      <span style="color:red;">{{this.arr.user_name}}</span>
      <img class="Header_content_left_image" :src="this.arr.img" alt="">
    </span>
    <span v-if="this.arr">我的钱包：￥<span style="color:red;font-size:20px;">{{this.$store.state.youMoney}}</span></span>
    <span class="Header_content_right" @click="loginCart()">我的 购物车</span>
  </div>
</template>

<script>
export default {
  name:"Header",
  props:["abs"],
  created:function(){
    this.arr = this.$store.state.personalID[0]
  },
  methods:{
    loginCart(){
      this.$router.push({name:"cart"})
    },
    loginBtn(){
      this.$router.push({name:"login"})
    }
  }
}
</script>

<style scoped>
  #Header_content{
    display: flex;
    height: 50px;
    background-color: snow;
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