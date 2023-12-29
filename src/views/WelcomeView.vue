<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1">
            <el-image style="width: 100%;height: 100%" fit="cover"
                      src="src\assets\images\background.jpeg"/>
        </div>
        <div class="welcome-title">
            <div style="font-size: 70px;font-weight: bold">欢迎来到小SHU书</div>
            <div style="font-size: 70px;font-weight: bold">资料共享平台</div>
        </div> 
        <div class="right-card">
             <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear" mode="out-in">
                    <component :is="Component" style="height: 100%"/>
                </transition>
            </router-view>
         </div>          
    </div>
</template>

<script setup>

</script>

<style scoped>
.welcome-title {
    position: absolute;
    bottom: 50%;
    left: 20%;
    margin: 0 auto;
    color: white;
    text-shadow: 0 0 10px black;
    text-align: center;
}
.right-card {
  width: 400px;
  float: right;
  background-color: var(--el-bg-color);
}
</style>
