<template>
    <el-table :data="recordsList" style="width: 100%">
        <el-table-column type="expand">
            <template slot-scope="props">
                <el-form label-position="left" inline class="table-expand">
                    <el-form-item label="订单编号">
                        <span>{{ props.row.orderId }}</span>
                    </el-form-item>
                    <el-form-item label="支付单号">
                        <span>{{ props.row.payId }}</span>
                    </el-form-item>
                    <el-form-item label="卖家：">
                        <span>{{ props.row.sellerOrBuyer.userName }}</span>
                    </el-form-item>
                    <el-form-item label="图书ID：">
                        <span>{{ props.row.bookId }}</span>
                    </el-form-item>
                    <el-form-item label="书名：">
                        <span>{{ props.row.book.bookName }}</span>
                    </el-form-item>
                    <el-form-item label="ISBN号：">
                        <span>{{ props.row.book.bookIsbn }}</span>
                    </el-form-item>
                    <el-form-item label="描述：">
                        <span>{{ props.row.book.bookOutline }}</span>
                    </el-form-item>
                    <el-form-item label="交易时间：">
                        <span>{{ props.row.createTime }}</span>
                    </el-form-item>
                </el-form>
            </template>
        </el-table-column>
        <el-table-column label="订单编号" prop="orderId">
        </el-table-column>
        <el-table-column label="图书名称" prop="book.bookName">
        </el-table-column>
        <el-table-column label="卖家">
            <template slot-scope="scope">
                <el-popover ref="popover" placement="right" title="联系方式" width="200" trigger="hover">
                    <p>邮箱：{{ scope.row.sellerOrBuyer.userEmail }}</p>
                    <p>手机号：{{ scope.row.sellerOrBuyer.userPhone }}</p>
                    <span slot="reference">
                        <el-button>{{ scope.row.sellerOrBuyer.userName }}</el-button>
                    </span>
                </el-popover>
            </template>
        </el-table-column>
        <el-table-column label="金额" prop="totalAmount">
        </el-table-column>
        <el-table-column label="订单状态" prop="goodsState">
            <template slot-scope="scope">
                <el-tag v-if="scope.row.goodsState == 0" type="info" effect="dark">待发货</el-tag>
                <el-tag v-if="scope.row.goodsState == 1" type="success" effect="dark">已发货</el-tag>
                <el-tag v-if="scope.row.goodsState == 2" type="danger" effect="dark">已收货</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <span v-if="scope.row.goodsState == 2">
                    <el-tag type="info" effect="dark">
                        您已收货
                    </el-tag>
                </span>
                <span v-else>
                    <el-popconfirm title="确定已收货吗？收货后钱款将转进对方账户！" @confirm=changeState(scope.row)>
                        <el-button type="primary" slot="reference">确认收货</el-button>
                    </el-popconfirm>
                </span>
            </template>
        </el-table-column>
    </el-table>
  </template>
    
  <script setup>
  import { ref } from 'vue';
  const buyerId = ref('');
  const recordsList = ref([]);
    // created() {
    //     this.buyerId = this.cookie.getCookie("LoginId");
    //     this.getRecordsList();
    // },
    function getRecordsList() {
            this.$http({
                method: 'get',
                url: '/getBuyList',
                params: {
                    buyerId: this.buyerId
                }
            })
                .then(res => {
                    this.recordsList = res.data.result;
                    if (res.data.errcode != "0") {
                        return this.$notify({
                            title: '警告',
                            message: res.data.errmsg,
                            type: 'warning'
                        });
                    }
                    console.log(this.recordsList);
                })
        }
        function changeState(data) {
            console.log('点击后得到数值！：',data);
            
            this.$http({
                method: 'put',
                url: '/setState',
                params: {
                    goodsState: 2,
                    orderhxId: data.orderhxId,
                    totalAmount: data.totalAmount,
                    sellerId: data.sellerId
                }
            })
                .then(res => {
                    this.getRecordsList();
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
                    console.log('返回值：', res.data);
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