<template>
  <div class="user">
    <el-container>
      <el-main>
        <!--        用户操作栏-->
        <el-row class="user-header">
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
            <el-row>
              <el-col :span="18">
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
            </el-row>
          </el-col>
          <el-col :span="4" class="add-button-pane">
            <el-button
              type="success"
              :icon="Plus"
              class="add-button"
              @click="addFromButton(addUserFormRef)"
            >
              添加用户
            </el-button>
          </el-col>
        </el-row>
        <!--        用户表格栏-->
        <el-row class="user-table">
          <el-col>
            <el-table :data="users" height="100%" empty-text="没有数据">
              <el-table-column fixed prop="id" label="Id" width="50" />
              <el-table-column prop="avatarUrl" label="图书封面">
                <template #default="scope">
                  <el-image
                    :src="scope.row.avatarUrl"
                    style="width: 70px; height: 70px;"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="username" label="用户名" />
              <el-table-column prop="email" label="邮箱" />
              <el-table-column prop="points" label="积分" />
              <el-table-column prop="role" label="角色" /> 
              <el-table-column fixed="right" label="操作">
                <template #default="users">
                  <el-button
                    @click="editFromButton(editUserFormRef, users.row)"
                    type="text"
                    >编辑</el-button
                  >
                  <el-button
                    @click="deleteUserDialog(users.row)"
                    type="text"
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
              @current-change="page"
            >
            </el-pagination>
          </el-col>
        </el-row>

        <!--         添加用户表单 -->
        <el-dialog
          v-model="addUserFormVisible"
          title="添加用户"
          class="add-user-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
          <el-form
            :model="addUserForm"
            :rules="userRules"
            ref="addUserFormRef"
            class="add-user-form"
            ><el-form-item
              label="邮箱"
              :label-width="formLabelWidth"
              prop="username"
            >
              <el-input
                v-model="addUserForm.username"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="密码"
              :label-width="formLabelWidth"
              prop="password"
            >
              <el-input
                v-model="addUserForm.password"
                type="password"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="手机号"
              :label-width="formLabelWidth"
              prop="phone"
            >
              <el-input
                v-model.number="addUserForm.phone"
                autocomplete="off"
                maxlength="11"
              ></el-input>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="addUserFormVisible = false">取消</el-button>
              <el-button type="primary" @click="addUserButton(addUserFormRef)">
                添加
              </el-button>
            </span>
          </template>
        </el-dialog>

        <!--        编辑用户表单-->
        <el-dialog
          v-model="editUserFormVisible"
          title="编辑用户"
          class="edit-user-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
          <el-form
            :model="editUserForm"
            ref="editUserFormRef"
            class="edit-user-form"
          >
            <el-form-item
              label="用户名"
              :label-width="formLabelWidth"
              prop="username"
            >
              <el-input
                v-model="editUserForm.username"
                autocomplete="off"
                disabled
              ></el-input>
            </el-form-item>
            <el-form-item
              label="新用户名"
              :label-width="formLabelWidth"
              prop="newUserName"
            >
              <el-input
                v-model="editUserForm.newUserName"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="密码"
              :label-width="formLabelWidth"
              prop="password"
            >
              <el-input
                v-model="editUserForm.newPassword"
                type="password"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="积分"
              :label-width="formLabelWidth"
              prop="points"
            >
              <el-input
                v-model="editUserForm.points"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="editUserFormVisible = false">取消</el-button>
              <el-button
                type="primary"
                @click="editUserButton(editUserFormRef)"
              >
                编辑
              </el-button>
            </span>
          </template>
        </el-dialog>

        <!--        删除用户对话框-->
        <el-dialog
          v-model="deleteUserDialogVisible"
          title="删除用户"
          width="500px"
        >
          <span>确认删除 {{ deleteName }}</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="deleteUserDialogVisible = false"
                >取消</el-button
              >
              <el-button type="danger" @click="deleteUser"> 确认 </el-button>
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
import { ElMessageBox } from "element-plus";

// 标签长度
let formLabelWidth = 120;
// 显示数据数量选项
let pageNum = ref(1);
let pageSize = ref(10);
let pageTotal = ref(0);
const page = (val: number) => {
  pageNum.value = val;
  searchUser();
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
  searchUser();
};
// 搜索框数据
const searchInput = ref();
// 搜索框按钮
const searchButton = () => {
  pageNum.value = 1;
  searchUser();
};
// 搜索用户
const users = ref();
const searchUser = () => {
    axios.get("/admin/showAllAccounts", {params : {
      page: pageNum.value ,
      pageSize: pageSize.value,
    }})
      .then((resp) => {
        users.value = resp.data.data.items;
        pageTotal.value = resp.data.data.total;
;
      });
};

