<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" :rules="rules">
      <el-row>
        <el-col :span="8">
          <el-form-item label="员工工号" prop="workId">
            <el-input v-model="form.workId" type="number" placeholder="请输入" @blur="getEmployeeByWorkId()" />
          </el-form-item>
          <el-form-item label="基本工资" prop="basicSalary">
            <el-input v-model="form.basicSalary" type="number" min="0" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="公积金基数" prop="accumulationFundBase">
            <el-input v-model="form.accumulationFundBase" min="0" type="number" readonly />
          </el-form-item>
          <el-form-item label="养老金基数" prop="pensionBase">
            <el-input v-model="form.pensionBase" type="number" min="0" readonly />
          </el-form-item>
          <el-form-item label="公积金比率" prop="accumulationFundPer">
            <el-input v-model="form.accumulationFundPer" type="number" min="0" max="1" step="0.01" placeholder="请输入" @change="allSalaryChange" />
          </el-form-item>
          <el-form-item label="实发工资" prop="realSalary">
            <el-input v-model="form.realSalary" type="number" min="0" readonly placeholder="请输入" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="员工姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="午餐补助" prop="lunchSalary">
            <el-input v-model="form.lunchSalary" type="number" min="0" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="交通补助" prop="trafficSalary">
            <el-input v-model="form.trafficSalary" type="number" min="0" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="应发工资" prop="allSalary">
            <el-input v-model="form.allSalary" type="number" min="0" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="医疗保险比率" prop="medicalPer">
            <el-input v-model="form.medicalPer" type="number" min="0" max="1" step="0.01" placeholder="请输入" @change="allSalaryChange" />
          </el-form-item>
          <el-form-item label="工资时间" prop="salaryDate">
            <el-date-picker
              v-model="form.salaryDate"
              type="month"
              format="yyyy-MM"
              value-format="yyyy-MM"
              placeholder="选择月"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="工资套账">
            <el-select v-model="salaryId" placeholder="请选择工资套账" @change="salaryChange">
              <el-option
                v-for="item in salaryList"
                :key="item.salaryId"
                :label="item.salaryName"
                :value="item.salaryId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="奖金" prop="bonus">
            <el-input v-model="form.bonus" type="number" min="0" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="考勤工资" prop="attendanceSalary">
            <el-input v-model="form.attendanceSalary" type="number" min="0" clearable placeholder="请输入" />
          </el-form-item>

        </el-col>
        <el-col :span="8">
          <el-form-item label="养老金比率" prop="pensionPer">
            <el-input v-model="form.pensionPer" type="number" min="0" max="1" step="0.01" placeholder="请输入" @change="allSalaryChange" />
          </el-form-item>

        </el-col>
        <el-col :span="8">
          <el-form-item label="医疗基数" prop="medicalBase">
            <el-input v-model="form.medicalBase" type="number" min="0" readonly />
          </el-form-item>

        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input
              v-model="form.remark"
              type="textarea"
              :rows="2"
              placeholder="请输入"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <br>
      <el-form-item style="float: right">
        <el-button type="primary" @click="onSubmit">{{ $route.query.id?'保存':'添加' }}</el-button>
        <el-button @click="onCancel">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { add, getById, update } from '@/api/empsalary.js'
