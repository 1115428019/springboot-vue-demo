<template>
<div>
  <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      :default-active="path"
      class="el-menu-vertical-demo"
      router
      >
    <el-sub-menu index="1" v-if="user.role === 1">
      <template #title>
        <el-icon><setting /></el-icon>
        <span>系统管理</span>
      </template>
        <el-menu-item index="/user">
          <el-icon><avatar /></el-icon>
          用户管理
        </el-menu-item>
    </el-sub-menu>
<!--    <el-menu-item index="/data">-->
<!--      <el-icon><histogram /></el-icon>-->
<!--      <span>数据管理</span>-->
<!--    </el-menu-item>-->
    <el-menu-item index="/book">
      <el-icon><reading /></el-icon>
      <span>书籍管理</span>
    </el-menu-item>
    <el-menu-item index="/news">
      <el-icon><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-394d1fd8=""><path fill="currentColor" d="M160 448a32 32 0 0 1-32-32V160.064a32 32 0 0 1 32-32h256a32 32 0 0 1 32 32V416a32 32 0 0 1-32 32H160zm448 0a32 32 0 0 1-32-32V160.064a32 32 0 0 1 32-32h255.936a32 32 0 0 1 32 32V416a32 32 0 0 1-32 32H608zM160 896a32 32 0 0 1-32-32V608a32 32 0 0 1 32-32h256a32 32 0 0 1 32 32v256a32 32 0 0 1-32 32H160zm448 0a32 32 0 0 1-32-32V608a32 32 0 0 1 32-32h255.936a32 32 0 0 1 32 32v256a32 32 0 0 1-32 32H608z"></path></svg></el-icon>
      <span>新闻管理</span>
    </el-menu-item>
  </el-menu>
</div>
</template>

<script>
import {Location,Reading,Histogram,Avatar} from '@element-plus/icons-vue';
import {Menu} from '@element-plus/icons-vue';
import {Setting} from '@element-plus/icons-vue';
import request from "../utils/request";

export default {
  name: "aside",
  components:{
    Location,Setting,Menu,Reading,Histogram,Avatar
  },
  data(){
    return{
      user:{},
      path: this.$route.path
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    request.get("/user/" + this.user.id).then(res => {
      if (res.code === '0') {
        this.user = res.data
      }
    })
  }
}

</script>

<style scoped>

</style>