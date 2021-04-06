<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" :rules="rules">
      <el-row>
        <el-col :span="8">
          <el-form-item label="员工工号" prop="workId">
            <el-input v-model="form.workId" type="number" placeholder="请输入" @blur="getEmployeeByWordId()" />
          </el-form-item>
          <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName" readonly placeholder="请输入" />
          </el-form-item>
          <el-form-item label="出差天数" prop="evectionNum">
            <el-input v-model="form.evectionNum" type="number" min="0" max="31" placeholder="请输入" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="早退次数" prop="earlyNum">
            <el-input v-model="form.earlyNum" type="number" min="0" max="31" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="缺勤天数" prop="absenteeismNum">
            <el-input v-model="form.absenteeismNum" type="number" min="0" max="31" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="考勤月份" prop="time">
            <el-date-picker
              v-model="form.time"
              type="month"
              value-format="yyyy-MM"
              format="yyyy-MM"
              placeholder="选择月"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出勤天数" prop="attendanceNum">
            <el-input v-model="form.attendanceNum" type="number" min="0" max="31" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="迟到次数" prop="lateNum">
            <el-input v-model="form.lateNum" type="number" min="0" max="31" placeholder="请输入" />
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
import { add, getById, update } from '@/api/attendancerecord.js'
import { getEmployeeByWordId } from '@/api/employee'
export default {
  data() {
    return {
      form: {
        recordId: null,
        eid: null,
        realName: '',
        workId: '',
        earlyNum: 0,
        time: null,
        absenteeismNum: 0,
        attendanceNum: 0,
        lateNum: 0,
        evectionNum: 0,
        entryId: null,
        entryDate: null,
        updateId: null,
        updateDate: null,
        remark: ''
      },
      rules: {
        workId: [{ required: true, trigger: 'blur', message: '请输入工号' }, { min: 8, max: 8, message: '工号长度为8位', trigger: 'blur' }],
        realName: [{ required: true, trigger: 'blur', message: '请输入姓名' }],
        evectionNum: [{ required: true, trigger: 'blur', message: '请输入出差天数' }],
        earlyNum: [{ required: true, trigger: 'blur', message: '请输入早退次数' }],
        absenteeismNum: [{ required: true, trigger: 'blur', message: '请输入缺勤天数' }],
        time: [{ required: true, trigger: 'blur', message: '请选择考勤月份' }],
        attendanceNum: [{ required: true, trigger: 'blur', message: '请输入出勤天数' }],
        lateNum: [{ required: true, trigger: 'blur', message: '请输入迟到次数' }]
      }
    }
  },
  mounted() {
    if (this.$route.query.id) {
      getById(this.$route.query.id).then(resp => {
        if (resp.code === 1) {
          this.form = resp.data
        }
      })
    }
  },
  methods: {
    getEmployeeByWordId() {
      if (this.form.workId && this.form.workId.length === 8) {
        getEmployeeByWordId(this.form.workId).then(resp => {
          if (resp.code === 1) {
            this.form.eid = resp.data.id
            this.form.realName = resp.data.name
          }
        })
      }
    },
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.$route.query.id) {
            this.form.entryId = this.$store.getters.id
            add(this.form).then(resp => {
              if (resp.code === 1) {
                this.$message({
                  message: '增加成功',
                  type: 'success'
                })
                this.$router.push('/attendance/index')
              }
            })
          } else {
            this.form.updateId = this.$store.getters.id
            update(this.form).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.$router.push('/attendance/index')
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

