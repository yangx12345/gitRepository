<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" label-width="80px">
      <el-form-item label="员工姓名">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="form.idCard" />
      </el-form-item>
      <el-form-item label="所属部门">
        <treeselect v-model="form.departmentId" :options="options" placeholder="" style="width:240px" />
      </el-form-item>
      <el-form-item label="在职状态">
        <el-select v-model="form.workState" placeholder="请选择">
          <el-option
            v-for="item in status"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="职工工号">
        <el-input v-model="form.workID" />
      </el-form-item>
      <el-form-item label="审核状态">
        <el-select v-model="form.registerStatus" placeholder="请选择">
          <el-option
            v-for="item in registerStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="getList()">查询</el-button>
      <el-button @click="resetData">重置</el-button>
      <el-button type="danger" :disabled="ids.length === 0" @click="batchDelete"> 删除</el-button>
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
        width="50"
        align="center"
      />
      <el-table-column
        prop="name"
        label="姓名"
        width="120"
        align="center"
      />
      <el-table-column
        prop="username"
        label="登录名"
        width="120"
        align="center"
      />
      <el-table-column
        prop="gender"
        label="性别"
        width="120"
        align="center"
      />
      <el-table-column
        prop="birthday"
        label="出生日期"
        width="120"
        align="center"
      />
      <el-table-column
        :formatter="roleFormatter"
        prop="role"
        label="角色"
        align="center"
        width="120"
      />
      <el-table-column
        prop="idCard"
        label="身份证号"
        width="180"
        align="center"
      />

      <el-table-column
        :formatter="nationFormatter"
        prop="nationId"
        label="民族"
        width="120"
        align="center"
      />
      <el-table-column
        prop="nativePlace"
        label="籍贯"
        width="120"
        align="center"
        show-overflow-tooltip
      />
      <el-table-column
        :formatter="politicFormatter"
        prop="politicId"
        label="政治面貌"
        width="120"
        align="center"
      />
      <el-table-column
        prop="phone"
        label="电话号码"
        width="120"
        align="center"
      />
      <el-table-column
        prop="address"
        label="联系地址"
        width="120"
        align="center"
        show-overflow-tooltip
      />

      <el-table-column
        :formatter="departmentFormatter"
        prop="departmentId"
        label="所属部门"
        align="center"
        width="120"
      />
      <el-table-column
        prop="beginDate"
        label="入职日期"
        align="center"
        width="120"
      />
      <el-table-column
        prop="workState"
        label="在职状态"
        align="center"
        width="120"
      />
      <el-table-column
        prop="workID"
        label="工号"
        align="center"
        width="120"
      />
      <el-table-column
        fixed="right"
        label="操作"
        align="center"
        width="220"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="updateClick(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="handleClick(scope.row)">删除</el-button>
          <el-button type="text" size="small" @click="openMessage(scope.row)">重置密码</el-button>
          <el-button type="text" size="small" :disabled="scope.row.registerStatus === '1'" @click="handleRegister(scope.row)">{{ formatRegisterStatus(scope.row.registerStatus) }}</el-button>
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
    <el-dialog title="员工审核" :visible.sync="dialogFormVisible">
      <el-form :model="currentUser">
        <el-form-item label="审核" label-width="120px">
          <el-radio-group v-model="currentUser.registerStatus">
            <el-radio label="0">待审核</el-radio>
            <el-radio label="1">审核通过</el-radio>
            <el-radio label="2">拒绝入职</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="设置工号" label-width="120px">
          <el-input v-model="currentUser.workID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitRegister()">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import { getListByCondition, batchDelete, restPassword, deleteById, update } from '@/api/employee'
import { getSelectTree } from '@/api/department'
import { politiceGetlist } from '@/api/politicsstatus'
import { nationGetlist } from '@/api/nation'
import { getDeptList } from '@/api/department'
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
      tableData: [],
      form: {
      },
      pageIndex: 1,
      pageSize: 10,
      ids: [],
      total: 0,
      options: [],
      politiceOptions: [],
      nationOptions: [],
      deptOptions: [],
      status: [
        {
          value: '在职',
          label: '在职'
        }, {
          value: '离职',
          label: '离职'
        }
      ],
      registerStatus: [
        {
          value: 0,
          label: '待审核'
        },
        {
          value: 1,
          label: '审核通过'
        },
        {
          value: 2,
          label: '审核拒绝'
        }
      ],
      dialogFormVisible: false,
      currentUser: {}
    }
  },
  mounted() {
    politiceGetlist().then(resp => {
      if (resp.code === 1) {
        this.politiceOptions = resp.data
      }
    })
    nationGetlist().then(resp => {
      if (resp.code === 1) {
        this.nationOptions = resp.data
      }
    })
    getDeptList().then(resp => {
      if (resp.code === 1) {
        this.deptOptions = resp.data
      }
    })
    this.getList()
    var data = {
      id: this.$store.getters.deptId
    }
    getSelectTree(data, this.$store.getters.deptId).then(resp => {
      if (resp.code === 1) {
        this.options = resp.data
      }
    })
  },
  methods: {
    nationFormatter(row) {
      for (let index = 0; index < this.nationOptions.length; index++) {
        const element = this.nationOptions[index]
        if (row.nationId === element.id) {
          return element.name
        }
      }
    },
    politicFormatter(row) {
      for (let index = 0; index < this.politiceOptions.length; index++) {
        const element = this.politiceOptions[index]
        if (row.politicId === element.id) {
          return element.name
        }
      }
    },
    departmentFormatter(row) {
      for (let index = 0; index < this.deptOptions.length; index++) {
        const element = this.deptOptions[index]
        if (row.departmentId === element.id) {
          return element.name
        }
      }
    },
    formatRegisterStatus(registerStatus) {
      if (registerStatus === '0') {
        return '待审核'
      }
      if (registerStatus === '1') {
        return '审核通过'
      }
      if (registerStatus === '2') {
        return '审核未通过'
      }
    },
    roleFormatter(row) {
      if (row.role === 0) {
        return '管理员'
      }
      if (row.role === 1) {
        return '人事'
      }
      if (row.role === 2) {
        return '员工'
      }
    },
    handleRegister(row) {
      this.currentUser = { ...row }
      this.dialogFormVisible = true
    },
    submitRegister() {
      this.dialogFormVisible = false
      update(this.currentUser).then((result) => {
        if (result.code === 1) {
          this.$message({
            message: '审核成功',
            type: 'success'
          })
          this.getList()
        }
      })
    },
    openMessage(row) {
      this.$confirm('此操作将重置员工' + row.name + '的登录密码, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        restPassword(row.id).then(resp => {
          if (resp.code === 1) {
            this.$message({
              message: '已将' + row.name + '密码重置为123456',
              type: 'success'
            })
          } else {
            this.$message({
              message: '重置失败！',
              type: 'error'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消重置密码'
        })
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
        path: '/user/add',
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
      if (!this.form.departmentId) this.form.departmentId = this.$store.getters.deptId
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

