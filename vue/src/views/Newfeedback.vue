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




  <div style="margin: auto;width: 80%;color: #a9a9a9;margin-top: 10px">
    <el-tabs type="border-card">
      <el-tab-pane label="feedback shown">
        <div style="margin: 10px 0">
          <el-input v-model="search" placeholder="Please input key content" style="width: 20%" clearable />
          <el-button type="primary" style="margin-left: 5px" @click="load">search</el-button>
          <el-button type="primary" @click="back" style="margin: 10px 20px">back</el-button>

        </div>
        <!--表格展示区域-->
        <el-table :data="tableData" border stripe style="width: 99%">
          <el-table-column prop="id" sortable label="ID"  />
          <el-table-column prop="content" label="content"  />
          <el-table-column prop="state" label="state" />
        </el-table>

      </el-tab-pane>
    </el-tabs>
  </div>




</template>

<script>
import request from "../utils/request";
import {ArrowRightBold,Search,ArrowDown} from '@element-plus/icons-vue'


export default {
  name: "CreateFeedback",
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
  methods: {
    filesUploadSuccess(res) {
      console.log(res)
      this.form.photo = res.data
    },
    back(){
      this.$router.push("/CreateFeedback")
    },

    load() {
      request.get("/feedback", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    ToFaq(){
      this.$router.push("/Faq")
    },
    save() {
      if (this.form.id) {//更新
        request.put("/feedback", this.form).then(res => {
          console.log(res)
          if (res.code == '0') {
            this.$message({
              type: "success",
              message: "Edit successfully!"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() //刷新表格数据
          this.dialogVisible = false //关闭弹窗
        })
      } else {//新增
        request.post("/feedback", this.form).then(res => {
          console.log(res)
          if (res.code == '0') {
            this.$message({
              type: "success",
              message: "Add successfully!"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() //刷新表格数据
          this.dialogVisible = false //关闭弹窗
        })
      }
    },
    add() {
      this.dialogVisible = true
      this.form = {} //把表单写的属性清空
      if (this.$refs['upload']) {
        this.$refs['upload'].clearFiles() //清除历史文件列表
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.$nextTick(() => {
        if (this.$refs['upload']) {
          this.$refs['upload'].clearFiles() //清除历史文件列表
        }
      })
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/feedback/" + id).then(res => {
        if (res.code == '0') {
          this.$message({
            type: "success",
            message: "Delete successfully!"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()  //重新加载表格的数据
      })
    },
    handleSizeChange(pageSize) { //改变每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) { //改变当前页码触发
      this.currentPage = pageNum
      this.load()
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