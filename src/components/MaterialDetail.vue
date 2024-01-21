<template>
  <Header></Header>
    <div id="wrap">
    <div id="page-header">
      <el-page-header @back="goBack" content="详情页面" title="返回首页"></el-page-header>
    </div>
    <el-divider></el-divider>
    <div id="detail-container">
       <!-- 商品详情 -->
      <div id="detail">
        <div id="img">
          <img :src="material.materialCoverUrl">
        </div>
        <div id="descriptions">
          <el-descriptions :title="material.school+material.major+material.subject" :column="3" :colon="false" style="font-size: 20px">
            <el-descriptions-item label="学校 :" :contentStyle="{'color':'#409EFF'}">{{ material.school  }}
            </el-descriptions-item>
            <el-descriptions-item label="专业 :" :contentStyle="{'color':'#409EFF'}">{{ material.major  }}
            </el-descriptions-item>
            <el-descriptions-item label="学科 :" :contentStyle="{'color':'#409EFF'}">{{ material.subject }}
            </el-descriptions-item>
            <el-descriptions-item label="上传时间 :" :contentStyle="{'color':'#409EFF'}">{{ formatDate(material.createTime) }}
            </el-descriptions-item>
            <el-descriptions-item label="上传者 :" :contentStyle="{'margin-top':'10px','font-size':'20px','color':'#222222','font-weight':'400',
                'width':'750px','height':'115px','display':'-webkit-box','overflow':'hidden','-webkit-box-orient':'vertical','-webkit-line-clamp':'4'}">
              {{ material.materialUploader }}
            </el-descriptions-item>
          </el-descriptions>
          <div>
            <span style="width: 100px;font-size: 18px">课程电子书</span>
            <span style="margin-left:85px;font-size: 18px;color: #ff1f1f">¥ </span><span
              class="discountPrice">{{ material.elecBookPoints}}</span>
            <span class="price">定价: ¥ {{ material.elecBookPoints}}</span>
            <el-button style="margin-left: 150px;" @click="searchInfo(1)" type="primary" plain :icon="ShoppingCart">立即购买</el-button>
            <el-popover
                placement="bottom"
                title="收藏"
                :width="150"
                trigger="hover"
                content="添加该商品到收藏夹"
                style="margin-left: 180px;margin-right: 10px">
                <template #reference>
                  <el-button @click="addToCollection(material.materialId)"  type="warning" :icon="Star" circle></el-button>
                </template> 
            </el-popover>
            <el-popover
                placement="bottom"
                title="分享"
                :width="150"
                trigger="hover"
                content="复制商品链接以分享">
                <template #reference><el-button stype="success" :icon="Link" circle @click="copyPageUrl"></el-button></template>
            </el-popover>
          </div>
          <div>
            <span style="width: 100px;font-size: 18px">教学计划</span>
            <span style="margin-left:102px;font-size: 18px;color: #ff1f1f">¥ </span><span
              class="discountPrice">{{ material.teachingPlanPoints }}</span>
            <span class="price">定价: ¥ {{ material.teachingPlanPoints}}</span>
            <el-button style="margin-left: 150px;" @click="searchInfo(2)" type="primary" plain :icon="ShoppingCart">立即购买</el-button>
            <el-popover
                placement="bottom"
                title="收藏"
                :width="150"
                trigger="hover"
                content="添加该商品到收藏夹"
                style="margin-left: 180px;margin-right: 10px">
                <template #reference>
                  <el-button @click="addToCollection(material.materialId)"  type="warning" :icon="Star" circle></el-button>
                </template> 
            </el-popover>
            <el-popover
                placement="bottom"
                title="分享"
                :width="150"
                trigger="hover"
                content="复制商品链接以分享">
                <template #reference><el-button stype="success" :icon="Link" circle @click="copyPageUrl" ></el-button></template>
            </el-popover>
          </div>
          <div>
            <span style="width: 100px;;font-size: 18px">课程PPT</span>
            <span style="margin-left:106px;font-size: 18px;color: #ff1f1f">¥ </span><span
              class="discountPrice">{{material.classPptPoints }}</span>
            <span class="price">定价: ¥ {{ material.classPptPoints}}</span>
            <el-button style="margin-left: 150px;" @click="searchInfo(3)" type="primary" plain :icon="ShoppingCart">立即购买</el-button>
            <el-popover
                placement="bottom"
                title="收藏"
                :width="150"
                trigger="hover"
                content="添加该商品到收藏夹"
                style="margin-left: 180px;margin-right: 10px">
                <template #reference>
                  <el-button @click="addToCollection(material.materialId)"  type="warning" :icon="Star" circle></el-button>
                </template> 
            </el-popover>
            <el-popover
                placement="bottom"
                title="分享"
                :width="150"
                trigger="hover"
                content="复制商品链接以分享">
                <template #reference><el-button stype="success" :icon="Link" circle @click="copyPageUrl"></el-button></template>
            </el-popover>
          </div>
          <div>
            <span style="width: 100px;font-size: 18px">历年卷</span>
            <span style="margin-left:120px; font-size: 18px;color: #ff1f1f">¥ </span><span
              class="discountPrice">{{material.calendarVolumePoints }}</span>
            <span class="price">定价: ¥ {{ material.calendarVolumePoints}}</span>
            <el-button style="margin-left: 150px;" @click="searchInfo(4)" type="primary" plain :icon="ShoppingCart">立即购买</el-button>
            <el-popover
                placement="bottom"
                title="收藏"
                :width="150"
                trigger="hover"
                content="添加该商品到收藏夹"
                style="margin-left: 180px;margin-right: 10px">
                <template #reference>
                  <el-button @click="addToCollection(material.materialId)"  type="warning" :icon="Star" circle></el-button>
                </template> 
            </el-popover>
            <el-popover
                placement="bottom"
                title="分享"
                :width="150"
                trigger="hover"
                content="复制商品链接以分享">
                <template #reference><el-button stype="success" :icon="Link" circle @click="copyPageUrl" ></el-button></template>
            </el-popover>
          </div>
          <div>
            <span style="width: 100px;;font-size: 18px">其它资料</span>
            <span style="margin-left:102px;font-size: 18px;color: #ff1f1f">¥ </span><span
              class="discountPrice">{{ material.anotherMaterialPoints}}</span>
            <span class="price">定价: ¥ {{ material.anotherMaterialPoints}}</span>
            <el-button style="margin-left: 150px;" @click="searchInfo(5)" type="primary" plain :icon="ShoppingCart">立即购买</el-button>
            <el-popover
                placement="bottom"
                title="收藏"
                :width="150"
                trigger="hover"
                content="添加该商品到收藏夹"
                style="margin-left: 180px;margin-right: 10px">
                <template #reference>
                  <el-button @click="addToCollection(material.materialId)"  type="warning" :icon="Star" circle></el-button>
                </template> 
            </el-popover>
            <el-popover
                placement="bottom"
                title="分享"
                :width="150"
                trigger="hover"
                content="复制商品链接以分享">
                <template #reference><el-button stype="success" :icon="Link" circle @click="copyPageUrl"></el-button></template>
            </el-popover>
          </div>

          <el-divider></el-divider>
          <div id="btn-group">
            <div id="detail">
            <span slot="label"><el-icon><Shop /></el-icon> 商品详情</span>
            <div class="detail-content" style="padding: 10px">
              <span style="font-size: 18px;">{{ material.materialProfile }}</span>
            </div>
          </div>
          
          </div>
        </div>
      </div>
      <el-dialog title="结算" v-model="checkTable" :loading="true">
        <el-table :data="checkData">
          <el-table-column type="index" width="60"></el-table-column>
          <el-table-column>
            <template #default="scope">
              <el-popover placement="right" title="" trigger="hover">
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column prop="materialName" label="资料：">{{material.school+material.major+material.subject}}</el-table-column>
          <el-table-column prop="materialPoints" label="单价(元)">{{ vauleMaterial}}</el-table-column>
        </el-table>
        <el-descriptions size="small" title="订单信息"
                         style="margin-top: 30px;margin-left: 40px;margin-bottom: 10px">
          <el-descriptions-item label="收货人"><span style="color: #409EFF">{{ userInfo.username }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="邮箱"><span style="color: #409EFF">{{ userInfo.email }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="剩余积分"><span style="color: #409EFF">{{ userInfo.points }}</span>
          </el-descriptions-item>
          <!-- <el-descriptions-item label="收货地址"><span style="color: #409EFF">{{ userInfo.shippingAddress }}</span>
          </el-descriptions-item> -->
          <!-- <el-descriptions-item label="支付方式">
            <el-radio-group v-model="payWay" size="mini">
              <el-radio-button label="支付宝"></el-radio-button>
              <el-radio-button label="微信"></el-radio-button>
              <el-radio-button label="信用卡"></el-radio-button>
              <el-radio-button label="货到付款"></el-radio-button>
            </el-radio-group>
          </el-descriptions-item> -->
        </el-descriptions>
        <el-button @click="buyNow()" style="margin-left: 600px" type="primary" round>支付
        </el-button>
      </el-dialog>

      <!-- 分隔线 -->
      <div style="margin: 60px auto 100px;">
        <el-divider content-position="right"></el-divider>
      </div>

      <!-- 推荐 -->
      <div id="recommendation">
        <el-divider content-position="left">
          <span style="font-size: 20px"><el-icon><Present /></el-icon> 同学校推荐</span>
        </el-divider>
        <div id="recommendmaterials">
          <el-row :gutter="20">
            <el-col :span="4" v-for="material in recommendmaterials">
              <div @click="materialDetail(material.materialId)" style="cursor: pointer">
                <el-card :body-style="{ padding: '0px' }" shadow="hover">
                  <img :src="material.materialCoverUrl" class="image">
                  <div style="padding: 12px;">
                    <span>{{ material.school+material.major+material.subject }}</span><br>
                    <span style="font-size: 18px;color: #ff1f1f">¥ </span><span
                      class="discountPrice">{{ decimals(material.anotherMaterialPoints+material.teachingPlanPoints+material.elecBookPoints+material.calendarVolumePoints+material.classPptPoints)}}</span>
                    <span class="price"> ¥ {{ decimals(material.anotherMaterialPoints+material.teachingPlanPoints+material.elecBookPoints+material.calendarVolumePoints+material.classPptPoints) }}</span>
                  </div>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>

      <!-- 详情和评论 -->
      <div id="detail-comment">
            <span slot="label"><el-icon><Comment /></el-icon> 商品评论</span>
            <span style="margin-left: 380px;"> 商品总分：</span>
            <el-rate style="margin-left: 20px;" v-model="material.materialGrade " :colors="colors" :max="5" show-score disabled></el-rate>
            <div v-if="commentData.length>0" class="comment-content">
              <el-table :data="commentData"  style="width: 100%">
                <el-table-column width="200">
                  <template #default="scope">
                    <div class="userInfo">
                      <img :src="scope.row.userAvatarUrl " >
                      <p>{{ scope.row.userNickname }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column width="700">
                  <template #default="scope">
                    <div class="materialGrade">
                      <el-rate v-model=" scope.row.materialGrade "  :colors="colors" :max="5" show-score disabled></el-rate>
                    </div>
                    <div class="materialComment">{{ scope.row.materialComment }}</div>
                  </template>
                </el-table-column>
                <el-table-column width="100px">
                  <template #default="scope">
                    <div class="createTime">{{ formatDate(scope.row.createTime) }}</div>
                  </template>
                </el-table-column>
                <el-table-column fixed="right" label="操作" >
                <template #default="scope" >
                  <el-button v-if="scope.row.userEmail==store.state.personalID[0].email" @click="deletematerialDialog(scope.row.commentId)" type="text"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
              </el-table>
            </div>
            <el-empty v-else description="暂无评论"></el-empty>
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
            <div id="pinglun">
              <el-divider content-position="left">
                <span style="font-size: 20px"><el-icon><Comment /></el-icon> 发表评论</span>
              </el-divider>
              <el-input
                v-model="textarea"
                :rows="6"
                type="textarea"
                placeholder="请发表评论"
              />
              <div style="display: flex; align-items: center; margin-top: 20px; float: right; margin-right: 20px;">
                <el-rate size="large"  v-model="textGrade" :colors="colors" show-score :max="5"></el-rate>
                <el-button type="primary" :icon="Edit" @click="sendCommit()">发布</el-button>
              </div>  
            </div>
      </div>
    </div>
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
import Header from "@/components/Home/Header.vue";
import {Link, Star, ShoppingCart, Edit} from '@element-plus/icons-vue';
import {ElMessage} from "element-plus";
import {ref, reactive, onMounted, watch} from 'vue';
import axios from "axios";
import { useRoute, useRouter } from 'vue-router';
import {useStore} from 'vuex';
// 登录跳转
function routerToLogin(){
  userouter.push("/")
}
const FormVisible = ref(false);
const store = useStore();
const username = ref(store.state.personalID[0].username)
const email = ref(store.state.personalID[0].email)
const router = useRoute();
const userouter = useRouter();
const checkTable = ref(false);
const checkData = reactive([{materialName: '', quantity: 0, discountPrice: 0, img: ''}]);
const userInfo = ref({id: '', userName: '', phoneNumber: '', shippingAddress: ''});
const category = ref('');
const loadingBtn = ref(false);
const commentData = ref([]);
const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']);
let pageNum = ref(1);
let pageSize = ref(10);
const total = ref('');
let material = ref({
        id: '',
        materialName: '',
        author: '',
        price: 0,
        discount: 1,
        press: '',
        publicationDate: '',
        brief: '',
        img: '',
      })
const recommendmaterials = ref([
  {id:'', materialName: '', price: 0, discount: 0, img: ''},
])
  // 返回首页
function goBack() {
  userouter.push("/home");
    }
    // 保留一位小数
function decimals(value) {
      return value.toFixed(1);
    }
    // 页面初始化时执行，根据编号查询图书
const initData = async (materialId) => {
  try {
    const response = await axios.get(`/material/${materialId}`);
    // 处理成功的响应
    material.value = response.data.data;
    material.value.materialGrade = decimals(material.value.materialGrade / 2);
    checkData[0] = response.data.data;
    category.value = response.data.data.categoryName;
    await initDataCategory(category.value); 
  } catch (error) {
    // 处理请求错误
    console.error('请求失败：', error);
  }
};
    // 页面初始化时执行，根据条件查询评论,带分页
const initDataCommit = async (materialId) => {
  try {
    const response = await axios.get('/material/comment',
    {
      params: {
        materialId: materialId,
        page: pageNum.value,
        pageSize: pageSize.value,
      }
    });
    // 处理成功的响应
    commentData.value = response.data.data.items;
    for(let i = 0; i <commentData.value.length; i++){
      commentData.value[i].materialGrade = commentData.value[i].materialGrade / 2 ; 
    }
    
    total.value = response.data.data.total;
  } catch (error) {
    // 处理请求错误
    console.error('请求失败：', error);
  }
};

// 页面初始化时执行，查询同类
const initDataCategory = async (categoryName) => {
  try {
    const response = await axios.get('/material?school='+material.value.school, {
      params: {
        page: 1,
        pageSize: 5
      }
    });
    recommendmaterials.value = response.data.data.items
    // 处理成功的响应
    console.log('成功：', recommendmaterials.value);
  } catch (error) {
    // 处理请求错误
    console.error('请求失败：', error);
  }
};
// 加入收藏
function addToCollection(materialId){
      let obj ={
        userEmail:email.value,
        materialId: materialId,
      }
      axios.post("/material/collectRecord", obj)
            .then((resp) => {
              if (resp.data.code === 0) {
                ElMessage({
                  showClose: true, type: 'success',
                  message: '添加成功！'
                });
              } else {
                ElMessage({
                  showClose: true, type: 'error',
                  message: resp.data.msg
                });
              }
            });
}
const typeMaterial = ref(0);
const vauleMaterial = ref(0);
// 立即购买信息
function searchInfo(type) {
  if (email.value == "session"){
      FormVisible.value = true;
    }else{

    typeMaterial.value = type
    if(type === 1){
      vauleMaterial.value = material.value.elecBookPoints
    }
    if(type ===2){
      vauleMaterial.value = material.value.teachingPlanPoints
    }
    if(type ===3){
      vauleMaterial.value = material.value.classPptPoints
    }
    if(type ===4){
      vauleMaterial.value = material.value.calendarVolumePoints
    }
    if(type ===5){
      vauleMaterial.value = material.value.anotherMaterialPoints
    }   
    checkTable.value = true
    axios.get('/user/userInfo',{params: {username: username.value,}}).then(response => {
    // 处理成功的响应
    userInfo.value = response.data.data; 
  })
  }
   
};
    // 结算立即购买
function buyNow() {
  let obj = {
    materialId: router.params.id,
    userEmail: store.state.personalID[0].email,
    type: typeMaterial.value
  }
  axios.post("/material/download", obj).then( async (resp) => {
    const code = resp.data.code;
    const message = resp.data.message
    if(code == 0){
        loadingBtn.value = true
        checkTable.value = false
        window.location.href = resp.data.data
    }
    if (code == 400) {
        ElMessage({
          message: message,
          type: "error",
        });
    }
});
      
}
// 切换标签页，显示评论
function handleClick(tab) {
    if (tab.name === 'comment') {
      initDataCommit(router.params.id);
   }
}
// 发表评论
const textarea = ref("")
const textGrade = ref(1.0)
function sendCommit(){
  if (email.value == "session"){
      FormVisible.value = true;
    }else{
  let obj = {
    materialId: router.params.id,
    userNickname: store.state.personalID[0].username,
    userEmail: store.state.personalID[0].email,
    userComment:textarea.value,
    userGrade:textGrade.value * 2,
  }
  axios.post("/material/comment/user", obj).then( async (resp) => {
    const code = resp.data.code;
    const message = resp.data.message
    if(code == 0){
      initDataCommit(router.params.id);
    }
    if (code == 400) {
        ElMessage({
          message: message,
          type: "error",
        });
    }
});
}
}
// 删除评论
function deletematerialDialog(row){
  let obj = {
    commentId: row,
    userEmail:store.state.personalID[0].email,
  }
  axios.delete("/material/comment/user", { data: obj }).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.msg
    if(code == 0){
      ElMessage({
          message: message,
          type: "success",
        });
      initDataCommit(router.params.id);
    }
    if (code == 400) {
        ElMessage({
          message: message,
          type: "error",
        });
    }
});
} 
//分享
const copyPageUrl = ()=> {
 // 获取当前网页的URL
 const currentPageUrl = window.location.href;
  // 创建一个临时textarea元素
  const textarea = document.createElement('textarea');
  textarea.value = currentPageUrl;
  document.body.appendChild(textarea);

  // 选中并复制文本
  textarea.select();
  document.execCommand('copy');

  // 移除临时元素
  document.body.removeChild(textarea);
  // 这里你可以添加一些反馈，比如提示用户复制成功
  ElMessage({
          message: "复制成功",
          type: "success",
        });
}
// 格式化日期
function formatDate(time){
      let date = new Date(time);
      let year = date.getFullYear();
      let month = date.getMonth()+1;
      let day = date.getDate();
      return year+"-"+month+"-"+day;
}
// 翻页功能
function pagechange(val) {
  pageNum.value = val;
  initDataCommit(router.params.id);
}
// 从同类推荐跳转到图书详情页
function materialDetail(id){
  userouter.push({name: 'MaterialDetail', params: {id: id}});
}
// 在组件挂载时执行查询图书的函数
onMounted(async () => {
  const materialId = router.params.id;
  userouter.afterEach((to, from, next) => {
        window.scrollTo(0, 0)
    })
  await initData(materialId);
  await initDataCommit(materialId);
  // 在这里可以执行其他操作，确保 initData 函数执行完成后再执行
});
// 使用 watch 监听路由变化
watch(() => router.params.id,
      (to, from) => {
        console.log(`Route changed from ${from.path} to ${to.path}`);
        window.location.reload()
      }
);

</script>
<style>
#wrap {
  margin: 80px auto;
  width: 1200px;
}

