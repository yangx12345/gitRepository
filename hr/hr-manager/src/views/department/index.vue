<template>
  <div class="container">
    <el-form v-if="role !==2" ref="form" :model="form" :inline="true" label-width="80px">
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="form.name" clearable />
      </el-form-item>
      <el-form-item label="状态" label-width="40" prop="enabled">
        <el-select v-model="form.enabled" placeholder="请选择" clearable>
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="getList()">查询</el-button>
      <el-button @click="resetData()">重置</el-button>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%;margin-top:10px"
      row-key="id"
    >
      <el-table-column
        fixed
        prop="id"
        label="编号"
      />
      <el-table-column
        prop="name"
        label="部门名称"
      />
      <el-table-column
        prop="enabled"
        :formatter="enalbleformatter"
        label="状态"
      />
      <el-table-column
        v-if="role !==2"
        fixed="right"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="add(scope.row)">增加</el-button>
          <el-button type="text" size="small" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-dialog :title="addOrupdate === 'add' ? '增加部门':'修改部门'" :visible.sync="dialogFormVisible" @close="cancale">
        <el-form ref="addForm" :model="entity" :rules="rules">
          <el-form-item label="部门名称" :label-width="formLabelWidth" prop="name">
            <el-input v-model="entity.name" autocomplete="off" />
          </el-form-item>
          <el-form-item label="状态" :label-width="formLabelWidth" prop="enabled">
            <el-switch
              v-model="entity.enabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
            />
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
import { mapGetters } from 'vuex'
import { getListByCondition, add, update, deleteById } from '@/api/department'
export default {

  data() {
    return {
      filterText: '',
      tableData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      form: {
        id: null,
        name: ''
      },
      options: [{
        value: 1,
        label: '正常'
      },
      {
        value: 0,
        label: '禁用'
      }],
      entity: {
        id: null,
        name: '',
        parentId: null,
        depPath: '',
        enabled: true,
        isParent: 0
      },
      dialogFormVisible: false,
      formLabelWidth: '100px',
      rules: {
        name: [{ required: true, message: '请输入职位名称', trigger: 'blur' }]
      },
      addOrupdate: ''
    }
  },
  computed: {
    ...mapGetters([
      'role',
      'deptId'
    ])
  },
  created() {
    this.getList()
  },
  methods: {
    resetEntity() {
      this.entity.id = null
      this.entity.name = ''
      this.entity.parentId = null
      this.entity.depPath = ''
      this.entity.enabled = true
      this.entity.isParent = 0
    },
    enalbleformatter(row) {
      if (row.enabled) {
        return '正常'
      } else {
        return '禁用'
      }
    },
    getList() {
      this.form.id = this.deptId
      getListByCondition(this.form, this.role).then(resp => {
        this.tableData = resp.data
      })
    },
    resetData() {
      this.$refs['form'].resetFields()
      this.getList()
    },
    add(row) {
      const { id, depPath } = row
      this.entity.parentId = id
      this.entity.depPath = depPath
      this.dialogFormVisible = true
      this.addOrupdate = 'add'
    },
    handleUpdate(row) {
      this.dialogFormVisible = true
      this.addOrupdate = 'update'
      this.entity = { ...row }
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
