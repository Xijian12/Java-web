<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/book"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/book">
        <el-icon><Files /></el-icon>
        <span>藏书管理</span>
      </el-menu-item>
      <el-menu-item index="/home/book/borrowlist">
        <el-icon><Notebook /></el-icon>
        <span>借阅管理</span>
      </el-menu-item>
      <el-menu-item index="/home/book/typelist">
        <el-icon><CopyDocument /></el-icon>
        <span>分类管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'BookLeft'
}
</script>

<style scoped>

</style>
