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
      <el-col :span="3" :offset="9">
        <div class="grid-content bg-purple">
          <div :key="fits"  class="row">
              <el-button type="primary" class="login_button" size="large">login</el-button>
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
      <div class="row4">
        <el-icon :size="25"><ArrowLeft /></el-icon>
        <img class="row4-img" src="../assets/image/1.png" @click="dance(dance)" alt="dance">
        <img class="row4-img" src="../assets/image/2.png" @click="" alt="football">
        <img class="row4-img" src="../assets/image/3.png" @click="" alt="Calligraphy studio">
        <img class="row4-img" src="../assets/image/4.png" @click="" alt="Instrument room">
        <img class="row4-img" src="../assets/image/5.png" @click="" alt="PingPing ball room">
        <img class="row4-img" src="../assets/image/6.png" @click="" alt="badminton">
        <img class="row4-img" src="../assets/image/7.png" @click="" alt="Piano room">
        <el-icon :size="25"><ArrowRight /></el-icon>
      </div>
      <div class="row4-recommend">
        <el-button type="primary" size="small"></el-button>
        Recommended
      </div>
    </div></el-col>
  </el-row>
  <el-row >
    <el-col
        :span="3"
        :offset="3"
    >
      <el-card :body-style="{ padding: '0px' }">
      <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          class="image"
          alt="1"
      />
      <div style="padding: 14px">
        <span>地址</span>
        <div class="bottom">
          <time class="time">时间:{{ currentDate }}</time>
          <el-button type="text" class="button">Operating</el-button>
        </div>
      </div>
    </el-card>
    </el-col>
    <el-col
        :span="3"
        :offset="4"
    >
      <el-card :body-style="{ padding: '0px' }">
        <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
            alt="2"
        />
        <div style="padding: 14px">
          <span>地址</span>
          <div class="bottom">
            <time class="time">时间:{{ currentDate }}</time>
            <el-button type="text" class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col
        :span="3"
        :offset="4"
    >
      <el-card :body-style="{ padding: '0px' }">
        <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
            alt="3"
        />
        <div style="padding: 14px">
          <span>地址</span>
          <div class="bottom">
            <time class="time">时间:{{ currentDate }}</time>
            <el-button type="text" class="button">Operating</el-button>
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
import {Search,ArrowRight,ArrowLeft,ArrowRightBold} from '@element-plus/icons-vue'
export default {
  name: "cover",
  components:{Search,ArrowRight,ArrowLeft,ArrowRightBold},
  data(){
    return{
      fits:'contain',
      fits2:'scale-down',
      currentDate:'1990',
    }
  },
  methods:{
    dance(category){
      sessionStorage.setItem("category",JSON.stringify(category))
      this.$router.push("/appointment")
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
  width: 10.11%;
  margin-left: 20px;
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
</style>