#detail-container #detail {
  margin: 30px auto 0;
  width: 1200px;
  height: 400px;
}

#detail-container #detail #img {
  margin-right: 50px;
  margin-left: 15px;
  width: 300px;
  float: left;
  border: 1px #dcdfe6 solid;
  padding: 10px;
}

#detail-container #detail #img img {
  margin-left: 15px;
  width: 260px;
  height: 360px;
}

#detail-container #detail #descriptions {
  width: 800px;
  float: left;
  font-size: 25px;
  margin-top: 20px;
}

.discountPrice {
  color: #ff1f1f;
}

.price {
  margin-left: 20px;
  text-decoration: line-through;
  color: rgba(151, 151, 151, 0.9);
  font-size: 16px;
}

#btn-group {
  margin-top:5px;
}

#recommendation .image {
  width: 160px;
  height: 200px;
  display: block;
  margin-left: 10px;
  margin-top: 5px;
}

#recommendmaterials {
  margin-top: 60px;
}

#detail-comment {
  margin: 100px auto;
}

#detail-comment .comment-content {
  padding: 15px;
}

#detail-comment .comment-content .userInfo img {
  width: 80px;
  height: 80px;
  margin-top: 10px;
  margin-left: 50px;
}

#detail-comment .comment-content .userInfo p {
  text-align: center;
  font-size: 16px;
  color: #409EFF;
}

#detail-comment .comment-content .materialGrade {
  margin: 0 auto 10px;
  width: 250px;
}

#detail-comment .comment-content .materialComment {
  height: 90px;
  display: -webkit-box;
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  text-indent: 30px;
}

#detail-comment .comment-content .date {
  margin-left: 1100px;
}

#pageBtn {
  float: right;  /* 使用 float: right; 将元素靠右 */
  display: table;
}
#pinglun{
  margin-top: 40px;
}

</style>

