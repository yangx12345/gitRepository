<template>
  <div id="app">
    <!-- <div class="tabber" style="position: absolute; top:0; left: 0;">
      人事管理系统
    </div> -->
    <div class="main">
      <router-view />
    </div>

  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style>

.main {

}
/* #app .sidebar-container {
  top: 49px  !important;
}
.tabber {
  line-height: 50px;
  font-size: 20px;
  padding-left: 30px;
  color: #fff;
  background-color: #1f2d3d !important;
  right: 0px;
}
.el-table--scrollable-x .el-table__body-wrapper {
    overflow-x: auto;
    height: calc(100vh - 300px);
    overflow: scroll;
}
.main-container {
 z-index: 999;
}
.navbar .hamburger-container{
  display: none;
}
.el-breadcrumb__inner {
  color: #999 !important;
}
.el-breadcrumb__item:last-child .el-breadcrumb__inner, .el-breadcrumb__item:last-child .el-breadcrumb__inner a, .el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover, .el-breadcrumb__item:last-child .el-breadcrumb__inner:hover {
  color: #fff !important;
} */
</style>
