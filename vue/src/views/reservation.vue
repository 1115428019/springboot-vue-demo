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
                <el-dropdown-item @click="to_personal_center()">Personal information</el-dropdown-item>
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
  <el-row>
    <el-col :span="24" >
      <div class="grid-content bg-purple">
        <el-tag type="warning" class="row2-warn" @click="dialogTableVisible = true" >预约公告|关于{{tableData.venueName}}的通知
          <el-icon><ArrowRightBold/></el-icon>
        </el-tag>
      </div>
    </el-col>
    <!--  通告-->
    <el-dialog v-model="dialogTableVisible" title="notice">
      <p>{{tableData.noticeForUse}}</p>
    </el-dialog>
  </el-row>
  <el-row style="height: 300px;margin: 0 auto;width: 80%;">
    <div style="position: relative;width: 100%;">
      <el-col style="width: 50%;">
        <el-image v-if="photoLoad" style="height: 300px;" :src="require('../assets/activityPhoto/'+ this.photo)" :fit="fit" />
      </el-col>
      <el-col style="width: 50%;">
        <el-divider>
          <el-icon><star-filled /></el-icon>
        </el-divider>
        <p class="">venueName:{{tableData.venueName}}</p>
        <el-divider>
          <el-icon><star-filled /></el-icon>
        </el-divider>
        <p>address:{{tableData.address}}</p>
        <el-divider>
          <el-icon><star-filled /></el-icon>
        </el-divider>
        <p>phone:{{tableData.phone}}</p>
        <el-divider>
          <el-icon><star-filled /></el-icon>
        </el-divider>
        <p>open_time:{{tableData.openTime}}</p>
        <p></p>
        <el-divider>
          <el-icon><star-filled /></el-icon>
        </el-divider>
      </el-col>
    </div>
  </el-row>
  <el-row style="width: 80%;margin: 0 auto;top: 20px;">
    <el-col>
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <h2>Introduction</h2>
          </div>
        </template>
        <div class="text item"><span style="font-weight: bold;font-size: 18px">Facility:</span>  {{tableData.facility}}</div>
        <div class="text item"><h3>Description:</h3><br>{{tableData.introduction}}</div>
      </el-card>
    </el-col>
  </el-row>
  <el-row style="width: 80%;margin: 0 auto;top: 20px;">
    <el-col>
      <el-tabs type="border-card" v-model="activateDay">
        <el-tab-pane label="Monday" name="1">
          <div
              v-for="(venueTime, index) in venueDateOne"
              :key="venueTime"
          >
            <el-radio size="large" border>{{venueTime.actualTime}}</el-radio>
            <span>Free location:{{venueTime.state}}</span>
            <el-checkbox style="left: 60%;" :v-model="'checked'+venueTime.reservableTime" @change="changeThisState(venueTime.reservableTime)" :label="'option'+venueTime.reservableTime" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Tuesday" name="2">
          <div
              v-for="(venueTime, index) in venueDateTwo"
              :key="venueTime"
          >
            <el-radio  size="large" border>{{venueTime.actualTime}}</el-radio>
            <span>Free location:{{venueTime.state}}</span>
            <el-checkbox @change="changeThisState(venueTime.reservableTime)" style="left: 60%;" :v-model="'checked'+venueTime.reservableTime" :label="'option'+venueTime.reservableTime" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Wednesday" name="3">
          <div
              v-for="(venueTime, index) in venueDateThree"
              :key="venueTime"
          >
            <el-radio  size="large" border>{{venueTime.actualTime}}</el-radio>
            <span>Free location:{{venueTime.state}}</span>
            <el-checkbox @change="changeThisState(venueTime.reservableTime)" style="left: 60%;" :v-model="'checked'+venueTime.reservableTime" :label="'option'+venueTime.reservableTime" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Thursday" name="4">
          <div
              v-for="(venueTime, index) in venueDateFour"
              :key="venueTime"
          >
            <el-radio  size="large" border>{{venueTime.actualTime}}</el-radio>
            <span>Free location:{{venueTime.state}}</span>
            <el-checkbox @change="changeThisState(venueTime.reservableTime)" style="left: 60%;" :v-model="'checked'+venueTime.reservableTime" :label="'option'+venueTime.reservableTime" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Friday" name="5">
          <div
              v-for="(venueTime, index) in venueDateFive"
              :key="venueTime"
          >
            <el-radio size="large" border>{{venueTime.actualTime}}</el-radio>
            <span>Free location:{{venueTime.state}}</span>
            <el-checkbox @change="changeThisState(venueTime.reservableTime)" style="left: 60%;" :v-model="'checked'+venueTime.reservableTime" :label="'option'+venueTime.reservableTime" size="large" />
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
  <el-row style="width: 80%;margin: 0 auto;top: 20px;">
    <el-col ><el-button type="primary" size="normal" style="z-index: 1" @click="user_Subscribe">submit</el-button></el-col>
  </el-row>
  <el-row><el-col>
    <div class="row4_bottom">Copyright © 2021 西南交通大学. All rights reserved.</div>
  </el-col></el-row>
