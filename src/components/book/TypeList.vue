<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="分类名称">
          <el-input v-model="searchObj.name" placeholder="分类名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="addType">新增分类</el-button>
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
          label="分类名称"
          property="name"
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
      <TypeForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:typeList="getTypeList"></TypeForm>
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
import TypeForm from './TypeForm';
export default {
  name: 'TypeList',
  components: {TypeForm},
  data() {
    return {
      id: null,
      searchObj: {
        name: '',
      },
      pageObj: {
        total:0,
        pagerCount: 0,
        pageSize:8,
        currentPage:0
      },
      dialogFormVisible:false,
      tableData: [],
      types:[]
    }
  },
  mounted(){
    this.getTypeList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getTypeList(obj) {
      postAction('book/typelist', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addType(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getTypeList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getTypeList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
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
        postAction('book/checkBook', {id:row.id}).then((res) => {
          if(!res.data.success){
            this.$message.error('该分类下存在图书，不能删除')
          }else{
            postAction('book/deleteType', {id:row.id}).then((res) => {
              this.$message.success('删除成功')
              this.getTypeList({currentPage:0,sizePerPage:this.pageObj.pageSize})
            });
          }
        });
      }).catch(() => {
      });
    },
    onSearch() {
      this.getTypeList({currentPage:0,sizePerPage:this.pageObj.pageSize,name:this.searchObj.name})
    },
  }
}
</script>

<style scoped>

</style>
