<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="登录名">
          <el-input v-model="searchObj.username" placeholder="登录名"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="searchObj.realname" placeholder="角色">
            <el-option label="全部" value=""></el-option>
            <el-option label="管理员" value="管理员"></el-option>
            <el-option label="操作员" value="操作员"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="addUser">新增用户</el-button>
        </el-form-item>
        <el-form-item>
          <el-upload
            class="upload-demo"
            :action="uploadUrl"
            :show-file-list="false"
            :on-success="uploadSuccess"
            :on-error="uploadError"
            >
            <el-button type="primary">导入用户</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="downLoad">下载导入模板</el-button>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          label="用户名"
          property="username"
        >
        </el-table-column>
        <el-table-column
          label="真实姓名"
          property="realname"
        >
        </el-table-column>
        <el-table-column
          label="角色"
          property="role"
        >
        </el-table-column>
        <el-table-column
          label="状态"
          property="status"
          width="100"
        >
          <template #default="scope">
            <el-tag size="medium" :type="scope.row.status === '1' ? 'success' : 'danger'" @click="changeStatus(scope.row)" style="cursor: pointer">
              {{ scope.row.status==='1'?'有效':'无效' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <UserForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:userList="getUserList"></UserForm>
    </el-main>
    <el-footer style="display: flex;justify-content: right">
      <el-pagination
        :page-size="pageObj.pageSize"
        :page-count="pageObj.pagerCount"
        :pager-count="11"
        @prev-click="onPrev"
        @next-click="onNext"
        @current-change="onPrev"
        :hide-on-single-page="true"
        background
        layout="prev, pager, next"
        :total="pageObj.total">
      </el-pagination>
    </el-footer>

  </el-container>
</template>

<script>
import { postAction} from '@api/manage';
import UserForm from './UserForm';
export default {
  name: 'BookList',
  components: {UserForm},
  data() {
    return {
      id: null,
      uploadUrl:'/service/user/importUser',
      searchObj: {
        username: '',
        realname: ''
      },
      pageObj: {
        total:0,
        pagerCount: 0,
        pageSize:8,
        currentPage:0
      },
      dialogFormVisible:false,
      tableData: [],

    }
  },
  mounted(){
    this.getUserList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getUserList(obj) {
      postAction('user/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addUser(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getUserList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getUserList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    changeStatus(row) {
      let status=row.status==='1'?'0':'1'
      postAction('user/updateStatus', {id:row.id,status:status}).then((res) => {
        this.getUserList({currentPage:this.pageObj.currentPage,sizePerPage:this.pageObj.pageSize})
      });
    },
    handleEdit(index, row) {
      this.id='';
      this.id=row.id;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    handleDelete(index, row) {
      this.$confirm('确定要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        postAction('user/deleteUser', {id:row.id}).then((res) => {
          this.$message.success('删除成功')
          this.onSearch()
        });
      }).catch(() => {
      });
    },
    onSearch() {
      this.getUserList({currentPage:0,sizePerPage:this.pageObj.pageSize,username:this.searchObj.username,role: this.searchObj.realname})
    },
    uploadSuccess(response){
      if(response.length>0){
        let str='';
        for(let i in response){
          if(response[i].index!=null){
            str+='<p>第'+response[i].index+'行：'+response[i].message+'</p>'
          }else{
            str+='<p>'+response[i].message+'</p>'
          }
        }
        this.$confirm(str, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          dangerouslyUseHTMLString: true,
          type: 'error'
        }).then(() => {
        }).catch(() => {
        });
      }else{
        this.$message.success('导入成功')
        this.getUserList({currentPage:0,sizePerPage:this.pageObj.pageSize})
      }
    },
    uploadError(){
      this.$message.error('导入失败')
    },
    downLoad(){
      window.location.href='http://localhost:9999/用户模板.xlsx'
    },
  }
}
</script>

<style scoped>

</style>
