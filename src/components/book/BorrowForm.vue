<template>
  <el-dialog :title="title" v-model="visible" width="40%" center>
    <el-form ref="form" :model="form" :rules="rules" label-width="200px" class="demo-ruleForm">
      <el-form-item label="书名" prop="bookid">
        <el-select
          v-model="form.bookid"
          filterable
          remote
          reserve-keyword
          placeholder="请输入书名"
          :remote-method="getBookList"
          :loading="loading">
          <el-option
            v-for="item in books"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="借阅人" prop="username">
        <el-input v-model="form.username" style="width: 205px;"></el-input>
      </el-form-item>
      <el-form-item label="身份证号" prop="card">
        <el-input v-model="form.card" style="width: 205px;"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone" style="width: 205px;"></el-input>
      </el-form-item>
      <el-form-item label="计划归还日期" prop="planreturndate">
        <el-date-picker
          v-model="form.planreturndate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际归还日期（归还时填写）" prop="realityreturndate">
        <el-date-picker
          v-model="form.realityreturndate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { postAction,getAction} from '@api/manage';
import { IdentityCodeValid } from '@/utils/IdentityCodeValid'
export default {
  name: 'BorrowForm',
  props: {
    dialogFormVisible: Boolean,
    id:String,
    sizePerPage:Number
  },
  watch:{
    dialogFormVisible:{
      handler (newValue, oldValue) {
        this.visible=newValue;
      },
    },
    id:{
      handler (newValue, oldValue) {
        if(newValue!=null){
          this.title='编辑借阅记录'
          getAction('borrow/detail/'+newValue, {}).then((res) => {
            this.form.id=res.data.id;
            this.form.bookid=res.data.bookid;
            this.form.bookname=res.data.bookname;
            this.form.borrowdate=res.data.borrowdate;
            this.form.planreturndate=res.data.planreturndate;
            this.form.realityreturndate=res.data.realityreturndate;
            this.form.username=res.data.username;
            this.form.card=res.data.card;
            this.form.phone=res.data.phone;
            this.books.push({label:this.form.bookname,value:this.form.bookid})
          });
        }else{
          this.title='新增借阅记录'
          this.form={
            id:null,
            bookid: undefined,
            bookname: '',
            borrowdate: '',
            planreturndate: '',
            realityreturndate: '',
            username: '',
            card: '',
            phone: ''
          }
        }
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      bookid: undefined,
      bookname: '',
      borrowdate: '',
      planreturndate: '',
      realityreturndate: '',
      username: '',
      card: '',
      sex: '',
      age: '',
      phone: ''
    }
    this.books=[];
  },
  data() {
    return {
      visible:false,
      loading: false,
      title:'新增借阅记录',
      dialogTableVisible: false,
      form: {
        id:null,
        bookid: undefined,
        bookname: '',
        borrowdate: '',
        planreturndate: '',
        realityreturndate: '',
        username: '',
        card: '',
        sex: '',
        age: '',
        phone: ''
      },
      books:[],
      borrowselfs:[],
      rules: {
        bookid: [
          { required: true, message: '请输入书名', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              getAction('book/detail/'+value, {}).then((res) => {
                if(res.data.stock<=0){
                  return callback(new Error('此书库存为0，无法借阅'));
                }else{
                  callback();
                }
              });
            }, trigger: 'change' }
        ],
        username: [
          { required: true, message: '请输入借阅人', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' },
        ],
        card: [
          { required: true, message: '请填写身份证号', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(!IdentityCodeValid(value)){
                return callback(new Error('身份证号格式不正确'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
        ],
        phone: [
          { required: true, message: '请填写联系方式', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' },
        ],
        planreturndate: [
          { required: true, message: '请填写计划归还日期', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              var year = new Date().getFullYear(),
                month = new Date().getMonth()+1,
                date = new Date().getDate(),
                currentDate = year + "-" + (month < 10 ? '0' + month : month) + "-" + (date < 10 ? '0' + date : date);
              if(value<currentDate){
                return callback(new Error('计划归还日期不能小于当前日期'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
        ],
      },
    };
  },
  methods:{
    getBookList(query) {
      this.loading=true;
      postAction('book/list', {name:query}).then((res) => {
        this.books=[];
        for(let item of res.data.list){
          this.books.push({label:item.name,value:item.id})
        }
        this.loading=false;
      });
    },
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.form.sex=this.form.card.slice(14,17)%2?"男":"女"
          this.form.age=new Date().getFullYear()-Number(this.form.card.slice(6,10));
          postAction('borrow/saveBorrow', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('borrowList',{currentPage:0,sizePerPage:this.sizePerPage})
          });
        } else {
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