// 选项框属性
const UserType = ref([
  {
    typeId: 1,
    typeName: "admin",
    label: "管理员",
  },
  {
    typeId: 2,
    typeName: "user",
    label: "用户",
  },
]);
// 添加用户对话框显示
let addUserFormVisible = ref(false);
// 添加用户表单按钮
const addFromButton = (formEl: FormInstance | undefined) => {
  addUserFormVisible.value = true;
  if (!formEl) return;
  formEl.resetFields();
};

// 添加用户表单
const addUserFormRef = ref<FormInstance>();
let addUserForm = reactive({
  groups: "",
  username: "",
  password: "",
  name: "",
  gender: "",
  idCard: "",
  phone: "",
  identity: "",
});

// 添加用户按钮
const addUserButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      axios
        .post("http://localhost:8888/user/save", addUserForm)
        .then((resp) => {
          const code = resp.data.code;

          // 添加失败
          if (code == 0) {
            ElMessageBox.alert("添加用户失败，请重试", "信息", {
              confirmButtonText: "确认",
            });
          }
          // 添加成功
          if (code == 1) {
            ElMessageBox.alert("添加成功", "信息", {
              confirmButtonText: "确认",
              callback: () => {
                addUserFormVisible.value = false;
              },
            });
          }
          // 用户名存在
          if (code == 2) {
            ElMessageBox.alert("添加失败，此用户名已存在", "信息", {
              confirmButtonText: "确认",
            });
          }
          // 借书卡存在
          if (code == 3) {
            ElMessageBox.alert("添加失败，此借书卡已存在", "信息", {
              confirmButtonText: "确认",
            });
          }
          // 手机号存在
          if (code == 4) {
            ElMessageBox.alert("添加失败，此手机号已存在", "信息", {
              confirmButtonText: "确认",
            });
          }
        });
    } else {
      return false;
    }
  });
};

// 编辑用户对话框
let editUserFormVisible = ref(false);
const editFromButton = (formEl: FormInstance | undefined, row: number) => {
  editUserFormVisible.value = true;
  editUserForm = reactive(JSON.parse(JSON.stringify(row)));
  if (!formEl) return;
  formEl.resetFields();
};

// 编辑用户表单
const editUserFormRef = ref<FormInstance>();
let editUserForm = reactive({
  username: "",
  newUserName: "",
  newPassword: "",
  points: "",
});

// 编辑用户按钮
const editUserButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      let obj = {
        oldUserName: editUserForm.username,
        newUserName: editUserForm.newUserName,
        newPassword: editUserForm.newPassword,
        points: editUserForm.points,
      }
      axios.post("/admin/updateUserInfo", obj).then((resp) => {
          const code = resp.data.code;
          // 编辑失败
          if (code == 400) {
            ElMessageBox.alert("编辑用户失败，请重试", "信息", {
              confirmButtonText: "确认",
            });
          }
          // 编辑成功
          if (code == 200) {
            ElMessageBox.alert("编辑成功", "信息", {
              confirmButtonText: "确认",
              callback: () => {
                editUserFormVisible.value = false;
              },
            });
          }
        })
      }
    })
};

// 删除用户
let deleteName = ref("");
let deleteId = ref(0);
let deleteUserDialogVisible = ref(false);
const deleteUserDialog = (row: any) => {
  deleteId.value = row.id;
  deleteName.value = row.username;
  deleteUserDialogVisible.value = true;
};

// 删除用户按钮
const deleteUser = () => {
  if (deleteId.value) {
    axios
      .post("http://localhost:8888/user/delete/" + deleteId.value)
      .then((resp) => {
        const code = resp.data.code;

        // 删除失败
        if (code == 0) {
          ElMessageBox.alert("删除用户失败，请重试", "信息", {
            confirmButtonText: "确认",
          });
        }
        // 删除成功
        if (code == 1) {
          ElMessageBox.alert("删除成功", "信息", {
            confirmButtonText: "确认",
            callback: () => {
              deleteUserDialogVisible.value = false;
            },
          });
        }
        // Id 不存在
        if (code == 2) {
          ElMessageBox.alert("删除失败，此 Id 不存在", "信息", {
            confirmButtonText: "确认",
          });
        }
      });
  }
};

// 初始化
const init = () => {
  searchUser();
};
init();
</script>

<style lang="scss">
@import "@/assets/css/user";
</style>
