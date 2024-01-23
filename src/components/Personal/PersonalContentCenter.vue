<template>
  <div class="contentTitle">
    <el-container class="container_middle">
        <el-aside width="200px">
            <el-col>
                <h4 style="text-align:center;color:#424242">个人中心</h4>
                 <el-menu default-active="/personal" class="el-menu-vertical-demo" unique-opened :router="true">
                    <el-menu-item index="/home">
                        <el-icon><HomeFilled/></el-icon>
                        <span slot="title">主页</span>
                    </el-menu-item>
                    <el-menu-item index="/personal/personalMyinfo">
                        <el-icon><UserFilled/></el-icon>
                        <span slot="title">个人信息</span>
                    </el-menu-item>
                    <el-sub-menu index="/personal/book">
                        <template #title>
                            <el-icon><Expand/></el-icon>
                            <span>图书管理</span>
                        </template>
                        <el-menu-item-group >
                            <template #title></template>
                            <el-menu-item index="/personal/favorite"><el-icon><StarFilled/></el-icon>我的收藏</el-menu-item>
                            <el-menu-item index="/personal/publishRecord"><el-icon><Collection /></el-icon>下载记录</el-menu-item>
                            <el-menu-item index="/personal/publish"><el-icon><Sell /></el-icon>发布图书</el-menu-item>
                            <el-menu-item index="/personal/bookViewPersonal"><el-icon><Sell /></el-icon>我的图书</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="/personal/material">         
                        <template #title>
                            <el-icon><Expand/></el-icon>
                            <span>资料管理</span>
                        </template>
                        <el-menu-item-group >
                            <template #title></template>
                             <el-menu-item index="/personal/favoriteMaterial"><el-icon><StarFilled/></el-icon>我的收藏</el-menu-item>
                            <el-menu-item index="/personal/publishRecordMaterial"><el-icon><Collection /></el-icon>下载记录</el-menu-item> 
                            <el-menu-item index="/personal/publishMaterial"><el-icon><Sell /></el-icon>发布资料</el-menu-item>
                            <el-menu-item index="/personal/materialViewPersonal"><el-icon><Sell /></el-icon>我的资料</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <!-- <el-sub-menu index="/personal/order">
                        <template #title>
                            <el-icon><ShoppingCart/></el-icon>
                            <span>订单管理</span>
                        </template>
                        <el-menu-item-group>
                            <template #title></template>
                            <el-menu-item index="/personal/buyrecord"><el-icon><Money /></el-icon>购买记录</el-menu-item>
                            <el-menu-item index="/personal/sellrecord"><el-icon><WalletFilled /></el-icon>出售记录</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu> -->
                </el-menu>
            </el-col>
        </el-aside>

        <el-main> 
            <router-view >
            </router-view>
        </el-main>
    </el-container>
  </div>
  <el-dialog
        v-model="FormVisible">
        您未进行登录请前往登录页面！
        <template #footer>
            <span class="dialog-footer">
              <el-button
                type="primary"
                @click="routerToLogin()"
              >
                确定
              </el-button>
            </span>
          </template>

        </el-dialog>
</template>

<script setup>
import router from "@/router"
import {useStore} from 'vuex';
import {ref, onMounted} from 'vue';
const store = useStore();
const email = ref(store.state.personalID[0].email)
// 登录跳转
function routerToLogin(){
  router.push("/")
}
const FormVisible = ref(false);
function init(){
    if (email.value == "session"){
      FormVisible.value = true;
    } 
}
onMounted(() => {
    router.afterEach((to, from, next) => {
        window.scrollTo(0, 800)
    })


});
init();
</script>

<style  lang="less" scoped>
.contentTitle{
    position: relative;
    width: 1200px;
    height:800px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    flex-direction: row;
  }
.container_middle {
        margin-top: 20px;
        height: 100%;
        .el-menu {
            border-right: 0;
        }
        .el-aside {
            background-color: #fff;
        }
        .el-main {
            width: 1000px;
            background-color: rgb(222, 238, 255);
        }
    }
</style>