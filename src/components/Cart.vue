<template>
  <div id="cart_content">
    <Header></Header>
    <CartContentTitle></CartContentTitle>
    <CartContentCenter></CartContentCenter>
  </div>
</template>

<script>
import Header from "@/components/Home/Header.vue";
import CartContentTitle from "@/components/Cart/CartContentTitle.vue";
import CartContentCenter from "@/components/Cart/CartContentCenter.vue";
export default {
  name: "Cart",
  data() {
    return {
      data1: [],
    };
  },
  components: {
    CartContentTitle,
    Header,
    CartContentCenter
  },
  created() {
    
  },
};
</script>

<style scoped>
#cart_content {
  background-color: #ffcc99;
  /* min-width: 1700px; */
  height: 100%;
}
</style>