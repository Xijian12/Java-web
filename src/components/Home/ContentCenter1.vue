<template>
  <div class="contentCenter">
    <div class="contentCenter_Left">
      <div class="contentCenter_Left_menu">
          <div class="contentCenter_Left_menu_title">主题市场</div>
          <div
            class="contentCenter_Left_menu_theme"
            v-for="(item, index) in theme"
            :key="index"
            @mouseover="yiru"
            @mouseout="yichu"
          >
            <div class="contentCenter_Left_menu_char">{{ item.theme }}</div>
            <div class="contentCenter_Left_menu_fuhao">></div>
          </div>
        </div>
      <div class="contentCenter_Left_item">
        <div class="contentCenter_Left_item_title">
          <a href="">天猫</a>
          <a href="">聚划算</a>
        </div>
        <div v-show="guanbi" class="fugai">
          <div class="fugai_left">
            <div class="fugai_left_product">
                <div class="fugai_left_title">
                住宅家具
              </div>
              <div class="fugai_left_item" >
                <a href="" v-for="(item,index) in chufangdianqi" :key="index">
                  {{item.name}}
                </a>
              </div>
            </div>
            <div class="fugai_left_product">
                <div class="fugai_left_title">
                住宅家具
              </div>
              <div class="fugai_left_item" >
                <a href="" v-for="(item,index) in chufangdianqi" :key="index">
                  {{item.name}}
                </a>
              </div>
            </div>
            <div class="fugai_left_product">
                <div class="fugai_left_title">
                住宅家具
              </div>
              <div class="fugai_left_item" >
                <a href="" v-for="(item,index) in chufangdianqi" :key="index">
                  {{item.name}}
                </a>
              </div>
            </div>
            
          
            
          </div>
          <div class="fugai_right">
            <div>猜你喜欢</div>
            <div class="fugai_right_product" v-for="(item,index) in 6" :key="index">
                <img src="https://gw.alicdn.com/imgextra/i1/O1CN01KgTkZ526XpIWPjIJy_!!6000000007672-0-tps-430-430.jpg_110x10000Q75.jpg" alt="">
                <span>真便宜{{item}}</span>
            </div>
          </div>
        </div>
        <div v-show="!guanbi" class="contentCenter_Left_item_content">
          <div class="contentCenter_Left_item_content_top">
            <div class="huandengpian">
              <el-carousel trigger="click">
                <el-carousel-item v-for="item in 4" :key="item">
                  <h3 class="small">{{ item }}</h3>
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
          <div class="contentCenter_Left_item_content_bottom">
            <div class="contentCenter_Left_item_content_bottom_image1">
              <div class="wenzi">
                <div class="round"></div>
                <div>聚划算</div>
              </div>
              <div class="image">
                <img src="../../assets/img/image1.png" alt="" />
              </div>
            </div>
            <div class="contentCenter_Left_item_content_bottom_image1">
              <div class="wenzi">
                <div class="round round2"></div>
                <div>有好货</div>
              </div>
              <div class="image">
                <img src="../../assets/img/image2.png" alt="" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="contentCenter_Right" >
      <div class="contentCenter_Right_top" v-if="!this.arr">
        <div class="touxiang">
          <div class="image">
            <img src="../../assets/img/taobaokefu.jpg" alt="" />
            <div>Hi,你好~~</div>
            <div class="jishanshan">领淘币抵钱</div>
          </div>
        </div>
        <div class="login">
          <div><button class="button1" @click="loginBtn()">登录</button></div>
          <div><button class="button2">注册领红包</button></div>
        </div>
      </div>
      <div class="contentCenter_Right_top" v-else>
        <div class="touxiang">
          <div class="image">
            <img :src="this.arr.img" alt="" style="width:80px;height:80px;border:1px solid #000;"/>
            <div>Hi,你好~~</div>
            <div class="jishanshan">{{this.arr.user_name}}</div>
            <button @click="findPaid()" style="width:120px;margin-left:-20px;">点击查看购买记录</button>
          </div>
        </div>
      </div>
      <div class="contentCenter_Right_center">专属会员服务</div>
      <div class="contentCenter_Right_bottom">
        <div
          class="contentCenter_Right_bottom_item"
          v-for="(item, index) in login"
          :key="index"
        >
          <img src="../../assets/img/login_image1.png" alt="" />
          <div class="wenzi">{{ item.title }}</div>
          <div class="fuhao">></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ContentCenter1",
  props:["abs"],
  data() {
    return {
      theme: [
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
        { id: 1, theme: "居家生活" },
      ],
      login: [
        {
          id: 1,
          image: "../../assets/img/login_image1.png",
          title: "官方物流寄送至全球十地",
        },
        {
          id: 2,
          image: "../../assets/img/login_image1.png",
          title: "官方物流寄送至全球十地",
        },
        {
          id: 3,
          image: "../../assets/img/login_image1.png",
          title: "官方物流寄送至全球十地",
        },
        {
          id: 4,
          image: "../../assets/img/login_image1.png",
          title: "官方物流寄送至全球十地",
        },
      ],
      guanbi:false,
      chufangdianqi:[
        {id:1,name:"上处置累累设置别"},
        {id:1,name:"净水器"},
        {id:1,name:"空气炸锅"},
        {id:1,name:"删除加热类设备"},
        {id:1,name:"删除视频机械"},
        {id:1,name:"商用出点"},
        {id:1,name:"厨房加点配件"},
        {id:1,name:"咖啡机"},
        {id:1,name:"咖啡机大活佛爱好的符号看"},
      ]
    };
  },
  computed: {},
  methods: {
    yiru() {
      this.guanbi = !this.guanbi
    },
    yichu() {
      this.guanbi = !this.guanbi
    },
    loginBtn(){
      this.$router.push({name:"login"})
    },
    findPaid(){
      this.$router.push({name:"personal"})
    }
  },
  created:function(){
    this.arr = this.$store.state.personalID[0]
  },
};
</script>

