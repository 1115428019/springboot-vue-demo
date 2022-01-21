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
    <el-col :span="3" :offset="9">
      <div class="grid-content bg-purple">
        <div :key="fits"  class="row">
          <el-button type="primary" class="login_button" size="large">login</el-button>
        </div>
      </div>
    </el-col>
  </el-row>
  <el-row>
    <el-col :span="24" >
      <div class="grid-content bg-purple">
      <el-tag type="warning" class="row2-warn"  >预约公告|关于体育中心爽约情况的通知
        <el-icon style="left:79%"><ArrowRightBold/></el-icon>
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
      <el-radio-group v-model="radio1">
        <el-radio-button label="all"></el-radio-button>
        <el-radio-button label="The rhinoceros pukou campus"></el-radio-button>
        <el-radio-button label="In the nine campus"></el-radio-button>
      </el-radio-group>
    </el-col></el-row>
    <el-row style="margin-bottom: 30px;"><el-col>
      <span>categories:</span>
      <el-radio-group v-model="radio2">
        <el-radio-button label="all"></el-radio-button>
        <el-radio-button label="dance"></el-radio-button>
        <el-radio-button label="football"></el-radio-button>
        <el-radio-button label="calligraphy studio"></el-radio-button>
        <el-radio-button label="instrument"></el-radio-button>
        <el-radio-button label="badminton"></el-radio-button>
        <el-radio-button label="table tennis"></el-radio-button>
        <el-radio-button label="piano"></el-radio-button>
        <el-radio-button label="cards and chess room"></el-radio-button>
        <el-radio-button label="the multi-function hall"></el-radio-button>
        <el-radio-button label="billiards"></el-radio-button>
        <el-radio-button label="tennis"></el-radio-button>
        <el-radio-button label="the gym"></el-radio-button>
        <el-radio-button label="volleyball"></el-radio-button>
        <el-radio-button label="the meeting room"></el-radio-button>
        <el-radio-button label="basketball"></el-radio-button>
      </el-radio-group>
    </el-col></el-row>
    <el-row><el-col>
      <div class="row6-1 row6-2"></div>
      <div class="row6-2">The reservation list</div>
      <div style="float: right">
        <el-input
          size="small"
          placeholder="Please Input the type of activities and Venues"
      >
        <template #suffix>
          <el-icon class="el-input__icon"><search /></el-icon>
        </template>
      </el-input></div>
    </el-col>
    <el-col :span="24">
      <el-card :body-style="{ padding: '0px' }" style="width: 22%;float: left;margin: 20px 30px 20px 0;">
      <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          class="image"
          alt="2"
      />
      <div style="padding: 14px">
        <span>地址</span>
        <div class="bottom">
          <time class="time">时间:7:30-17:30</time>
          <el-button type="text" class="button">Operating</el-button>
        </div>
      </div>
    </el-card>
      <el-card :body-style="{ padding: '0px' }" style="width: 22%;float: left;margin: 20px 30px 20px 0;">
        <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
            alt="2"
        />
        <div style="padding: 14px">
          <span>地址</span>
          <div class="bottom">
            <time class="time">时间:7:30-17:30</time>
            <el-button type="text" class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
      <el-card :body-style="{ padding: '0px' }" style="width: 22%;float: left;margin: 20px 30px 20px 0;">
        <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
            alt="2"
        />
        <div style="padding: 14px">
          <span>地址</span>
          <div class="bottom">
            <time class="time">时间:7:30-17:30</time>
            <el-button type="text" class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
      <el-card :body-style="{ padding: '0px' }" style="width: 22%;float: left;margin: 20px 30px 20px 0;">
        <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
            alt="2"
        />
        <div style="padding: 14px">
          <span>地址</span>
          <div class="bottom">
            <time class="time">时间:7:30-17:30</time>
            <el-button type="text" class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
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
</template>

<script>
import {ArrowRightBold,Search} from '@element-plus/icons-vue'
import { ref } from 'vue'

export default {
  name: "appointment",
  components:{ArrowRightBold,Search},
  methods:{
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum
      this.load()
    }
  },
  data(){
    return{
      fits:'contain',
      radio1:ref('all'),
      radio2:ref('all'),
      currentPage:1,
      pageSize:10,
      total:0,
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