<template>
   <div class="newleft toplist">
    <div class="hd">
      <a href="pdf-class.html" class="mydoc"><i class="keke_iconfont">&#xe636;</i>全部文档</a>
      <h2 :class="{ 'active': activeTab === 'recommend' }" @click="changeTab('recommend')">推荐文档</h2>
      <h2 :class="{ 'active': activeTab === 'latest' }" @click="changeTab('latest')">最新上传</h2>
      <span class="active-line" :style="{ transform: `translateX(${activeTab === 'recommend' ? '0' : '40'}px)`, width: '40px' }"></span>
    </div>
    <div class="bd bd1" v-show="activeTab === 'recommend'">
      <ul class="newdoclist">
        <!-- 推荐文档列表内容 -->
      </ul>
    </div>
    <div class="bd bd2" v-show="activeTab === 'latest'">
      <ul class="newdoclist">
        <!-- 最新上传文档列表内容 -->
      </ul>
    </div>
  </div>
  <div class="right_userlist">
    <div class="hd">
      <div class="titCell"></div>
      <h2>会员排行</h2>
    </div>
  </div>
  </template>
  
  <script lang="ts" setup>

  </script>
  
  <style lang="scss">
.bodybox{background:#fff;margin-top:0;padding:0}
.newleft{width:75%;float:left;box-sizing:border-box}
.newleft .hd,.right_userlist .hd{margin-bottom:20px;overflow:hidden;padding-bottom:15px;position:relative;padding-left:20px}
.newleft .hd{position:relative;padding-left:0}
.newleft .hd:after{top:42px;width:100%;height:1px;position:absolute;right:20px;background-color:#eee;content:''}
.right_userlist .hd:after{top:42px;width:100%;height:1px;position:absolute;left:0;background-color:#eee;content:''}
.newleft .hd h2{cursor:pointer;display:inline-block;margin-right:25px;font-size:16px;color:#666}
.newleft .hd h2.active,.right_userlist .hd h2{font-weight:800;font-size:20px;color:#333}
.newleft .hd h2 span,.right_userlist .hd h2 span{font-weight:400;font-size:14px;margin-left:15px;color:#999}
.newleft .hd a.mydoc{float:right;margin:5px 20px 0 0;color:#313131;font-size:13px}
.right_userlist .hd:before{border-radius:0 10px 10px 0;top:7px;width:5px;height:15px;position:absolute;left:0;background-color:#0057ff;content:''}
.active-line{position:absolute;left:0;bottom:2px;width:5px;border-radius:0;height:4px;background:#0057ff;z-index:1;-webkit-transition:all .3s linear;transition:all .3s linear}
.newleft .bd ul li{line-height:35px;float:left;width:50%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
.newleft .bd ul li span{float:right;color:#999;margin:0 20px}
.newleft .bd ul li a{font-size:14px;color:#000;white-space:nowrap;font-weight:400;overflow:hidden;text-overflow:ellipsis}
.newleft .bd .keke_iconfont{font-size:16px;margin-right:7px}
.right_userlist{width:25%;float:right;box-sizing:border-box;padding-left:25px;position:relative}
.right_userlist:before{top:78px;width:1px;height:228px;position:absolute;left:2px;background-color:#f3f3f3;content:''}
.right_userlist .hd .titCell{float:right;margin-top:13px}
.right_userlist .hd .titCell li{font-size:0;display:inline-block;margin:0 3px;border-radius:50%;width:7px;height:7px;text-align:center;background:#ccc;cursor:pointer}
.right_userlist .hd .titCell li.on{background:red;color:#fff;width:15px;border-radius:5px}
.right_userlist .bd{height:260px;overflow:hidden}
.newdoclist .ranking_num{margin-right:15px;float:left;font-size:18px}
.newdoclist li a img{width:23px;height:23px;border-radius:30px;display:inline-block;vertical-align:top;margin:5px 10px 0 0}
.newdoclist li{overflow:hidden;line-height:37px;height:37px}

  </style>
  