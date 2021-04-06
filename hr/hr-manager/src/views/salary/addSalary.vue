<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" :rules="rules">
      <el-row>
        <el-col :span="8">
          <el-form-item label="套账名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="午餐补助" prop="lunchSalary">
            <el-input v-model="form.lunchSalary" type="number" min="0" clearable placeholder="请输入" />
          </el-form-item>
          <el-form-item label="应发工资" prop="allSalary">
            <el-input v-model="form.allSalary" type="number" min="0" clearable placeholder="请输入" @change="allSalaryChange()" />
          </el-form-item>
          <el-form-item label="养老金基数" prop="pensionBase">
            <el-input v-model="form.pensionBase" type="number" min="0" readonly />
          </el-form-item>
          <el-form-item label="医疗保险比率" prop="medicalPer">
            <el-input v-model="form.medicalPer" type="number" min="0" max="1" clearable step="0.01" placeholder="请输入" @change="allSalaryChange()" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="基本工资" prop="basicSalary">
            <el-input v-model="form.basicSalary" type="number" min="0" clearable placeholder="请输入" />
          </el-form-item>
          <el-form-item label="交通补助" prop="trafficSalary">
            <el-input v-model="form.trafficSalary" type="number" min="0" clearable placeholder="请输入" />
          </el-form-item>
          <el-form-item label="公积金基数" prop="accumulationFundBase">
            <el-input v-model="form.accumulationFundBase" type="number" min="0" readonly />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="奖金" prop="bonus">
            <el-input v-model="form.bonus" type="number" min="0" clearable placeholder="请输入" />
          </el-form-item>
          <el-form-item label="考勤工资" prop="attendanceSalary">
            <el-input v-model="form.attendanceSalary" type="number" min="0" clearable placeholder="请输入" />
          </el-form-item>
          <el-form-item label="公积金比率" prop="accumulationFundPer">
            <el-input v-model="form.accumulationFundPer" type="number" min="0" max="1" step="0.01" clearable placeholder="请输入" @change="allSalaryChange()" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="养老金比率" prop="pensionPer">
            <el-input v-model="form.pensionPer" type="number" min="0" max="1" step="0.01" clearable placeholder="请输入" @change="allSalaryChange()" />
          </el-form-item>
          <el-form-item v-if="!id" label="创建时间" prop="createDate">
            <div class="block">
              <el-date-picker
                v-model="form.createDate"
                readonly
                type="date"
                value-format="yyyy-MM-dd"
                format="yyyy-MM-dd"
                placeholder="选择日期"
              />
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医疗基数" prop="medicalBase">
            <el-input v-model="form.medicalBase" type="number" readonly />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="备注">
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
import { add, getById, update } from '@/api/salary.js'
import { getEmployeeByWordId } from '@/api/employee'
export default {
  data() {
    return {
      form: {
        basicSalary: 0,
        bonus: 0,
        lunchSalary: 0,
        attendanceSalary: 0,
        trafficSalary: 0,
        allSalary: 0,
        accumulationFundBase: 0,
        medicalBase: 0,
        pensionBase: 0,
        createDate: null
      },
      id: null,
      rules: {
        name: [{ required: true, message: '请输入套账名称', trigger: 'blur' }],
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
        createDate: [{ required: true, message: '请输入创建时间', trigger: 'blur' }]
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
    }
  },
  watch: {
    // 如果 `allSalary` 发生改变，这个函数就会运行
    allSalary: function(newAllSalary, oldAllSalary) {
      this.form.allSalary = newAllSalary
    }
  },
  mounted() {
    this.getCurrentDate()
    this.id = this.$route.query.id
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
    getEmployeeByWordId() {
      getEmployeeByWordId(this.form.wordId).then(resp => {
        if (resp.code === 1) {
          this.form.name = resp.data.form
          this.form.eid = resp.data.id
          this.form.realName = resp.data.name
        }
      })
    },
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.$route.query.id) {
            add(this.form).then(resp => {
              if (resp.code === 1) {
                this.$message({
                  message: '增加成功',
                  type: 'success'
                })
                this.$router.push('/salary/index')
              }
            })
          } else {
            update(this.form).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.$router.push('/salary/index')
              }
            })
          }
        }
      })
    },
    onCancel() {
      this.$refs['form'].resetFields()
    },
    getCurrentDate() {
      var val = new Date()
      var date = val.getDate()
      var year = val.getFullYear()
      var mouth = val.getMonth() + 1
      if (mouth > 9) {
        this.form.createDate = year + '-' + mouth + '-' + date
      } else {
        this.form.createDate = year + '-0' + mouth + '-' + date
      }
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

