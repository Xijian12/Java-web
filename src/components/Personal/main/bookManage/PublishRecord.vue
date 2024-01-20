<template>
    <div>
        <div>
            <el-table :data="recordsList" style="width: 100%" empty-text="没有数据">
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
              <el-table-column prop="bookAuthor" label="图书作者"/> 
              <el-table-column prop="username" label="上传者" />
              <el-table-column prop="createTime" label="下载时间" >
                <template #default="scope">
                 {{ formatDate(scope.row.createTime) }}
                </template>
                </el-table-column>
              <el-table-column fixed="right" label="操作">
                <template #default="books">
                  <el-button @click="deleteBookDialog(books.row.downloadId)" type="text"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
        </div>
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
    </div>
</template>
    
<script setup>
import {ref, onMounted} from 'vue'
import { useStore } from 'vuex';
import axios from "axios";
import {ElMessage} from "element-plus";
const pageNum = ref(1);
const pageSize = ref(10);
const total = ref();
const store = useStore();
const email = ref(store.state.personalID[0].email)
const recordsList = ref([]);
function initData() {
  axios.get("/book/downloadRecord",{params:{
        page:pageNum.value,
        pageSize:pageSize.value,
        userEmail:email.value,
    }}).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.message
    recordsList.value = resp.data.data.items;
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
   
};
const Nindex = (index) => {
      // 当前页数 - 1 * 每页数据条数 + 1
      const page = pageNum.value // 当前页码
      const pagesize = pageSize.value // 每页条数
      return index + 1 + (page - 1) * pagesize
}
// 格式化日期
function formatDate(time){
      let date = new Date(time);
      let year = date.getFullYear();
      let month = date.getMonth()+1;
      let day = date.getDate();
      return year+"-"+month+"-"+day;
}
function deleteBookDialog(downloadId) {
  axios.delete("/book/downloadRecord",{params:{
    downloadId:downloadId,
    }}).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.msg
    if(code == 0){
      ElMessage({
          message: message,
          type: "success",
        });
        initData()
    }
    if (code == 400) {
        ElMessage({
          message: message,
          type: "error",
        });
    }
    })
}
onMounted(() => {
  initData();
});
function pagechange(val) {
  pageNum.value = val;
  initData();
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
  
  .dialog_btn {
    margin-left: 25%;
  }
</style>