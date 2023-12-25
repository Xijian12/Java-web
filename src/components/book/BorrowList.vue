<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="书名">
          <el-input v-model="searchObj.bookname" placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item label="借阅人">
          <el-input v-model="searchObj.username" placeholder="借阅人"></el-input>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="searchObj.card" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="addBorrow">新增借阅</el-button>
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
          property="bookname"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="出版社"
          property="publisher"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="单价（元）"
          property="price"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="借阅人"
          property="username"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="身份证"
          property="card"
          width="180"
        >
        </el-table-column>
        <el-table-column
          label="联系方式"
          property="phone"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="借出日期"
          property="borrowdate"
          sortable
          width="120"
        >
        </el-table-column>
        <el-table-column
          label="计划归还日期"
          property="planreturndate"
          sortable
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="实际归还日期"
          property="realityreturndate"
          width="150"
        >
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="200">
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
      <BorrowForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:borrowList="getBorrowList"></BorrowForm>
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
import BorrowForm from './BorrowForm';
export default {
  name: 'BorrowList',
  components: {BorrowForm},
  data() {
    return {
      id: null,
      searchObj: {
        bookname: '',
        card: '',
        username: '',
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
    this.getBorrowList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getBorrowList(obj) {
      postAction('borrow/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addBorrow(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getBorrowList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getBorrowList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
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
        postAction('borrow/deleteBorrow', {id:row.id}).then((res) => {
          this.$message.success('删除成功')
          this.onSearch();
        });
      }).catch(() => {
      });
    },
    onSearch() {
      this.getBorrowList({currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        bookname:this.searchObj.bookname,
        card: this.searchObj.card,
        username: this.searchObj.username
      })
    },
  }
}
</script>

<style scoped>

</style>
