<template>
  <!--header-->
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





  <!-- Faq -->
  <el-card class="box-card" style="width: 80%;margin: auto">
    <template #header>
      <div class="card-header">
        <span >SWJTU Sport Center F&Q</span>
      </div>
    </template>
    <el-row :gutter="20">
      <el-col span="6"><div class="grid-content" />
        <div class="demo-collapse" style="margin: auto">
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="Q:   Is the gym open to the public?" name="1" >
              <div>
                Hello, due to epidemic control reasons, the gymnasium on campus has
                not reopened to the public for the time being, and is only available for on-campus
                personnel.
              </div>
            </el-collapse-item>


            <el-collapse-item title="Q: What are the opening hours of the gym?" name="2">
              <div>
                The gym is open from Monday to Friday from 8:00am to 17:00pm. Please refer
                to the reservation page for details.
              </div>
            </el-collapse-item>

            <el-collapse-item title="Q: What is the price of a single ticket?" name="3">
              <div>
                The gymnasium is provided free of charge to teachers and students on campus,
                without payment.
              </div>
            </el-collapse-item>

            <el-collapse-item title="Q: Can personal information be amended?" name="4">
              <div>
                Yes, the webpage supports modifying the user name, phone number, password,
                and user avatar. The mobile terminal does not currently support modifying personal
                information.
              </div>
            </el-collapse-item>

            <el-collapse-item title="Q: Can the user see the booked order records?" name="5">
              <div>
                Yes, users can view all records of historical orders, including completed and
                unfulfilled orders.
              </div>
            </el-collapse-item>

            <el-collapse-item title="Q: How can I view the details of the venue?" name="6">
              <div>
                Click the picture or text on the home page or the display page of the venue
                overview to view the detailed information of the venue.
              </div>
            </el-collapse-item>

            <el-collapse-item title="Q: How to make an appointment?" name="7">
              <div>
                You can make an appointment on the details page of the venue, and users can choose a suitable time to
                make an appointment according to their own needs.
              </div>

            </el-collapse-item>

            <el-collapse-item title="Q: Why did the appointment fail?" name="8">
              <div>
                The reason for the failure of the reservation is that the number
                of reservations in the venue has reached the upper limit in the
                time slot selected by the user, and the user can see the remaining
                seats in each time slot of different venues at the appointment meeting.
              </div>
            </el-collapse-item>
            <el-collapse-item title="Q: What should I do if I find damage to equipment or other equipment in the gym?" name="9">
              <div>
                The web page can submit feedback on the feedback page, and the
                administrator can view the feedback submitted by the students in
                the background management system. You can view the contact information
                of managers on the mobile terminal.
              </div>
            </el-collapse-item>
            <el-collapse-item title="Q: What if I forget my password?" name="10">
              <div>
                You can click the "Forgot Password" button on the registration page
                to change your password according to the prompts.
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-col>

    </el-row>
  </el-card>
</template>

<script >
import request from "../utils/request";
import {ArrowRightBold,Search,ArrowDown} from '@element-plus/icons-vue'

import { ref } from 'vue'

const activeName = ref('1')
export default {
  name: "faq",

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
    ToFaq(){
      this.$router.push("/Faq")
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

<style>
.el-collapse.el-collapse-item{
  width: 500px;
}
</style>

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



.el-tabs{
  width: 60%;
  margin: auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: auto;
  font-size: 20px;
  font-weight: bold;
}


  /deep/ .el-collapse-item__header{
    font-size: 18px;
    color: cornflowerblue;
    width: 1000px;
    font-weight: bold;
  }
/deep/ .el-collapse-item__content{
  width: 900px;
  font-size: 16px;
}


</style>