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
<!--          <div :fit="fits" class="row1-title1">体育中心系统</div>-->
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
          <a  @click="ToShowOrder" style="cursor: pointer;">My order</a>
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
                <el-dropdown-item @click="to_personal_center()">Personal information</el-dropdown-item>
                <el-dropdown-item @click="ToFeedback()">Complaints Suggestions</el-dropdown-item>
                <el-dropdown-item @click="ToFaq">assistance center</el-dropdown-item>
                <el-dropdown-item @click="to_chat_sb">BBS interface</el-dropdown-item>
                <!--                  <el-dropdown-item disabled>Action 4</el-dropdown-item>-->
                <el-dropdown-item divided @click="cleanUser()">quit</el-dropdown-item>
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


<div style="margin-top: 10px;">
    <el-tabs type="border-card" style="width: 80%">
      <!-- all orders-->
      <el-tab-pane
          label="All orders"
          @click="Alltotal"
      >
       <el-card
           class="box-card"
           v-for="(userorder, index) in tableData"
           :key="userorder"
       >
          <template #header>
            <div class="card-header">
              <span>{{userorder.venueName}}</span>

              <el-tag class="ml-2" v-if="userorder.state==0">Unused</el-tag>
              <el-tag class="ml-2" v-if="userorder.state==1">Used</el-tag>
            </div>
          </template>
          <el-row>
            <el-col span="6"><div class="grid-content" />
              <img
                  :src="require('../assets/activityPhoto/'+userorder.venueId+'.png')"
                  class="image"
                  :alt="userorder.venueName"
                  @click="to_reservation(userorder.venueId)"
                 />
            </el-col>
            <el-col span="18"><div class="grid-content" />
              <div class="text item">order id: {{userorder.id}}</div>
              <div class="text item">venue name: {{userorder.venueName}}</div>
              <div class="text item">address: {{userorder.address}}</div>
              <div class="text item">reserve time: {{userorder.reserveTime}}</div>
            </el-col>
          </el-row>
        </el-card>

      </el-tab-pane>
      <!-- Uncompleted order-->
      <el-tab-pane
          label="Unused orders"
          @click="UnusedTotal"
      >
        <div
            v-for="(userorder, index) in tableData"
            :key="userorder"
        >
        <el-card
            class="box-card"
            v-if="userorder.state==0"
        >
          <template #header >
            <div class="card-header">
              <span>{{userorder.venueName}}</span>

              <el-tag class="ml-2" >Unused</el-tag>
            </div>
          </template>
          <el-row>
            <el-col span="6"><div class="grid-content" />
              <img
                  :src="require('../assets/activityPhoto/'+userorder.venueId+'.png')"
                  class="image"
                  :alt="userorder.venueName"
                  @click="to_reservation(userorder.venueId)"
              />
            </el-col>
            <el-col span="18"><div class="grid-content" />
              <div class="text item">order id: {{userorder.id}}</div>
              <div class="text item">venue name: {{userorder.venueName}}</div>
              <div class="text item">address: {{userorder.address}}</div>
              <div class="text item">reserve time: {{userorder.reserveTime}}</div>
            </el-col>
          </el-row>
        </el-card>
        </div>
      </el-tab-pane>
      <!-- Completed-->
      <el-tab-pane
          label="Used orders"
          @click="UsedTotal"
      >
        <div  v-for="(userorder, index) in tableData"
              :key="userorder">
        <el-card
            class="box-card"
            v-if="userorder.state==1"
        >
          <template #header >
            <div class="card-header">
              <span>{{userorder.venueName}}</span>

              <el-tag class="ml-2">Used</el-tag>
            </div>
          </template>
          <el-row >
            <el-col span="6"><div class="grid-content" />
              <img
                  :src="require('../assets/activityPhoto/'+userorder.venueId+'.png')"
                  class="image"
                  :alt="userorder.venueName"
                  @click="to_reservation(userorder.venueId)"
              />
            </el-col>
            <el-col span="18"><div class="grid-content" />
              <div class="text item">order id: {{userorder.id}}</div>
              <div class="text item">venue name: {{userorder.venueName}}</div>
              <div class="text item">address: {{userorder.address}}</div>
              <div class="text item">reserve time: {{userorder.reserveTime}}</div>
            </el-col>
          </el-row>
        </el-card>
        </div>
      </el-tab-pane>
      <div style="margin-top: 10px;">
        <el-pagination
            v-model:currentPage="currentPage"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            v-show="tF"
        >
        </el-pagination>
        <el-pagination
            v-model:currentPage="currentPage"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="UsedTotal"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            v-show="Utf"
        >
        </el-pagination>
        <el-pagination
            v-model:currentPage="currentPage"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="UnusedTotal"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            v-show="Uutf"
        >
        </el-pagination>
      </div>
    </el-tabs>
</div>
  </template>

<script>
import request from "../utils/request";
import {ArrowRightBold,Search,ArrowDown} from '@element-plus/icons-vue'

export default {
  name: 'Showorder',
  components:{ArrowRightBold,Search,ArrowDown},
  data() {
    return {
      user:{},
      form:{},
      dialogVisible:false,
      search: '',
      currentPage: 1,
      pageSize: 20,
      total: 0,
      tableData: [],
      fits:'contain',
      addNum:true,
      anti_AddNum:false,
      username:'',
      another:'',
      totalReal:'',
      UsedTotal:'',
      UnusedTotal:'',
      tF:true,
      Utf:false,
      Uutf:false,
    }
  },
  created() {
    if(sessionStorage.getItem("user")!=null){
      this.addNum = false
      this.anti_AddNum = true
      this.username = JSON.parse(sessionStorage.getItem("user"))
    }
    this.load()
  },
  methods:{
    Alltotal(){
      this.tF = true
      this.Utf = false
      this.Uutf = false
    },
    ToFaq(){
      this.$router.push("/Faq")
    },
    UnusedTotal(){
      this.tF = false
      this.Utf = false
      this.Uutf = true
    },
    UsedTotal(){
      this.tF = false
      this.Utf = true
      this.Uutf = false
    },
    filesUploadSuccess(res){
      console.log(res)
      this.form.photo = res.data
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum
      this.load()
    },
    load(){
      request.get("/Userorder",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          userId: this.username.id,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
        this.totalReal = this.total
      })
      let number = 0
      for(let i=0;i<this.tableData.length;i++){
        if(this.tableData[i].state==1){
          number = number+1
        }
      }
      this.UsedTotal = number
      number=0
      for(let i=0;i<this.tableData.length;i++){
        if(this.tableData[i].state==0){
          number = number+1
        }
      }
      this.UnusedTotal= number
    },
    to_chat_sb(){
      this.$router.push("/chat")
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
    to_reservation(id){
      this.$router.push({name:'reservation',params:{ac_id:id}})
    },
    ToFeedback(){
      this.$router.push("/CreateFeedback")
    },
    ToShowOrder(){
      this.$router.push("/Showorder")
    },
    cleanUser(){
      sessionStorage.removeItem("user")
      this.$router.go(0)
    },
  }
}
</script>

<style scoped>
.el-tabs{
  width: 60%;
  margin: auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 15px;
}

.text {
  font-size: 18px;
}

.item {
  margin-bottom: 20px;
}

.image {
  width: 70%;
  display: block;
}
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
  font-family: "Microsoft JhengHei",serif;
  font-weight: bold;
  font-size: 20px;
  width: 300px;
  color: #8c939d;
  top:20px;
  position: relative;
}
.login_button{
  font-size: x-large;
  color:white;
  font-family: "Times New Roman",serif;
}

</style>