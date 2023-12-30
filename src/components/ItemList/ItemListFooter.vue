<template>
  <div class="pinlun">
      <div class="title">热门评论</div>
      <ul class="pinlun_content" v-if="this.pinglun.length !== 0">
          <li  class="pinlun_content_detail" v-for="(item,index) in this.pinglun" :key="index">
                <div>
                    <img src="https://i03piccdn.sogoucdn.com/1f1dac102a365433" mode="" />
                    <div class="name">名字：{{item.usercart_mobile}}</div>
                    <div class="date">{{item.created_time.split("T").join(" ").split(".")[0]}}</div>
                </div>
                <div>{{item.content}}</div>
          </li>
      </ul>
      <ul class="pinlun_content" v-else>
          <div class="haimeiyoupinglun">还没有人评论，请评论一下吧</div>
      </ul>
      <div class="shuru">
           <div >
                <textarea name="" id="" cols="30" rows="5" v-model="content"></textarea>
                <div><button @click="clickSend()">点击发送</button></div>
           </div>
           <div class="tixing">
              <div>发表评论需知：</div>
              <div>1、请勿在评论中发表违法违规信息</div>
              <div>2、谢绝人身攻击、地域歧视、刷屏、广告等恶性言论</div>
              <div>3、所有评论均代表玩家本人意见，不代表政函数立场</div>
              <div>4、用户发表的评论，经管理员审核后方可显示</div>
              <div>5、如果游戏出现问题，请在此以评论方式留言给我们</div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
    name:"ItemListFooter",
    data(){
        return{
            content:"",
            user:[],
            pinglun:[],
        }
    },
    props:["product"],
    created(){
        this.user = this.$store.state.personalID[0]
        this.$http.get("home/userpinglun/").then(response=>{
            this.pinglun = response.data.filter(item=>item.product_name === parseInt(this.product.id))
        })
    },
    watch:{
        pinglun(){
            this.$http.get("home/userpinglun/").then(response=>{
                this.pinglun = response.data.filter(item=>item.product_name === parseInt(this.product.id))
            })
        }
    },
    filter:{
        dataformat(data){
            data.split("T").join(" ").split(".")[0]
        }
    },
    methods:{
        clickSend(){
            if(this.content){
                this.$http.post("users/userpinglun/",{
                    content:this.content,
                    user_name:this.user.user_id,
                    product_name:this.product.id
                })
                this.$message.success("发送成功")
                this.content = ""
            }else{
                alert("请填写内容")
            }
            
        },
        jisuan(){
            
        }
    },
}
</script>
<style scoped>
    .pinlun{
        width: 1180px;
        height: 100%;
        background-color: aquamarine;
        position: relative;
        left: 50%;
        transform: translateX(-50%);
        margin: 20px 10px;
    }
    .title{
        margin: 20px;
        font-size: 20px;
        font-weight: 700;
        color: tomato;
    }
    .pinlun_content{
        width: 1180px;
        height: 100%;
        background-color: gray;
        max-height: 600px;
        overflow: auto;
        overflow-x: hidden;
        /* overflow-y: hidden; */
    }
    .pinlun_content_detail{
        width: 100%;
        height: 150px;
        background-color: pink;
        display: flex;
        flex-direction: column;
        border: 1px solid #000;
    }
    .pinlun_content_detail > div:nth-child(1){
        flex: 1;
        display: flex;
    }
    .pinlun_content_detail > div:nth-child(1) > img{
        width: 30px;
        height: 30px;
        border-radius: 30px;
        margin: 0 10px;
    }
    .pinlun_content_detail > div:nth-child(1) > .name {
        margin-right: 30px;
        font-weight: 600;
        line-height: 30px;
    }
    .pinlun_content_detail > div:nth-child(1) > .date {
        line-height: 30px;
        font-size: 15px;
    }
    .pinlun_content_detail > div:nth-child(2){
        flex: 4;
        margin: 10px;
    }
     .shuru{
        width: 1180px;
        height: 300px;
        background-color: #f0f0f0;
        display: flex;
    }
    .shuru > div:nth-child(1){
        display: flex;
        flex-direction: column;
    }
    .shuru > div:nth-child(1) >  textarea{
        width: 500px;
        height: 190px;
        margin: 20px;
        padding-top: 10px;
        padding-left: 10px;
        padding-right: 10px;
    }
    .shuru > div:nth-child(1) > div{
        display: flex;
        justify-content: flex-end;
    }
    .shuru > div:nth-child(1) > div > button{
        width: 80px;
        height: 30px;
        background-color: skyblue;
        margin-right: 20px;
    }
    .shuru > .tixing{
        width: 500px;
        height: 200px;
        margin: 20px;
        border: 1px solid #000;
    }
    .shuru > .tixing > div{
        margin-top: 10px;
        margin-left: 10px;
        font-weight: 300px;
    }
    .haimeiyoupinglun{
        width: 100%;
        height: 150px;
        background-color: pink;
        font-size: 30px;

    }
</style>