<template>
  <div style="width: 100%; height: 100vh;background-color: darkslateblue; overflow: hidden">
    <div style="width: 400px; margin: 100px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
      <el-form  ref="rule_form"
                :model="rule_form"
                size="normal"
                :rules="rules"
                >
        <el-form-item  prop="username">
          <el-input  v-model="rule_form.username">
            <template #prefix>
              <el-icon class="el-avatar--icon" size="normal" style="width: 100%;height: 100%"><avatar /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="rule_form.password" show-password>
            <template #prefix>
            <el-icon class="ui-icon-locked" style="width: 100%;height: 100%"><lock /></el-icon>
          </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {Avatar,Lock} from '@element-plus/icons-vue';
import request from "../utils/request";

export default {
  name: "login",
  data(){
    return{
      rule_form:{},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
        ],
        password:[
          {required:true,message:'请输入密码',trigger:'blur'},
        ],
      }
    }
  },
  components:{
   Avatar,Lock
  },
  methods:{
    login(){
      this.$refs['rule_form'].validate((valid)=>{
        if(valid){
      request.post("http://localhost:9090/user/login",this.rule_form).then(res=> {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "登录成功"
          })
          this.$router.push("/")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>