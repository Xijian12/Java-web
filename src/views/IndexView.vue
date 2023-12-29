<template>
  <div class="header-wrap">
            <div class="header">
                <div class="header-right">
                    大辣椒粉辣椒粉离开家
                    <span>
                        12412414141
                    </span>
                    <a href="#">请登录</a>
                    <a href="#">注册</a>
                </div>
            </div>
        </div> 
        <div class="nav">
            
        </div>
  <div>
    <el-button @click="userLogout">退出登录</el-button>
  </div>
</template>

<script setup>
import { logout } from '@/net'
import router from "@/router";

function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style scoped>
/* header */
.header-wrap{
    height: 45px;
    background-color: #333;
}
.header{
    width: 1200px;
    margin: 0 auto;
}
.header-right{
    float: right;
    height: 45px;
    line-height: 45px;

}
.header span{
    color: #fff;
    font-size: 20px;
    margin-right: 40px;
}
.header a{
    color: #fff;
    font-size: 14px;
    margin-right: 25px;
}


</style>
