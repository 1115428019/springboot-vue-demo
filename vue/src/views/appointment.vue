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
                <el-dropdown-item>dishonest actions</el-dropdown-item>
                <el-dropdown-item @click="ToFeedback()">Complaints Suggestions</el-dropdown-item>
                <el-dropdown-item>assistance center</el-dropdown-item>
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
  <el-row>
    <el-col :span="24" >
      <div class="grid-content bg-purple">
      <el-tag type="warning" class="row2-warn" @click="dialogTableVisible = true" >预约公告|关于体育中心爽约情况的通知
        <el-icon style="left:79%" ><ArrowRightBold/></el-icon>
      </el-tag>
    </div>
    </el-col>
  </el-row>
  <div  style="margin: 0 auto;width: 80%;">
  <el-row>
    <el-col style="font-size: 16px;font-weight: 600;margin-bottom: 20px;">Personal appointment</el-col>
  </el-row>
    <el-row style="margin-bottom: 10px;"><el-col>
      <span>campus:</span>
      <el-radio-group v-model="radio1" @change="campus()">
        <el-radio-button label="0">all</el-radio-button>
        <el-radio-button label="1">The rhinoceros pukou campus</el-radio-button>
        <el-radio-button label="2">In the nine campus</el-radio-button>
      </el-radio-group>
    </el-col></el-row>
    <el-row style="margin-bottom: 30px;"><el-col>
      <span>categories:</span>
      <el-radio-group v-model="radio2" @change="AcCategory()">
        <el-radio-button label="0">all</el-radio-button>
        <el-radio-button label="15">dance</el-radio-button>
        <el-radio-button label="5">football</el-radio-button>
        <el-radio-button label="4">calligraphy studio</el-radio-button>
        <el-radio-button label="3">instrument</el-radio-button>
        <el-radio-button label="2">badminton</el-radio-button>
        <el-radio-button label="6">table tennis</el-radio-button>
        <el-radio-button label="7">piano</el-radio-button>
        <el-radio-button label="8">cards and chess room</el-radio-button>
        <el-radio-button label="9">the multi-function hall</el-radio-button>
        <el-radio-button label="10">billiards</el-radio-button>
        <el-radio-button label="11">tennis</el-radio-button>
        <el-radio-button label="12">the gym</el-radio-button>
        <el-radio-button label="13">volleyball</el-radio-button>
        <el-radio-button label="14">the meeting room</el-radio-button>
        <el-radio-button label="1">basketball</el-radio-button>
      </el-radio-group>
    </el-col></el-row>
    <el-row><el-col>
      <div class="row6-1 row6-2"></div>
      <div class="row6-2">The reservation list</div>
      <div style="float: right">
        <el-input
          size="small"
          placeholder="Please Input the type of activities and Venues"
          @keyup.enter.native="searchInfo"
          v-model="another"
      >
        <template #suffix>
          <el-icon class="el-input__icon"><search /></el-icon>
        </template>
      </el-input></div>
    </el-col>
    <el-col :span="24">
      <el-col
          v-for="(activity, index) in tableData"
          :key="activity"
          :span="6"
          :offset="index > 0 ? 3 : 0"
      >
        <el-card :body-style="{ padding: '0px' }">
          <img
              :src="require('../assets/activityPhoto/'+activity.photo)"
              class="image"
              :alt="activity.venueName"
              @click="to_reservation(activity.id)"
          />
          <div style="padding: 14px">
            <p>venueName:</p>
            <span>{{activity.venueName}}</span>
            <p>address:</p>
            <span>{{activity.address}}</span>
            <div class="bottom">
              <time class="time">time:{{ activity.openTime }}</time>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-col>
      <el-col style="margin-bottom: 100px;" :span="24">
        <div style="margin: 10px 0;float: right">
          <el-pagination
              v-model:currentPage="currentPage"
              :page-sizes="[5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          >
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </div>
  <el-row><el-col>
    <div class="row4_bottom">Copyright © 2021 西南交通大学. All rights reserved.</div>
  </el-col></el-row>
<!--  通告-->
  <el-dialog v-model="dialogTableVisible" title="notice">
   <p>Personal reputation starts at 0, every time you keep an appointment, your reputation will increase once, and every time you skip an appointment, your reputation will decrease by 1. When your reputation falls below 0, you will not be able to book the venue again, and you will have to wait for a week before making another appointment</p>
  </el-dialog>
</template>

<script>
import {ArrowRightBold,Search,ArrowDown} from '@element-plus/icons-vue'
import { ref } from 'vue'
import request from "../utils/request";


export default {
  name: "appointment",
  components:{ArrowRightBold,Search,ArrowDown},
  created() {
    this.load()
    if(sessionStorage.getItem("user")!=null){
      this.addNum = false
      this.anti_AddNum = true
      this.username = JSON.parse(sessionStorage.getItem("user"))
    }
  },
  methods:{
    cleanUser(){
      sessionStorage.removeItem("user")
      this.$router.go(0)
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
      let sub = this.$route.params.searchName
      this.another = sub
      request.get("/Activity",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.another,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
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
    ToShowOrder(){
      this.$router.push("/Showorder")
    },
    to_personal_center(){
      this.$router.push("/personal_center")
    },
    to_reservation(id){
      this.$router.push({name:'reservation',params:{ac_id:id}})
    },
    searchInfo(){
      request.get("/Activity",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.another,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    campus(){
      request.get("/Activity/radio1",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          radio: this.radio1,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    AcCategory(){
      request.get("/Activity/radio2",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          radio: this.radio2,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    ToFeedback(){
      this.$router.push("/CreateFeedback")
    },
  },
  data(){
    return{
      fits:'contain',
      radio1:ref('all'),
      radio2:ref('all'),
      dialogTableVisible:ref(false),
      currentPage:1,
      pageSize:10,
      total:0,
      tableData:[
      ],
      addNum:true,
      anti_AddNum:false,
      username:'',
      another:'',
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

.row2-warn{
  display: block;
  font-size: small;
  width: 80%;
  margin-left: 10%;
  text-align: left;
  height: 30px;
  line-height: 30px;
  margin-bottom: 30px;
}

.row4_bottom{
  text-align:center;
  margin-top:20px;
  color: #7e8187;
  background: #f9f9f9;
}

body{
  font-size: 14px;
  line-height: 1;
  font-family: Microsoft YaHei,Helvetica Neue,Helvetica,Arial,sans-serif;
  background-color: #fff;
  color: #333;
}

el-radio-button {
  position: relative;
  display: inline-block;
  outline: 0;
  margin-right: 20px;
  border: 1px solid transparent;
  border-radius: 20px;
  overflow: hidden;
  height: 25px;
}

el-radio-group {
  display: inline-flex;
  align-items: center;
  flex-wrap: wrap;
  font-size: 0;
}

.row6-1{
  width: 5px;
  height: 17px;
  margin-right: 5px;
  background-image: linear-gradient(#6495ed,#1e90ff);
  border-radius: 10px;
}

.row6-2{
  float: left;
}
</style>