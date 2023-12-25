<template>
  <el-container>
    <el-header>
      <h3>图书管理系统</h3>
      <div class="d-f j-c-s a-i-c">
        <el-menu
           class="el-menu-demo"
           mode="horizontal"
           @select="handleSelect"
           :ellipsis="false"
           background-color="#4a96fd" text-color="#fff" active-text-color="#FFD700"
           style="border-bottom: 0px"
        >
          <el-menu-item v-if="$currentUser.role!=='读者'" index="/home/book" style="font-size: 1.1vw">图书管理</el-menu-item>
          <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/analyse" style="font-size: 1.1vw">智能分析</el-menu-item>
          <el-menu-item index="/home/user" style="font-size: 1.1vw">个人中心</el-menu-item>
          <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/system" style="font-size: 1.1vw">系统管理</el-menu-item>
        </el-menu>
        <div class="d-f a-i-c" style="margin-left: 50px;">
          <el-icon color="#fff"><Avatar /></el-icon>
          <span style="font-size: small;margin-left: 5px;">{{$currentUser.role}}</span>
          <a href="javascript:void(0)" style="margin-left: 10px;" @click="logout"><i class="iconfont icon-tuichu"></i></a>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  methods:{
    handleSelect:function (index){
      this.$router.push(index)
    },
    logout:function (){
      this.$confirm('确定退出系统?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$router.push('/')
      }).catch(() => {
      });
    }
  }
}
</script>
<style scoped>
a{
  display: inline-block;
  text-decoration: none;
  color: #fff;
}
.icon-tuichu:before {
  content: "\e606";
}
.el-header {
  background: #4a96fd;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.el-aside {
  text-align: center;
  height: 100vh;
}
.el-main {
}
</style>
