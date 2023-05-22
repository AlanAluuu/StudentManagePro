<template>
  <div>
    <div style="margin-bottom: 20px;margin-top: 5px">
      <el-input  v-model="deptname" placeholder="请输入学号" suffix-icon="el-icon-search" style="width: 200px;"
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
      <el-table-column prop="sno" label="学号" width="240">
      </el-table-column>
      <el-table-column prop="pno" label="学会号" width="240">
      </el-table-column>
      <el-table-column prop="edate" label="入会日期" width="240">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.spid)"
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
        <el-form-item label="学名" prop="sno">
          <el-col :span="20">
            <el-input v-model="form.sno"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="学会号" prop="pno">
          <el-col :span="20">
            <el-input v-model="form.pno"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="入会日期" prop="pno">
          <el-col :span="20">
            <el-input v-model="form.edate"></el-input>
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
  name: "SpManage",

  data() {

    return {
      tableData: [],
      pageSize:7,
      pageNum:1,
      total:0,
      sno:'',
      centerDialogVisible:false,
      form:{
        spid:'',
        sno:'',
        pno:'',
        edate:'',
        flag:''
      },
      rules: {
        sno: [
          {required: true, message: '请输入系号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        pno: [
          {required: true, message: '请输入系名', trigger: 'blur'}
        ],
        edate:[

        ]
      }
    }
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
    },
    del(spid){
      console.log(spid)

      this.$axios.get(this.$httpUrl+'/sp/del?spid='+spid).then(res=>res.data).then(res=>{
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
        this.form.sno = row.sno
        this.form.pno = row.pno
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
      this.$axios.post(this.$httpUrl+'/sp/save',this.form).then(res=>res.data).then(res=>{
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
      this.$axios.post(this.$httpUrl+'/sp/update',this.form).then(res=>res.data).then(res=>{
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
          if(this.form.flag!=''){
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
      this.sno=''
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/sp/listPage',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          sno:this.sno,
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
