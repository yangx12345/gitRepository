<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :inline="true" label-width="120px">
      <el-form-item label="登录名">
        <el-input v-model="form.username" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="登录密码">
        <el-input v-model="form.password" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="员工姓名">
        <el-input v-model="form.name" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.region" placeholder="请输入">
          <el-radio label="男" value="男" />
          <el-radio label="女" value="女" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker
          v-model="form.birthday"
          type="date"
          placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item label="角色">
        <el-radio-group v-model="form.region" placeholder="请输入">
          <el-radio label="系统管理员" value="0" />
          <el-radio label="人事" value="1" />
          <el-radio label="普通员工" value="2" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="form.idCard" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="婚姻状况">
        <el-radio-group v-model="form.region" placeholder="请输入">
          <el-radio label="已婚" value="已婚" />
          <el-radio label="未婚" value="未婚" />
          <el-radio label="离异" value="离异" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="民族">
        <el-input v-model="form.nationId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="籍贯">
        <el-input v-model="form.nativePlace" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="政治面貌">
        <el-input v-model="form.politicId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="form.phone" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="联系地址">
        <el-input v-model="form.address" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="所属部门">
        <el-input v-model="form.departmentId" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="职位ID">
        <el-input v-model="form.posId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="聘用形式">
        <el-input v-model="form.engageForm" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="学历">
        <el-select v-model="value" placeholder="请选择">
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
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职称ID">
        <el-input v-model="form.jobLevelId" placeholder="请输入" />
      </el-form-item>
      <br>
      <el-form-item style="float: right">
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <el-button @click="onCancel">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      options: ['博士', '硕士', '本科', '大专', '高中', '初中', '小学', '其他']
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

