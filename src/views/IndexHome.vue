<template>
    <div id="home_content">
      <Header></Header>
      <ContentTitle></ContentTitle>
      <ContentCenter1 ></ContentCenter1>
      <ContentCenter2></ContentCenter2>
      <ContentCenter3></ContentCenter3>
      <Footer></Footer>
      <div class="comeToHeader">
        <div class="active">精选推荐</div>
        <div class="cen">反馈</div>
        <div style="border-top: 1px solid gray">客服帮助</div>
        <div class="cen" @click="toTop()">顶部</div>
      </div>
    </div>
  </template>
  
  <script>
  import Header from "@/components/Home/Header.vue";
  import ContentTitle from "@/components/Home/ContentTitle.vue";
  import ContentCenter1 from "@/components/Home/ContentCenter1.vue";
  import ContentCenter2 from "@/components/Home/ContentCenter2.vue";
  import ContentCenter3 from "@/components/Home/ContentCenter3.vue";
  import Footer from "@/components/Home/Footer.vue";
  export default {
    name: "home",
    components: {
      Header,
      ContentTitle,
      Footer,
      ContentCenter1,
      ContentCenter2,
      ContentCenter3,
    },
    mounted: function () {
      this.bindEvent();
    },
  
    methods: {
      enlarge() {
        this.list = document.querySelector(".comeToHeader");
      },
      toTop() {
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
      },
      bindEvent() {
        this.enlarge();
        this.list.addEventListener("mouseover", (e) => {
          e = e || window.event;
          // 处理时间目标兼容
          const target = e.target || e.srcElement;
          // 4-2.切换边框显示
          for (let i = 0; i < this.list.children.length; i++) {
            this.list.children[i].classList.remove("active");
          }
          // 当前点击的是 img,的让父元素有 active
          target.classList.add("active");
        });
        this.list.addEventListener("mouseout", () => {
          for (let i = 0; i < this.list.children.length; i++) {
            this.list.children[i].classList.remove("active");
          }
          // 当前点击的是 img,的让父元素有 active
          this.list.children[0].classList.add("active");
        });
      },
    },
  };
  </script>
  
  <style scoped>
  #home_content {
    background-color: #ffdf99;
    /* min-width: 1700px; */
  }
  .comeToHeader {
    position: fixed;
    right: 15%;
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
  }
  .comeToHeader > div:nth-child(1) {
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
  }
  .comeToHeader > div:nth-child(4) {
    border-bottom-left-radius: 12px;
    border-bottom-right-radius: 12px;
  }
  .comeToHeader > div.active {
    background-color: orangered;
  }
  .cen {
    border-top: 1px solid gray;
    line-height: 50px;
  }
  </style>