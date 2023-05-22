<template>
  <div>
    <div style="margin-bottom: 20px;margin-top: 5px">
      <el-input  v-model="cno" placeholder="请输入班号" suffix-icon="el-icon-search" style="width: 200px;"
                 @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="success" style="margin-left: 5px;" @click="resetParam">重置</el-button>

      <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
    </div>
    <el-table
        :data="tableData"
        :header-cell-style="{'text-align':'center' ,background: '#f2f5fc', color: '#555555'}"
        :cell-style="{'text-align':'center'}"
        style="width: 100%"
        border>
      <el-table-column prop="cno" label="班号" width="200">
      </el-table-column>
      <el-table-column prop="cnum" label="班级人数" width="160">
      </el-table-column>
      <el-table-column prop="cdate" label="入校年份" width="200">
      </el-table-column>
      <el-table-column prop="cmn" label="专业名" width="200">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.cno)"
              style="margin-left: 5px;"
          >
            <el-button slot="reference" size="small" type="danger" >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="pageNum"
        :page-sizes="[2, 5, 7]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="40%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="班号" prop="cno">
          <el-col :span="20">
            <el-input v-model="form.cno"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="人数" prop="cnum">
          <el-col :span="20">
            <el-input v-model="form.cnum"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="入校年份" prop="cdate">
          <el-col :span="20">
            <el-input v-model="form.cdate"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="专业名" prop="cmn">
          <el-col :span="20">
            <el-input v-model="form.cmn"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ClassesManage",

  data() {

    return {
      tableData: [],
      pageSize:7,
      pageNum:1,
      total:0,
      cno:'',
      centerDialogVisible:false,
      form:{
        cno:'',
        cnum:'',
        cdate:'',
        cmn:'0',
        flag:''
      },
      rules: {
        cno: [
          {required: true, message: '请输入班号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        cnum: [
          {required: true, message: '请输入班级人数', trigger: 'blur'},
          {min: 1, max: 4, message: '长度在 1 到 4 个位', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,4}$/,message: '年龄必须为正整数字',trigger: "blur"},
        ],
        cdate: [
          {required: true, message: '请输入入校年份（格式为xxxx-xx-xx）', trigger: 'blur'}
        ],
        cmn: [
          {required: true, message: '请输入班级所在专业', trigger: 'blur'}
        ]

      }
    }
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
    },
    del(cno){
      console.log(cno)

      this.$axios.get(this.$httpUrl+'/classes/del?cno='+cno).then(res=>res.data).then(res=>{
        console.log(res.data)
        if(res.code==200){

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.loadPost()
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    mod(row){

      console.log("mod(row)")
      console.log(row)
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        //赋值到表单
        this.form.cno = row.cno
        this.form.cnum = row.cnum
        this.form.cdate = row.cdate
        this.form.cmn = row.cmn
        this.form.flag='1'
      })
      console.log(this.form)

    },
    add(){
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })
    },

    doSave(){
      this.$axios.post(this.$httpUrl+'/classes/save',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this. resetForm()
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    doMod(){
      this.$axios.post(this.$httpUrl+'/classes/update',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this. resetForm()
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    save(){
      console.log("before save ")
      this.$refs.form.validate((valid) => {
        console.log("before do valid")
        if (valid) {
          console.log(this.form.flag)
          if(this.form.flag){
            console.log("before doMod() ")
            this.form.flag=''
            this.doMod();
          }else{
            console.log("before doSave() ")
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetParam(){
      this.cno=''
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/classes/listPage',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          cno:this.cno,
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){
          this.tableData=res.data
          this.total=res.total
        }else {
          alert("获取数据失败")
        }
      })
    },
    beforeMount(){
      this.loadPost();
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum=1
      this.pageSize=val
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum=val
      this.loadPost()
    }
  },
}


</script>

<style scoped>
.el-pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 80%;
  text-align: center;
}
</style>
