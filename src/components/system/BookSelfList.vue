<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="书架号">
          <el-input v-model="searchObj.name" placeholder="书架号"></el-input>
        </el-form-item>
        <el-form-item label="容量大于">
          <el-select v-model="searchObj.capacity1" placeholder="容量" style="width: 100px;">
            <el-option label="请选择" value=""></el-option>
            <el-option label="10" value="10"></el-option>
            <el-option label="50" value="50"></el-option>
            <el-option label="100" value="100"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="容量小于">
          <el-select v-model="searchObj.capacity2" placeholder="容量" style="width: 100px;">
            <el-option label="请选择" value=""></el-option>
            <el-option label="10" value="10"></el-option>
            <el-option label="50" value="50"></el-option>
            <el-option label="100" value="100"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="addBookSelf">新增书架</el-button>
        </el-form-item>
        <el-form-item>
          <el-upload
            class="upload-demo"
            :action="uploadUrl"
            :show-file-list="false"
            :on-success="uploadSuccess"
            :on-error="uploadError"
            >
            <el-button type="primary">导入书架</el-button>
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
          label="书架号"
          property="name"
        >
        </el-table-column>
        <el-table-column
          label="剩余容量"
          property="capacity"
        >
        </el-table-column>
        <el-table-column
          label="已用容量"
          property="uses"
          width="100"
        >
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
      <BookSelfForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:bookSelfList="getBookSelfList"></BookSelfForm>
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
import BookSelfForm from './BookSelfForm';
import Vue from 'vue';
export default {
  name: 'BookSelfList',
  components: {BookSelfForm},
  data() {
    return {
      id: null,
      uploadUrl:'/service/bookself/importBookSelf',
      searchObj: {
        name: '',
        capacity1: '',
        capacity2: ''
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
    this.getBookSelfList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getBookSelfList(obj) {
      postAction('bookself/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addBookSelf(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getBookSelfList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getBookSelfList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
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
        if(row.uses!=null&&row.uses!==''){
          this.$message.error('该书架已有图书，无法删除')
        }else{
          postAction('bookself/deleteBookSelf', {id:row.id}).then((res) => {
            this.$message.success('删除成功')
            this.getBookSelfList({currentPage:0,sizePerPage:this.pageObj.pageSize})
          });
        }
      }).catch(() => {
      });
    },
    onSearch() {
      console.log(this.searchObj)
      this.getBookSelfList({currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        name:this.searchObj.name,
        capacity1: this.searchObj.capacity1,
        capacity2: this.searchObj.capacity2})
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
        this.getBookSelfList({currentPage:0,sizePerPage:this.pageObj.pageSize})
      }
    },
    uploadError(){
      this.$message.error('导入失败')
    },
    downLoad(){
      window.location.href='http://localhost:9999/图书模板.xlsx'
    },
  }
}
</script>

<style scoped>

</style>
