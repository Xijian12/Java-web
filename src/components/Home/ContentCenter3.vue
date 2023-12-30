<template>
  <div
    class="contentcenter"
    :style="{ height: `${this.heightT}` + 'px' }"
    ref="getHeight"
  >
    <div>
      <span class="contentcenter_title">
        猜你喜欢<span class="contentcenter_title_fangkuai">个性推荐</span>
      </span>
    </div>
    <div
      class="contentcenter_product"
      v-for="(item, index) in view"
      :key="index"
    >
          <!-- <router-link :to="{path:'/page1',query:{username}}">page1</router-link> // query方式
           <router-link :to="{name:'page2',params:{username1}}">page2</router-link> // params方式 -->
      <router-link
        :to="{
          path: '/itemList',
          query:{
            id:item.id,
            car_name:item.product_name,
            car_model:item.car_model,
            car_price:item.product_price,
            car_color:item.car_color,
            car_image:item.product_image,
            car_image2:item.product_image,
            introduce:item.introduce,
            howManyToBuy:item.howManyToBuy,
            car_purchase_method:item.car_purchase_method,
            car_package:item.car_package,
            user_name:item.user_name
          }
        }"
      >
        <div class="contentcenter_product_image">
          <img :src="item.product_image" alt="" />
        </div>
        <div style="margin-left:5px;">{{item.product_name}}</div>
        <div class="contentcenter_product_wenzi">
          {{ item.introduce }}
        </div>
        <div class="contentcenter_product_price">
          <span>￥{{item.product_price}}W</span> <span>0 人付款</span>
        </div>
      </router-link>
    </div>
    <div class="footer">
      <span class="loading"> 正在加载新商品，请稍后... </span>
      <span class="end"> 已经没有商品了 </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "ContentCenter3",
  data() {
    return {
      heightT: 748,
      size: 10,
      page: 0,
      view: [],
      back: [],
      flag: true,
      data2:[]
    };
  },
  mounted: function () {
    this.$http.get(`home/usercart`).then(response => {
      this.data2 = response.data
      this.back.push(this.data2);
      this.back = this.back[0]
      this.initView();
    })
  },
  methods: {
    initView: function () {
      this.view.push(
        ...this.back.slice(this.page * this.size, (this.page + 1) * this.size)
      );
    },
  },
  created: function () {
    
    let that = this;
    window.onscroll = function () {
      const windowHeight =
        document.documentElement.clientHeight || document.body.clientHeight;
      const scrollTop =
        (document.documentElement.scrollTop || document.body.scrollTop) -
        windowHeight;
      const scrollHeight =
        document.documentElement.scrollHeight || document.body.scrollHeight;
      const end = document.querySelector(".end");
      const loading = document.querySelector(".loading");
      //   let height = that.$refs.getHeight.style.height;
      if (
        scrollTop + windowHeight >= scrollHeight - windowHeight &&
        that.view.length < that.back.length
      ) {
        setTimeout(() => {
          if (that.back.length - that.view.length >= 6) {
            that.heightT += 678;
            that.page++;
            that.initView();
          } else {
            that.heightT += 339;
            that.page++;
            that.initView();
          }
        }, 1000);
        end.classList.remove("active");
        loading.classList.add("active");
      } else {
        loading.classList.remove("active");
        end.classList.add("active");
      }
    };
  },
};
</script>
<style scoped>
.contentcenter {
  width: 1164px;
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
  height: 70px;
  width: 100%;
  font-size: 27px;
  margin: 10px;
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
.contentcenter_product {
  width: 229px;
  height: 324px;
  /* height: 100%; */
  margin-top: 15px;
  float: left;
  /* background-color: #fff; */
}
.contentcenter_product > a {
  text-decoration: none;
}
.contentcenter_product:hover {
  border-radius: 12px;
  box-shadow: 5px 5px 30px 10px#E8E8E8;
}
.contentcenter_product_image {
  width: 215px;
  height: 215px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
}
.contentcenter_product_image > img {
  width: 100%;
  height: 100%;
  border-radius: 12px;
}
.contentcenter_product_wenzi {
  width: 205px;
  height: 44px;
  margin: 10px 5px 5px 5px;
  overflow: hidden;
  color: black;
}
.contentcenter_product_price {
  width: 215px;
  height: 33px;
  position: relative;
  margin-top: 10px;
  line-height: 33px;
}
.contentcenter_product_price > span:nth-child(1) {
  color: red;
  font-size: 20px;
}
.contentcenter_product_price > span:nth-child(2) {
  color: #787878;
  font-size: 13px;
  margin-left: 10px;
}
.footer {
  width: 100%;
  height: 30px;
  position: absolute;
  bottom: 0;
}
.loading,
.end {
  width: 100%;
  height: 100%;
  position: relative;
  left: 40%;
  display: none;
}
.loading.active,
.end.active {
  display: block;
}
</style>