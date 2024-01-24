<template>
  <div class="book">
    <el-container>
      <el-main>
        <!--        图书操作栏-->
        <el-row class="book-header">
          <el-col :span="4" class="search-page-pane">
            <el-select
              v-model="pageSize"
              placeholder="数据显示"
              @change="changeSize"
              class="search-size"
            >
              <el-option
                v-for="item in sizeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="16" class="search-input-pane">
            <!-- <el-row>
              <el-col :span="4">
                <el-select
                  v-model="searchModel"
                  placeholder="搜索类型"
                  @change="changeSearch"
                  class="search-size"
                >
                  <el-option
                    v-for="item in searchOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="14">
                <el-input
                  placeholder="请输入搜索内容"
                  v-model="searchInput"
                  :suffix-icon="Search"
                  class="search-input"
                >
                </el-input>
              </el-col>
              <el-col :span="6">
                <el-button
                  type="primary"
                  class="search-button"
                  @click="searchButton"
                >
                  搜索
                </el-button>
              </el-col>
            </el-row> -->
          </el-col>
          <el-col :span="4" class="add-button-pane">
            <el-button
              type="success"
              :icon="Plus"
              class="add-button"
              @click="addFromButton(addBookFormRef)"
            >
              上传图书
            </el-button>
          </el-col>
        </el-row>
        <!--        图书表格栏-->
        <el-row class="book-table">
          <el-col>
            <el-table :data="books" height="100%" empty-text="没有数据">
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
              <el-table-column prop="bookPublishHouse" label="出版社" />
              <el-table-column prop="bookUploader" label="上传者" />
              <el-table-column prop="bookDownloadNum" label="下载量" />
              <el-table-column prop="bookClickNum" label="点击量" />
              <el-table-column prop="categoryName" label="分类名称" />
              <el-table-column prop="categoryAlias" label="分类别名" />
              <el-table-column fixed="right" label="操作" width="100">
                <template #default="books">
                  <el-button
                    @click="editFromButton(editBookFormRef, books.row)"
                    type="text"
                    >编辑</el-button
                  >
                  <el-button @click="deleteBookDialog(books.row)" type="text"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              background
              v-model:current-page="pageNum"
              v-model:page-size="pageSize"
              layout="prev, pager, next"
              :total="pageTotal"
              @current-change="pageChange"
            >
            </el-pagination>
          </el-col>
        </el-row>

        <!--         添加图书表单 -->
        <el-dialog
          v-model="addBookFormVisible"
          title="添加书籍"
          class="add-book-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
        <el-form   :model="addBookForm"
            :rules="rules"
            ref="addBookFormRef"
            class="add-book-form">
        <el-form-item label="图书名" style="width:350px" prop="bookName">
         <el-input v-model="addBookForm.bookName"></el-input>
      </el-form-item>
      <el-form-item label="图书作者" style="width:350px" prop="bookAuthor">
         <el-input v-model="addBookForm.bookAuthor"></el-input>
      </el-form-item>
      <el-form-item label="图书版号" style="width:350px" prop="bookVersion">
         <el-input v-model="addBookForm.bookVersion"></el-input>
      </el-form-item>
      <el-form-item label="图书出版社" style="width:350px" prop="bookPublishHouse">
         <el-input v-model="addBookForm.bookPublishHouse"></el-input>
      </el-form-item>
      <el-form-item label="下载所需积分" style="width:350px" prop="bookPoints">
         <el-input v-model="addBookForm.bookPoints"></el-input>
      </el-form-item>
      <el-form-item label="图书类别" prop="categoryName" >
        <el-select v-model="addBookForm.categoryName"  placeholder="请选择图书分类">
          <el-option v-for="item in categoryList" :key="item.categoryName" :label="item.categoryName"
            :value="item.categoryName"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="图书简介" prop="bookProfile">
        <el-input type="textarea" v-model="addBookForm.bookProfile"></el-input>
      </el-form-item>
      <el-form-item label="图书封面上传" prop="pictures">
        <el-upload class="box_upload"  action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
          accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="图书文件上传" prop="files">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadFileBook"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
        </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="addBookFormVisible = false">取消</el-button>
              <el-button type="primary" :loading="isLoading"  @click="onSubmit()">
                添加
              </el-button>
            </span>
          </template>
        </el-dialog>

        <!--        编辑图书表单-->
        <el-dialog
          v-model="editBookFormVisible"
          title="编辑书籍"
          class="edit-book-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
        <el-form   :model="editBookForm"
            :rules="rules"
            ref="editBookFormRef"
            class="add-book-form">
        <el-form-item label="图书名" style="width:350px" prop="bookName">
         <el-input v-model="editBookForm.bookName"></el-input>
      </el-form-item>
      <el-form-item label="图书作者" style="width:350px" prop="bookAuthor">
         <el-input v-model="editBookForm.bookAuthor"></el-input>
      </el-form-item>
      <el-form-item label="图书版号" style="width:350px" prop="bookVersion">
         <el-input v-model="editBookForm.bookVersion"></el-input>
      </el-form-item>
      <el-form-item label="图书出版社" style="width:350px" prop="bookPublishHouse">
         <el-input v-model="editBookForm.bookPublishHouse"></el-input>
      </el-form-item>
      <el-form-item label="下载所需积分" style="width:350px" prop="bookPoints">
         <el-input v-model="editBookForm.bookPoints"></el-input>
      </el-form-item>
      <el-form-item label="图书类别" prop="categoryName" >
        <el-select v-model="editBookForm.categoryName"  placeholder="请选择图书分类">
          <el-option v-for="item in categoryList" :key="item.categoryName" :label="item.categoryName"
            :value="item.categoryName"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="图书简介" prop="bookProfile">
        <el-input type="textarea" v-model="editBookForm.bookProfile"></el-input>
      </el-form-item>
      <el-form-item label="图书封面上传" prop="pictures">
        <el-upload class="box_upload"  action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
          accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="图书文件上传" prop="files">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadFileBook"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
        </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="editBookFormVisible = false">取消</el-button>
              <el-button
              :loading="isLoading" 
                type="primary"
                @click="editBookButton(editBookFormRef)"
              >
                编辑
              </el-button>
            </span>
          </template>
        </el-dialog>

        <!--        删除图书对话框-->
        <el-dialog
          v-model="deleteBookDialogVisible"
          title="删除图书"
          width="500px"
        >
          <span>确认删除 {{ deleteName }}</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="deleteBookDialogVisible = false"
                >取消</el-button
              >
              <el-button type="danger" @click="deleteBook"> 确认 </el-button>
            </span>
          </template>
        </el-dialog>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { FormInstance, FormRules } from "element-plus";
