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
    
          <el-col :span="6" class="add-button-pane">
            <el-button
              type="success"
              :icon="Plus"
              class="add-button"

              @click="addFromButton(addBookFormRef)"
            >
              添加公共图书分类
            </el-button>
          </el-col>
        </el-row>
        <!--        图书表格栏-->
        <el-row class="book-table">
          <el-col>
            <el-table :data="books" height="100%" empty-text="没有数据">
              <el-table-column fixed prop="categoryId" label="Id" width="100" />
              <el-table-column prop="categoryName" label="分类名称" width="200" />
              <el-table-column prop="categoryAlias" label="分类别名" width="200" />
              <el-table-column prop="categoryClickNum" label="分类点击量" width="150" />
              <el-table-column prop="categoryDownloadNum" label="分类下载量" width="150" />
              <el-table-column fixed="right" label="操作" width="150">
                <template #default="books">
                  <el-button
                    @click="editFromButton(books.$index, books.row)"
                    type="text"
                    >编辑</el-button
                  >
                  <el-button @click="deleteBookDialog(books.$index, books.row)" type="text"
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
              :total=pageTotal
              @current-change="page"
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
          <el-form
            :model="addBookForm"
            :rules="bookRules"
            ref="addBookFormRef"
            class="add-book-form"
          >
            <el-form-item
              label="分类名称"
              :label-width="formLabelWidth"
              prop="categoryName"
            >
              <el-input
                v-model="addBookForm.categoryName"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="分类别名"
              :label-width="formLabelWidth"
              prop="categoryAlias"
            >
              <el-input
                v-model="addBookForm.categoryAlias"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="addBookFormVisible = false">取消</el-button>
              <el-button type="primary" @click="addBookButton(addBookFormRef)">
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
          <el-form
            :model="editBookForm"
            :rules="bookRules"
            ref="editBookFormRef"
            class="edit-book-form"
          >
          <!-- <el-form-item
              label="图书分类ID"
              :label-width="formLabelWidth"
              prop="categoryId"
            >
              <el-input
                v-model="editBookForm.categoryId"
                autocomplete="off"
              ></el-input>
            </el-form-item> -->
          <el-form-item
              label="分类名称"
              :label-width="formLabelWidth"
              prop="categoryName"
            >
              <el-input
                v-model="editBookForm.categoryName"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="分类别名"
              :label-width="formLabelWidth"
              prop="categoryAlias"
            >
              <el-input
                v-model="editBookForm.categoryAlias"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="editBookFormVisible = false">取消</el-button>
              <el-button
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
          <span>确认删除 {{ deleteList.bookIds }}</span>
          <div>
            <span>分类名称 {{ deleteList.categoryName }}</span>
          </div>
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
import { reactive, ref, onMounted } from "vue";
import type { FormInstance, FormRules } from "element-plus";
import { Plus, Search } from "@element-plus/icons-vue";
import axios from "axios";
import { ElMessageBox, ElMessage } from "element-plus";
import router from "@/router";
// 标签长度
let formLabelWidth = 120;
// 获取图书数据
let books = ref();
// 查询条件数据
let searchObj = reactive({});
// 显示数据数量选项
let pageNum = ref(1);
let pageSize = ref(20);
let pageTotal = ref(0);
const page = (val: number) => {
  pageNum.value = val;
  searchBook();
};

// 数据显示框
let sizeOptions = [
  {
    value: 20,
    label: "20条数据/页",
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

// 搜索图书
const searchBook = () => {
  axios.get("/category",{
      params:{
      page: pageNum.value,
      pageSize: pageSize.value}
    }).then((resp) => {
      books.value = resp.data;
      pageTotal.value = resp.data.total_count;
      const code = resp.data.code;
      const message = resp.data.message;
      // // 查询失败
      // if (code == 400) {
      //   ElMessage({
      //     message: message,
      //     type: "error",
      //   });
      // }
      // // 查询成功
      // if (code == 200) {
      //   ElMessage({
      //     message: message,
      //     type: "success",
      //   });
      // }
    });
};

// 图书表单规则
const bookRules = reactive<FormRules>({
  categoryName: [{ required: true, message: "请输入分类名称", trigger: "blur" }],
  categoryAlias: [{ required: true, message: "请输入分类别名", trigger: "blur" }],
  bookId: [{ required: true, message: "请输入作者名称", trigger: "blur" }],
});

// 添加图书对话框显示
let addBookFormVisible = ref(false);
// 添加表单按钮
const addFromButton = (formEl: FormInstance | undefined) => {
  addBookFormVisible.value = true;
  if (!formEl) return;
  formEl.resetFields();
};

// 添加图书表单
const addBookFormRef = ref<FormInstance>();
let addBookForm = reactive({
  categoryName: "",
  categoryAlias: "",
});

// 添加图书按钮
const addBookButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      axios
        .post("/category", addBookForm)
        .then((resp) => {
          const code = resp.data.code;

          // 添加失败
          // if (code == ) {
          //   ElMessageBox.alert("添加图书种类失败，请重试", "信息", {
          //     confirmButtonText: "确认",
          //   });
          // }
          // 添加成功
          if (code == 200) {
            ElMessageBox.alert("添加图书种类成功", "信息", {
              confirmButtonText: "确认",
              callback: () => {
                addBookFormVisible.value = false;
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

// 编辑图书对话框
let editBookFormVisible = ref(false);
const editFromButton = (index: number, row: any) => {
  editBookFormVisible.value = true;
  editBookForm = reactive(JSON.parse(JSON.stringify(row)));
};

// 编辑图书表单
const editBookFormRef = ref<FormInstance>();
let editBookForm = reactive({
  categoryName: "",
  categoryAlias: "",
  categoryId: "",
});

// 编辑图书按钮
const editBookButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      axios.put("/category", editBookForm).then((resp) => {
        const code = resp.data.code;
        const message = resp.data.message;
        // // 编辑失败
        // if (code == 400) {
        //   ElMessageBox.alert(message, {
        //     confirmButtonText: "确认",
        //   });
        // }
        // 编辑成功
        if (code == 200) {
          ElMessageBox.alert(message, {
            confirmButtonText: "确认",
            callback: () => {
              editBookFormVisible.value = false;
              searchBook();
            },
          });
        }
      });
    } else {
      return false;
    }
  });
};

// 删除图书
let deleteList = reactive({
  bookIds: "",
  categoryName: "",
});
let deleteBookDialogVisible = ref(false);
const deleteBookDialog = (index: number, row: any) => {
  deleteList.bookIds = row.categoryId;
  deleteList.categoryName = row.categoryName;
  deleteBookDialogVisible.value = true;
};

// 删除图书按钮
const deleteBook = () => {
  if (deleteList.bookIds) {
    axios.delete(`/category/${deleteList.bookIds}`).then((resp) => {
      const code = resp.data.code;
      const message = resp.data.message;
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
      // Id 不存在// 删除失败
      if (code == 400) {
        ElMessageBox.alert(message, {
          confirmButtonText: "确认",
        });
      }
    });
  }
};

// 初始化
function init(){
  // 获取图书种类
  searchBook();
};
onMounted(() => {
  init();
  // 在这里可以执行其他操作，确保 initData 函数执行完成后再执行
});  
</script>

<style lang="scss">
@import "@/assets/css/book";
</style>
