<template>
  <div class="contentCenter">
    <div class="guanggao">这是广告</div>
    <div class="category">
      <div class="category_content">
        <a class="category_content_item" style="font-weight: 600">首页</a>
        <a
          class="category_content_item"
          v-for="(item, index) in this.shuju"
          :key="index"
          >{{ item.name }}</a
        >
        <div class="category_content_search">
          <input type="text" /><button>按钮</button>
        </div>
      </div>
    </div>
    <div class="content">
      <div class="box">
        <div class="show">
          <!-- 展示图片 -->
          <!-- <img
            src="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img1_2.jpg"
            alt=""
          /> -->
          <img :src="this.data1.car_image" alt="" />
          <!-- 遮罩层 -->
          <div class="mask"></div>
        </div>

        <div
          class="enlarge"
          :style="{ backgroundImage: 'url(' + this.data1.car_image + ')' }"
        ></div>
        <!-- 展示图片选择 -->
        <div class="list">
          <!-- <img
            src="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img1_1.jpg"
            data-show="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img1_2.jpg"
            data-big="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img1_3.jpg"
            alt=""
          /> -->
          <!-- <p>
            <img
              src="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img2_1.jpg"
              data-show="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img2_2.jpg"
              data-big="https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img2_3.jpg"
              alt=""
            />
          </p> -->
          <p class="active">
            <img
              :src="this.data1.car_image"
              :data-show="this.data1.car_image"
              :data-big="this.data1.car_image"
              alt=""
            />
          </p>
          <p v-if="this.data1.car_image2">
            <img
              :src="this.data1.car_image2"
              :data-show="this.data1.car_image2"
              :data-big="this.data1.car_image2"
              alt=""
            />
          </p>
        </div>
      </div>

      <div class="content_right">
        <div class="content_right_first">
          <div style="font-size: 15px; font-weight: 600">
            {{ this.data1.car_name }}
          </div>
          <div style="font-size: 8px; color: red; margin-top: 10px">
            {{ this.data1.introduce }}
          </div>
          <div class="content_right_first_bg">
            <div>
              京 东 价
              <span style="color: red; font-size: 20px"
                >￥ {{ this.data1.car_price }}w</span
              >
              降价通知
            </div>
            <div>
              优惠券 满
              <span style="color: red; font-size: 15px">{{
                this.data1.car_price * 10000
              }}</span>
              减600
            </div>
            <div>
              促 {{ "\xa0" }} 销 换购 购买1件可优惠换购热销商品 立即换购>>
            </div>
          </div>
          <div style="margin-top: 15px">
            配 送 至 海外泰国 该商品在该地区暂不支持销售支持 可配送海外
          </div>
        </div>
        <div class="content_right_second">
          <!-- 选择颜色 -->
          <div v-if="this.data1.car_color">
            选择颜色
            <span
              class="content_right_second_span choiceColor"
              v-for="(item, index) in this.data1.car_color"
              :key="index"
            >
              {{ item }}
            </span>
          </div>
          <div v-else>
            选择颜色
            <span class="content_right_second_span active"> 黑色 </span>
          </div>
          <!-- 选择型号 -->
          <div v-if="this.data1.car_model">
            选择型号
            <span
              class="content_right_second_span choiceModel"
              v-for="(item, index) in this.data1.car_model"
              :key="index"
            >
              {{ item }}
            </span>
          </div>
          <div v-else>
            选择型号
            <span class="content_right_second_span active"> 顶配 </span>
          </div>
          <!-- 选择付款方式 -->
          <div v-if="this.data1.car_purchase_method">
            选择型号
            <span
              class="content_right_second_span purchaseMethod"
              v-for="(item, index) in this.data1.car_purchase_method"
              :key="index"
            >
              {{ item }}
            </span>
          </div>
          <div v-else>
            选择付款方式
            <span class="content_right_second_span active"> 全款 </span>
          </div>
          <!-- 选择套餐 -->
          <div v-if="this.data1.car_package">
            套 {{ "\xa0\xa0\xa0\xa0" }} 装
            <span
              class="content_right_second_span choicePackage"
              v-for="(item, index) in this.data1.car_package"
              :key="index"
            >
              {{ item }}
            </span>
          </div>
          <div v-else>
            选择付款方式
            <span class="content_right_second_span active"> 全款1 </span>
          </div>
        </div>
        <div class="content_right_third">
          <el-input-number
            style="width: 80px; margin: 20px"
            v-model="num"
            controls-position="right"
            @change="handleChange"
            :min="1"
            :max="10"
          ></el-input-number>
          <button class="content_tight_third_button"
            @click="changeCart()"
          >加入购物车</button>
          <button class="content_tight_third_button">到货通知</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import {nanoid} from 'nanoid'