<style scoped>
.fugai {
  width: 100%;
  height: 520px;
  display: flex;
  flex-direction: row;
  border: 2px solid red;
  border-radius: 12px;
}
.fugai > .fugai_left{
  flex: 4;
  padding: 20px;
  margin-right: 1px;
  display: flex;
  flex-direction: column;
  align-content: space-between;
  overflow: auto;
  border-right: 2px solid #d3dce6;
}
.fugai > .fugai_left > .fugai_left_product{
  width: 100%;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;

}
.fugai > .fugai_left > .fugai_left_product > .fugai_left_title{
  font-size: 20px;
  font-weight: 600;
}
.fugai > .fugai_left > .fugai_left_product > .fugai_left_item{
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.fugai > .fugai_left > .fugai_left_product > .fugai_left_item > a {
  margin-top: 2px;
  margin-right: 20px;
  text-decoration: none;
  color: #a0a0a0;
}
.fugai > .fugai_right{
  flex: 3;
  padding: 20px;
}
.fugai > .fugai_right > div{
  font-size: 20px;
  font-weight: 600;
}
.fugai > .fugai_right > .fugai_right_product {
  margin-top: 20px;
  width: 50%;
  height: 131px;
  background-color: #fff;
  float: left;
}
.fugai > .fugai_right > .fugai_right_product > img{
  width: 100%;
  height: 110px;
}
.fugai > .fugai_right > .fugai_right_product > span {
  position: relative;
  left: 30%;
}
.contentCenter {
  position: relative;
  width: 1200px;
  height: 590px;
  /* background-color: orangered; */
  left: 50%;
  transform: translate(-50%);
  margin-top: 20px;
  display: flex;
  flex-direction: row;
}
.contentCenter_Left {
  flex: 3;
  height: 100%;
  margin: 0 20px 0 0;
  /* background-color: palegreen; */
  border-radius: 20px;
  display: flex;
  flex-direction: row;
}
.contentCenter_Left_menu {
  flex: 1;
  height: 100%;
  background-color: #fff;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
}
.contentCenter_Left_menu_title {
  font-size: 20px;
  text-align: center;
  line-height: 50px;
  /* color: orangered; */
  font-weight: 700;
}
.contentCenter_Left_menu_theme {
  width: 100%;
  height: 40px;
  /* background-color: red; */
  display: flex;
  flex-direction: row;
  line-height: 40px;
}
.contentCenter_Left_menu_theme:hover {
  background-color: tomato;
}
.contentCenter_Left_menu_char {
  flex: 9;
  margin-left: 10px;
}
.contentCenter_Left_menu_fuhao {
  flex: 1;
  color: #a0a0a0;
}
.contentCenter_Left_item {
  flex: 4;
  height: 100%;
  background-color: #fff;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}
.contentCenter_Left_item_title {
  font-size: 15px;
  line-height: 50px;
  font-weight: 700;
}
.contentCenter_Left_item_title > a {
  color: red;
  margin-left: 30px;
  text-decoration: none;
}
.contentCenter_Left_item_content {
  width: 100%;
  height: 520px;
  display: flex;
  flex-direction: column;
}
.contentCenter_Left_item_content_top {
  flex: 1;
  /* background-color: pink; */
}
.contentCenter_Left_item_content_top > .huandengpian {
  width: 660px;
  /* height: 100%; */
  height: 250px;
}
.el-carousel {
  overflow: hidden;
  height: 100%;
  width: 100%;
  margin-left: 20px;
  border-radius: 15px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 140px;
  opacity: 0.75;
  line-height: 250px;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.contentCenter_Left_item_content_top > .huandengpian > ol {
  width: 170px;
  height: 30px;
  position: relative;
  left: 50%;
  top: -15%;
  transform: translateX(-50%);
  margin-left: 20px;
  background-color: rgba(0, 0, 0, 0.5);
  border-radius: 30px;
}
.contentCenter_Left_item_content_top > .huandengpian > ol > li {
  width: 15px;
  height: 15px;
  margin-left: 10px;
  margin-right: 8px;
  float: left;
  border-radius: 15px;
  background-color: #fff;
  margin-top: 8px;
  cursor: pointer;
}
.contentCenter_Left_item_content_top > .huandengpian > ol > li.active {
  background-color: orange;
}
.contentCenter_Left_item_content_bottom {
  flex: 1;
  /* background-color: rosybrown; */
  display: flex;
  flex-direction: row;
}
.contentCenter_Left_item_content_bottom_image1 {
  margin-left: 20px;
}
.contentCenter_Left_item_content_bottom_image1 > .wenzi {
  /* background-color: sandybrown; */
  height: 30px;
  line-height: 30px;
  display: flex;
  flex-direction: row;
}
.contentCenter_Left_item_content_bottom_image1 > .wenzi > .round {
  width: 20px;
  height: 20px;
  background-color: red;
  border-radius: 20px;
  margin: 5px;
}
.contentCenter_Left_item_content_bottom_image1 > .wenzi > .round2 {
  background-color: skyblue;
}
.contentCenter_Left_item_content_bottom_image1 > .image {
  width: 100%;
  height: 200px;
}
.contentCenter_Left_item_content_bottom_image1 > .image > img {
  width: 100%;
  height: 100%;
  border-radius: 20px;
}
.contentCenter_Right {
  flex: 1;
  height: 100%;
  background-color: #fff;
  border-radius: 30px;
  display: flex;
  flex-direction: column;
}
.contentCenter_Right_top {
  flex: 3;
  /* background-color: skyblue; */
  display: flex;
  flex-direction: column;
}
.contentCenter_Right_top > .touxiang {
  flex: 3;
  /* background-color: turquoise; */
}
.contentCenter_Right_top > .touxiang > .image {
  width: 80px;
  height: 100%;
  /* background-color: thistle; */
  position: relative;
  left: 50%;
  transform: translate(-50%);
  /* border-radius: 60px; */
}
.contentCenter_Right_top > .touxiang > .image > img {
  position: relative;
  margin-top: 30px;
  left: 50%;
  transform: translate(-50%);
  width: 50px;
  height: 50px;
  border-radius: 50px;
}
.contentCenter_Right_top > .touxiang > .image > div {
  width: 100%;
  font-size: 10px;
  text-align: center;
}
.contentCenter_Right_top > .touxiang > .image > .jishanshan {
  font-size: 15px;
  color: orangered;
}
.contentCenter_Right_top > .login {
  flex: 1;
  /* background-color: yellowgreen; */
  display: flex;
  flex-direction: row;
}
.contentCenter_Right_top > .login > div {
  /* background-color: violet; */
  flex: 1;
  height: 80%;
  display: flex;
  justify-items: center;
  margin-left: 20px;
  border-radius: 5px;
}
.contentCenter_Right_top > .login > div > .button1 {
  background: orangered;
  color: #fff;
  border: none;
  padding: 0 20px;
  border-radius: 5px;
}
.contentCenter_Right_top > .login > div > .button2 {
  border: 1px solid orangered;
  color: orangered;
  background-color: #fff;
  padding: 0 20px;
}
.contentCenter_Right_center {
  flex: 1;
  /* background-color: springgreen; */
  font-size: 20px;
  font-weight: 700;
  line-height: 50px;
  margin-left: 10px;
}
.contentCenter_Right_bottom {
  flex: 6;
  /* background-color: tomato; */
  display: flex;
  flex-direction: column;
}
.contentCenter_Right_bottom_item {
  /* background-color: aqua; */
  width: 100%;
  height: 60px;
  display: flex;
  flex-direction: row;
  margin-top: 20px;
}
.contentCenter_Right_bottom_item > img {
  flex: 2;
}
.contentCenter_Right_bottom_item > .wenzi {
  flex: 6;
  font-size: 10px;
  line-height: 60px;
  margin-left: 10px;
}
.contentCenter_Right_bottom_item > .fuhao {
  flex: 1;
  line-height: 60px;
}
</style>