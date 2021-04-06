<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" label-width="100px">
      <el-form-item v-if="role !==2" label="员工姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入" clearable />
      </el-form-item>
      <el-form-item v-if="role !==2" label="员工工号" prop="workId">
        <el-input v-model="form.workId" placeholder="请输入" clearable />
      </el-form-item>
      <el-form-item v-if="role !==2" label="调动前部门" prop="beforeDepId">
        <treeselect v-model="form.beforeDepId" placeholder="请选择" :options="options" style="width:240px" />
      </el-form-item>
      <el-form-item v-if="role !==2" label="调动前职位" prop="beforeJobId">
        <el-select v-model="form.beforeJobId" placeholder="请选择">
          <el-option
            v-for="item in positionOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item v-if="role !==2" label="调动后职位" prop="afterJobId">
        <el-select v-model="form.afterJobId" placeholder="请选择">
          <el-option
            v-for="item in positionOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item v-if="role !==2" label="调动后部门" prop="afterDepId">
        <treeselect v-model="form.afterDepId" :options="options" placeholder="请选择" style="width:240px" />
      </el-form-item>
      <el-form-item label="调动日期" prop="removeDate">
        <el-date-picker
          v-model="form.removeDate"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
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
        width="50"
        align="center"
      />
      <el-table-column
        prop="name"
        label="姓名"
        width="80"
        align="center"
      />
      <el-table-column
        prop="workId"
        label="工号"
        width="120"
        align="center"
      />
      <el-table-column
        :formatter="beforeDepartmentFormatter"
        prop="beforeDepId"
        label="调动前部门"
        width="120"
        align="center"
      />
      <el-table-column
        :formatter="beforeJobFormatter"
        prop="beforeJobId"
        label="调动前职位"
        width="120"
        align="center"
      />
      <el-table-column
        :formatter="afterDepartmentFormatter"
        prop="afterDepId"
        label="调动后部门"
        width="120"
        align="center"
      />
      <el-table-column
        :formatter="afterJobFormatter"
        prop="afterJobId"
        label="调动后职位"
        width="120"
        align="center"
      />
      <el-table-column
        prop="removeDate"
        label="调动日期"
        width="120"
        align="center"
      />

      <el-table-column
        prop="reason"
        label="调动原因"
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
import { getListByCondition, batchDelete, deleteById } from '@/api/employeeremove'
import { positionGetlist } from '@/api/position'
// import the component
import treeselect from '@riophae/vue-treeselect'
// import the styles
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { getSelectTree } from '@/api/department'
import { getDeptList } from '@/api/department'
import { mapGetters } from 'vuex'
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
      positionOptions: [],
      deptOptions: []
    }
  },
  computed: {
    ...mapGetters([
      'role',
      'id'
    ])
  },
  mounted() {
    getDeptList().then(resp => {
      if (resp.code === 1) {
        this.deptOptions = resp.data
      }
    })
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
    this.getList()
  },
  methods: {
    beforeDepartmentFormatter(row) {
      for (let index = 0; index < this.deptOptions.length; index++) {
        const element = this.deptOptions[index]
        if (row.beforeDepId === element.id) {
          return element.name
        }
      }
    },
    afterDepartmentFormatter(row) {
      for (let index = 0; index < this.deptOptions.length; index++) {
        const element = this.deptOptions[index]
        if (row.afterDepId === element.id) {
          return element.name
        }
      }
    },
    afterJobFormatter(row) {
      for (let index = 0; index < this.positionOptions.length; index++) {
        const element = this.positionOptions[index]
        if (row.afterJobId === element.id) {
          return element.name
        }
      }
    },
    beforeJobFormatter(row) {
      for (let index = 0; index < this.positionOptions.length; index++) {
        const element = this.positionOptions[index]
        if (row.beforeJobId === element.id) {
          return element.name
        }
      }
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
        path: '/employeeremove/add',
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

