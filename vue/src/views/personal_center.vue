<template>
  <el-row  style="height: 120px;box-shadow: 0 7px 7px -7px #5E5E5E; " >
    <el-col :span="3" :offset="1">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <el-image
              class="row1-image1"
              :src="require('../assets/image/swjtu.jpg')"
              :fit="fits"
          ></el-image>
        </div>
      </div>
    </el-col>
    <el-col :span="4">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <el-image
              class="row1-image2"
              :src="require('../assets/image/swjtu2.jpg')"
              :fit="fits"
          ></el-image>
        </div>
      </div>
    </el-col>
    <el-col :span="4">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <div :fit="fits" class="row1-title1">体育中心系统</div>
          <div :fit="fits" class="row1-title2">Sports Management System</div>
        </div>
      </div>
    </el-col>
    <el-col :span="3" :offset="1" v-if="anti_AddNum">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <el-button type="primary" class="login_button" size="large" @click="to_home()">Home page</el-button>
        </div>
      </div>
    </el-col>
    <el-col :span="3" v-if="anti_AddNum">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <el-button type="primary" class="login_button" size="large" @click="to_appointment()">Activities</el-button>
        </div>
      </div>
    </el-col>
    <el-col :span="3"   v-if="anti_AddNum">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <span data-v-1611e720="" class="badgeLine">|</span>
          <a @click="ToShowOrder" style="cursor: pointer;">My order</a>
          <span data-v-1611e720="" class="badgeLine">|</span>
          <el-dropdown>
            <span class="el-dropdown-link">
               <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
              {{username.username}}
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="to_personal_center()" >Personal information</el-dropdown-item>
                <el-dropdown-item @click="ToFeedback()">Complaints Suggestions</el-dropdown-item>
                <el-dropdown-item @click="ToFaq">assistance center</el-dropdown-item>
                <el-dropdown-item @click="to_chat_sb">BBS interface</el-dropdown-item>
                <!--                  <el-dropdown-item disabled>Action 4</el-dropdown-item>-->
                <el-dropdown-item divided @click="cleanUser">quit</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </el-col>
    <el-col :span="3" :offset="9" v-if="addNum">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <el-button type="primary" class="login_button" size="large" @click="to_login()">login</el-button>
        </div>
      </div>
    </el-col>
  </el-row>
  <el-row class="information_center">
    <h3 style="">Personal information</h3>
    <el-col>
      <div style="position:relative;float: left;z-index: 1">
        <img
            style="width: 120px;height: 150px"
                :src="require('../assets/userPhoto/'+username.photo+'.jpg')">
        <el-upload
            ref="upload"
            action="http://localhost:9090/files/upload"
            :limit="1"
            :show-file-list="false"
            :on-success="fileUpLoad"
            :on-exceed="handleExceed"
        >
            <el-button type="success">update photo</el-button>
        </el-upload>
      </div>
      <div style="position:relative;">
        <el-form :model="form" label-width="120px">
          <el-form-item label="username">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="password">
            <el-input v-model="form.password" style="width: 80%" show-password="false"></el-input>
          </el-form-item>
          <el-form-item label="phone">
            <el-input v-model="form.phone"  style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="reputation">
            <el-input v-model="form.reputation" disabled="false" style="width: 10%"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="update">save</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </el-row>
  <el-row>
    <el-col>
      <div class="row_bottom">Copyright © 2021 西南交通大学. All rights reserved.</div>
    </el-col>
  </el-row>
</template>

<script>
import {ArrowRightBold, Search,ArrowDown} from "@element-plus/icons-vue";
import request from "../utils/request";
import { ref } from 'vue'

export default {
  name: "personal_center",
  components:{ArrowRightBold,Search,ArrowDown},
  created() {
    if(sessionStorage.getItem("user")!=null){
      this.addNum = false
      this.anti_AddNum = true
      this.username = JSON.parse(sessionStorage.getItem("user"))
      let str = sessionStorage.getItem("user") || "{}"
      this.form = JSON.parse(str)
    }
  },
  methods:{
    ToShowOrder(){
      this.$router.push("/Showorder")
    },
    ToFeedback(){
      this.$router.push("/CreateFeedback")
    },
    cleanUser(){
      sessionStorage.removeItem("user")
      this.$router.go(0)
    },
    ToFaq(){
      this.$router.push("/Faq")
    },
    to_home(){
      this.$router.push("/cover")
    },
    to_appointment(){
      this.$router.push("/appointment")
    },
    to_login(){
      this.$router.push("/login")
    },
    to_personal_center(){
      this.$router.push("/personal_center")
    },
    update() {
      request.put("/user", this.form).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
          sessionStorage.setItem("user", JSON.stringify(this.form))
          // 触发Layout更新用户信息
          this.$emit("userInfo")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },
    fileUpLoad(res){
       console.log(res)
       this.username.photo = res.data
        this.form.photo = res.data
        this.update()
     },
    handleExceed(){
      if (this.$refs['upload']) {
        this.$refs['upload'].clearFiles()  // 清除历史文件列表
      }
    },
    to_chat_sb(){
      this.$router.push("/chat")
    },
  },
  data(){
    return{
      upload:ref(),
      form:{},
      addNum:true,
      anti_AddNum:false,
      username:'',
      fits:'contain',
    }
  }
}
</script>

<style scoped>
.row {
  margin-top: 10%;
}

.row1-image1{
  width: 50%;
  height: 50%;
  float:right;
}

.row1-image2{
}

.row1-title1{
  text-align: center;
  font-size: xx-large;
  font-family: "KaiTi",serif;
  letter-spacing: normal;
}

.row1-title2{
  text-align: center;
  font-size: small;
  font-family: "Microsoft JhengHei",serif;
  font-weight: bold;
  color: #8c939d;
}

.login_button{
  font-size: x-large;
  color:white;
  font-family: "Times New Roman",serif;
}

.badgeLine{
  font-family: Microsoft YaHei,Helvetica Neue,Helvetica,Arial,sans-serif;
  line-height: 20px;
  list-style: none;
  font-size: 20px;
  color: #ccc;
}

.row_bottom{
  text-align:center;
  margin-top:20px;
  color: #7e8187;
  background: #f9f9f9;
}

.information_center{
  background-color: #f6f7fb;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  padding: 20px 50px 50px;
  margin-bottom: 50px;
  width: 80%;
  margin: 0 auto;
}

</style>