export default {
  name: "ItemListContentCenter1",
  data() {
    return {
      num: 1,
      shuju: [
        {
          id: 1,
          name: "iphone7",
        },
        {
          id: 1,
          name: "iphone7",
        },
        {
          id: 1,
          name: "iphone7",
        },
        {
          id: 1,
          name: "iphone7",
        },
        {
          id: 1,
          name: "iphone7",
        },
        {
          id: 1,
          name: "iphone7",
        },
        {
          id: 1,
          name: "iphone7",
        },
      ],
      data1: [],
      ObjectNumber:1
    };
  },
  mounted: function () {
    this.overOut(); // 移出遮布罩
    this.move(); // 移动遮布罩
    this.bindEvent(); // 选择想要查看的图片
    this.cColor(); // 选择颜色
    this.cModel(); // 选择颜色
    this.cPurchaseMethod(); // 选择付款方式
    this.cChoicePackage(); // 选择付款方式
  },
  created() {
    this.data1 = this.$route.query;
  },
  methods: {
    handleChange(value) {
      this.ObjectNumber = value
    },
    enlarge() {
      this.ele = document.querySelector(".box");
      this.show = document.querySelector(".show");
      this.mask = document.querySelector(".mask");
      this.enlarge2 = document.querySelector(".enlarge");
      this.list = document.querySelector(".list");
      this.choiceColor = document.querySelectorAll(".choiceColor");
      this.choiceModel = document.querySelectorAll(".choiceModel");
      this.purchaseMethod = document.querySelectorAll(".purchaseMethod");
      this.choicePackage = document.querySelectorAll(".choicePackage");
      this.data1All_ = document.querySelectorAll(".content_right_second_span");
    },
    // 选择选项的包装
    // cCarChoice(ele,acc){
    //  ele[0].classList.add("active")
    //   for(let i = 0;i<ele.length;i++){
    //     ele[i].addEventListener("click",e=>{
    //       e = e || window.event;
    //       const target = e.target || e.srcElement;
    //       console.log(target.innerHTML);
    //       for (let j = 0; j < acc.length; j++) {
    //       ele[j].classList.remove("active");
    //     }
    //       target.classList.add("active");
    //     })
    //   }
    // },
    
    changeCart(){
      if(!sessionStorage.getItem("token")){
        this.$message.warning("添加失败，请先登入账号")
      }else{
        this.$message.success("添加成功")
        let user_id = this.$store.state.personalID
        console.log(this.data1);
        this.$http.post(`users/usercart/`,{
          product_name:this.data1.car_name,
          product_price:this.data1.car_price,
          product_image:this.data1.car_image,
          product_much:this.ObjectNumber || 1,
          introduce:this.data1.introduce,
          user_name:user_id[0].user_id,
          // car_choice:false
        })
        // const todoObj = {
        //   id:nanoid(),
        //   car_name:this.data1.car_name,
        //   car_price:this.data1.car_price,
        //   car_image:this.data1.car_image,
        //   car_number:this.ObjectNumber || 1,
        //   car_choice:false
        // }
        // this.$store.commit('addToCart',this.store_data)
        // this.$store.dispatch('addToCart',todoObj)
        // this.$message.success("添加成功");
      }
    },
    // 选择颜色
    cColor() {
      this.enlarge();
      // this.cCarChoice(this.choiceColor,this.data1.car_color)
      this.choiceColor[0].classList.add("active");
      for (let i = 0; i < this.choiceColor.length; i++) {
        this.choiceColor[i].addEventListener("click", (e) => {
          e = e || window.event;
          const target = e.target || e.srcElement;
          for (let j = 0; j < this.data1.car_color.length; j++) {
            this.choiceColor[j].classList.remove("active");
          }
          target.classList.add("active");
        });
      }
    },
    // 选择配置
    cModel() {
      this.enlarge();
      // this.cCarChoice(this.choiceModel,this.data1.car_model)
      this.choiceModel[0].classList.add("active");
      for (let i = 0; i < this.choiceModel.length; i++) {
        this.choiceModel[i].addEventListener("click", (e) => {
          e = e || window.event;
          const target = e.target || e.srcElement;
          console.log(target.innerHTML);
          for (let j = 0; j < this.data1.car_model.length; j++) {
            this.choiceModel[j].classList.remove("active");
          }
          target.classList.add("active");
        });
      }
    },
    // 选择购买方式
    cPurchaseMethod() {
      this.enlarge();
      // this.cCarChoice(this.purchaseMethod,this.data1.car_purchase_method)
      this.purchaseMethod[0].classList.add("active");
      for (let i = 0; i < this.purchaseMethod.length; i++) {
        this.purchaseMethod[i].addEventListener("click", (e) => {
          e = e || window.event;
          const target = e.target || e.srcElement;
          console.log(target.innerHTML);
          for (let j = 0; j < this.data1.car_purchase_method.length; j++) {
            this.purchaseMethod[j].classList.remove("active");
          }
          target.classList.add("active");
        });
      }
    },
    // 选择套餐
    cChoicePackage() {
      this.enlarge();
      // this.cCarChoice(this.choicePackage,this.data1.car_package)
      this.choicePackage[0].classList.add("active");
      for (let i = 0; i < this.choicePackage.length; i++) {
        this.choicePackage[i].addEventListener("click", (e) => {
          e = e || window.event;
          const target = e.target || e.srcElement;
          console.log(target.innerHTML);
          for (let j = 0; j < this.data1.car_package.length; j++) {
            this.choicePackage[j].classList.remove("active");
          }
          target.classList.add("active");
        });
      }
    },
    overOut() {
      this.enlarge();
      this.show.addEventListener("mouseover", () => {
        this.mask.classList.add("active");
        this.enlarge2.classList.add("active");
      });
      this.show.addEventListener("mouseout", () => {
        this.mask.classList.remove("active");
        this.enlarge2.classList.remove("active");
      });
    },
    // 获取尺寸
    getProp() {
      this.enlarge();
      // 獲取遮罩層尺寸
      this.mask_height = parseInt(window.getComputedStyle(this.mask).height);
      this.mask_width = parseInt(window.getComputedStyle(this.mask).width);
      // console.log(this.mask_height,this.mask_width); 200 200
      // show 盒子尺寸
      this.show_width = this.show.offsetWidth;
      this.show_height = this.show.offsetHeight;
      // console.log(this.show_width); 450
      // console.log(this.show_height); 451
      // 背景图片尺寸
      const bg = window
        .getComputedStyle(this.enlarge2)
        .backgroundSize.split(" ");
      this.bg_width = parseInt(bg[0]); // 800px
      this.bg_height = parseInt(bg[1]); // 800px
    },
    // 调整比例
    setScale() {
      this.enlarge();
      this.getProp();
      // 计算
      this.enlarge_width = (this.mask_width / this.show_width) * this.bg_width;
      this.enlarge_height =
        (this.mask_height / this.show_height) * this.bg_height;
      // 设置
      this.enlarge2.style.width = this.enlarge_width + "px";
      this.enlarge2.style.height = this.enlarge_height + "px";
    },
    // 动起来
    move() {
      this.setScale();
      this.enlarge();
      this.getProp();
      // 给show 盒子绑定一个鼠标移动事件
      this.show.addEventListener("mousemove", (e) => {
        e = e || window.event;
        // 拿坐标
        let x = e.offsetX - 100;
        let y = e.offsetY - 100;

        // 边界值判断
        if (x <= 0) x = 0;
        if (y <= 0) y = 0;
        if (x >= this.show_width - this.mask_width)
          x = this.show_width - this.mask_width;
        if (y >= this.show_height - this.mask_height)
          y = this.show_height - this.mask_height;

        // 3-4.给遮罩层赋值
        this.mask.style.left = x + "px";
        this.mask.style.top = y + "px";

        // 3-5.右边跟着动
        const moveX = (this.enlarge_width * x) / this.mask_width;
        const moveY = (this.enlarge_height * y) / this.mask_height;

        // 3-6.给背景图赋值
        this.enlarge2.style.backgroundPosition = `-${moveX}px -${moveY}px`;
      });
    },
    bindEvent() {
      this.enlarge();
      this.list.addEventListener("mouseover", (e) => {
        e = e || window.event;
        // 处理时间目标兼容
        const target = e.target || e.srcElement;
        if (target.nodeName === "IMG") {
          // 4-2.切换边框显示
          for (let i = 0; i < this.list.children.length; i++) {
            this.list.children[i].classList.remove("active");
          }
          // 当前点击的是 img,的让父元素有 active
          target.parentElement.classList.add("active");

          // 4-3.换图片
          // 拿到当前点击的这个元素的身上的自定义属性
          const showImg = target.dataset.show;
          const enlargeImg = target.dataset.big;

          // 赋值给对应的值
          this.show.firstElementChild.src = showImg;
          this.enlarge2.style.backgroundImage = `url(${enlargeImg})`;
        }
      });
    },
  },
};
</script>

