<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" :rules="formRules">
      <el-row>
        <el-col :span="8">
          <el-form-item label="调动人工号" prop="workId">
            <el-input v-model="form.workId" placeholder="请输入" @blur="getEmployeeByWordId()" />
          </el-form-item>
          <el-form-item label="调动人姓名" prop="name">
            <el-input v-model="form.name" readonly placeholder="请输入" />
          </el-form-item>
          <el-form-item label="调用日期" prop="removeDate">
            <div class="block">
              <el-date-picker
                v-model="form.removeDate"
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
          <el-form-item label="调动前部门" prop="beforeDepId">
            <treeselect v-model="form.beforeDepId" disabled :options="options" placeholder="" style="width:240px" />
          </el-form-item>
          <el-form-item label="调用后部门" prop="afterDepId">
            <treeselect v-model="form.afterDepId" :options="options" placeholder="" style="width:240px" />
          </el-form-item>

        </el-col>
        <el-col :span="8">
          <el-form-item label="调动前职位" prop="beforeJobId">
            <el-select v-model="form.beforeJobId" disabled placeholder="请选择">
              <el-option
                v-for="item in positionOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="调动后职位" prop="afterJobId">
            <el-select v-model="form.afterJobId" placeholder="请选择">
              <el-option
                v-for="item in positionOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>

        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="调动原因" prop="reason">
            <el-input
              v-model="form.reason"
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
import { add, getById, update } from '@/api/employeeremove.js'
import { getEmployeeByWordId } from '@/api/employee'
import { positionGetlist } from '@/api/position'
// import the component
import treeselect from '@riophae/vue-treeselect'
// import the styles
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { getSelectTree } from '@/api/department'
export default {
  components: {
    treeselect
  },
  data() {
    return {
      form: {
        id: null,
        workId: '',
        eid: null,
        name: '',
        beforeDepId: null,
        beforeJobId: null,
        afterDepId: null,
        afterJobId: null,
        removeDate: null,
        reason: '',
        remark: ''
      },
      options: [],
      positionOptions: [],
      formRules: {
        workId: [{ required: true, trigger: 'blur', message: '请输入工号' }, { min: 8, max: 8, message: '工号长度为8位', trigger: 'blur' }],
        name: [{ required: true, trigger: 'blur', message: '请输入姓名' }],
        removeDate: [{ required: true, trigger: 'blur', message: '请输入调动日期' }],
        beforeDepId: [{ required: true, trigger: 'blur', message: '请选择调动前部门' }],
        afterDepId: [{ required: true, trigger: 'blur', message: '请选择调动后部门' }]
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
    positionGetlist().then(resp => {
      if (resp.code === 1) {
        this.positionOptions = resp.data
      }
    })
    if (this.$route.query.id) {
      getById(this.$route.query.id).then(resp => {
        if (resp.code === 1) {
          this.form = { ...resp.data }
        }
      })
    } else {
      this.getCurrentDate()
    }
  },
  methods: {
    getEmployeeByWordId() {
      if (this.form.workId && this.form.workId.length === 8) {
        getEmployeeByWordId(this.form.workId).then(resp => {
          if (resp.code === 1) {
            this.form.eid = resp.data.id
            this.form.name = resp.data.name
            this.form.beforeJobId = resp.data.posId
            this.form.beforeDepId = resp.data.departmentId
          }
        })
      }
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
                this.$router.push('/employeeremove/index')
              }
            })
          } else {
            update(this.form).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.$router.push('/employeeremove/index')
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
        this.form.removeDate = year + '-' + mouth + '-' + date
      } else {
        this.form.removeDate = year + '-0' + mouth + '-' + date
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

