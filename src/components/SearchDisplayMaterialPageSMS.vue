<template>
  <!--给<router-view>加上key属性 解决重复请求无效问题-->
  <router-view :key="router.path"></router-view>
</template>

<script setup>
import router from "@/router"
</script>

<style scoped>
</style>