<template>
  <el-container>
    <AnalyseLeft></AnalyseLeft>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import AnalyseLeft from './AnalyseLeft';
export default {
  name: 'AnalyseMain',
  components: {AnalyseLeft},
  props: {
    msg: String
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
