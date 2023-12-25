<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/system/userlist"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/system/userlist">
        <el-icon><User /></el-icon>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/home/system/bookselflist">
        <el-icon><Files /></el-icon>
        <span slot="title">书架管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'SystemLeft'
}
</script>

<style scoped>

</style>
