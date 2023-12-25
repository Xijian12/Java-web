<template>
  <el-container>
    <UserLeft></UserLeft>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import UserLeft from "./UserLeft";
export default {
  name: 'UserMain',
  components: {UserLeft},
  props: {
    msg: String
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
