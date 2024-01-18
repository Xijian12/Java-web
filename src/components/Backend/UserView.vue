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
          <div style="margin-top: 50px">
            <el-form :model="addUserForm" :rules="rules" @validate="onValidate"  ref="addUserFormRef">
                <el-form-item prop="username">
                    <el-input v-model="addUserForm.username" :maxlength="8" type="text" placeholder="用户名">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="addUserForm.password" :maxlength="16" type="password" placeholder="密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password_repeat">
                    <el-input v-model="addUserForm.password_repeat" :maxlength="16" type="password" placeholder="重复密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="email">
                    <el-input v-model="addUserForm.email" type="email" placeholder="电子邮件地址">
                        <template #prefix>
                            <el-icon><Message /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="code">
                    <el-row :gutter="10" style="width: 100%">
                        <el-col :span="17">
                            <el-input v-model="addUserForm.code" :maxlength="6" type="text" placeholder="请输入验证码">
                                <template #prefix>
                                    <el-icon><EditPen /></el-icon>
                                </template>
                            </el-input>
                        </el-col>
                        <el-col :span="5">
                            <el-button type="success" @click="validateEmail"
                                       :disabled="!isEmailValid || coldTime > 0">
                                {{coldTime > 0 ? '请稍后 ' + coldTime + ' 秒' : '获取验证码'}}
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form-item>
            </el-form>
        </div>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="addUserFormVisible = false">取消</el-button>
              <el-button type="primary" @click="addUserButton(addUserFormRef)">
                立即注册
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
import { ElMessageBox, ElMessage  } from "element-plus";
import {get, post} from "@/net";
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

// 添加用户对话框显示
let addUserFormVisible = ref(false);
// 添加用户表单按钮
const addFromButton = (formEl: FormInstance | undefined) => {
  addUserFormVisible.value = true;
  if (!formEl) return;
  formEl.resetFields();
};

// 添加用户表单
const validateUsername = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
        callback(new Error('用户名不能包含特殊字符，只能是中文/英文'))
    } else {
        callback()
    }
}
const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== addUserForm.password) {
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
}
const rules = {
    username: [
        { validator: validateUsername, trigger: ['blur', 'change'] },
        { min: 2, max: 8, message: '用户名的长度必须在2-8个字符之间', trigger: ['blur', 'change'] },
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 16, message: '密码的长度必须在6-16个字符之间', trigger: ['blur', 'change'] }
    ],
    password_repeat: [
        { validator: validatePassword, trigger: ['blur', 'change'] },
    ],
    email: [
        { required: true, message: '请输入邮件地址', trigger: 'blur' },
        {type: 'email', message: '请输入合法的电子邮件地址', trigger: ['blur', 'change']}
    ],
    code: [
        { required: true, message: '请输入获取的验证码', trigger: 'blur' },
    ]
}
const onValidate = (prop, isValid) => {
    if(prop === 'email')
        isEmailValid.value = isValid
}
const addUserFormRef = ref<FormInstance>();
let addUserForm = reactive({
  password_repeat: "",
  username: "",
  password: "",
  name: "",
  gender: "",
  email: "",
  code: "",
});

// 添加用户按钮
const addUserButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
        if(valid) {
            post('/api/auth/register', {
                username: addUserForm.username,
                password: addUserForm.password,
                email: addUserForm.email,
                code: addUserForm.code
            }, () => {
                ElMessage.success('注册成功，欢迎加入我们')
                addUserFormVisible.value = false;
            })
        } else {
            ElMessage.warning('请完整填写注册表单内容！')
        }
    })
};
const isEmailValid = ref(false)
const coldTime = ref(0)
// 邮箱验证
const validateEmail = () => {
    coldTime.value = 60
    get(`/api/auth/ask-code?email=${addUserForm.email}&type=register`, () => {
        ElMessage.success(`验证码已发送到邮箱: ${addUserForm.email}，请注意查收`)
        const handle = setInterval(() => {
          coldTime.value--
          if(coldTime.value === 0) {
            clearInterval(handle)
          }
        }, 1000)
    }, undefined, (message) => {
        ElMessage.warning(message)
        coldTime.value = 0
    })
}
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
let deleteEmail = ref("");
let deleteUserDialogVisible = ref(false);
const deleteUserDialog = (row: any) => {
  deleteEmail.value = row.email;
  deleteName.value = row.username;
  deleteUserDialogVisible.value = true;
};

// 删除用户按钮
const deleteUser = () => {
  if (deleteEmail.value) {
    axios.get("admin/deleteUser?email=" + deleteEmail.value)
      .then((resp) => {
        const code = resp.data.code;
        // 删除失败
        if (code == 400) {
          ElMessageBox.alert("删除用户失败，请重试", "信息", {
            confirmButtonText: "确认",
          });
        }
        // 删除成功
        if (code == 200) {
          ElMessageBox.alert("删除成功", "信息", {
            confirmButtonText: "确认",
            callback: () => {
              deleteUserDialogVisible.value = false;
            },
          });
          searchUser();
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
