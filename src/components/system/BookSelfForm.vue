<template>
  <el-dialog :title="title" v-model="visible" width="30%" center>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="demo-ruleForm">
      <el-form-item label="书架号" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="剩余容量" prop="capacity">
        <el-input v-model.number="form.capacity"></el-input>
      </el-form-item>
      <el-form-item label="已用容量" prop="uses">
        <el-input v-model.number="form.uses"></el-input>
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
  name: 'BookSelfForm',
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
          this.title='编辑书架'
        }else{
          this.title='新增书架'
        }
        getAction('bookself/detail/'+newValue, {}).then((res) => {
          this.form.id=res.data.id;
          this.form.name=res.data.name;
          this.form.capacity=res.data.capacity;
          this.form.uses=res.data.uses;
        });
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      name: '',
      capacity:'',
      uses:''
    }
  },
  data() {
    return {
      visible:false,
      title:'新增书架',
      dialogTableVisible: false,
      form: {
        id:null,
        name: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入书架号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              postAction('bookself/checkBookSelf', this.form).then((res) => {
                if(!res.data.success){
                  return callback(new Error('书架号已存在'));
                }else{
                  callback();
                }
              });
            }, trigger: 'blur' }
        ],
        capacity: [
          { required: true, message: '请填写剩余容量', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value>9999){
                return callback(new Error('剩余容量不能大于9999'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
          { type: 'number', message: '剩余容量必须为整数值'}
        ],
        uses: [
          { required: true, message: '请填写剩余容量，没有请填0', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value>9999){
                return callback(new Error('剩余容量不能大于9999'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
          { type: 'number', message: '剩余容量必须为整数值'}
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          postAction('bookself/saveBookSelf', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('bookSelfList',{currentPage:0,sizePerPage:this.sizePerPage})
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
