<template>

  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="background-color:rgb(238, 241, 246);height: 100%;margin-left: -1px">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px;height: 100%;border-bottom: rgba(168,168,168,0.3) 1px solid" >
        <Header @doCollapse="doCollapse" :icon="icon"></Header>
      </el-header>

      <el-main style="height: 100%;">
<!--        <Main></Main>-->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
// import Main from "@/components/Main";
export default {
  name: "Index",
  components: { Header, Aside},
  data(){
    return{
      isCollapse:false,
      aside_width:'200px',
      icon:'el-icon-s-fold'
    }
  },
  methods:{
    doCollapse(){
      console.log(1111)
      this.isCollapse = !this.isCollapse
      if(!this.isCollapse){// 展开
        this.aside_width='200px'
        this.icon='el-icon-s-fold'
      }else{//关起、关闭、收起
        this.aside_width='64px'
        this.icon='el-icon-s-unfold'
      }
    }
  }
}
</script>

<style scoped>
  .el-header {
    background-color: rgb(89,99,179);
    color: #333;
    line-height: 60px;
  }
  el-main {
    padding: 5px;
  }
  .el-aside {
    color: #333;
  }
</style>