<!--  未登录提示-->
  <el-dialog v-model="dialogUserVisible" title="notice">
    <p>The user has not logged in. Please log in and make a reservation</p>
  </el-dialog>
</template>

<script>
import {ArrowDown, ArrowRightBold, Search,StarFilled} from "@element-plus/icons-vue";
import { ref } from 'vue'
import request from "../utils/request";
import { ElMessageBox, ElMessage } from 'element-plus'

export default {
  data(){
    return{
      dialogUserVisible:ref(false),
      photoLoad:false,
      fits:'contain',
      dialogTableVisible:ref(false),
      addNum:true,
      anti_AddNum:false,
      username:'',
      tableData:[],
      fit:'contain',
      photo:'',
      activateDay:ref('1'),
      checked1:ref(false),
      checked2:ref(false),
      checked3:ref(false),
      checked4:ref(false),
      checked5:ref(false),
      checked6:ref(false),
      checked7:ref(false),
      checked8:ref(false),
      checked9:ref(false),
      checked10:ref(false),
      checked11:ref(false),
      checked12:ref(false),
      checked13:ref(false),
      checked14:ref(false),
      checkedTime:'',
      venueDateOne:[],
      venueDateTwo:[],
      venueDateThree:[],
      venueDateFour:[],
      venueDateFive:[],
      venueAll:[],
      venueId: '',
    }
    },
  name: "reservation",
  components:{ArrowRightBold,Search,ArrowDown,StarFilled},
  created() {
    let ac_id = this.$route.params.ac_id
    this.venueId = ac_id
    this.load()
    if(sessionStorage.getItem("user")!=null){
      this.addNum = false
      this.anti_AddNum = true
      this.username = JSON.parse(sessionStorage.getItem("user"))
    }
  },
  methods:{
    ToFeedback(){
      this.$router.push("/CreateFeedback")
    },
    cleanUser(){
      sessionStorage.removeItem("user")
      this.$router.go(0)
    },
    user_Subscribe(){
      if(sessionStorage.getItem("user")!=null){
        this.addNum = false
        this.anti_AddNum = true
        this.username = JSON.parse(sessionStorage.getItem("user"))
          if(this.checked1==true){
            this.checkedTime=1
            this.postSth()
          }
        if(this.checked2==true){
          this.checkedTime=2
          this.postSth()
        }
        if(this.checked3==true){
          this.checkedTime=3
          this.postSth()
        }
        if(this.checked4==true){
          this.checkedTime=4
          this.postSth()
        }
        if(this.checked5==true){
          this.checkedTime=5
          this.postSth()
        }
        if(this.checked6==true){
          this.checkedTime=6
          this.postSth()
        }
        if(this.checked7==true){
          this.checkedTime=7
          this.postSth()
        }
        if(this.checked8==true){
          this.checkedTime=8
          this.postSth()
        }
        if(this.checked9==true){
          this.checkedTime=9
          this.postSth()
        }
        if(this.checked10==true){
          this.checkedTime=10
          this.postSth()
        }
        if(this.checked11==true){
          this.checkedTime=11
          this.postSth()
        }
        if(this.checked12==true){
          this.checkedTime=12
          this.postSth()
        }
        if(this.checked13==true){
          this.checkedTime=13
          this.postSth()
        }
        if(this.checked14==true){
          this.checkedTime=14
          this.postSth()
        }
      }
      else{
        this.dialogUserVisible = true
      }
    },
    to_chat_sb(){
      this.$router.push("/chat")
    },
    postSth(){
      this.username = JSON.parse(sessionStorage.getItem("user"))
      request.get("/VenueTime/operation",{
        params: {
          userId:this.username.id,
          venueId:this.venueId,
          day:this.activateDay,
          arrayTime: this.checkedTime,
        }
      }).then(res =>{
        console.log(res)
        ElMessageBox.confirm(
            res.data,
            'Warning',
            {
              confirmButtonText: 'OK',
              cancelButtonText: 'Cancel',
              type: 'warning',
            }
        )
      })
      this.$router.push("/pay")
    },
    sortKey(array,key){
      return array.sort(function(a,b){
        let x = a[key];
        let y = b[key];
        return ((x>y)?1:(y<x)?-1:0)   //从小到大排序
      })
    },
    ToFaq(){
      this.$router.push("/Faq")
    },
    load(){
      let ac_id = this.$route.params.ac_id
      request.get("/Activity/detail",{
        params: {
          ac_id: ac_id,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data
        this.photoLoad = true
        this.photo = res.data.photo
        this.venueId = this.tableData.id
      })
      request.get("VenueTime",{
        params: {
          venueId:this.venueId
        }
      }).then(res =>{
        console.log(res)
        this.venueAll = res.data
        for(let i=0;i<this.venueAll.length;i++){

          if(this.venueAll[i].day==1){this.venueDateOne.push(this.venueAll[i])}
          else if(this.venueAll[i].day==2){this.venueDateTwo.push(this.venueAll[i])}
          else if(this.venueAll[i].day==3){this.venueDateThree.push(this.venueAll[i])}
          else if(this.venueAll[i].day==4){this.venueDateFour.push(this.venueAll[i])}
          else if(this.venueAll[i].day==5){this.venueDateFive.push(this.venueAll[i])}
        }
        this.venueDateOne=this.sortKey(this.venueDateOne,'reservableTime')
        this.venueDateTwo=this.sortKey(this.venueDateTwo,'reservableTime')
        this.venueDateThree=this.sortKey(this.venueDateThree,'reservableTime')
        this.venueDateFour=this.sortKey(this.venueDateFour,'reservableTime')
        this.venueDateFive=this.sortKey(this.venueDateFive,'reservableTime')
      })
    },
    changeThisState(num){
      if(num==1){this.checked1=!this.checked1}
      else if(num==2){this.checked2=!this.checked2}
      else if(num==3){this.checked3=!this.checked3}
      else if(num==4){this.checked4=!this.checked4}
      else if(num==5){this.checked5=!this.checked5}
      else if(num==6){this.checked6=!this.checked6}
      else if(num==7){this.checked7=!this.checked7}
      else if(num==8){this.checked8=!this.checked8}
      else if(num==9){this.checked9=!this.checked9}
      else if(num==10){this.checked10=!this.checked10}
      else if(num==11){this.checked11=!this.checked11}
      else if(num==12){this.checked12=!this.checked12}
      else if(num==13){this.checked13=!this.checked13}
      else if(num==14){this.checked14=!this.checked14}
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
    ToShowOrder(){
      this.$router.push("/Showorder")
    },
  },


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

</style>