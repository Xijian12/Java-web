<template>
  <div class="borrow">
    <el-container>
      <el-main>
        <!--        借阅图书面板-->
        <el-row class="borrow-pane">
          <el-col>
            <el-col><span class="title">借阅图书</span></el-col>
            <!--            图书搜索框-->
            <el-col class="search-book-pane"
              ><el-input
                class="search-book-input"
                placeholder="请输入要搜索的ISBN号码"
                v-model="searchBookInput.ISBN"
              ></el-input
              ><el-button
                class="search-book-button"
                type="primary"
                @click="searchBook"
                >搜索</el-button
              ></el-col
            >
          </el-col>
          <el-col>
            <el-row>
              <!--              图书信息面板-->
              <el-col :span="12" class="book-info-pane">
                <span class="title">图书信息</span>
                <span class="sub-title">图书名称：{{ book.name }}</span>
                <span class="sub-title">作者名称：{{ book.author }}</span>
                <span class="sub-title">出版社名称：{{ book.publish }}</span>
                <span class="sub-title">ISBN号码：{{ book.ISBN }}</span>
                <span class="sub-title">库存（本）：{{ book.num }}</span>
                <sapn class="sub-title"
                  >可借阅（本）：{{ book.remaining }}</sapn
                >
              </el-col>
              <!--              用户表单-->
              <el-col :span="12" class="user-form-pane">
                <el-form v-model="userInfo" ref="userInfoRef">
                  <span class="title">用户信息</span>
                  <el-input
                    placeholder="借阅人卡号"
                    v-model="userInfo.reader_id"
                  ></el-input>
                  <el-input
                    placeholder="图书ID"
                    v-model="userInfo.book_id"
                  ></el-input>
                  <el-date-picker
                    v-model="userInfo.reserve_time"
                    type="date"
                    placeholder="选择还书日期"
                    format="YYYY-MM-DD"
                    value-format="YYYY-MM-DD"
                    style="width: 100%"
                    class="custom-datepicker"
                  ></el-date-picker>
                  <el-button type="primary" @click="borrowBook"
                    >借阅图书</el-button
                  >
                </el-form>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { FormInstance } from "element-plus";
import axios from "axios";
import { ElMessageBox } from "element-plus";
// 图书信息
let book = reactive({
  name: "",
  author: "",
  num: "",
  ISBN: "",
  publish: "",
  remaining: "",
  version: "",
});
// 搜索图书输入框
const searchBookInput = reactive({
  ISBN: "",
});
const searchBook = () => {
  axios.post("api/manager/checkbooktable/", searchBookInput).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.message;
    // 查询成功
    if (code == 200) {
      book.name = resp.data.result_list.name;
      book.author = resp.data.result_list.author;
      book.num = resp.data.result_list.num;
      book.ISBN = resp.data.result_list.ISBN;
      book.publish = resp.data.result_list.publish;
      book.remaining = resp.data.result_list.remaining;
      ElMessageBox.alert(message);
    }
    // 查询失败
    if (code == 400) {
      ElMessageBox.alert(message);
    }
  });
};

// 用户信息
const userInfoRef = ref<FormInstance>();
const userInfo = reactive({
  reader_id: "",
  book_id: "",
  reserve_time: "",
});

// 借阅图书按钮
const borrowBook = () => {
  let obj = {
    ISBN: searchBookInput.ISBN,
    reader_id: userInfo.reader_id,
    book_id: userInfo.book_id,
    reserve_time: userInfo.reserve_time,
  };
  axios.post("/api/manager/borrowbook/", obj).then((resp) => {
    const code = resp.data.code;
    const message = resp.data.message;

    // 借阅失败
    if (code == 400) {
      ElMessageBox.alert(message, {
        confirmButtonText: "确认",
      });
    }
    // 借阅成功
    if (code == 200) {
      ElMessageBox.alert(message, {
        confirmButtonText: "确认",
      });
    }
  });
};
</script>

<style lang="scss">
@import "@/assets/css/borrow";
</style>
