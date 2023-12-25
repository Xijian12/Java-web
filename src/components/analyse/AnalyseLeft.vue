<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/analyse/book"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/analyse/book">
        <el-icon><Files /></el-icon>
        <span>藏书分析</span>
      </el-menu-item>
      <el-menu-item index="/home/analyse/borrow">
        <el-icon><Notebook /></el-icon>
        <span>借阅分析</span>
      </el-menu-item>
      <el-menu-item index="/home/analyse/user">
        <el-icon><User /></el-icon>
        <span>读者分析</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'AnalyseLeft'
}
</script>

<style scoped>

</style>
