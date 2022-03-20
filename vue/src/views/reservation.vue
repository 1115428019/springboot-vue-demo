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
                <el-dropdown-item divided>quit</el-dropdown-item>
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
      <el-tabs type="border-card">
        <el-tab-pane label="Monday">
          <div>
            <el-radio v-model="radio1" size="large" border>12:00-13:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option one" size="large" />
          </div>
          <div><el-radio v-model="radio2" size="large" border>13:00-14:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option two" size="large" />
          </div>
          <div>
            <el-radio v-model="radio3" size="large" border>14:00-15:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option three" size="large" />
          </div>
          <div><el-radio v-model="radio4" size="large" border>15:00-16:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option four" size="large" />
          </div>
          <div>
            <el-radio v-model="radio5" size="large" border>16:00-17:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option five" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Tuesday">
          <div>
            <el-radio v-model="radio1" size="large" border>12:00-13:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option one" size="large" />
          </div>
          <div><el-radio v-model="radio2" size="large" border>13:00-14:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option two" size="large" />
          </div>
          <div>
            <el-radio v-model="radio3" size="large" border>14:00-15:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option three" size="large" />
          </div>
          <div><el-radio v-model="radio4" size="large" border>15:00-16:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option four" size="large" />
          </div>
          <div>
            <el-radio v-model="radio5" size="large" border>16:00-17:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option five" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Wednesday">
          <div>
            <el-radio v-model="radio1" size="large" border>12:00-13:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option one" size="large" />
          </div>
          <div><el-radio v-model="radio2" size="large" border>13:00-14:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option two" size="large" />
          </div>
          <div>
            <el-radio v-model="radio3" size="large" border>14:00-15:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option three" size="large" />
          </div>
          <div><el-radio v-model="radio4" size="large" border>15:00-16:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option four" size="large" />
          </div>
          <div>
            <el-radio v-model="radio5" size="large" border>16:00-17:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option five" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Thursday">
          <div>
            <el-radio v-model="radio1" size="large" border>12:00-13:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option one" size="large" />
          </div>
          <div><el-radio v-model="radio2" size="large" border>13:00-14:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option two" size="large" />
          </div>
          <div>
            <el-radio v-model="radio3" size="large" border>14:00-15:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option three" size="large" />
          </div>
          <div><el-radio v-model="radio4" size="large" border>15:00-16:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option four" size="large" />
          </div>
          <div>
            <el-radio v-model="radio5" size="large" border>16:00-17:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option five" size="large" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="Friday">
          <div>
            <el-radio v-model="radio1" size="large" border>12:00-13:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option one" size="large" />
          </div>
          <div><el-radio v-model="radio2" size="large" border>13:00-14:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option two" size="large" />
          </div>
          <div>
            <el-radio v-model="radio3" size="large" border>14:00-15:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option three" size="large" />
          </div>
          <div><el-radio v-model="radio4" size="large" border>15:00-16:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option four" size="large" />
          </div>
          <div>
            <el-radio v-model="radio5" size="large" border>16:00-17:00</el-radio>
            <span>Free location:</span>
            <el-checkbox style="left: 60%;" v-model="checked2" label="option five" size="large" />
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
  <el-row style="width: 80%;margin: 0 auto;top: 20px;">
    <el-col ><el-button type="primary" size="normal">submit</el-button></el-col>
  </el-row>
  <el-row><el-col>
    <div class="row4_bottom">Copyright © 2021 西南交通大学. All rights reserved.</div>
  </el-col></el-row>
</template>

<script>
import {ArrowDown, ArrowRightBold, Search,StarFilled} from "@element-plus/icons-vue";
import { ref } from 'vue'
import request from "../utils/request";

export default {
  data(){
    return{
      photoLoad:false,
      fits:'contain',
      dialogTableVisible:ref(false),
      addNum:true,
      anti_AddNum:false,
      username:'',
      tableData:[],
      fit:'contain',
      photo:'',
      radio1:ref(''),
      radio2:ref(''),
      radio3:ref(''),
      radio4:ref(''),
      radio5:ref(''),
      radio6:ref(''),
      radio7:ref(''),
      radio8:ref(''),
      radio9:ref(''),
      radio10:ref(''),
      venueDateOne:[],
      venueDateTwo:[],
      venueDateThree:[],
      venueDateFour:[],
      venueDateFive:[],
    }
    },
  name: "reservation",
  components:{ArrowRightBold,Search,ArrowDown,StarFilled},
  created() {

    this.load()
    if(sessionStorage.getItem("user")!=null){
      this.addNum = false
      this.anti_AddNum = true
      this.username = JSON.parse(sessionStorage.getItem("user"))
    }
  },
  methods:{
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
      })
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