import { Plus, Search } from "@element-plus/icons-vue";
import axios from "axios";
import { ElMessageBox, ElMessage } from "element-plus";
import {useStore} from 'vuex';
const isLoading = ref(false);
const store = useStore();
const usereamil = ref(store.state.personalID[0].email)
const categoryList = ref([{
    categoryId:"",
    categoryName:"",
  }]);

// 获取图书数据
let books = ref();

// 显示数据数量选项
let pageNum = ref(1);
let pageSize = ref(10);
let pageTotal = ref(0);
const pageChange = (val: number) => {
  pageNum.value = val;
  searchBook();
};
const Nindex = (index: number) => {
      // 当前页数 - 1 * 每页数据条数 + 1
      const page = pageNum.value // 当前页码
      const pagesize = pageSize.value // 每页条数
      return index + 1 + (page - 1) * pagesize
};
// 数据显示框
let sizeOptions = [
  {
    value: 10,
    label: "10条数据/页",
  },
  {
    value: 50,
    label: "50条数据/页",
  },
  {
    value: 100,
    label: "100条数据/页",
  },
];
// 修改显示数据量
const changeSize = (value: number) => {
  pageSize.value = value;
  searchBook();
};

// 搜索框选项
let searchModel = ref("分类名称");
let searchOptions = [
  {
    value: "categoryName",
    label: "分类名称",
  },
];
const changeSearch = (value: string) => {
  searchModel.value = value;
};

// 搜索框数据
const searchInput = ref("");
// 搜索图书
const searchBook = () => {
  axios.get("/book/userUpload",{
      params:{
      userEmail: usereamil.value,
      page: pageNum.value,
      pageSize: pageSize.value}
    }).then((resp)=>{
      books.value = resp.data.data.content
      pageTotal.value = resp.data.data.totalElements
    }) 
};

// 添加图书对话框显示
const rules = ref(
  {
    bookName: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookAuthor: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookVersion: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookPublishHouse: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    downloadPoints: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    bookProfile: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    categoryName: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    // files: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
    // pictures: [{ required: true, message: '请输入图书名！', trigger: 'blur' },],
  })
let addBookFormVisible = ref(false);
// 添加表单按钮
const addFromButton = (formEl: FormInstance | undefined) => {
  addBookFormVisible.value = true;
  if (!formEl) return;
  formEl.resetFields();
};

// 添加图书表单
const addBookFormRef = ref<FormInstance>();
const addBookForm = reactive<any>(
    {
      pictures: [],
      files: [],
      bookName: "",
      bookAuthor: "",
      bookVersion: "",
      bookPublishHouse: "",
      bookCoverUrl: "",
      bookPoints: "",
      bookProfile: "",
      categoryName: "",
      bookUploader: usereamil.value,
      bookCoverUuid: "",
      bookFileUuid: "",
})

