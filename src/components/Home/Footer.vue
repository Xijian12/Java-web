<template>
 <div class="footer">
      <div class="container">
        <div class="footer-main">
          <h4>ElementUI</h4>
          <a href="https://element.eleme.cn/#/zh-CN" target="_blank" class="footer-main-link">官方网站</a>
          <a href="https://github.com/ElemeFE/element" target="_blank" class="footer-main-link">代码仓库</a>
          <a href="https://github.com/ElemeFE/element/releases" target="_blank" class="footer-main-link">更新日志</a>
          <a href="https://github.com/ElemeFE/element/blob/dev/FAQ.md" target="_blank" class="footer-main-link">常见问题</a>
          <a href="https://gitter.im/ElemeFE/element" target="_blank" class="footer-main-link">在线讨论</a>
          <a href="https://github.com/ElemeFE/element/blob/master/.github/CONTRIBUTING.zh-CN.md" target="_blank"
             class="footer-main-link">贡献指南</a>
        </div>
        <div class="footer-main">
          <h4>Vue</h4>
          <a href="https://v3.cn.vuejs.org/" target="_blank" class="footer-main-link">官方网站</a>
          <a href="https://v3.cn.vuejs.org/guide/introduction.html" target="_blank" class="footer-main-link">文档</a>
          <a href="https://v3.cn.vuejs.org/api/" target="_blank" class="footer-main-link">API参考</a>
          <a href="https://next.router.vuejs.org/" target="_blank" class="footer-main-link">Vue Router</a>
          <a href="https://cli.vuejs.org/" target="_blank" class="footer-main-link">Vue CLI</a>
        </div>
        <div class="footer-main">
          <h4>Github</h4>
          <a href="https://github.com" target="_blank" class="footer-main-link">Github社区</a>
          <a href="https://github.com/Xijian12/Java-web" target="_blank"
             class="footer-main-link">在Github上查看项目源码</a>
        </div>
      </div>
 </div>
</template>

<script setup>
</script>

<style>

.footer {
  background-color: #f7fbfd;
  width: 100%;
  padding: 40px 150px;
  margin-top: 20px;
  box-sizing: border-box;
  height: 300px;
}

.footer .container {
  margin-left: 150px;
  box-sizing: border-box;
  width:1200px;
}

.footer .footer-main {
  font-size: 0;
  display: inline-block;
  vertical-align: top;
  width: 400px;
}

.footer .footer-main h4 {
  font-size: 18px;
  color: #333;
  line-height: 1;
  margin: 0 0 15px;
}

.footer .footer-main .footer-main-link {
  display: block;
  margin: 0;
  line-height: 2;
  font-size: 14px;
  color: #666;
  text-decoration: none;
}

.footer .footer-main .footer-main-link:hover {
  color: #222222;
}

.footer .footer-social {
  float: right;
  text-align: right;
}

.footer .footer-social .footer-social-title {
  color: #666;
  font-size: 18px;
  line-height: 1;
  margin: 0 0 20px;
  padding: 0;
  font-weight: 700;
}

</style>