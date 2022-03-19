<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" @click="load" style="margin-left: 5px">查询</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%"
      >
      <el-table-column prop="id" label="ID" sortable />
      <el-table-column prop="name" label="名称"  />
      <el-table-column prop="price" label="单价" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="createTime" label="出版时间" />
      <el-table-column label="封面">
        <template #default="scope">
          <el-image style="width: 100px;width: 100px"
          :src="scope.row.cover"
          :preview-src-list="[scope.row.cover]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column  label="操作">
        <template #default="scope">
          <el-button size="mini"  @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
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
      <el-dialog v-model="dialogVisible" title="Tips"  width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="form.price" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="form.author" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="出版时间">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="封面">
            <el-upload ref="upload" action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess">
              <el-button  type="success">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
        <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template  >

<script>
// @ is an alias to /src
import request from "../utils/request";

export default {
  name: 'book',
  components: {
  },
  created() {
    this.load()
  },
  methods:{
    filesUploadSuccess(res){
      console.log(res)
      this.form.cover = res.data
    },
    load(){
      request.get("/Book",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })

    },
    add(){
      this.dialogVisible=true
      this.form = {}
      if (this.$refs['upload']) {
        this.$refs['upload'].clearFiles()  // 清除历史文件列表
      }
    },
    save(){
      if (this.form.id){
        request.put("/Book",this.form).then(res =>{
          console.log(res)
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
        })}
      else{
        request.post("/Book",this.form).then(res =>{
          console.log(res)
          this.dialogVisible=false
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })
          }
          else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
        this.load()
        this.dialogVisible = false
      }
    },
    handleDelete(id){
      request.delete("/Book/"+ id).then(res =>{
        console.log(res)
        if(res.code === '0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }
        else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load()
      })
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.$nextTick(() => {
        if (this.$refs['upload']) {
          this.$refs['upload'].clearFiles()  // 清除历史文件列表
        }
      })
    },
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
      dialogVisible:false,
      form:{},
      search:'',
      total:0,
      currentPage:1,
      pageSize:10,
      tableData:[
      ]
    }
  }
}


</script>
<style>
.el-table .el-table__cell{
  z-index: auto;
}
</style>
