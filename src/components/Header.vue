<template>

  <div style="display: flex;line-height: 60px;">
    <div style="margin-top: 8px;">
      <i :class="icon" style="font-size: 20px;cursor: pointer;color: #eeeeee" @click="collapse;"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 25px;color: dimgrey">
      <span style="color: white ">学生管理系统</span>
    </div>
    <el-dropdown>
      <span style="font-size: 20px;color: azure ">{{user.name}}</span>
      <i class="el-icon-s-custom" style="margin-left: 15px ;font-size: 20px;color: #eeeeee"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人资料</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>

</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      user : JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  props:{
    icon:String
  },
  methods:{
    toUser(){
      console.log('toUser')
      this.$router.push("/Home")
    },
    logout() {
      console.log('logout')

      this.$confirm('您确定要退出登录吗?', '提示', {
        confirmButtonText: '确定',  //确认按钮的文字显示
        type: 'warning',
        center: true, //文字居中显示

      })
          .then(() => {
            this.$message({
              type:'success',
              message:'退出登录成功'
            })
            this.$router.push("/")
            sessionStorage.clear()
          })
          .catch(() => {
            this.$message({
              type:'info',
              message:'已取消退出登录'
            })
          })

    },
    collapse(){
      this.$emit('doCollapse')
    }


  },
  created(){
    this.$router.push("/Home")
  }


}
</script>

<style scoped>

</style>