<template>
  <div class="Container">
    <el-form
      ref="loginForm"
      v-loading="loading"
      :rules="loginRules"
      element-loading-text="正在登录..."
      element-loading-spinner="el-icon-loading"
      :model="loginForm"
      class="loginContainer"
    >
      <h3 class="loginTitle">人事管理系统登录</h3>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          size="normal"
          type="text"
          auto-complete="off"
          placeholder="请输入用户名"
        />
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          size="normal"
          type="password"
          show-password
          auto-complete="off"
          placeholder="请输入密码"
        />
      </el-form-item>
      <el-button type="text" style="margin-top:-10px" @click="register()">立即注册</el-button>
      <el-button size="normal" type="primary" style="width: 100%;margin-left:0px" @click="handleLogin">登录</el-button>
    </el-form>
    <el-dialog title="注册" :visible.sync="registerFormVisible" @close="cancaleRegister()">
      <el-form ref="registerForm" :model="registerForm" :inline="true" :rules="registerRules">
        <el-form-item label="登录名称" :label-width="formLabelWidth" prop="username">
          <el-input v-model="registerForm.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="真实姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="registerForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="registerForm.password" autocomplete="off" show-password />
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth" prop="password1">
          <el-input v-model="registerForm.password1" autocomplete="off" show-password />
        </el-form-item>
        <el-form-item label="选择角色" :label-width="formLabelWidth" prop="role">
          <el-select v-model="registerForm.role" placeholder="请选择">
            <el-option label="员工" value="2" />
            <el-option label="人事" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="选择部门" :label-width="formLabelWidth" prop="departmentId">
          <treeselect v-model="registerForm.departmentId" :options="options" placeholder="" style="width:240px" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancaleRegister()">取 消</el-button>
        <el-button type="primary" @click="registerSubmit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import the component
import treeselect from '@riophae/vue-treeselect'
// import the styles
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { getSelectTree } from '@/api/department'
import { register } from '@/api/employee'
export default {
  name: 'Login',
  components: {
    treeselect
  },
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码最少为6位'))
      } else {
        callback()
      }
    }
    const validateregisterPassword = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入密码'))
      } else if (value.length < 6) {
        callback(new Error('密码最少为6位'))
      } else if (value.length > 20) {
        callback(new Error('密码最多为20位'))
      } else {
        callback()
      }
    }
    const validateregisterPassword1 = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入确认密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      registerRules: {
        username: [{ required: true, trigger: 'blur', message: '请输入登录名' }, { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }],
        name: [{ required: true, trigger: 'blur', message: '请输入真实姓名' }],
        password: [{ required: true, trigger: 'blur', validator: validateregisterPassword }],
        password1: [{ required: true, trigger: 'blur', validator: validateregisterPassword1 }],
        role: [{ required: true, trigger: 'blur', message: '请选择角色' }],
        departmentId: [{ required: true, trigger: 'blur', message: '请选择部门' }]
      },
      loading: false,
      redirect: undefined,
      formLabelWidth: '100px',
      registerForm: {},
      registerFormVisible: false,
      password1: '',
      options: []
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    var data = {
      id: '1'
    }
    getSelectTree(data, data.id).then(resp => {
      if (resp.code === 1) {
        this.options = resp.data
      }
    })
  },
  methods: {
    register() {
      this.registerFormVisible = true
    },
    cancaleRegister() {
      this.registerFormVisible = false
      this.$refs['registerForm'].resetFields()
    },
    registerSubmit() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.$delete(this.registerForm, 'password1')
          register(this.registerForm).then((result) => {
            if (result.code === 1) {
              this.$message({
                type: 'success',
                message: '注册成功等待审核'
              })
              this.cancaleRegister()
            }
          })
        }
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            window.location.reload()
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }
    .el-form-item__content{
        display: flex;
        align-items: center;
    }
.Container {
  display: flex;
  justify-content: center;
  align-items: center;
  background: url('../../assets/cover/cover.webp') no-repeat 0px 0px;
  background-size: 100vw 100vh;
  height: 100vh;
}
</style>
