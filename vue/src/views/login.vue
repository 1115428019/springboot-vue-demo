<template>
  <div style="width: 100%; height: 100vh;overflow: hidden;background-color: antiquewhite">
    <div style="width: 700px;height: 400px; margin: 200px auto;background-color: ghostwhite">
      <div style="font-size: 30px; text-align: center; padding: 30px 0;vertical-align: middle;height: 130px">欢迎登录</div>
      <el-form  ref="form"
                :model="form"
                size="normal"
                :rules="rules"
                style="width: 70%;margin: auto;"
                >
        <el-form-item  prop="username">
          <el-input  v-model="form.username">
            <template #prefix>
              <el-icon class="el-avatar--icon" size="normal" style="width: 100%;height: 100%"><avatar /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" show-password>
            <template #prefix>
            <el-icon class="ui-icon-locked" style="width: 100%;height: 100%"><lock /></el-icon>
          </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <div style="display: flex">
            <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 50%;" placeholder="请输入验证码"></el-input>
            <ValidCode @input="createValidCode" />
          </div>
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
import ValidCode from "../components/ValidCode";

export default {
  name: "login",
  data(){
    return{
      form:{},
      validCode: '',
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
   Avatar,Lock,ValidCode
  },
  methods:{
    createValidCode(data) {
      this.validCode = data
    },
    login(){
      this.$refs['form'].validate((valid)=>{
        if(valid){
          if (!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
      request.post("/user/login",this.form).then(res=> {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "登录成功"
          })
          sessionStorage.setItem("user",JSON.stringify(res.data))
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