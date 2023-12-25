<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="书名">
          <el-input v-model="searchObj.bookname" placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          type="index"
        >
        </el-table-column>
        <el-table-column
          label="书名"
          property="bookname"
        >
        </el-table-column>
        <el-table-column
          label="出版社"
          property="publisher"
        >
        </el-table-column>
        <el-table-column
          label="单价（元）"
          property="price"
        >
        </el-table-column>
        <el-table-column
          label="借阅日期"
          property="borrowdate"
          sortable
        >
        </el-table-column>
        <el-table-column
          label="计划归还日期"
          property="planreturndate"
          sortable
        >
        </el-table-column>
        <el-table-column
          label="实际归还日期"
          property="realityreturndate"
        >
        </el-table-column>
      </el-table>
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
import {postAction} from '@api/manage';

export default {
  name: 'UserBorrowList',
  components: {},
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
    this.getBorrowList({currentPage:0,sizePerPage:this.pageObj.pageSize,usercard:this.$currentUser.card})
  },
  methods: {
    getBorrowList(obj) {
      postAction('borrow/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getBorrowList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getBorrowList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onSearch() {
      this.getBorrowList({currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        bookname:this.searchObj.bookname
      })
    },
  }
}
</script>

<style scoped>

</style>
