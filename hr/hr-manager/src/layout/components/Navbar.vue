<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <div>{{ name }}|{{ roleFormatters(role) }}</div>
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/dashboard">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided @click.native="person">
            <span style="display:block;">个人信息</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="changePassword">
            <span style="display:block;">修改密码</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" @close="cancale">
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item label="旧密码" :label-width="formLabelWidth" prop="oldPassword">
          <el-input v-model="form.oldPassword" autocomplete="off" show-password />
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth" prop="newPassword">
          <el-input v-model="form.newPassword" autocomplete="off" show-password />
        </el-form-item>
        <el-form-item label="确认新密码" :label-width="formLabelWidth" prop="newPassword1">
          <el-input v-model="form.newPassword1" autocomplete="off" show-password />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancale()">取 消</el-button>
        <el-button type="primary" @click="changePasswordSubmit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { changePassword } from '@/api/employee'
export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  data() {
    const validatenewPassword1 = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入确认密码'))
      } else if (value !== this.form.newPassword) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      formLabelWidth: '100px',
      dialogFormVisible: false,
      form: {},
      rules: {
        oldPassword: [{ required: true, trigger: 'blur', message: '请输入旧密码' }],
        newPassword: [{ required: true, trigger: 'blur', message: '请输入新密码' }, { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }],
        newPassword1: [{ required: true, trigger: 'blur', validator: validatenewPassword1 }]
      }
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'name',
      'role',
      'username'
    ])
  },
  methods: {
    roleFormatters(role) {
      if (role === 0) {
        return '管理员'
      }
      if (role === 1) {
        return '人事'
      }
      if (role === 2) {
        return '员工'
      }
    },
    changePassword() {
      this.dialogFormVisible = true
    },
    cancale() {
      this.$refs['form'].resetFields()
      this.dialogFormVisible = false
    },
    changePasswordSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          changePassword(this.form.oldPassword, this.form.newPassword, this.username).then(resp => {
            if (resp.code === 1) {
              this.$message({
                type: 'success',
                message: '密码修改成功'
              })
              this.cancale()
            }
          })
        }
      })
    },
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    person() {
      this.$router.push({
        path: '/person'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
