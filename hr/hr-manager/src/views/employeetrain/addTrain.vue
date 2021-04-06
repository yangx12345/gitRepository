<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" :rules="rules">
      <el-row>
        <el-col :span="8">
          <el-form-item label="所属部门" prop="departmentId">
            <treeselect v-model="form.departmentId" :options="options" placeholder="" style="width:240px" @input="getUserList()" />
          </el-form-item>
          <el-form-item label="员工工号" prop="workIds">
            <el-select v-model="form.workIds" multiple placeholder="请选择">
              <el-option
                v-for="item in tableData"
                :key="item.id"
                :label="item.workID"
                :value="item.workID"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="培训日期" prop="trainDate">
            <el-date-picker
              v-model="form.trainDate"
              type="date"
              value-format="yyyy-MM-dd"
              format="yyyy-MM-dd"
              placeholder="选择日期"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="培训内容" prop="trainContent">
            <el-input
              v-model="form.trainContent"
              type="textarea"
              :rows="2"
              placeholder="请输入"
            />
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
import { getEmployeeByCurrentDeptId } from '@/api/employee'
import { batchSave, getById, update } from '@/api/employeetrain.js'
import { getSelectTree } from '@/api/department'
// import the component
import treeselect from '@riophae/vue-treeselect'
// import the styles
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  components: {
    treeselect
  },
  data() {
    return {
      form: {

      },
      tableData: [],
      options: [],
      rules: {
        departmentId: [{ required: true, trigger: 'blur', message: '请选择部门' }],
        trainDate: [{ required: true, trigger: 'blur', message: '请选择培训日期' }],
        trainContent: [{ required: true, trigger: 'blur', message: '请填写培训内容' }]
      }
    }
  },
  mounted() {
    var data = {
      id: this.$store.getters.deptId
    }
    getSelectTree(data, this.$store.getters.deptId).then(resp => {
      if (resp.code === 1) {
        this.options = resp.data
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
    getUserList() {
      if (!this.form.departmentId) {
        this.form.departmentId = this.$store.getters.deptId
      }
      getEmployeeByCurrentDeptId(this.form.departmentId).then(resp => {
        this.tableData = resp.data
      })
    },
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.$route.query.id) {
            batchSave(this.form).then(resp => {
              if (resp.code === 1) {
                this.$message({
                  message: '增加成功',
                  type: 'success'
                })
                this.$router.push('/employeetrain/index')
              }
            })
          } else {
            update(this.form).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.$router.push('/employeetrain/index')
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

