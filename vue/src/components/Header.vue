<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bold;color: dodgerblue">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown>
    <span class="el-dropdown-link">
      <span id="nickName"></span>
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="logout">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import $ from 'jquery'

export default {
  name: "Header",
  data(){
    return{
    }
  },
  created() {
    let  user = JSON.parse(sessionStorage.getItem('user'))
    let nickName = ''
    if(user){
      nickName = user.nickName
    }
    $(document).ready(function () {
      $("#nickName").append(nickName)
    })
  },
  methods:{
    logout(){
      sessionStorage.clear()
      this.$router.push('/login')
    },
  }
}

</script>
<style scoped>

</style>