function uploadFile(param) {
  isLoading.value = true;
    const uploadForm1 = new FormData();
    uploadForm1.append('bookCover', param.file);
    // 发送上传请求
    axios.post("/upload/uploadBookcover", uploadForm1)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          addBookForm.bookCoverUrl = response.data.data[0]
          addBookForm.bookCoverUuid = response.data.data[1]
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
}
function uploadFileBook(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('bookFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadBook", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          addBookForm.bookFileUuid = response.data.data
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
}
function exceed(files, fileList) {
    return ElMessage.error("最多上传4张图片！");
}
function imgPreview(file, fileList) {
              let fileName = file.name;
              let regex = /(.jpg|.jpeg|.png)$/;
              if (regex.test(fileName.toLowerCase())) {
                  let picUrl = URL.createObjectURL(file.raw);
                  addBookForm.files = fileList;
              } else {
                  //移除最后一个元素
                  fileList.pop();
                  ElMessage.error('请注意图片格式！');
              }
}
function beforeAvatarUpload(file) { 
              const isLt2M = file.size / 1024 / 1024 < 4;
              if (!isLt2M) {
                ElMessage({
                      message: '上传文件大小不能超过 4MB!',
                      type: 'warning'
                  });
              }
              return isLt2M
}
//提交方法
function onSubmit() {    
    // 发送上传请求
    axios.post("/book",addBookForm)
        .then(response => {
          const code = response.data.code
          const message = response.data.message
           // 添加失败
          if (code == 400) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
            });
          }
          // 添加成功
          if (code == 200) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",

          })
          addBookFormVisible.value = false;
          searchBook();
        }
      }).catch(error => {
          // 处理错误
          console.error(error);
        });

} 

// 编辑图书对话框
let editBookFormVisible = ref(false);
const editFromButton = (formEl: FormInstance | undefined, row: any) => {
  editBookFormVisible.value = true;
  editBookForm = reactive(JSON.parse(JSON.stringify(row)));
  editBookForm.downloadPoints = row.bookPoints;
  if (!formEl) return;
  formEl.resetFields();
};

// 编辑图书表单
const editBookFormRef = ref<FormInstance>();
let editBookForm = reactive<any>(
    {
      pictures: [],
      files: [],
      bookName: "",
      bookAuthor: "",
      bookVersion: "",
      bookPublishHouse: "",
      bookCoverUrl: "",
      downloadPoints: "",
      bookProfile: "",
      categoryName: "",
      bookUploader: usereamil.value,
      bookCoverUuid: "",
      bookFileUuid: "",
})

// 编辑图书按钮
const editBookButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      axios.put("/book/admin", editBookForm)
        .then((resp) => {
          const code = resp.data.code;
          const message = resp.data.message;

          // 编辑失败
          if (code == 400) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
            });
          }
          // 编辑成功
          if (code == 200) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
              callback: () => {
                editBookFormVisible.value = false;
              },
            });
            searchBook();
          }
        });
    } else {
      return false;
    }
  });
};

// 删除图书
let deleteName = ref("");
let deleteId = reactive({
  book_id: "",
});
let deleteBookDialogVisible = ref(false);
const deleteBookDialog = (row: any) => {
  deleteId.book_id = row.bookId;
  deleteName.value = row.bookName;
  deleteBookDialogVisible.value = true;
};

// 删除图书按钮
const deleteBook = () => {
  if (deleteId.book_id != "") {
    axios.delete("/book/user", {data:{
      userEmail:usereamil.value,
      bookIds:[deleteId.book_id]}}).then((resp) => {
      const code = resp.data.code;
      const message = resp.data.message;

      // 删除失败
      if (code == 400) {
        ElMessageBox.alert(message, {
          confirmButtonText: "确认",
        });
      }
      // 删除成功
      if (code == 200) {
        ElMessageBox.alert(message, {
          confirmButtonText: "确认",
          callback: () => {
            deleteBookDialogVisible.value = false;
            searchBook();
          },
        });
      }
    });
  }
};
function initData() {
  // 发起简单的 GET 请求
 axios.get("/category",{
      params:{
      page: 1,
      pageSize: 200}
    })
  .then(response => {
    // 处理成功的响应
    categoryList.value = response.data
  })
  .catch(error => {
    // 处理请求错误
    console.error('请求失败：', error);
  });
     
    }

// 初始化
const init = () => {
  // 获取图书
  initData();
  searchBook();
};
init();
</script>

<style lang="scss">
@import "@/assets/css/book";
.add-book-form{
      width: 50%;
      margin-left: 10%;
      padding: 30px;
}
</style>
