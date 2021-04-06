<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" label-width="80px">
      <el-form-item v-if="role !==2" label="员工工号" prop="workId">
        <el-input v-model="form.workId" />
      </el-form-item>
      <el-form-item v-if="role !==2" label="员工姓名" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="时间" prop="salaryDate">
        <el-date-picker
          v-model="form.salaryDate"
          type="month"
          format="yyyy-MM"
          value-format="yyyy-MM"
          placeholder="选择月"
        />
      </el-form-item>
      <el-button type="primary" @click="getList()">查询</el-button>
      <el-button :disabled="tableData.length === 0" type="success" @click="excel()">导出</el-button>
      <el-button @click="resetData()">重置</el-button>
      <el-button v-if="role !==2" type="danger" :disabled="ids.length === 0" @click="batchDelete">删除</el-button>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        prop="id"
        label="编号"
        width="55"
        align="center"
      />
      <el-table-column
        prop="salaryDate"
        label="工资时间"
        width="120"
        align="center"
      />
      <el-table-column
        prop="workId"
        label="员工工号"
        width="120"
        align="center"
      />
      <el-table-column
        prop="name"
        label="真实姓名"
        width="120"
        align="center"
      />
      <el-table-column
        prop="basicSalary"
        label="基本工资"
        width="120"
        align="center"
      />
      <el-table-column
        prop="bonus"
        label="奖金"
        width="120"
        align="center"
      />
      <el-table-column
        prop="lunchSalary"
        label="午餐补助"
        width="120"
        align="center"
      />
      <el-table-column
        prop="trafficSalary"
        label="交通补助"
        width="120"
        align="center"
      />
      <el-table-column
        prop="attendanceSalary"
        label="考勤工资"
        width="120"
        align="center"
      />
      <el-table-column
        prop="allSalary"
        label="应发工资"
        width="120"
        align="center"
      />
      <el-table-column
        prop="pensionBase"
        label="养老金基数"
        width="120"
        align="center"
      />
      <el-table-column
        prop="pensionPer"
        label="养老金比率"
        width="120"
        align="center"
      />

      <el-table-column
        prop="medicalBase"
        label="医疗基数"
        width="120"
        align="center"
      />
      <el-table-column
        prop="medicalPer"
        label="医疗保险比率"
        width="120"
        align="center"
      />
      <el-table-column
        prop="accumulationFundBase"
        label="公积金基数"
        width="120"
        align="center"
      />
      <el-table-column
        prop="accumulationFundPer"
        label="公积金比率"
        width="120"
        align="center"
      />
      <el-table-column
        prop="realSalary"
        label="实发工资"
        width="120"
        align="center"
      />
      <el-table-column
        prop="remark"
        label="备注"
        width="120"
        align="center"
      />
      <el-table-column
        v-if="role !==2"
        fixed="right"
        label="操作"
        width="120"
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
import { getListByCondition, batchDelete, deleteById, exportExcel } from '@/api/empsalary'
import { mapGetters } from 'vuex'
export default {

  data() {
    return {
      tableData: [],
      form: {
        workId: '',
        name: '',
        salaryDate: null
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
    excel() {
      exportExcel(this.form).then((result) => {
        this.downloadFile(result)
      })
    },
    downloadFile(data) {
      // 文件导出
      if (!data) {
        return
      }
      const url = window.URL.createObjectURL(new Blob([data]))
      const link = document.createElement('a')
      link.style.display = 'none'
      link.href = url
      link.setAttribute('download', '员工工资报表.xlsx')
      document.body.appendChild(link)
      link.click()
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
        path: '/empsalary/add',
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

