<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" label-width="80px">
      <el-form-item v-if="role !==2" label="员工工号" prop="workId">
        <el-input v-model="form.workId" clearable />
      </el-form-item>
      <el-form-item v-if="role !==2" label="员工姓名" prop="realName">
        <el-input v-model="form.realName" clearable />
      </el-form-item>
      <el-form-item label="考勤月份" prop="time">
        <el-date-picker
          v-model="form.time"
          clearable
          type="month"
          value-format="yyyy-MM"
          format="yyyy-MM"
          placeholder="选择月"
        />
      </el-form-item>
      <el-button type="primary" @click="getList()">查询</el-button>
      <el-button @click="resetData()">重置</el-button>
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
        prop="recordId"
        label="编号"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="workId"
        label="工号"
        min-width="120"
        align="center"
      />
      <el-table-column
        prop="realName"
        label="真实姓名"
        min-width="120"
        align="center"
      />
      <el-table-column
        prop="earlyNum"
        label="早退次数"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="absenteeismNum"
        label="缺勤天数"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="attendanceNum"
        label="出勤天数"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="lateNum"
        label="迟到次数"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="evectionNum"
        label="出差天数"
        min-width="80"
        align="center"
      />
      <el-table-column
        prop="time"
        label="考勤月份"
        min-width="100"
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
import { getListByCondition, batchDelete, deleteById } from '@/api/attendancerecord'
import { getEmployeeByWordId } from '@/api/employee'
import { mapGetters } from 'vuex'
export default {

  data() {
    return {
      tableData: [],
      form: {
        time: null
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
    getEmployeeByWordId() {
      getEmployeeByWordId(this.form.wordId).then(resp => {
        if (resp.code === 1) {
          this.form.name = resp.data.form
          this.form.eid = resp.data.id
          this.form.realName = resp.data.name
        }
      })
    },
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
        deleteById(row.recordId).then(response => {
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
        path: '/attendance/add',
        query: {
          id: row.recordId
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
      this.ids = val.map(item => item.recordId)
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

