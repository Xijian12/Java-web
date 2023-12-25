<template>
  <el-container>
    <SystemLeft></SystemLeft>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import SystemLeft from './SystemLeft';
export default {
  name: 'SystemMain',
  components: {SystemLeft},
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