<style scoped>
.guanggao {
  width: 100%;
  height: 150px;
  background-color: paleturquoise;
  font-size: 100px;
  line-height: 150px;
  text-align: center;
}
.category {
  width: 100%;
  height: 30px;
  background-color: #cecece;
}
.category_content {
  position: relative;
  width: 1200px;
  height: 20px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #cecece;
}

.category_content > .category_content_item {
  width: 100%;
  height: 20px;
  margin: 0 10px;
  padding: 0 10px;
  cursor: pointer;
}
.category_content > .category_content_item:hover {
  background-color: #fff;
  color: #000;
  border-radius: 12px;
}
.category_content > .category_content_search {
  position: relative;
  top: -20px;
  display: flex;
  justify-content: flex-end;
}
.category_content > .category_content_search > input {
  border-top-left-radius: 12px;
  border-bottom-left-radius: 12px;
  border: 1px solid #fff;
}
.category_content > .category_content_search > button {
  border-top-right-radius: 12px;
  border-bottom-right-radius: 12px;
  border: 1px solid #000;
  background-color: #000;
  color: #fff;
  width: 40px;
}
.content {
  position: relative;
  width: 1200px;
  height: 100%;
  left: 50%;
  transform: translateX(-50%);
}
.box {
  width: 450px;
  height: 600px;
  border: 1px solid #333;
  margin: 20px;
  position: relative;
  background-color: #fff;
}
.box > .show {
  width: 450px;
  height: 450px;
  border-bottom: 1px solid #333;
  position: relative;
  cursor: none;
}
.box > .show > .mask {
  width: 200px;
  height: 200px;
  background-color: rgba(120, 120, 120, 0.5);
  position: absolute;
  left: 10px;
  top: 10px;
  display: none;

  /* 我不当事件目标 */
  pointer-events: none;
}
.box > .show > .mask.active {
  display: block;
}
.box > .show > img {
  width: 100%;
  height: 100%;
}
.box > .enlarge {
  width: 500px;
  height: 500px;
  border: 1px solid red;
  position: absolute;
  left: 110%;
  top: 0;
  /* background: url('https://zhenghanshu-1304914507.cos.ap-shanghai.myqcloud.com/jd_img1_3.jpg') no-repeat; */
  background-size: 1200px 1200px;
  background-position: 0 0;
  background-color: #fff;
  display: none;
}
.box > .enlarge.active {
  display: block;
  z-index: 2;
}

