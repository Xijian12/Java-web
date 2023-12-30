<template>
  <div class="contentCenter">
    <div class="contentCenter_title">
      <span class="contentCenter_title_span"> 全部商品 </span>
      <!-- <span>|</span>
      <span> 全部商品 </span> -->
    </div>
    <div class="contentCenter_center" v-if="this.cartAll.length === 0">
      <span style="margin: 50px; font-size: 50px">请添加商品</span>
    </div>
    <div class="contentCenter_center" v-else>
      <ul>
        <li><input type="checkbox" v-model="isAll" /> 全选</li>
        <li>商品信息</li>
        <li>单价</li>
        <li>数量</li>
        <li>金额</li>
        <li>操作</li>
      </ul>
      <ol v-for="(item, index) in this.cartAll" :key="index">
        <li>
          <input
            type="checkbox"
            :checked="item.car_choice"
            @change="handleCheck(item.id, item)"
          />
          <img :src="item.product_image" alt="" />
        </li>
        <li>{{ item.product_name }}</li>
        <li>{{ item.product_price * 10000 }}</li>
        <li>{{ item.product_much }}件</li>
        <li>￥{{ item.product_price * 10000 * item.product_much }}</li>
        <li class="shanchu" @click="deleteBtn(item.id)">单个删除</li>
      </ol>
    </div>
    <div class="contentCenter_footer">
      <ul>
        <li><input type="checkbox" v-model="isAll" />全选</li>
        <li class="shanchu" @click="clearAll()">多个删除</li>
        <li>已选商品{{ this.$store.state.sumNumber }}件</li>
        <li>
          合计（不含运费）：￥<span style="color: orangered; font-size: 20px">{{
            this.$store.state.sumPrice
          }}</span>
        </li>
        <li><button @click="pay()">结算</button></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "CartContentCenter",
  data() {
    return {
      cartAll: [],
      sumPrice: 0,
      
    };
  },
  computed: {
    
    total() {
      return this.cartAll.length;
    },
    doneTotal() {
      return this.cartAll.reduce((pre, current) => {
        return pre + (current.car_choice ? 1 : 0);
      }, 0);
    },
    isAll: {
      get() {
        return this.doneTotal === this.total && this.total > 0;
      },
      set(value) {
        this.checkAllInput(value);
      },
    },
  },
  mounted:{

  },
  created() {
    // this.cartAll = this.$store.state.CartAll;
    let userid = this.$store.state.personalID[0].user_id;
    this.$http.get(`home/usercart/`).then(response => {
        this.cartAll =  response.data.filter(item => item.user_name === userid)
        let abc = this.cartAll.map(
          // (item) => item.car_price 
          item => parseInt(item.product_price) * 10000 * item.product_much
        );
        for (let i = 0; i < abc.length; i++) {
          this.sumPrice += abc[i];
        }
        
        for(let i = 0;i<this.cartAll.length;i++){
          const todoObj = {
            id:this.cartAll[i].id,
            product_price:this.cartAll[i].product_price,
            product_much:this.cartAll[i].product_much,
            product_image:this.cartAll[i].product_image,
            product_name:this.cartAll[i].product_name,
            car_choice:false
          }
          this.$store.dispatch('addToCart',todoObj)
        }
     })
    
  },
  methods: {
    // 是否取消选择
    handleCheck(id, item) {
      this.$store.commit("ifChoice", id);
      if (item.car_choice === true) {
        this.$store.commit(
          "addPrice",
          item.product_price * 10000 * item.product_much
        );
      } else {
        this.$store.commit(
          "jianPrice",
          item.product_price * 10000 * item.product_much
        );
      }
    },
    // 是否全选
    checkAllInput(done) {
      this.$store.commit("allChoice", done);
      this.cartAll = this.$store.state.CartAll;
    },
    // 是否删除
    deleteBtn(id) {
      if (confirm("确定删除")) {
        this.$store.commit("deleteId", id);
        this.cartAll = this.$store.state.CartAll;
        this.$http.delete(`cart/?id=${id}`).then((response)=>{
            this.$message.success(response.data.message)
        })
      }
    },
    // 是否删除全部
    clearAll() {
      if (confirm("确定删除")) {
        let bbb = this.$store.state.CartAll.filter((todo) => {
            return todo.car_choice 
        });
        for(let i = 0;i < bbb.length;i++){
          this.$http.delete(`cart/?id=${bbb[i].id}`).then((response)=>{
            this.$message.success(response.data.message)
          })
        }
        this.$store.commit("clearAllId");
        this.cartAll = this.$store.state.CartAll;
        
      }
    },
    pay() {
      var r = confirm("请问是否付款");
      if (r == true) {
        if(this.$store.state.youMoney > this.$store.state.sumPrice){
          // 付钱
          let user_id = this.$store.state.personalID
          this.$http.post(`cart/?id=${user_id[0].user_id}&jine=${100000000-this.$store.state.sumPrice}`).then(()=>{
              this.$message.success("支付成功")
            })
          this.$store.commit("gaifuqianle", this.$store.state.sumPrice);
          // 还没购买的东西
          this.payDoneBefore = this.cartAll.filter((item) => {
            return item.car_choice === false;
          });

          this.$store.commit("pay1", this.payDoneBefore);
          // 购买的东西
          this.payDoneAfter = this.cartAll.filter((item) => {
            return item.car_choice === true;
          });
          for(let i = 0;i < this.payDoneAfter.length;i++){
            this.$http.delete(`cart/?id=${this.payDoneAfter[i].id}`).then(()=>{
              this.$message.success("支付成功")
            })
          }
          for(let i = 0;i < this.payDoneAfter.length;i++){
            this.$http.post(`users/usercartdone/`,{
              product_name:this.payDoneAfter[i].product_name,
              product_price:this.payDoneAfter[i].product_price,
              product_image:this.payDoneAfter[i].product_image,
              product_much:this.payDoneAfter[i].product_much,
              introduce:this.payDoneAfter.introduce,
              user_name:user_id[0].user_id,
            })
          }
          
          this.$store.commit("pay2", this.payDoneAfter);
          // 刷新页面
          this.cartAll = this.$store.state.CartAll;
          // this.$message.success("支付成功")
        }else{
          alert("你太穷了,还房贷了？")
        }
      } else {
        console.log("不好");
      }
    },
  },
};
</script>

<style scoped>
.shanchu {
  cursor: pointer;
}
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
.contentCenter_center > ol > li:nth-child(1) > input {
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
.contentCenter_footer {
  width: 100%;
  height: 80px;
  background-color: gray;
}
.contentCenter_footer > ul {
  display: flex;
  flex-direction: row;
}
.contentCenter_footer > ul > li {
  flex: 1;
  line-height: 80px;
}
.contentCenter_footer > ul > li:nth-child(2) {
  flex: 3;
}
.contentCenter_footer > ul > li:nth-child(4) {
  flex: 2;
}
.contentCenter_footer > ul > li > button {
  width: 100px;
  height: 40px;
  background-color: orangered;
  color: #fff;
  font-size: 20px;
  border-radius: 12px;
  cursor: pointer;
}
.contentCenter_footer > ul > li > input {
  margin-left: 10px;
}
</style>