<template>
  <div class="contentTitle">
    <div class="title_search">
    </div>
  </div>
</template>

<script setup>
import router from "@/router"


function ToIndex(){
     router.push("/home")
   }
function findProduct(){
    router.push("/cart")
   }

</script>

<style scoped>
  .contentTitle{
    position: relative;
    width: 1200px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    flex-direction: row;
  }
  .image{
    flex: 3;
    height: 60px;
    /* width: 190px; */
    margin-top: 20px;
  }
  .image > span {
    position: relative;
    top: -40%;
    height: 25px;
    border: 1px solid #000;
 
  }
  .image > .taobao_img{
    height: 100%;
    width: 100px;
    padding-right: 10px;
  }
  .title_search{
    flex: 4;
    margin-top: 35px;
    margin-left: 40px;
  }
  .title_seacrh_input{
    height: 30px;
    width: 320px;
    border: 0px;
    outline:none;
    padding-left: 10px;
    border: 2px solid orangered;
    /* flex: 6; */
  }
  .title_searcch_button1{
    position: relative;
    right: 0;
    height: 30px;
    width: 80px;
    background-color: orangered;
    border: 2px solid orangered;
    color: #fff;
    font-size: 15px;
    cursor: pointer;
  }
  .title_searcch_button2{
    position: relative;
    margin-left: 5px;
    right: 0;
    height: 30px;
    width: 140px;
    background-color: orange;
    border: 1px solid orangered;
    color: orangered;
    font-size: 15px;
    cursor: pointer;
  }
  .title_searcch_button3{
    position: relative;
    margin-left: 5px;
    right: 0;
    height: 30px;
    width: 140px;
    background-color: orange;
    border: 1px solid orangered;
    color: orangered;
    font-size: 15px;
    cursor: pointer;
  }
</style>