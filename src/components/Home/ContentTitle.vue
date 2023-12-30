<template>
  <div class="contentTitle">
    <div class="image">
      <img class="taobao_img" src="../../assets/img/taobao.png" alt="">
      <a class="wenzi" href="www.baidu.com" >
        中国大陆
      </a>
    </div>
    <div class="title_search">
      <i class="iconfont icon-sousuo sousuo"></i>
      <input v-model="keyword" class="title_seacrh_input"  type="search" placeholder="搜索商品" >
      <!-- <el-button class="title_searcch_button" round>搜索</el-button> -->
      <div class="search_content" ref="search" v-show="keyword">
        <ul v-for="(item,index) in list" :key="index" @click="handleToProduct(item.id)">
          <li class="search-item border-bottom">{{item.product_name}}</li>
        </ul>
        <ul v-show="hasNoData">
          <li class="search-item border-bottom" >没有匹配的数据</li>
        </ul>
      </div>
      <div class="title_seacrh_wenzi">
        <a class="title_wenzi">海澜之家  套装 秋 移动电源 紧身裤牛仔高腰 洗衣机 泰茶 收纳盒   更多。。。</a>
      </div>
    </div>
    <button @click="clickme()">今日签到</button>
  </div>
</template>

<script>
export default {
  name:"ContentTitle",
  data(){
    return{
      keyword:"",
      search:[],
      hasNoData:"",
      timer:null,
      list:[]
    }
  },
  methods:{
    clickme(){
      this.$store.commit("addyouMoney",100);
    },
    handleToProduct(id){
      console.log(id);
      let router = this.list.filter(item => item.id === id)
      console.log(router);
      this.$router.push({path:"/itemList",
        query:{
            id:router[0].id,
            car_name:router[0].product_name,
            car_model:router[0].car_model,
            car_price:router[0].product_price,
            car_color:router[0].car_color,
            car_image:router[0].product_image,
            car_image2:router[0].product_image,
            introduce:router[0].introduce,
            howManyToBuy:router[0].howManyToBuy,
            car_purchase_method:router[0].car_purchase_method,
            car_package:router[0].car_package,
            user_name:router[0].user_name
          }
      })
    }
  },
  watch:{
    keyword(){
      if(this.timer){
        clearTimeout(this.timer)
      }
      if(!this.keyword){
        this.list = []
        return
      }
      this.timer = setTimeout(()=>{
        const result = []
        for(let i = 0;i < this.search.length;i++){

            if(this.search[i].product_name.indexOf(this.keyword) > -1){
              // console.log(this.search[i].product_name);
              result.push(this.search[i])
            }
        }
        if(result.length === 0){
          this.hasNoData = "社么都没用"
        }else{
          this.list = result
          console.log(this.list);
          this.hasNoData = ""
        }
      },100)
    }
  },
  mounted:function(){
    this.$http.get(`home/usercart`).then(response=>{
      this.search = response.data.filter(item => item.user_name === 1)
    })
  }
}
</script>

<style scoped>
  .contentTitle{
    display: flex;
    flex-flow: wrap;
    justify-content: space-evenly;
  }
  .image{
    height: 60px;
    /* width: 190px; */
    margin-top: 30px;
    display: flex;
    flex-direction: row;
    flex: 2;
    justify-content: flex-end;
  }
  .image > .taobao_img{
    height: 100%;
    width: 100px;
    padding-right: 10px;
    border-right: 2px solid orangered;
  }
  .image > .wenzi {
    height: 100%;
    width: 70px;
    font-size: 25px;
    margin-left: 10px;
    margin-top: -5px;
    color: orangered;
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
    color:	#A0A0A0;
    cursor: pointer;
    margin-left: 15px;
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