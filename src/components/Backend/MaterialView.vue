<template>
  <div class="book">
    <el-container>
      <el-main>
        <!--        资料操作栏-->
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
            <el-row>
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
            </el-row>
          </el-col>
          <el-col :span="4" class="add-button-pane">
            <el-button
              type="success"
              :icon="Plus"
              class="add-button"
              @click="addFromButton(addBookFormRef)"
            >
              添加资料
            </el-button>
          </el-col>
        </el-row>
        <!--        资料表格栏-->
        <el-row class="book-table">
          <el-col>
            <el-table :data="materials" height="100%" empty-text="没有数据">
              <el-table-column type="index" :index="Nindex" label="序号" width="70px"/>
              <el-table-column prop="materialCoverUrl" label="资料封面">
                <template #default="scope">
                  <el-image
                    :src="scope.row.materialCoverUrl"
                    style="width: 70px; height: 70px;"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="school" label="学校" />
              <el-table-column prop="major" label="专业"/> 
              <el-table-column prop="subject" label="学科" >
                <template v-slot="{ row }">
                  <router-link
                    :to="`/MaterialDetail/${row.materialId}`"
                    >
                    {{ row.subject }}
                  </router-link>
                </template>
              </el-table-column>
              <el-table-column prop="materialUploader" label="上传者" />
              <el-table-column prop="materialDownloadNum" label="下载量" />
              <el-table-column prop="materialClickNum" label="点击量" />
              <el-table-column prop="elecBookPoints" label="课程电子下载所需积分" />
              <el-table-column prop="teachingPlanPoints" label="教学计划下载所需积分" />
              <el-table-column prop="classPptPoints" label="课程PPT下载所需积分" />
              <el-table-column prop="calendarVolumePoints" label="历年卷下载所需积分" />
              <el-table-column prop="anotherMaterialPoints" label="其他资料下载所需积分" />
              <el-table-column fixed="right" label="操作" width="100">
                <template #default="materials">
                  <el-button
                    @click="editFromButton(editBookFormRef, materials.row)"
                    type="text"
                    >编辑</el-button
                  >
                  <el-button @click="deleteBookDialog(materials.row)" type="text"
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

        <!--         添加资料表单 -->
        <el-dialog
          v-model="addBookFormVisible"
          title="添加资料"
          class="add-book-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
        <el-form :model="materialForm" :rules="rules" label-width="150px">
      <el-form-item label="学校" style="width:350px" prop="school">
        <el-input v-model="materialForm.school"></el-input>
      </el-form-item>
      <el-form-item label="专业" style="width:350px" prop="major">
        <el-input v-model="materialForm.major"></el-input>
      </el-form-item>
      <el-form-item label="学科" style="width:350px" prop="subject">
        <el-input v-model="materialForm.subject"></el-input>
      </el-form-item>
      <el-form-item label="资料简介" prop="materialProfile">
        <el-input type="textarea" v-model="materialForm.materialProfile"></el-input>
      </el-form-item>
      <el-form-item label="资料封面上传" prop="materialCoverUrl">
        <el-upload class="box_upload"  action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
          accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程电子书上传" prop="elecBookUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadElecBook"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程电子书下载所需积分:" label-width="200px" style="width:350px" prop="elecBookPoints">
         <el-input v-model="materialForm.elecBookPoints"></el-input>
      </el-form-item>
      <el-form-item label="教学计划上传" prop="teachingPlanUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadTeachingPlan"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="教学计划下载所需积分:" label-width="200px"  style="width:350px" prop="teachingPlanPoints">
         <el-input v-model="materialForm.teachingPlanPoints"></el-input>
      </el-form-item>
      <el-form-item label="课程PPT上传" prop="classPptUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadClassPpt"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程PPT下载所需积分:" label-width="200px"  style="width:350px" prop="classPptPoints">
         <el-input v-model="materialForm.classPptPoints"></el-input>
      </el-form-item>
      <el-form-item label="历年卷上传" prop="calendarVolumeUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadCalendarVolume"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="历年卷下载所需积分:" label-width="200px"  style="width:350px" prop="calendarVolumePoints">
         <el-input v-model="materialForm.calendarVolumePoints"></el-input>
      </el-form-item>
      <el-form-item label="其他资料上传" prop="anotherMaterialUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadAnotherMaterial"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="其他资料下载所需积分:" label-width="200px"    style="width:350px" prop="anotherMaterialPoints">
         <el-input v-model="materialForm.anotherMaterialPoints"></el-input>
      </el-form-item>
        </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="addBookFormVisible = false">取消</el-button>
              <el-button :loading="isLoading"  type="primary" @click="onSubmit()">
                添加
              </el-button>
            </span>
          </template>
        </el-dialog>

        <!--        编辑资料表单-->
        <el-dialog
          v-model="editBookFormVisible"
          title="编辑资料"
          class="edit-book-dialog"
          width="500px"
          :close-on-click-modal="false"
        >
        <el-form :model="editBookForm" :rules="rules" ref="editBookFormRef" label-width="150px">
      <el-form-item label="学校" style="width:350px" prop="school">
        <el-input v-model="editBookForm.school"></el-input>
      </el-form-item>
      <el-form-item label="专业" style="width:350px" prop="major">
        <el-input v-model="editBookForm.major"></el-input>
      </el-form-item>
      <el-form-item label="学科" style="width:350px" prop="subject">
        <el-input v-model="editBookForm.subject"></el-input>
      </el-form-item>
      <el-form-item label="资料简介" prop="materialProfile">
        <el-input type="textarea" v-model="editBookForm.materialProfile"></el-input>
      </el-form-item>
      <el-form-item label="资料封面上传" prop="materialCoverUrl">
        <el-upload class="box_upload"  action="/upload/uploadBookcover" name="bookCover" :http-request="uploadFile"
          accept="" list-type="picture" :limit="4" :on-exceed="exceed" ref="uploadRef" 
          :show-file-list="true" :on-change="imgPreview" :before-upload="beforeAvatarUpload" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程电子书上传" prop="elecBookUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadElecBook"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程电子书下载所需积分:" label-width="200px" style="width:350px" prop="elecBookPoints">
         <el-input v-model="editBookForm.elecBookPoints"></el-input>
      </el-form-item>
      <el-form-item label="教学计划上传" prop="teachingPlanUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadTeachingPlan"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="教学计划下载所需积分:" label-width="200px"  style="width:350px" prop="teachingPlanPoints">
         <el-input v-model="editBookForm.teachingPlanPoints"></el-input>
      </el-form-item>
      <el-form-item label="课程PPT上传" prop="classPptUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadClassPpt"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程PPT下载所需积分:" label-width="200px"  style="width:350px" prop="classPptPoints">
         <el-input v-model="editBookForm.classPptPoints"></el-input>
      </el-form-item>
      <el-form-item label="历年卷上传" prop="calendarVolumeUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadCalendarVolume"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="历年卷下载所需积分:" label-width="200px"  style="width:350px" prop="calendarVolumePoints">
         <el-input v-model="editBookForm.calendarVolumePoints"></el-input>
      </el-form-item>
      <el-form-item label="其他资料上传" prop="anotherMaterialUuid">
        <el-upload class="box_upload"  action="/upload/uploadBook" name="bookFile" :http-request="uploadAnotherMaterial"
          accept="" list-type="pdf" :limit="4" :on-exceed="exceed" ref="uploadBookRef" 
          :show-file-list="true" multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="其他资料下载所需积分:" label-width="200px"    style="width:350px" prop="anotherMaterialPoints">
         <el-input v-model="editBookForm.anotherMaterialPoints"></el-input>
      </el-form-item>
        </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="editBookFormVisible = false">取消</el-button>
              <el-button
                type="primary"
                :loading="isLoading" 
                @click="editBookButton(editBookFormRef)"
              >
                编辑
              </el-button>
            </span>
          </template>
        </el-dialog>

        <!--        删除资料对话框-->
        <el-dialog
          v-model="deleteBookDialogVisible"
          title="删除资料"
          width="500px"
        >
          <span>确认删除</span>
          <el-form  label-width="150px">
            <el-form-item label="资料类型：" style="width:350px" prop="type">
              <el-select
              v-model="materialType"
              class="search-size"
            >
              <el-option
                v-for="item in materialTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            </el-form-item>
          </el-form>
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
import type { FormInstance, FormRules, UploadInstance } from "element-plus";
import { Plus, Search } from "@element-plus/icons-vue";
import axios from "axios";
import { ElMessageBox, ElMessage } from "element-plus";
import {useStore} from 'vuex';
const isLoading = ref(false);
const store = useStore();
const usereamil = ref(store.state.personalID[0].email)
const username = ref(store.state.personalID[0].username)
const uploadRef = ref<UploadInstance>()
const uploadBookRef = ref<UploadInstance>()
// 获取资料数据
let materials = ref();
// 显示数据数量选项
let pageNum = ref(1);
let pageSize = ref(6);
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
  {
    value: 6,
    label: "6条数据/页",
  }
];
// 修改显示数据量
const changeSize = (value: number) => {
  pageSize.value = value;
  searchBook();
};
// 搜索框选项
let searchModel = ref("学校");
let searchOptions = [
  {
    value: "学校",
    label: "学校",
  },
  {
    value: "专业",
    label: "专业",
  },
  {
    value: "学科",
    label: "学科",
  },
];
const changeSearch = (value: string) => {
  searchModel.value = value;
};
// 搜索框数据
const searchInput = ref("");
// 搜索框按钮
const searchButton = () => {
  pageNum.value = 1;
  searchBook();
};
// 搜索资料
function searchBook(){
  if(searchModel.value ==="学校"){
    axios.get('/material', {
      params: {
        school:searchInput.value,
        page: pageNum.value,
        pageSize: pageSize.value
      }
    }).then((resp)=>{
      materials.value = resp.data.data.items
      pageTotal.value = resp.data.data.total
    }) 
  }
  if(searchModel.value ==="专业"){
    axios.get('/material', {
      params: {
        major:searchInput.value,
        page: pageNum.value,
        pageSize: pageSize.value
      }
    }).then((resp)=>{
      materials.value = resp.data.data.items
      pageTotal.value = resp.data.data.total
    }) 
  }
  if(searchModel.value ==="学科"){
    axios.get('/material', {
      params: {
        subject:searchInput.value,
        page: pageNum.value,
        pageSize: pageSize.value
      }
    }).then((resp)=>{
      materials.value = resp.data.data.items
      pageTotal.value = resp.data.data.total
    }) 
  }  
};
// 添加资料
let addBookFormVisible = ref(false);
const addBookFormRef = ref<FormInstance>();
const addFromButton = (formEl: FormInstance | undefined) => {
  addBookFormVisible.value = true;
  if (!formEl) return;
  formEl.resetFields();
};
const materialForm = reactive<any>(
    {
     school:'',
     major:'',
     subject:'',
     materialUploader: usereamil,
     materialProfile:'',
     materialCoverUrl:'',
     materialCoverUuid:'',
     elecBookUuid:'',
     elecBookPoints:'',
     teachingPlanUuid:'',
     teachingPlanPoints:'',
     classPptUuid:'',
     classPptPoints:'',
     calendarVolumeUuid:'',
     calendarVolumePoints:'',
     anotherMaterialUuid:'',
     anotherMaterialPoints:'',
    })
