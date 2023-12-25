<template>
  <el-container>
    <BookLeft></BookLeft>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import BookLeft from './BookLeft';
export default {
  name: 'BookMain',
  components: {BookLeft},
  props: {
    msg: String
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
