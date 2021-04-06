<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" label-width="60px">
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
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      show-summary
      :summary-method="getSummaries"
    >
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
import { getListByCondition, exportStatisticalSalaryExcel } from '@/api/empsalary'
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
      'role'
    ])
  },
  mounted() {
    this.getList()
  },
  methods: {
    getSummaries(param) {
      const { columns, data } = param
      const sums = []
      console.log(param)
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计'
          return
        }
        const values = data.map(item => Number(item[column.property]))
        if (!values.every(value => isNaN(value)) && column.label !== '员工工号') {
          sums[index] = values.reduce((prev, curr) => {
            const value = curr
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
          sums[index] = sums[index].toFixed(2)
        } else {
          sums[index] = ''
        }
      })
      return sums
    },
    excel() {
      exportStatisticalSalaryExcel(this.form).then((result) => {
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
      link.setAttribute('download', '工资报表.xlsx')
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
    resetData() {
      this.$refs['form'].resetFields()
      this.getList()
    },
    getList() {
      getListByCondition(this.form, this.pageIndex, this.pageSize).then(resp => {
        this.tableData = resp.data.list
        this.total = resp.data.total
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