const rules = ref(
  {
    school: [{ required: true, message: '请输入学校名！', trigger: 'blur' },],
     major: [{ required: true, message: '请输入专业名！', trigger: 'blur' },],
     subject: [{ required: true, message: '请输入学科名！', trigger: 'blur' },],
  })
function uploadFile(param) {
    isLoading.value = true;
    const uploadForm1 = new FormData();
    uploadForm1.append('materialCover', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterialcover", uploadForm1)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          materialForm.materialCoverUrl = response.data.data[0]
          materialForm.materialCoverUuid = response.data.data[1]
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
}
function uploadElecBook(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          // 处理成功响应
          isLoading.value = false;
          materialForm.elecBookUuid = response.data.data
        })
}
function uploadTeachingPlan(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          materialForm.teachingPlanUuid = response.data.data
        })
}
function uploadClassPpt(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          materialForm.classPptUuid = response.data.data
        })
}
function uploadCalendarVolume(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          materialForm.calendarVolumeUuid = response.data.data
        })
}
function uploadAnotherMaterial(param) {
  isLoading.value = true;
    const uploadForm2 = new FormData();
    uploadForm2.append('materialFile', param.file);
    // 发送上传请求
    axios.post("/upload/uploadMaterial", uploadForm2)
        .then(response => {
          isLoading.value = false;
          // 处理成功响应
          materialForm.anotherMaterialUuid = response.data.data
        })
}
function exceed(files, fileList) {
    return ElMessage.error("最多上传4张图片！");
}
function imgPreview(file, fileList) {
              let fileName = file.name;
              let regex = /(.jpg|.jpeg|.png)$/;
              if (regex.test(fileName.toLowerCase())) {
                  let picUrl = URL.createObjectURL(file.raw);
                  materialForm.files = fileList;
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
    console.log("上传", materialForm)
    // 发送上传请求
    axios.post("/material", materialForm)
        .then(response => {
          const code = response.data.code
          const message = response.data.msg
           // 添加失败
          if (code == 400) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
            });
          }
          // 添加成功
          if (code == 0) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
          })
          searchBook();
          addBookFormVisible.value = false
        }
      }).catch(error => {
          // 处理错误
          console.error(error);
        });

} 
// 编辑资料对话框
let editBookFormVisible = ref(false);
const editFromButton = (formEl: FormInstance | undefined, row: any) => {
  editBookFormVisible.value = true;
  editBookForm = reactive(JSON.parse(JSON.stringify(row)));
  if (!formEl) return;
  formEl.resetFields();
};
// 编辑资料表单
const editBookFormRef = ref<FormInstance>();
let editBookForm = reactive<any>(
    {
     school:'',
     major:'',
     subject:'',
     materialUploader: usereamil,
     materialProfile:'',
     materialCoverUrl:'',
     materialCoverUuid:'',
     elecBookUuid:'',
     elecBookPoints:'',
     teachingPlanUuid:'',
     teachingPlanPoints:'',
     classPptUuid:'',
     classPptPoints:'',
     calendarVolumeUuid:'',
     calendarVolumePoints:'',
     anotherMaterialUuid:'',
     anotherMaterialPoints:'',
    })