.box > .list {
  height: 149px;
  padding: 47px 40px;
}

.box > .list > p {
  height: 54px;
  width: 54px;
  float: left;
  margin-right: 20px;
  border: 1px solid transparent;
  cursor: pointer;
}
.box > .list > p > img {
  width: 100%;
  height: 100%;
}

.box > .list > p.active {
  border: 1px solid red;
}

.content_right {
  position: relative;
  width: 700px;
  height: 600px;
  border: 1px solid #000;
  margin-top: -622px;
  margin-left: 500px;
  z-index: 1;
  display: flex;
  flex-direction: column;
}
.content_right_first {
  flex: 3;
  background-color: beige;
  padding: 20px;
  border-bottom: 1px solid #000;
}
.content_right_first_bg {
  margin-top: 8px;
  width: 100%;
  height: 150px;
  background-color: rgba(120, 120, 120, 0.5);
  display: flex;
  flex-direction: column;
}
.content_right_first_bg > div {
  flex: 1;
  line-height: 50px;
  margin-left: 20px;
}
.content_right_second {
  flex: 3;
  background-color: beige;
  border-bottom: 1px solid #000;
  display: flex;
  flex-direction: column;
}
.content_right_second > div {
  flex: 1;
  line-height: 50px;
  margin-left: 20px;
}
.content_right_second_span {
  margin-left: 10px;
  border: 1px solid #000;
  cursor: pointer;
}
.content_right_second_span.active {
  border: 1px solid orangered;
  color: orangered;
}
.content_right_third {
  flex: 1;
  background-color: beige;
}
.content_tight_third_button {
  width: 150px;
  height: 40px;
  margin-right: 20px;
}
</style>