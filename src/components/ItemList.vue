<template>
  <div id="itemList_content">
    <Header></Header>
    <ItemListContentTitle></ItemListContentTitle>
    <ItemListContentCenter1></ItemListContentCenter1>
    <ItemListFooter :product="this.product"></ItemListFooter>
  </div>
</template>

<script>
import Header from "@/components/Home/Header.vue";
import ItemListContentTitle from "@/components/ItemList/ItemListContentTitle.vue";
import ItemListContentCenter1 from "@/components/ItemList/ItemListContentCenter1.vue";
import ItemListFooter from "@/components/ItemList/ItemListFooter.vue";
export default {
  name: "ItemList",
  components: {
    Header,
    ItemListContentTitle,
    ItemListContentCenter1,
    ItemListFooter
  },
  data(){
    return{
      product:[]
    }
  },
  created(){
    this.product = this.$route.query;
  }
};
</script>

<style scoped>
#itemList_content {
  background-color: #ffcc99;
  /* min-width: 1700px; */
  height: 100%;
}
</style>