import { getEmployeeByWordId } from '@/api/employee'
import { getList } from '@/api/salary'
export default {
  data() {
    return {
      form: {
        id: null,
        name: '',
        eid: null,
        workId: '',
        basicSalary: 0,
        bonus: 0,
        lunchSalary: 0,
        trafficSalary: 0,
        allSalary: 0,
        pensionBase: 0,
        pensionPer: 0,
        medicalBase: 0,
        medicalPer: 0,
        accumulationFundBase: 0,
        accumulationFundPer: 0,
        salaryDate: '',
        attendanceSalary: 0,
        realSalary: 0
      },
      salaryList: [],
      salaryId: null,
      employee: {},
      rules: {
        name: [{ required: true, message: '请输入员工名称', trigger: 'blur' }],
        workId: [{ required: true, message: '请输入员工工号', trigger: 'blur' }],
        realSalary: [{ required: true, message: '请输入实发工资', trigger: 'blur' }],
        lunchSalary: [{ required: true, message: '请输入午餐补助', trigger: 'blur' }],
        allSalary: [{ required: true, message: '请输入应发工资', trigger: 'blur' }],
        pensionBase: [{ required: true, message: '请输入养老金基数', trigger: 'blur' }],
        medicalPer: [{ required: true, message: '请输入医疗保险比率', trigger: 'blur' }],
        basicSalary: [{ required: true, message: '请输入基本工资', trigger: 'blur' }],
        trafficSalary: [{ required: true, message: '请输入交通补助', trigger: 'blur' }],
        accumulationFundBase: [{ required: true, message: '请输入公积金基数', trigger: 'blur' }],
        bonus: [{ required: true, message: '请输入奖金', trigger: 'blur' }],
        attendanceSalary: [{ required: true, message: '请输入考勤工资', trigger: 'blur' }],
        accumulationFundPer: [{ required: true, message: '请输入公积金比率', trigger: 'blur' }],
        pensionPer: [{ required: true, message: '请输入养老金比率', trigger: 'blur' }],
        medicalBase: [{ required: true, message: '请输入医疗基数', trigger: 'blur' }],
        salaryDate: [{ required: true, message: '请选择工资时间', trigger: 'blur' }]
      }
    }
  },
  computed: {
    // 计算属性的 getter
    allSalary: function() {
      // `this` 指向 vm 实例
      return parseInt(this.form.basicSalary) +
      parseInt(this.form.bonus) +
      parseInt(this.form.lunchSalary) +
      parseInt(this.form.attendanceSalary)
    },
    realSalary: function() {
      return parseInt(this.form.allSalary) -
      parseInt(this.form.pensionBase) -
      parseInt(this.form.medicalBase) -
      parseInt(this.form.accumulationFundBase)
    }
  },
  watch: {
    // 如果 `allSalary` 发生改变，这个函数就会运行
    allSalary: function(newAllSalary, oldAllSalary) {
      this.form.allSalary = newAllSalary
    },
    realSalary: function(newAllSalary, oldAllSalary) {
      this.form.realSalary = newAllSalary
    }
  },
  mounted() {
    getList().then(resp => {
      if (resp.code === 1) {
        this.salaryList = resp.data
      }
    })
    if (this.$route.query.id) {
      getById(this.$route.query.id).then(resp => {
        if (resp.code === 1) {
          this.form = resp.data
        }
      })
    }
  },
  methods: {
    allSalaryChange() {
      if (this.form.accumulationFundPer && this.form.allSalary) {
        this.form.accumulationFundBase = parseInt(this.form.allSalary) * parseFloat(this.form.accumulationFundPer)
      } else {
        this.form.accumulationFundBase = 0
      }
      if (this.form.pensionPer && this.form.allSalary) {
        this.form.pensionBase = parseInt(this.form.allSalary) * parseFloat(this.form.pensionPer)
      } else {
        this.form.pensionBase = 0
      }
      if (this.form.medicalPer && this.form.allSalary) {
        this.form.medicalBase = parseInt(this.form.allSalary) * parseFloat(this.form.medicalPer)
      } else {
        this.form.medicalBase = 0
      }
    },
    salaryChange(val) {
      this.salaryList.forEach((salary) => {
        if (salary.salaryId === val) {
          this.form = { ...salary }
          this.form.workId = this.employee.workID
          this.form.eid = this.employee.id
          this.form.name = this.employee.name
        }
      })
    },
    getEmployeeByWorkId() {
      if (this.form.workId) {
        getEmployeeByWordId(this.form.workId).then(resp => {
          if (resp.code === 1) {
            this.employee = resp.data
            this.form.workId = this.employee.workID
            this.form.eid = this.employee.id
            this.form.name = this.employee.name
          }
        })
      }
    },
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.$delete(this.form, 'createDate')
          this.$delete(this.form, 'salaryId')
          this.$delete(this.form, 'salaryName')
          if (!this.$route.query.id) {
            add(this.form).then(resp => {
              if (resp.code === 1) {
                this.$message({
                  message: '增加成功',
                  type: 'success'
                })
                this.$router.push('/empsalary/index')
              }
            })
          } else {
            update(this.form).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.$router.push('/empsalary/index')
              }
            })
          }
        }
      })
    },
    onCancel() {
      this.$refs['form'].resetFields()
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

