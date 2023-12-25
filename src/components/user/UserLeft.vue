<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/user/borrow"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/user/borrow">
        <el-icon><Calendar /></el-icon>
        <span slot="title">借阅记录</span>
      </el-menu-item>
      <el-menu-item index="/home/user/password">
        <el-icon><Lock /></el-icon>
        <span slot="title">修改密码</span>
      </el-menu-item>
      <el-menu-item index="/home/user/phone">
        <el-icon><Phone /></el-icon>
        <span slot="title">修改联系方式</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'UserLeft'
}
</script>

<style scoped>

</style>
