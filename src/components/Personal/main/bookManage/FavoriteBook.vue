<template>
    <el-table :data="favoritesList" style="width: 100%">
        <el-table-column type="index" :index="Nindex" label="序号" width="70px"/>
        <el-table-column prop="bookCoverUrl" label="图书封面">
                <template #default="scope">
                  <el-image
                    :src="scope.row.bookCoverUrl"
                    style="width: 70px; height: 70px;"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="bookName" label="图书名称" >
              <template v-slot="{ row }">
                  <router-link
                    :to="`/BookDetail/${row.bookId}`"
                  >
                    {{ row.bookName }}
                  </router-link>
              </template>
              </el-table-column>
        <el-table-column label="上传者" prop="username">
        </el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-button size="medium" type="danger" @click="handleDelete(scope.row.collectId)">取消收藏</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div id="pageBtn">
              <el-pagination
                  background
                  v-model:current-page="pageNum"
                  v-model:page-size="pageSize"
                  layout="prev, pager, next"
                  :total="total"
                  @current-change="pagechange">
              </el-pagination>
            </div>
  </template>
    
<script setup>
import {ref, onMounted} from 'vue';
import {useStore} from 'vuex';
import {ElMessage} from "element-plus";
import axios from "axios";
const pageNum = ref(1);
const pageSize = ref(10);
const total = ref();
const store = useStore();
const email = ref(store.state.personalID[0].email)
const favoritesList = ref([])
const Nindex = (index) => {
      // 当前页数 - 1 * 每页数据条数 + 1
      const page = pageNum.value // 当前页码
      const pagesize = pageSize.value // 每页条数
      return index + 1 + (page - 1) * pagesize
}

function getFavoritesList() {
    axios.get("/book/collectRecord",{params:{
        page:pageNum.value,
        pageSize:pageSize.value,
        userEmail:email.value,
    }}).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.message
    favoritesList.value = resp.data.data.items;
    total.value = resp.data.data.total
    if(code == 200){
      ElMessage({
          message: message,
          type: "success",
        });
    }
    if (code == 400) {
        ElMessage({
          message: message,
          type: "error",
        });
    }
    })
}
function handleDelete(collectId) {
    axios.delete("/book/collectRecord",{params:{
        collectId:collectId,
    }}).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.msg
    if(code == 0){
      ElMessage({
          message: message,
          type: "success",
        });
    getFavoritesList()
    }
    if (code == 400) {
        ElMessage({
          message: message,
          type: "error",
        });
    }
    })
            
}
// 在组件挂载时执行查询图书的函数
onMounted( () => {
  getFavoritesList();
  // 在这里可以执行其他操作，确保 initData 函数执行完成后再执行
});
// 翻页功能
function pagechange(val) {
  pageNum.value = val;
  getFavoritesList();
}

  </script>
    
  <style lang="less" scoped>
  .table-expand {
    font-size: 0;
  }
  
  .table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  
  .table-expand .el-form-item {
    padding-left: 50px;
    margin-right: 0;
    margin-bottom: 0;
    width: 30%;
  }
  #pageBtn {
  float: right;  /* 使用 float: right; 将元素靠右 */
  display: table;
}
  </style>