<template>
    <el-row  style="height: 110px; " >
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
            <a @click="to_personal_center()" style="cursor: pointer;">My order</a>
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
                  <el-dropdown-item>Personal information</el-dropdown-item>
                  <el-dropdown-item>dishonest actions</el-dropdown-item>
                  <el-dropdown-item>Complaints Suggestions</el-dropdown-item>
                  <el-dropdown-item>assistance center</el-dropdown-item>
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
  <el-row style="height: 500px">
    <el-col :span="24" style="width: 100%" class="row2back">
      <div class="grid-content bg-purple">
              <div class="row2-title1">智能预约</div>
              <div class="row2-title2">INTELLIGENT APPOINTMENT</div>
              <el-input
                  size="large"
                  placeholder="Please Input the type of activities and Venues"
                  class="search"
                  v-model="search"
                  @keyup.enter.native="searchInfo"
              >
                <template #suffix>
                  <el-icon class="el-input__icon"><search /></el-icon>
                </template>
              </el-input>
      </div>
    </el-col>
  </el-row>
  <el-row >
    <el-col :span="24" ><div class="grid-content bg-purple">
      <el-tag type="warning" class="row3-warn"  >系统公告|各位老师~同学
        <el-icon style="left:86%"><ArrowRightBold/></el-icon>
      </el-tag>
      <div class="row4" >
        <el-carousel indicator-position="outside" arrow="always" :interval="500000000000" v-if="smallIconData.length>0">
          <el-carousel-item v-for="(list,num) in smallIconData" :key="num" >
            <img  v-for="(imgList,index2) in list" class="row4-img" :src="require('../assets/image/'+imgList.photo+'.png')" @click="classify(index2)" :alt="imgList.alt">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="row4-recommend">
        <el-button type="primary" size="small"></el-button>
        Recommended
      </div>
    </div></el-col>
  </el-row>
  <el-row >
    <el-col
        v-for="(activity, index) in tableData"
        :key="activity"
        :span="5"
        :offset="index > 0 ? 3 : 0"
        style="margin-left: 10%;"
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
  </el-row>
  <el-row>
    <el-col>
      <div class="row5_bottom">Copyright © 2021 西南交通大学. All rights reserved.</div>
    </el-col>
  </el-row>
</template>

<script>
import {Search,ArrowRight,ArrowLeft,ArrowRightBold,ArrowDown} from '@element-plus/icons-vue'
import request from "../utils/request";
export default {
  name: "cover",
  components:{Search,ArrowRight,ArrowLeft,ArrowRightBold,ArrowDown},
  data(){
    return{
      fits:'contain',
      fits2:'scale-down',
      currentDate:'1990',
      search:'',
      tableData:[
      ],
      smallIconData:[
      ],
      addNum:true,
      anti_AddNum:false,
      username:'',
    }
  },
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
    load(){
      request.get("/Activity/cover",{
        params: {
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data
      })
      request.get("/Activity/cover/smallIcon",{
      }).then(res =>{
        console.log(res)
        this.smallIconData = res.data
        if(this.smallIconData && this.smallIconData.length>0) {
          let newDataList = []
          let current = 0
          for (let i = 0; i <= this.smallIconData.length - 1; i++) {
            if (i % 7 !== 0 || i === 0) {
              if (!newDataList[current]) {
                newDataList.push([this.smallIconData[i]])
              } else {
                newDataList[current].push(this.smallIconData[i])
              }
            } else {
              current++
              newDataList.push([this.smallIconData[i]])
            }
          }
          this.smallIconData = [...newDataList]
        }
      })
    },
    classify(category){
      sessionStorage.setItem("category",JSON.stringify(category))
      this.$router.push("/appointment")
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
    searchInfo(){
      this.$router.push({name:'appointment',params:{searchName:this.search}})
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

.row2back{
  background-image: url('../assets/image/first_back.jpg');
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.search{
  z-index: 100;
  color: darkslateblue;
  width: 40%;
  left: 30%;
  margin-top: 10px;
}

.row2-title1{
  margin-top: 10%;
  text-align: center;
  color: darkred;
  font-size: x-large;
  font-weight: bold;
  font-style: italic;
}

.row2-title2{
  color: darkred;
  text-align: center;
  font-size: x-large;
  font-family: "Times New Roman",serif;
}

.row3-warn{
  display: block;
  font-size: small;
  width: 80%;
  margin-left: 10%;
  text-align: left;
  height: 30px;
  line-height: 30px;
}

.row4{
  width: 80%;
  margin-left: 10%;
  height: 200px;
}

.row4-img{
  float: left;
  margin-left: 30px;
  margin-right: 20px;
}

.row4-recommend{
  margin-left: 10%;
}

.image{
  width: 100%;
  display: block;
}

.row5_bottom{
 text-align:center;
  margin-top:20px;
  color: #7e8187;
  background: #f9f9f9;
}

.badgeLine{
  font-family: Microsoft YaHei,Helvetica Neue,Helvetica,Arial,sans-serif;
  line-height: 20px;
  list-style: none;
  font-size: 20px;
  color: #ccc;
}

.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>