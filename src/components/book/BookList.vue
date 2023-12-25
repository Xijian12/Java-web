<template>
  <el-container>
    <el-header>
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="书名">
          <el-input v-model="searchObj.name" placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="searchObj.typeid" placeholder="分类">
            <el-option label="全部" value=""></el-option>
            <el-option :label="item.name" :value="item.id" v-for="item in types" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="书架">
          <el-select v-model="searchObj.bookselfid" placeholder="书架" style="width: 120px">
            <el-option label="全部" value=""></el-option>
            <el-option :label="item.name" :value="item.id" v-for="item in bookselfs" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addBook">新增图书</el-button>
        </el-form-item>
        <el-form-item>
          <el-upload
            class="upload-demo"
            :action="uploadUrl"
            :show-file-list="false"
            :on-success="uploadSuccess"
            :on-error="uploadError"
          >
            <el-button type="primary">导入图书</el-button>
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
          label="书名"
          property="name"
        >
        </el-table-column>
        <el-table-column
          label="所属分类"
          property="typename"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="存放书架"
          property="bookselfname"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="出版社"
          property="publisher"
        >
        </el-table-column>
        <el-table-column
          label="作者"
          property="author"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="单价（元）"
          property="price"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="借阅量"
          property="borrow"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="库存"
          property="stock"
          width="100"
        >
        </el-table-column>
        <el-table-column label="操作" width="200">
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
      <BookForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:bookList="getBookList"></BookForm>
    </el-main>
    <el-footer style="text-align: right">
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
import BookForm from './BookForm';
import Vue from 'vue';
export default {
  name: 'BookList',
  components: {BookForm},
  data() {
    return {
      id: null,
      uploadUrl:'/service/book/importBook',
      searchObj: {
        name: '',
        typeid: '',
        bookselfid: '',
      },
      pageObj: {
        total:0,
        pagerCount: 0,
        pageSize:8,
        currentPage:0
      },
      dialogFormVisible:false,
      tableData: [],
      types:[],
      bookselfs:[],
    }
  },
  mounted(){
    this.getBookList({currentPage:0,sizePerPage:this.pageObj.pageSize})
    this.getTypeList();
    this.getBookSelfList();
  },
  methods: {
    getTypeList() {
      postAction('book/getTypeList', {}).then((res) => {
        this.types=res.data;
      });
    },
    getBookSelfList() {
      postAction('bookself/getBookSelfList', {}).then((res) => {
        this.bookselfs=res.data;
      });
    },
    getBookList(obj) {
      postAction('book/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addBook(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getBookList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getBookList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
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
        postAction('book/deleteBook', {id:row.id}).then((res) => {
          this.$message.success('删除成功')
          this.onSearch()
        });
      }).catch(() => {
      });
    },
    onSearch() {
      this.getBookList({currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        name:this.searchObj.name,
        typeid: this.searchObj.typeid,
        bookselfid: this.searchObj.bookselfid
      })
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
        this.getTypeList();
        this.getBookList({currentPage:0,sizePerPage:this.pageObj.pageSize})
      }
    },
    uploadError(){
      this.$message.error('导入失败')
    },
    downLoad(){
      window.location.href=Vue.prototype.$url+'/图书模板.xlsx'
    },
  }
}
</script>

<style scoped>

</style>