// 编辑资料按钮
const editBookButton = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      axios.put("/material/admin", editBookForm)
        .then((resp) => {
          const code = resp.data.code;
          const message = resp.data.msg;

          // 编辑失败
          if (code == 400) {
            ElMessageBox.alert(message, {
              confirmButtonText: "确认",
            });
          }
          // 编辑成功
          if (code == 0) {
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
// 删除资料
const materialType=ref()
let materialTypeOptions = [
  {
    value: 0,
    label: "全部资料",
  },
  {
    value: 1,
    label: "课程电子书",
  },
  {
    value: 2,
    label: "教学计划",
  },
  {
    value: 3,
    label: "课程PPT",
  },
  {
    value: 4,
    label: "历年卷",
  },
  {
    value: 5,
    label: "其他资料",
  },
];
let deleteId = reactive({
  materialId: "",
});
let deleteBookDialogVisible = ref(false);
const deleteBookDialog = (row: any) => {
  deleteId.materialId = row.materialId;
  deleteBookDialogVisible.value = true;
};

// 删除资料按钮
const deleteBook = () => {
  let obj = {
    adminAccount:username.value,
    materialIds:[deleteId.materialId],
    type:materialType.value
  }
    axios.delete("material/admin", { data: obj }).then((resp) => {
      const code = resp.data.code;
      const message = resp.data.msg;

      // 删除失败
      if (code == 1) {
        ElMessageBox.alert(message, {
          confirmButtonText: "确认",
        });
      }
      // 删除成功
      if (code == 0) {
        ElMessageBox.alert(message, {
          confirmButtonText: "确认",
          callback: () => {
            deleteBookDialogVisible.value = false;
            searchBook();
          },
        });
      }
    });
};

// 初始化
const init = () => {
  // 获取资料
  searchBook();
};
init();
</script>

<style lang="scss">
@import "@/assets/css/book";
</style>
