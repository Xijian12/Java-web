<template>
    <div class="contentTitle">
     <div v-if="userName==null" class="LeftBtn">
        <!--个人信息-->
        <el-button id="ShowInfoBtn" size="medium" icon="el-icon-user" @click="drawer_info = true" type="primary">
          个人信息
        </el-button>
        <el-drawer
            v-model="drawer_info"
            title=""
            :direction="direction"
        >
        <el-divider content-position="left"> 
            <el-icon><Avatar/></el-icon>
            个人信息
        </el-divider>
        <div id="UserInfo">
            <el-image
                style="width: 150px;height: 150px;margin-top: 30px;margin-left: 100px;margin-bottom: 30px;"
                :src="'/src/assets/images/user.png'"
                fit="fill">
            </el-image>
            <el-descriptions class="" :column="1" size="" border>
              <el-descriptions-item>
                <template #label><el-icon><Postcard/></el-icon>账号</template>
                {{ userInfo.userId }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label><el-icon><User/></el-icon> 用户名</template>
                {{ userInfo.userName }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label><el-icon><UploadFilled/></el-icon>邮箱</template>
                {{ userInfo.phoneNumber }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label><el-icon><Postcard/></el-icon> 邮箱</template>
                {{ userInfo.email }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label><el-icon><Postcard/></el-icon> 收货地址</template>
                {{ userInfo.shippingAddress }}
              </el-descriptions-item>
            </el-descriptions>
            <el-button id="EditUserInfoBtn" @click="drawer_info = false; editUserInfo = true;" type="primary" round>
              修改
            </el-button>
          </div>         
        </el-drawer>
        <el-dialog title="修改个人信息" width="30%" v-model="editUserInfo" center>
          <el-form :model="userInfo" :rules="editUserInfoRules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="账号">
              <el-input v-model="userInfo.userId" readonly clearable></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="userName">
              <el-input v-model="userInfo.userName" clearable minlength="2" maxlength="5" show-word-limit></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phoneNumber">
              <el-input v-model="userInfo.phoneNumber" clearable></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="userInfo.email" clearable></el-input>
            </el-form-item>
            <el-form-item label="收货地址" prop="shippingAddress">
              <el-input v-model="userInfo.shippingAddress" clearable></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitEdit('ruleForm')">修改</el-button>
            <el-button @click="resetEdit('ruleForm')">重置</el-button>
          </span>
        </el-dialog>
        <!--我的购物车-->
        <el-button id="ShowCartBtn" size="medium" icon="el-icon-shopping-cart-1" @click="drawer_cart = true"
                   type="primary" style="margin-left: 16px;">
          我的购物车
        </el-button>
        <el-drawer
          title=""
          v-model="drawer_cart"
          :direction="direction">
        <el-divider content-position="left"><i class="el-icon-shopping-cart-2"></i> 我的购物车</el-divider>
        <el-table
            :data="cartData"
            style="margin-top: 40px;width: 100%;"
            show-summary
            :summary-method="getSummaries"
            highlight-current-row
            v-loading="loading"
            empty-text="这里什么都没有..."
            @selection-change="handleSelectionChange"
            height="500">
          <el-table-column
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column>
            <template slot-scope="scope">
              <el-popover placement="right" title="" trigger="hover">
                <img :src="scope.row.img" alt="" style="width: 150px;height: 170px">
                <img slot="reference" :src="scope.row.img" style="width: 50px;height: 55px">
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
              prop="bookName"
              label="书名"
              width="120">
          </el-table-column>
          <el-table-column
              prop="quantity"
              label="数量"
              sortable
              width="80">
          </el-table-column>
          <el-table-column
              prop="discountPrice"
              label="单价(元)"
              sortable
              width="120">
          </el-table-column>
          <el-table-column
              prop="author"
              label="作者"
              width="120">
          </el-table-column>
          <el-table-column
              prop="press"
              label="出版社"
              width="120">
          </el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              width="150">
            <template slot-scope="scope">
              <el-button @click="bookDetail_cart(scope.row.bookId)" style="margin-left: 5px" type="text"
                         size="medium">查看详情
              </el-button>
              <el-button
                  @click.native.prevent="deleteRow_cart(cartData,scope.row.bookId,scope.row.quantity)"
                  style="margin-left: 15px"
                  type="text"
                  size="medium">
                移除<i class="el-icon-delete"></i>
              </el-button>
              <el-input-number @change="(currentValue,oldValue) => changeQuantity(currentValue,oldValue,scope.row)"
                               v-model="scope.row.quantity" controls-position="right" size="mini"
                               :step="1" step-strictly :min="1" :max="99"></el-input-number>
            </template>
          </el-table-column>
        </el-table>
        <el-button v-if="multipleSelection.length>0" class="CheckBtn" @click="beforeCheck" icon="el-icon-sell" type="primary"
                   round>结算 ({{multipleSelection.length}})
        </el-button>
        <el-button v-else class="CheckBtn" disabled icon="el-icon-sell" type="primary" round>结 算</el-button>
      </el-drawer>
        <el-dialog title="结算" v-model="checkTable"
                   style="overflow-y: hidden;margin-top: -90px">
          <el-table :data="multipleSelection" height="400">
            <el-table-column type="index" width="60"></el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <el-popover placement="right" title="" trigger="hover">
                  <img :src="scope.row.img" alt="" style="width: 150px;height: 170px">
                  <img slot="reference" :src="scope.row.img" style="width: 50px;height: 55px">
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column property="bookName" label="书名"></el-table-column>
            <el-table-column property="quantity" label="数量"></el-table-column>
            <el-table-column property="discountPrice" label="单价(元)"></el-table-column>
          </el-table>
          <el-descriptions v-loading="loading" size="small" title="订单信息"
                           style="margin-top: 30px;margin-left: 40px;margin-bottom: 10px">
            <el-descriptions-item label="收货人"><span style="color: #409EFF">{{ userInfo.userName }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="手机号"><span style="color: #409EFF">{{ userInfo.phoneNumber }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="总金额"><span style="color: #409EFF">{{ total }}</span></el-descriptions-item>
            <el-descriptions-item label="收货地址"><span style="color: #409EFF">{{ userInfo.shippingAddress }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="支付方式">
              <el-radio-group v-model="payWay" size="mini">
                <el-radio-button label="支付宝"></el-radio-button>
                <el-radio-button label="微信"></el-radio-button>
                <el-radio-button label="信用卡"></el-radio-button>
                <el-radio-button label="货到付款"></el-radio-button>
              </el-radio-group>
            </el-descriptions-item>
          </el-descriptions>
          <el-button :loading="loadingBtn" @click="check(2)" style="margin-left: 600px" type="primary" round>去付款
          </el-button>
        </el-dialog>
        <!--我的订单-->
        <el-button id="ShowOrderBtn" size="medium" icon="el-icon-document" @click="showDealInfo" type="primary"
                   style="margin-left: 16px;">
          我的订单
        </el-button>
        <!-- <el-drawer
            title="DengDeng-plus"
            :visible.sync="drawer_deal"
            :direction="direction"
            style="width: 180%">
          <el-divider content-position="left"><i class="el-icon-tickets"></i> 我的订单</el-divider>
          <el-table
              :data="dealData"
              stripe
              height="550px"
              empty-text="这里什么都没有..."
              v-loading="loading"
              style="width: 100%;margin-top: 40px;">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <div id="eachOrderInfo" v-for="(dealDetail,index) in props.row.dealDetails">
                    <el-form-item label="" style="line-height: 20px">
                      <div id="description" style="width: 650px;margin-left: 20px">
                        <div style="float: left">
                          <el-popover placement="right" title="" trigger="hover">
                            <img :src="dealDetail.bookImg" alt="" style="width: 150px;height: 170px">
                            <img slot="reference" :src="dealDetail.bookImg"
                                 style="width: 55px;height: 60px;margin-left: 15px">
                          </el-popover>
                        </div>
                        <div
                            style="float: left;margin-left: 30px;width: 150px;text-overflow: ellipsis;overflow: hidden;white-space:nowrap">
                          书名 : {{ dealDetail.bookName }}
                        </div>
                        <div style="float: left;margin-left: 20px;width: 100px;">数量 : {{ dealDetail.bookQuantity }}
                          (本)
                        </div>
                        <div style="float: left;margin-left: 20px;width: 120px;">单价 : {{ dealDetail.bookPrice }} (元)
                        </div>
                        <el-button @click="bookDetail_deal(dealDetail.bookId)" style="margin-left: 20px" type="text"
                                   icon="el-icon-info">查看详情
                        </el-button>
                      </div>
                    </el-form-item>
                  </div>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column
                type="index"
                fixed="left"
                width="40">
            </el-table-column>
            <el-table-column
                label="状态"
                width="120"
                :filters="[{ text: '交易完成', value: 1 },
              { text: '尚未发货', value: 2 },
              { text: '已发货', value: 3 },
              { text: '已签收', value: 4 },
              { text: '已评论', value: 5 },
              { text: '待支付', value: 6 }]"
                :filter-method="filterState"
                prop="deal.state">
              <template slot-scope="scope">
                <el-tag v-if="scope.row.deal.state === 1" type="success">交易完成</el-tag>
                <el-tag v-else-if="scope.row.deal.state === 2" type="info">尚未发货</el-tag>
                <el-tag v-else-if="scope.row.deal.state === 3" type="primary">已发货</el-tag>
                <el-tag v-else-if="scope.row.deal.state === 4" type="primary">已签收</el-tag>
                <el-tag v-else-if="scope.row.deal.state === 5" type="primary">已评论</el-tag>
                <el-tag v-else type="warning">待支付</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                label="时间"
                sortable
                width="180"
                prop="deal.createTime">
            </el-table-column>
            <el-table-column
                label="总额"
                sortable
                width="100"
                prop="deal.total">
            </el-table-column>
            <el-table-column
                label="收货地址"
                width="200"
                prop="deal.shippingAddress">
            </el-table-column>
            <el-table-column
                label="支付方式"
                :filters="[{ text: '支付宝', value: '支付宝' },{ text: '微信', value: '微信' },{ text: '银行卡', value: '银行卡' },{ text: '货到付款', value: '货到付款' }]"
                :filter-method="filterPayWay"
                width="100"
                prop="deal.payWay">
            </el-table-column>
            <el-table-column
                label="订单号"
                sortable
                width="300"
                prop="deal.id">
            </el-table-column>
            <el-table-column label="操作" fixed="right" width="120">
              <template slot-scope="scope">
                <el-button v-if="scope.row.deal.state===1" type="text" disabled
                           style="pointer-events: none">无操作
                </el-button>
                <el-button v-else-if="scope.row.deal.state===2" @click="remindShipping" type="text">提醒发货
                </el-button>
                <el-button v-else-if="scope.row.deal.state===3" @click="receipt(scope.row.deal.id)"
                           type="text">确认收货
                </el-button>
                <el-button v-else-if="scope.row.deal.state===4" @click="comment(scope.row.deal.id)"
                           type="text">去评论
                </el-button>
                <el-button v-else-if="scope.row.deal.state===5" type="text" disabled
                           style="pointer-events: none">无操作
                </el-button>
                <div v-else>
                  <el-button @click="pay(scope.row.deal.id)" type="text" size="mini">去支付</el-button>
                  <el-button @click.native.prevent="deleteDeal(dealData,scope.row.deal.id)" type="text" size="mini">取消订单
                  </el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-drawer> -->
        <!--评论的dialog-->
        <!-- <el-dialog title="评论" :class="commentContents.length>1?'dialogStyle':''" :visible.sync="commentVisible" width="50%" :before-close="cancelComment">
          <div :class="commentContents.length>1?'dialogContentStyle':''">
            <div v-for="commentContent in commentContents">
              <el-form :model="commentContent">
                <el-row :gutter="20">
                  <el-col :span="9">
                    <el-form-item label="">
                      <el-image :src="commentContent.bookImg" style="width: 200px;height: 250px"></el-image>
                    </el-form-item>
                  </el-col>
                  <el-col :span="15">
                    <div>
                      <el-form-item label="图书名称 : ">
                        {{ commentContent.bookName }}
                      </el-form-item>
                      <el-form-item label="评分 : ">
                        <el-rate style="margin-top: 12px" v-model="commentContent.rate" :colors="colors" show-text></el-rate>
                      </el-form-item>
                      <el-form-item label="评价内容 : ">
                        <el-input type="textarea" maxlength="60" show-word-limit autosize placeholder="请输入内容"
                                  v-model="commentContent.content"></el-input>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </div>
          <div slot="footer" class="dialog-footer">
            <el-button @click="commentVisible = false">取 消</el-button>
            <el-button @click="confirmComment" v-loading="loadingBtn" type="primary">提交评论</el-button>
          </div>
        </el-dialog> -->
        <!--支付的dialog-->
        <!-- <el-dialog
            title="支付"
            :visible.sync="payVisible"
            width="30%"
            :before-close="cancelPay">
          <span>请选择支付方式 :</span>
          <el-radio-group v-model="payWay" size="mini" style="margin-top: 10px">
            <el-radio label="支付宝"></el-radio>
            <el-radio label="微信"></el-radio>
            <el-radio label="信用卡"></el-radio>
            <el-radio label="货到付款"></el-radio>
          </el-radio-group>
          <span slot="footer" class="dialog-footer">
            <el-button @click="payVisible = false">取 消</el-button>
            <el-button v-loading="loadingBtn" type="primary" @click="confirmPay">确认支付</el-button>
          </span>
        </el-dialog> -->
        <!--我的收藏-->
        <el-button id="LogoutBtn" size="medium" icon="el-icon-star-off" @click="showCollectionInfo" type="primary"
                   style="margin-left: 16px;">
          我的收藏
        </el-button>
        <!-- <el-drawer
            title="DengDeng-plus"
            :visible.sync="drawer_collection"
            :direction="direction"
            style="width: 150%">
          <el-divider content-position="left"><i class="el-icon-star-on"></i> 我的收藏</el-divider>
          <el-table
              :data="collectionData.filter(data => !search || data.bookName.toLowerCase().includes(search.toLowerCase())
               || data.author.toLowerCase().includes(search.toLowerCase()) || data.press.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%"
              highlight-current-row
              stripe
              v-loading="loading"
              empty-text="这里什么都没有..."
              height="500">
            <el-table-column type="index" width="30"></el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <el-popover placement="right" title="" trigger="hover">
                  <img :src="scope.row.img" alt="" style="width: 150px;height: 170px">
                  <img slot="reference" :src="scope.row.img" style="width: 50px;height: 55px">
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column
                label="书名"
                prop="bookName"
                width="120">
            </el-table-column>
            <el-table-column
                label="现价"
                prop="discountPrice"
                sortable
                width="100">
            </el-table-column>
            <el-table-column
                label="折扣"
                sortable
                prop="discount"
                width="100">
              <template slot-scope="scope">
                <el-tag v-if="scope.row.discount<1" type="success">{{ (scope.row.discount - 1).toFixed(1) * 100 }}%
                </el-tag>
                <el-tag v-else type="info">无</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                label="定价"
                prop="price"
                sortable
                width="100">
            </el-table-column>
            <el-table-column
                label="作者"
                prop="author"
                width="120">
            </el-table-column>
            <el-table-column
                label="出版社"
                prop="press"
                width="120">
            </el-table-column>
            <el-table-column
                fixed="right"
                align="right"
                width="180">
              <template slot="header" slot-scope="scope">
                <el-input
                    v-model="search"
                    size="mini"
                    placeholder="输入关键字搜索"
                    clearable
                    prefix-icon="el-icon-search"
                    style="width: 150px"/>
              </template>
              <template slot-scope="scope">
                <el-button
                    size="mini"
                    icon="el-icon-info"
                    @click="bookDetail_col(scope.row.bookId)">查看详情
                </el-button>
                <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete"
                    @click.native.prevent="deleteRow_col(collectionData,scope.row.bookId)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-drawer>
       -->
    </div> 
</div>   
</template>
<script setup>
import { ref } from 'vue';
const multipleSelection = ref([]);
const direction = ref('ltr')
const  userName = ref(null);
const  drawer_info = ref(false);
const drawer_cart = ref(false);
const  userInfo = ref({
        userId: '11323',
        userName: 'faljflkj',
        phoneNumber: '13223131',
        email: 'afk;la!',
        shippingAddress: 'fafaf',
        icon: '23'
      })
const editUserInfo = ref(false);
const checkTable = ref(false);
const cartData = ref([]);
 
    // 打开个人信息
    function showUserInfo() {
    //   axios.get("http://localhost:8081/user/selectUserById/" + this.id)
    //       .then((resp) => {
    //         this.userInfo = resp.data;
    //       })
      drawer_info = true;
    }
    // 确认修改
    function submitEdit(formName) {
      // 1.验证表单内容
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 2.用户二次确认
          this.$confirm('确认修改？').then(_ => {
            // 3.修改
            let data = {
              id: this.id,
              userName: this.userInfo.userName,
              phoneNumber: this.userInfo.phoneNumber,
              email: this.userInfo.email,
              shippingAddress: this.userInfo.shippingAddress
            };
            axios.put("http://localhost:8081/user/updateUser",data)
                .then((resp) => {
                  if (resp.data === 1) {
                    sessionStorage.setItem("userName", this.userInfo.userName);
                    this.userName = this.userInfo.userName;
                    this.editUserInfo = false;
                    this.$message({
                      showClose: true,
                      center: true,
                      type: 'success',
                      message: '修改成功！即将打开个人信息'
                    });
                    // 延迟1000毫秒打开我的个人信息
                    setTimeout(() => {
                      this.drawer_info = true;
                    }, 1000)
                  } else {
                    this.editUserInfo = false;
                    this.$message({
                      showClose: true,
                      center: true,
                      type: 'error',
                      message: '修改失败，请重试'
                    });
                  }
                })
          }).catch(_ => {
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
    // 重置修改
    function resetEdit(formName) {
      this.$refs[formName].resetFields();
    }
    // 打开购物车
    function showCartInfo() {
      // 判断是否是游客
      let id = sessionStorage.getItem("id")==null?this.visitorId:this.id;
      this.loading = true;
      this.drawer_cart = true;
      axios.get("http://localhost:8081/user/cartInfo/" + id)
          .then((resp) => {
            this.cartData = resp.data;
            this.loading = false;
          });
    }
    // 从购物车中删除一行
    function deleteRow_cart(rows, bookId, quantity) {
      this.$confirm('确认从购物车中移除此图书？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 判断是否是游客，获取id
        let id = sessionStorage.getItem("id")==null?this.visitorId:this.id;
        let data = {
          id: id,
          bookId: bookId,
          quantity: quantity
        };
        axios.post("http://localhost:8081/user/deleteCart",data)
            .then((resp) => {
              if (resp.data === 1) {
                this.myDelete_cart(rows, bookId);
                this.$message({
                  type: 'success',
                  message: '移除成功!'
                });
              } else {
                this.$message({
                  type: 'error',
                  message: '移除失败，请刷新后重试'
                });
              }
            });
      })
    }
    // 自定义的删除数组的方法
    function myDelete_cart(rows, id){
      for (let i = 0; i < rows.length; i++) {
        if (rows[i].bookId === id) {
          rows.splice(i,1);
        }
      }
    }
    // 从购物车中跳转到图书详情页
    function bookDetail_cart(bookId) {
      this.drawer_cart = false;
      this.$router.push({name: 'BookDetail', params: {id: bookId}});
    }
    // 购物车价格总计
    function getSummaries(param) {
      const sums = [];
      let total = 0;
      for (let i = 0; i < multipleSelection.length; i++) {
        total += multipleSelection[i].quantity * multipleSelection[i].discountPrice;
      }
      sums[1] = '总计 :';
      sums[2] = total.toFixed(2) + ' (元)';
      total = parseFloat(total.toFixed(2));
      return sums;
    }
    // 改变购物车中图书数量
    function changeQuantity(currentValue, oldValue, row) {
      // 判断是否是游客，获取id
      let id = sessionStorage.getItem("id")==null?this.visitorId:this.id;
      let data = {
        id: id,
        bookId: row.bookId,
        quantity: currentValue-oldValue
      };
      axios.put("http://localhost:8081/user/changeQuantity",data)
          .then((resp) => {
            if (resp.data === 1) {
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
            } else {
              this.$message({
                type: 'error',
                message: '修改失败，请刷新后重试'
              });
            }
          });
    }
    // 选中的行
    function handleSelectionChange(val) {
      multipleSelection = val;
    }
    // 结算功能
    function beforeCheck() {
      if (sessionStorage.getItem("id") == null){
        this.$message({
          type: 'warning',
          message: '您还未登录，登录后才能进行结算！'
        });
      } else {
        this.drawer_cart = false;
        this.loading = true;
        this.checkTable = true;
        this.payWay = '支付宝';
        axios.get("http://localhost:8081/user/selectUserById/" + this.id)
            .then((resp) => {
              this.userInfo = resp.data;
              this.loading = false;
            });
      }
    }
    // 结算
    function check(state) {
      this.loadingBtn = true;
      let deal = {
        userId: this.id,
        shippingAddress: this.userInfo.shippingAddress,
        total: this.total,
        payWay: this.payWay,
        state: state
      };
      let dealDetails = this.getDealDetails();
      axios.post("http://localhost:8081/deal/check", {deal: deal, dealDetails: dealDetails})
          .then((resp) => {
            this.loadingBtn = false;
            this.checkTable = false;
            this.payWay = "支付宝";
            if (resp.data === 1) {
              if (state === 2) {
                this.$message({
                  showClose: true,
                  type: 'success',
                  message: '结算成功！'
                });
              }
              if (state === 6) {
                this.$message({
                  showClose: true,
                  type: 'warning',
                  message: '您有一笔新的待支付订单，请尽快在 我的订单 中处理'
                });
              }
            } else {
              this.$message({
                showClose: true,
                type: 'error',
                message: '结算失败，请重试'
              });
            }
          });
    }
    // 组装订单详情
    function getDealDetails() {
      let dealDetails = [];
      for (let i = 0; i < this.multipleSelection.length; i++) {
        let dealDetail = {
          bookId: this.multipleSelection[i].bookId,
          bookName: this.multipleSelection[i].bookName,
          bookPrice: this.multipleSelection[i].discountPrice,
          bookQuantity: this.multipleSelection[i].quantity,
          bookImg: this.multipleSelection[i].img
        };
        dealDetails.push(dealDetail);
      }
      return dealDetails;
    }
    // 取消结算
    function cancelCheck() {
      this.$confirm('确认取消支付？未支付的订单稍后可在 我的订单 中重新支付')
          .then(_ => {
            this.check(6);
            this.payWay = "支付宝";
          })
          .catch(_ => {
          });
    }
    // 打开我的订单
    function showDealInfo() {
      this.loading = true;
      this.drawer_deal = true;
      axios.get("http://localhost:8081/deal/selectDealInfo/" + this.id)
          .then((resp) => {
            this.loading = false;
            this.dealData = resp.data;
          });
    }
    // 订单状态筛选功能-状态筛选
    function filterState(value, row) {
      return row.deal.state === value;
    }
    // 订单状态筛选功能-支付方式筛选
    function filterPayWay(value, row) {
      return row.deal.payWay === value;
    }
    // 提醒发货
    function remindShipping() {
      this.$message({
        showClose: true,
        message: '提醒成功，会尽快为您安排发货',
        type: 'success'
      });
    }
    // 收货
    function receipt(id) {
      this.$confirm('确认签收?', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true;
        axios.post("http://localhost:8081/deal/updateDealState/"+id+"/4")
            .then((resp) => {
              this.loading = false;
              if (resp.data === 1) {
                for (let i = 0; i < this.dealData.length; i++) {
                  if (this.dealData[i].deal.id === id){
                    this.dealData[i].deal.state = 4;
                    break;
                  }
                }
                this.$message({
                  message: '签收成功',
                  type: 'success'
                });
              } else {
                this.$message({
                  message: '签收失败，请重试',
                  type: 'error'
                });
              }
            });
      })
    }
    // 去评论
    function comment(id) {
      this.commentContents = [];
      let dealDetails;
      //根据deal的id取到dealDetails
      for (let i = 0; i < this.dealData.length; i++) {
        if (this.dealData[i].deal.id === id){
          dealDetails = this.dealData[i].dealDetails;
          break;
        }
      }
      console.log(dealDetails);
      for (let i = 0; i < dealDetails.length; i++) {
        // 使用this.$set方法动态设置data的数据
        this.$set(this.commentContents, i,
            {
              'bookId': dealDetails[i].bookId,
              'bookName': dealDetails[i].bookName,
              'userId': this.id,
              'userName': this.userName,
              'rate': 5,
              'bookImg': dealDetails[i].bookImg,
              content: null
            });
      }
      this.dealId_comment = id;
      this.commentVisible = true;
    }
    // 取消评论
    function cancelComment(done) {
      this.$confirm('确认取消评论？').then(_ => {
        done();
      }).catch(_ => {
      });
    }
    // 确认评论
    function confirmComment() {
      this.commentVisible = false;
      this.loadingBtn = true;
      axios.post("http://localhost:8081/comment/insertComment",this.commentContents)
          .then((resp) => {
            this.loadingBtn = false;
            if (resp.data === 1){
              this.loading = true;
              axios.post("http://localhost:8081/deal/updateDealState/"+this.dealId_comment+"/5")
                  .then((_resp) => {
                this.loading = false;
                if (_resp.data === 1) {
                  for (let i = 0; i < this.dealData.length; i++) {
                    if (this.dealData[i].deal.id === this.dealId_comment){
                      this.dealData[i].deal.state = 5;
                      break;
                    }
                  }
                  this.$message({
                    message: '评论成功',
                    type: 'success'
                  });
                }
              });
            } else {
              this.$message({
                message: '评论失败，请重试',
                type: 'error'
              });
            }
          });
    }
    // 去支付
    function pay(dealId) {
      this.payWay = '支付宝';
      this.dealId = dealId;
      this.payVisible = true;
    }
    // 取消支付
    function cancelPay(done) {
      this.$confirm('确认取消支付？').then(_ => {
        done();
      }).catch(_ => {
      });
    }
    // 确认支付
    function confirmPay() {
      this.drawer_deal = false;
      this.payVisible = false;
      this.loadingBtn = true;
      axios.get("http://localhost:8081/deal/pay/"+this.dealId+"/"+this.payWay)
      .then((resp) => {
        this.loadingBtn = false;
        if (resp.data === 1) {
          this.$message({
            showClose: true,
            type: 'success',
            message: '支付成功！'
          });
        } else {
          this.$message({
            showClose: true,
            type: 'error',
            message: '支付失败，请重试'
          });
        }
      });
    }
    // 取消订单
    function deleteDeal(rows, id) {
      this.$confirm('确认取消该订单？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete("http://localhost:8081/deal/deleteDeal/"+id)
        .then((resp) => {
          if (resp.data === 1) {
            this.myDelete_deal(rows,id);
            this.$message({
              showClose: true,
              type: 'success',
              message: '取消订单成功！'
            });
          } else {
            this.$message({
              showClose: true,
              type: 'error',
              message: '取消订单成功失败，请重试'
            });
          }
        });
      });
    }
    // 自定义的删除数组的方法
    function myDelete_deal(rows, id){
      for (let i = 0; i < rows.length; i++) {
        if (rows[i].deal.id === id) {
          rows.splice(i,1);
          break;
        }
      }
    }
    // 从购物车中跳转到图书详情页
    function bookDetail_deal(bookId) {
      this.drawer_deal = false;
      this.$router.push({name: 'BookDetail', params: {id: bookId}});
    }
    // 打开收藏夹
    function showCollectionInfo() {
      this.loading = true;
      this.drawer_collection = true;
      axios.get("http://localhost:8081/user/collectionInfo/" + this.id)
          .then((resp) => {
            this.collectionData = resp.data;
            this.loading = false;
          });
    }
    // 从收藏夹中跳转到图书详情页
    function bookDetail_col(bookId) {
      this.drawer_collection = false;
      this.$router.push({name: 'BookDetail', params: {id: bookId}});
    }
    // 从收藏夹中删除一行
    function deleteRow_col(rows, bookId) {
      this.$confirm('确认从收藏夹中移除此图书？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete("http://localhost:8081/user/deleteCollection/"+this.id+"/"+bookId)
            .then((resp) => {
              if (resp.data === 1) {
                this.myDelete_col(rows, bookId);
                this.$message({
                  type: 'success',
                  message: '移除成功!'
                });
              } else {
                this.$message({
                  type: 'error',
                  message: '移除失败，请刷新后重试'
                });
              }
            });
      })
    }
    // 自定义的删除数组的方法
    function myDelete_col(rows, id){
      for (let i = 0; i < rows.length; i++) {
        if (rows[i].bookId === id) {
          rows.splice(i,1);
          break;
        }
      }
    }
    // 搜索功能
    function Search(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const classification = this.spliceClassification(this.ruleForm.classification);
          this.$router.push({
            path: '/SearchDisplay',
            // 使用 JSON.stringify() 方法先转为Json字符串，接收的时候再转回对象
            query: {
              keyWord: JSON.stringify(this.ruleForm.keyWord),
              scope: JSON.stringify(this.ruleForm.scope),
              classification: JSON.stringify(classification)
            }
          });
          this.dialogFormVisible = false;
          this.activeIndex = null;
          this.$refs[form].resetFields();
          this.ruleForm.classification = [];
        } else {
          return false;
        }
      });
    }
    // 遍历数组，拼接出用户选中的分类
    function spliceClassification(array) {
      const result = [];
      for (let i = 0, len = array.length; i < len; i++) {
        let arr = array[i];
        for (let j = 0, len = arr.length; j < len; j++) {
          if (j === arr.length - 1) {
            result.push(arr[j]);
          }
        }
      }
      return result;
    }
    // 取消搜索
    function CancelSearch(form) {
      this.dialogFormVisible = false;
      this.$refs[form].resetFields();
      this.ruleForm.classification = [];
    }
</script>
  

<style scoped>
 .contentTitle{
    margin-top: 50px;
    position: relative;
    width: 100%;
    height: 800px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    flex-direction: row;
    background-color: #ffff;
  }
#ShowInfoBtn {
  text-align: center;
  position: fixed;
  width: 135px;
  margin-left: 15px;
  margin-top: 140px;
}

#ShowCartBtn {
  text-align: center;
  position: fixed;
  width: 135px;
  margin-left: 15px;
  margin-top: 185px;
}

#ShowOrderBtn {
  text-align: center;
  position: fixed;
  width: 135px;
  margin-left: 15px;
  margin-top: 230px;
}

#LoginBtn {
  text-align: center;
  position: fixed;
  width: 135px;
  margin-left: 15px;
  margin-top: 185px;
}

#LogoutBtn {
  text-align: center;
  position: fixed;
  width: 135px;
  margin-left: 15px;
  margin-top: 275px;
}

#ShowCartBtn-visitor {
  text-align: center;
  position: fixed;
  width: 135px;
  margin-left: 15px;
  margin-top: 230px;
}

#EditUserInfoBtn {
  margin-top: 20px;
  margin-left: 330px;
}

</style>