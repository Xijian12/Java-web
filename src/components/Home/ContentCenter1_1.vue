<template>
  <div class="web-module">
        <ul class="keke_doc_clearfix">  
           <div class="module_subbox">
              <a href="/SearchDisplay/1"   class="keke_doc_clearfix">                    
                <div class="lefticon">
                <img src="@/assets/images/181200ix6weosezwysgwvw.png" /></div>
                <div class="web-module-right">
                  <div class="righthd">全部图书</div>
                  <div class="rightbd">海量图书 供你查阅</div>
                </div>
              </a>                
            </div>           
            <div class="module_subbox">
              <a href="https://www.pdfdz.com/plugin.php?id=keke_group"   class="keke_doc_clearfix">                    
                <div class="lefticon">
                <img src="@\assets\images\181821ze01cp13x4w4poc7.png" /></div>
                <div class="web-module-right">
                  <div class="righthd">加入VIP</div>
                  <div class="rightbd">会员特权 畅快阅读</div>         
                </div>
              </a>                
            </div>
            <div class="module_subbox">
              <a href="/SearchDisplay/1"  class="keke_doc_clearfix">                    
                <div class="lefticon">
                <img src="@/assets/images/181710yrarppyvdy4dpuc8.png" /></div>
                <div class="web-module-right">
                  <div class="righthd">最新上架</div>
                </div>
              </a>                
            </div>
            <div class="module_subbox">
              <a href="/personal/publishRecord"  class="keke_doc_clearfix">                    
                <div class="lefticon">
                <img src="@/assets/images/182122wipvnkjvjqjk1zf7.png" /></div>
                  <div class="web-module-right">
                    <div class="righthd">我的下载</div>
                    <div class="rightbd">下载知识 就是力量</div>
                </div>
               </a>                
              </div>
              <div class="module_subbox">
                <a href="/personal/favorite"   class="keke_doc_clearfix">                    
                  <div class="lefticon">
                  <img src="@/assets/images/182300pogbsbsg6el1pexe.png" /></div>
                    <div class="web-module-right">
                      <div class="righthd">我的收藏</div>
                      <div class="rightbd">收藏知识 收藏未来</div>
                  </div>
                </a>                
              </div>
            </ul>
    </div>

</template>

<script setup>

</script>

<style scoped>
.module_subbox {
  width:240px;
  display: flex; /* 使用 Flex 布局 */
  align-items: center; /* 纵向居中内容 */
  
}
.module_subbox:hover {
  transform: translateY(-5px); /* 上升 5 像素，根据需要调整距离 */
}
.web-module{
  position: relative;
  width: 1200px;
  height: 100px;
  /* background-color: orangered; */
  left: 50%;
  transform: translate(-50%);
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  background-color:#ffff;
  border-radius: 12px;

}
.web-module-right {
  display: flex;
  flex-direction: column; /* 垂直排列文字 */
  justify-content: center; /* 纵向居中文字 */
}
.lefticon {
  margin-right: 10px; /* 根据需要调整图片和文字之间的间距 */
}
.righthd,
.rightbd {
  margin: 0; /* 重置默认的 margin */
}
.keke_doc_clearfix {
    zoom: 1;
    display: flex;
}

</style>