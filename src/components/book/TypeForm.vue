<template>
  <el-dialog :title="title" v-model="visible" width="30%">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="demo-ruleForm">
      <el-form-item label="分类名称" prop="name">
        <el-input v-model="form.name"></el-input>
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
  name: 'TypeForm',
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
          this.title='编辑分类'
        }else{
          this.title='新增分类'
        }
        getAction('book/typeDetail/'+newValue, {}).then((res) => {
          this.form.id=res.data.id;
          this.form.name=res.data.name;
        });
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      name: '',
    }
  },
  data() {
    return {
      visible:false,
      title:'新增分类',
      dialogTableVisible: false,
      form: {
        id:null,
        name: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              postAction('book/checkType', this.form).then((res) => {
                if(!res.data.success){
                  return callback(new Error('分类名已存在'));
                }else{
                  callback();
                }
              });
            }, trigger: 'blur' }
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          postAction('book/saveType', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('typeList',{currentPage:0,sizePerPage:this.sizePerPage})
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
