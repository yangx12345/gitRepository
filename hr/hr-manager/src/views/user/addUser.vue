<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :inline="true" label-width="120px" :rules="userRules">
      <el-form-item label="登录名" prop="username">
        <el-input v-model="form.username" placeholder="请输入" :disabled="type==='update'" />
      </el-form-item>
      <el-form-item v-if="!this.$route.query.id" label="登录密码" prop="password">
        <el-input v-model="form.password" placeholder="请输入" show-password />
      </el-form-item>
      <el-form-item label="员工姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.gender" placeholder="请输入">
          <el-radio label="男" value="男" />
          <el-radio label="女" value="女" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker
          v-model="form.birthday"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="角色" prop="role">
        <el-radio-group v-model="form.role" placeholder="请输入">
          <el-radio :label="0">系统管理员</el-radio>
          <el-radio :label="1">人事</el-radio>
          <el-radio :label="2">普通员工</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input v-model="form.idCard" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="婚姻状况">
        <el-radio-group v-model="form.wedlock" placeholder="请输入">
          <el-radio label="已婚" value="已婚" />
          <el-radio label="未婚" value="未婚" />
          <el-radio label="离异" value="离异" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="民族">
        <el-select v-model="form.nationId" placeholder="请选择">
          <el-option
            v-for="item in nationOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="籍贯">
        <el-input v-model="form.nativePlace" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="政治面貌">
        <el-select v-model="form.politicId" placeholder="请选择">
          <el-option
            v-for="item in politiceOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="联系地址">
        <el-input v-model="form.address" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="所属部门" prop="departmentId">
        <treeselect v-model="form.departmentId" :options="deptoptions" placeholder="" style="width:240px" />
      </el-form-item>

      <el-form-item label="职称">
        <el-select v-model="form.jobLevelId" placeholder="请选择">
          <el-option
            v-for="item in joblevelOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="职位">
        <el-select v-model="form.posId" placeholder="请选择">
          <el-option
            v-for="item in positionOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="聘用形式">
        <el-input v-model="form.engageForm" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="学历">
        <el-select v-model="form.tiptopDegree" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属专业">
        <el-input v-model="form.specialty" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="毕业院校">
        <el-input v-model="form.school" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="入职日期">
        <el-date-picker
          v-model="form.beginDate"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="在职状态">
        <el-radio-group v-model="form.workState" placeholder="请输入">
          <el-radio label="在职" value="在职" />
          <el-radio label="离职" value="离职" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="工号" prop="workID">
        <el-input v-model="form.workID" placeholder="请输入" type="number" />
      </el-form-item>
      <el-form-item label="合同期限">
        <el-input v-model="form.contractTerm" placeholder="请输入" type="number" />
      </el-form-item>
      <el-form-item label="转正日期" label-width="100px">
        <el-date-picker
          v-model="form.conversionTime"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="离职日期">
        <el-date-picker
          v-model="form.notWorkDate"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="合同开始日期">
        <el-date-picker
          v-model="form.beginContract"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="合同结束日期">
        <el-date-picker
          v-model="form.endContract"
          type="date"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="工龄" label-width="100px">
        <el-input v-model="form.workAge" placeholder="请输入" type="number" />
      </el-form-item>
      <br>
      <el-form-item style="float: right">
        <el-button type="primary" @click="onSubmit">{{ $route.query.id?'保存':'添加' }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { add, getById, update } from '@/api/employee.js'
import { politiceGetlist } from '@/api/politicsstatus'
import { nationGetlist } from '@/api/nation'
import { positionGetlist } from '@/api/position'
import { joblevelGetlist } from '@/api/joblevel'
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
      form: {},
      politiceOptions: [],
      nationOptions: [],
      positionOptions: [],
      joblevelOptions: [],
      deptoptions: [],
      options: ['博士', '硕士', '本科', '大专', '高中', '初中', '小学', '其他'],
      userRules: {
        username: [{ required: true, trigger: 'blur', message: '请输入登录名' }, { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur', message: '请输入密码' }, { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }],
        name: [{ required: true, trigger: 'blur', message: '请输入真实姓名' }],
        role: [{ required: true, trigger: 'blur', message: '请选择角色' }],
        idCard: [{ pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, trigger: 'blur', message: '身份证不合法' }],
        email: [{ pattern: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/, trigger: 'blur', message: '邮箱不合法' }],
        phone: [{ pattern: /^1[3|4|5|7|8][0-9]{9}$/, trigger: 'blur', message: '电话号码不合法' }],
        workID: [{ required: true, trigger: 'blur', message: '请输入工号' }, { min: 8, max: 8, message: '工号位8位数字', trigger: 'blur' }],
        departmentId: [{ required: true, trigger: 'blur', message: '请选择部门' }]
      },
      type: ''
    }
  },
  mounted() {
    var data = {
      id: this.$store.getters.deptId
    }
    getSelectTree(data, this.$store.getters.deptId).then(resp => {
      if (resp.code === 1) {
        this.deptoptions = resp.data
      }
    })
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
    joblevelGetlist().then(resp => {
      if (resp.code === 1) {
        this.joblevelOptions = resp.data
      }
    })
    positionGetlist().then(resp => {
      if (resp.code === 1) {
        this.positionOptions = resp.data
      }
    })
    if (this.$route.query.id) {
      this.type = 'update'
      getById(this.$route.query.id).then(resp => {
        if (resp.code === 1) {
          this.form = resp.data
          this.form.role = Number(this.form.role)
        }
      })
    }
  },
  methods: {
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
                this.$router.push('/user/index')
              }
            })
          } else {
            update(this.form).then((result) => {
              if (result.code === 1) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.$router.push('/user/index')
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

