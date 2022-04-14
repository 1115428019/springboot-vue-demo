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
  <el-row style="width: 80%;margin: 0 auto;top: 20px;">
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%"
      >
        <el-table-column prop="id" label="ID" sortable />
        <el-table-column prop="username" label="作者"  />
        <el-table-column prop="content" label="内容" />
        <el-table-column prop="time" label="评论时间" />
        <el-table-column  label="操作">
          <template #default="scope">
            <el-button size="mini"  @click="details(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    <el-dialog title="提示" v-model="dialogVisible" width="50%">
      <el-form :model="form" label-width="120px">
        <div id="div1"></div>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveNew">确 定</el-button>
          </span>
      </template>
    </el-dialog>
  </el-row>
  <el-row style="width: 80%;margin: 0 auto;top: 20px;">
    <el-col><el-button @click="addNew">add a comment</el-button></el-col>
  </el-row>
  <el-dialog title="详情" v-model="vis" width="50%">
    <el-card>
      <div v-html="detail.content" style="min-height: 100px"></div>
    </el-card>
  </el-dialog>
</template>



<script>
import request from "../utils/request";
import {ArrowDown, ArrowRightBold, Search} from "@element-plus/icons-vue";
import {ref} from "vue";
import E from 'wangeditor'
let editor;

export default {
  name: "chat",
  components:{ArrowRightBold,Search,ArrowDown},
  created() {
    this.load()
    if(sessionStorage.getItem("user")!=null){
      this.addNum = false
      this.anti_AddNum = true
      this.username = JSON.parse(sessionStorage.getItem("user"))
    }
  },
  data(){
    return{
      fits:'contain',
      dialogTableVisible:ref(false),
      addNum:true,
      anti_AddNum:false,
      username:'',
      another:'',
      tableData:[],
      vis: false,
      detail: {},
      dialogVisible:false,
      form:[],
      getTime:'',
    }
  },
  methods:{
    details(row) {
      this.detail = row
      this.vis = true
    },
    getCurrentTime() {
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth()+1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      this.getTime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;
    },
    saveNew(){
      this.form.content = editor.txt.text()
      this.form.username = this.username.username
      this.getCurrentTime()
      this.form.time = this.getTime
      this.form.id = this.tableData.length+1
      request.put("/comment/news",this.form).then(res =>{
        this.dialogVisible=false
        if(res.code === '0'){
          this.$message({
            type:"success",
            message:"更新成功"
          })
        }
        else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load()
        this.dialogVisible = false
      })
      this.tableData.push(this.form)
      this.dialogVisible = false
      },
    addNew(){
      this.dialogVisible=true
      this.form = {}
      this.$nextTick(() => {
        // 关联弹窗里面的div，new一个 editor对象
        if (!editor) {
          editor = new E('#div1')
          // 配置 server 接口地址
          editor.create()
        }
        editor.txt.html("")
      })
    },
    load(){
      request.get("/comment",{
        params: {
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data
      })
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
    to_chat_sb(){
      this.$router.push("/chat")
    },
    ToFeedback(){
      this.$router.push("/CreateFeedback")
      },
    ToShowOrder(){
      this.$router.push("/Showorder")
    },
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

</style>