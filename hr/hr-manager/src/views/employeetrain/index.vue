<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" label-width="80px">
      <el-form-item v-if="role !==2" label="员工姓名" prop="name">
        <el-input v-model="form.name" clearable />
      </el-form-item>
      <el-form-item v-if="role !==2" label="员工工号" prop="workId">
        <el-input v-model="form.workId" clearable />
      </el-form-item>
      <el-form-item label="培训日期" prop="trainDate">
        <el-date-picker
          v-model="form.trainDate"
          clearable
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="培训日期"
        />
      </el-form-item>
      <el-button type="primary" @click="getList()">查询</el-button>
      <el-button @click="resetData">重置</el-button>
      <el-button v-if="role !==2" type="danger" :disabled="ids.length === 0" @click="batchDelete"> 删除</el-button>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        prop="id"
        label="编号"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="workId"
        label="员工工号"
        min-width="120"
        align="center"
      />
      <el-table-column
        prop="name"
        label="员工姓名"
        min-width="120"
        align="center"
      />
      <el-table-column
        prop="trainDate"
        label="培训日期"
        min-width="120"
        align="center"
      />
      <el-table-column
        prop="trainContent"
        label="培训内容"
        min-width="300"
        align="center"
      />
      <el-table-column
        v-if="role !==2"
        fixed="right"
        label="操作"
        min-width="120"
        align="center"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="updateClick(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="handleClick(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        :current-page="pageIndex"
        :page-size="pageSize"
        :total="total"
        style="float: right;margin-top:10px"
        background
        layout="total, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>

</template>

<script>
import { getListByCondition, batchDelete, deleteById } from '@/api/employeetrain'
import { mapGetters } from 'vuex'
export default {

  data() {
    return {
      tableData: [],
      form: {
      },
      pageIndex: 1,
      pageSize: 10,
      ids: [],
      total: 0
    }
  },
  computed: {
    ...mapGetters([
      'role',
      'id'
    ])
  },
  mounted() {
    this.getList()
  },
  methods: {
    handleSizeChange(val) {
      this.pagesize = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.pageIndex = val
      this.getList()
    },
    handleClick(row) {
      this.$confirm('您确定要删除该条数据吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(row.id).then(response => {
          const res = response
          if (res.code === 1) {
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.getList()
          } else {
            this.$message({
              message: '删除失败！',
              type: 'error'
            })
          }
        })
      })
    },
    updateClick(row) {
      this.$router.push({
        path: '/employeetrain/add',
        query: {
          id: row.id
        }
      })
    },
    resetData() {
      this.$refs['form'].resetFields()
      this.getList()
    },
    getList() {
      if (this.role === 2) {
        this.form.eid = this.id
      }
      getListByCondition(this.form, this.pageIndex, this.pageSize).then(resp => {
        this.tableData = resp.data.list
        this.total = resp.data.total
      })
    },
    handleSelectionChange(val) {
      this.ids = val.map(item => item.id)
    },
    batchDelete() {
      this.$confirm('您确定要批量删除已选定的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ids = this.ids.toString()
        batchDelete(ids).then(response => {
          const res = response
          if (res.code === 1) {
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.getList()
          } else {
            this.$message({
              message: '删除失败！',
              type: 'error'
            })
          }
        })
      })
    }
  }
}
</script>

<style scoped>
.container{
  padding: 20px;
}
</style>

