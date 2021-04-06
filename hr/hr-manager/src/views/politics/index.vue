<template>
  <div class="container">
    <el-form ref="form" :model="form" :inline="true" :label-width="formLabelWidth">
      <el-form-item label="政治面貌名称" prop="name">
        <el-input v-model="form.name" clearable />
      </el-form-item>
      <el-button type="primary" @click="getList()">查询</el-button>
      <el-button @click="resetData()">重置</el-button>
      <el-button type="primary" @click="add()">增加</el-button>
      <el-button :disabled="ids.length===0" type="danger" @click="batchDelete()">批量删除</el-button>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%;margin-top:10px"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        fixed
        prop="id"
        label="编号"
        min-width="80"
      />
      <el-table-column
        prop="name"
        label="政治面貌名称"
        min-width="120"
      />
      <el-table-column
        fixed="right"
        label="操作"
        min-width="120"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
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
    <div>
      <el-dialog :title="addOrupdate === 'add' ? '增加政治面貌':'修改政治面貌'" :visible.sync="dialogFormVisible" @close="cancale">
        <el-form ref="addForm" :model="entity" :rules="rules">
          <el-form-item label="政治面貌名称" :label-width="formLabelWidth" prop="name">
            <el-input v-model="entity.name" autocomplete="off" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancale()">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

  </div>

</template>

<script>
import { getListByCondition, batchDelete, add, update, deleteById } from '@/api/politicsstatus'
export default {

  data() {
    return {
      tableData: [],
      form: {
        name: '',
        enabled: null
      },
      pageIndex: 1,
      pageSize: 10,
      total: 0,
      ids: [],
      entity: {
        id: null,
        name: ''
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      rules: {
        name: [{ required: true, message: '请输入政治面貌名称', trigger: 'blur' }]
      },
      addOrupdate: ''
    }
  },
  created() {
    this.getList()
  },
  methods: {
    resetEntity() {
      this.entity.id = null
      this.entity.name = ''
    },
    handleSizeChange(val) {
      this.pagesize = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.pageIndex = val
      this.getList()
    },
    add() {
      this.dialogFormVisible = true
      this.addOrupdate = 'add'
    },
    submit() {
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          if (this.addOrupdate === 'add') {
            add(this.entity).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '增加成功',
                  type: 'success'
                })
                this.cancale()
                this.getList()
              }
            })
          } else {
            update(this.entity).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '修改成功',
                  type: 'success'
                })
                this.cancale()
                this.getList()
              }
            })
          }
        } else {
          return false
        }
      })
    },
    cancale() {
      this.addOrupdate = ''
      this.dialogFormVisible = false
      this.$refs['addForm'].clearValidate()
      this.resetEntity()
    },
    handleDelete(row) {
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
    handleUpdate(row) {
      this.dialogFormVisible = true
      this.addOrupdate = 'update'
      this.entity = { ...row }
    },
    getList() {
      getListByCondition(this.form, this.pageIndex, this.pageSize).then(resp => {
        this.tableData = resp.data.list
        this.total = resp.data.total
      })
    },
    resetData() {
      this.$refs['form'].resetFields()
      this.getList()
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
        batchDelete(this.ids.toString()).then(response => {
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

