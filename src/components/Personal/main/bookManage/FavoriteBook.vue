<template>
    <el-table :data="favoritesList" style="width: 100%">
        <el-table-column type="expand">
            <template slot-scope="props">
                <el-form label-position="left" inline class="table-expand">
                    <el-form-item label="收藏记录ID：">
                        <span>{{ props.row.favoritesId }}</span>
                    </el-form-item>
                    <el-form-item label="图书ID：">
                        <span>{{ props.row.book.bookId }}</span>
                    </el-form-item>
                    <el-form-item label="卖家：">
                        <span>{{ props.row.book.seller.sellerName }}</span>
                    </el-form-item>
                    <el-form-item label="书名：">
                        <span>{{ props.row.book.bookName }}</span>
                    </el-form-item>
                    <el-form-item label="ISBN号：">
                        <span>{{ props.row.book.bookIsbn }}</span>
                    </el-form-item>
                    <el-form-item label="分类：">
                        <span>{{ props.row.book.category.categoryName }}</span>
                    </el-form-item>
                    <el-form-item label="价格：">
                        <span>{{ props.row.book.bookPrice }}</span>
                    </el-form-item>
                    <el-form-item label="描述：">
                        <span>{{ props.row.book.bookOutline }}</span>
                    </el-form-item>
                </el-form>
            </template>
        </el-table-column>
        <el-table-column label="收藏记录ID" prop="favoritesId">
        </el-table-column>
        <el-table-column label="图书ID" prop="book.bookId">
        </el-table-column>
        <el-table-column label="书名" prop="book.bookName">
        </el-table-column>
        <el-table-column label="卖家" prop="book.seller.sellerName">
        </el-table-column>
        <el-table-column label="图书状态" prop="book.bookRelease">
            <template slot-scope="scope">
                <el-tag v-if="scope.row.book.bookRelease == 3" type="info" effect="dark">已下架</el-tag>
                <el-tag v-else effect="dark">上架中</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button size="medium" type="danger" @click="handleDelete(scope.row.favoritesId)">取消收藏</el-button>
            </template>
        </el-table-column>
    </el-table>
  </template>
    
  <script setup>
  import {ref} from 'vue'
  const favoritesList = ref([])
  const userId = ref('')
    // created() {
    //     this.userId = this.cookie.getCookie("LoginId");
    //     this.getFavoritesList();
    // },
  function getFavoritesList() {
            this.$http({
                method: 'get',
                url: '/favoritesList',
                params: {
                    userId: this.userId
                }
            })
                .then(res => {
                    this.favoritesList = res.data.result;
                    if (res.data.errcode != "0") {
                        return this.$notify({
                            title: '警告',
                            message: res.data.errmsg,
                            type: 'warning'
                        });
                    } else {
                        this.$notify({
                            title: '成功',
                            message: res.data.errmsg,
                            type: 'success'
                        });
                    }
                    console.log("this.favoritesList",this.favoritesList);
                })
        }
        function handleDelete(favoritesId) {
            this.$http({
                method: 'delete',
                url: '/deleteFavorite',
                params: {
                    favoritesId: favoritesId
                }
            })
                .then(res => {
                    console.log("res.data:",res.data);
                    if (res.data.errcode != "0") {
                        console.log(res.data.errcode);
                        this.$message.error(res.data.errmsg);
                    } else {
                        console.log(res.data.errcode);
                        this.$message.success(res.data.errmsg);
                        return this.getFavoritesList();
                    }
                })
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
  </style>