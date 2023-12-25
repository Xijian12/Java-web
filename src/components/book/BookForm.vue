<template>
  <el-dialog :title="title" v-model="visible" width="30%" center>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="demo-ruleForm">
      <el-form-item label="书名" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="存放书架" prop="bookselfid">
        <el-select v-model="form.bookselfid" placeholder="请选择存放书架">
          <el-option :label="item.name" :value="item.id" v-for="item in bookselfs" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属分类" prop="typeid">
        <el-select v-model="form.typeid" placeholder="请选择所属分类">
          <el-option :label="item.name" :value="item.id" v-for="item in types" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="出版社" prop="publisher">
        <el-input v-model="form.publisher"></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="form.author"></el-input>
      </el-form-item>
      <el-form-item label="单价" prop="price">
        <el-input v-model="form.price"></el-input>
      </el-form-item>
      <el-form-item label="借阅量" prop="borrow">
        <el-input v-model.number="form.borrow"></el-input>
      </el-form-item>
      <el-form-item label="库存" prop="stock">
        <el-input v-model.number="form.stock"></el-input>
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
export default {
  name: 'BookForm',
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
          this.title='编辑图书'
        }else{
          this.title='新增图书'
        }
        getAction('book/detail/'+newValue, {}).then((res) => {
          this.form.id=res.data.id;
          this.form.name=res.data.name;
          this.form.typeid=res.data.typeid;
          this.form.bookselfid=res.data.bookselfid;
          this.form.publisher=res.data.publisher;
          this.form.author=res.data.author;
          this.form.price=res.data.price;
          this.form.borrow=res.data.borrow;
          this.form.stock=res.data.stock;
        });
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      name: '',
      typeid: undefined,
      bookselfid: undefined,
      publisher: '',
      author: '',
      price: '',
      borrow: '',
      stock: '',
    }
    this.getTypeList();
    this.getBookSelfList();
  },
  data() {
    return {
      visible:false,
      title:'新增图书',
      dialogTableVisible: false,
      form: {
        id:null,
        name: '',
        typeid: undefined,
        bookselfid: undefined,
        publisher: '',
        author: '',
        price: '',
        borrow: '',
        stock: '',
      },
      types:[],
      bookselfs:[],
      rules: {
        name: [
          { required: true, message: '请输入书名', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' },
        ],
        typeid: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        bookselfid: [
          { required: true, message: '请选择存放书架', trigger: 'change' }
        ],
        publisher: [
          { required: true, message: '请填写出版社', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' },
        ],
        author: [
          { required: true, message: '请填写作者', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' },
        ],
        price: [
          { required: true, message: '请填写单价', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(isNaN(Number(value))){
                return callback(new Error('单价必须为数字值'));
              }
              if(value>99999){
                return callback(new Error('单价不能大于99999'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
        ],
        borrow: [
          { required: true, message: '请填写借阅量，没有填0', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value>999){
                return callback(new Error('借阅量不能大于999'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
          { type: 'number', message: '借阅量必须为数字值'}
        ],
        stock: [
          { required: true, message: '请填写库存，没有填0', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value>999){
                return callback(new Error('库存不能大于999'));
              }else{
                if(this.form.id==null){
                  let self=this.bookselfs.filter((item)=>{
                    return item.id===this.form.bookselfid;
                  })[0]
                  if(self.capacity<this.form.stock){
                    return callback(new Error('入库数量大于所选书架号的剩余容量，书架剩余容量为'+self.capacity));
                  }else{
                    callback();
                  }
                }else{
                  callback();
                }
              }
            }, trigger: 'blur' },
          { type: 'number', message: '库存必须为数字值'}
        ],
      },
    };
  },
  methods:{
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
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          postAction('book/saveBook', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('bookList',{currentPage:0,sizePerPage:this.sizePerPage})
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
