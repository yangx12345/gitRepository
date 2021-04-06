import Vue from 'vue'
import Router from 'vue-router'
import { getRole } from '@/utils/auth'
Vue.use(Router)
const role = getRole()
/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [

  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/person',
    hidden: true,
    component: Layout,
    children: [{
      path: '',
      name: 'person',
      component: () => import('@/views/person/index'),
      meta: { title: '个人信息' }
    }]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/index',
    hidden: role === '2',
    meta: { title: '员工管理', icon: 'form' },
    children: [
      {
        path: 'index',
        name: 'user',
        component: () => import('@/views/user/index.vue'),
        meta: { title: '员工列表', icon: 'form' }
      },
      {
        path: 'add',
        name: 'user',
        component: () => import('@/views/user/addUser'),
        meta: { title: '添加/编辑员工', icon: 'form' }
      }

    ]
  },
  {
    path: '/department',
    redirect: '/department/index',
    component: Layout,
    meta: role === '2' ? { title: '部门查看', icon: 'form' } : { title: '部门管理', icon: 'form' },
    children: [

      {
        path: 'index',
        name: 'department',
        component: () => import('@/views/department/index.vue'),
        meta: role === '2' ? { title: '部门查看', icon: 'form' } : { title: '部门管理', icon: 'form' }
      }]
  },
  {
    path: '/employeeremove',
    component: Layout,
    redirect: '/employeeremove/index',
    meta: { title: '员工调动管理', icon: 'form' },
    children: [

      {
        path: 'add',
        name: 'addemployeeremover',
        hidden: role === '2',
        component: () => import('@/views/employeeremove/addRemove.vue'),
        meta: { title: '调动员工', icon: 'form' }
      },
      {
        path: 'index',
        name: 'employeeremoverecord',
        component: () => import('@/views/employeeremove/index'),
        meta: { title: '调动记录', icon: 'form' }
      }

    ]
  },
  {
    path: '/attendance',
    component: Layout,
    redirect: '/attendance/index',
    meta: { title: '考勤管理', icon: 'form' },
    children: [

      {
        path: 'add',
        name: 'attendance',
        hidden: role === '2',
        component: () => import('@/views/attendance/addRecord.vue'),
        meta: { title: '添加/编辑考勤', icon: 'form' }
      },
      {
        path: 'index',
        name: 'attendanceRecord',
        component: () => import('@/views/attendance/index'),
        meta: { title: '考勤记录', icon: 'form' }
      }

    ]
  },
  {
    path: '/employeetrain',
    component: Layout,
    redirect: '/employeetrain/index',
    meta: { title: '培训管理', icon: 'form' },
    children: [

      {
        path: 'add',
        name: 'employeetrain',
        hidden: role === '2',
        component: () => import('@/views/employeetrain/addTrain.vue'),
        meta: { title: '添加/编辑培训', icon: 'form' }
      },
      {
        path: 'index',
        name: 'employeetrainRecord',
        component: () => import('@/views/employeetrain/index'),
        meta: { title: '培训记录', icon: 'form' }
      }

    ]
  },
  {
    path: '/salary',
    component: Layout,
    redirect: '/salary/index',
    hidden: role === '2',
    meta: { title: '工资套账管理', icon: 'form' },
    children: [

      {
        path: 'add',
        name: 'addsalary',
        component: () => import('@/views/salary/addSalary.vue'),
        meta: { title: '添加/编辑工资套账', icon: 'form' }
      },
      {
        path: 'index',
        name: 'salary',
        component: () => import('@/views/salary/index'),
        meta: { title: '工资套账', icon: 'form' }
      }

    ]
  },
  {
    path: '/empsalary',
    component: Layout,
    redirect: '/empsalary/index',
    meta: { title: '员工工资管理', icon: 'form' },
    children: [

      {
        path: 'add',
        name: 'addempsalary',
        hidden: role === '2',
        component: () => import('@/views/empsalary/addEmpSalary.vue'),
        meta: { title: '添加/编辑员工工资', icon: 'form' }
      },
      {
        path: 'index',
        name: 'empsalary',
        component: () => import('@/views/empsalary/index'),
        meta: role === '2' ? { title: '工资查看', icon: 'form' } : { title: '员工工资管理', icon: 'form' }
      }

    ]
  },
  {
    path: '/statistical',
    component: Layout,
    hidden: role === '2',
    redirect: '/statistical/index',
    meta: { title: '统计管理', icon: 'form' },
    children: [
      {
        path: 'salary',
        name: 'salary',
        component: () => import('@/views/statistical/salary'),
        meta: { title: '工资统计', icon: 'form' }
      },
      {
        path: 'employee',
        name: 'employee',
        component: () => import('@/views/statistical/employee'),
        meta: { title: '员工统计', icon: 'form' }
      }

    ]
  },
  {
    path: '/joblevel',
    component: Layout,
    redirect: '/joblevel/index',
    hidden: role === '2',
    meta: { title: '职称管理', icon: 'form' },
    children: [
      {
        path: 'index',
        name: 'joblevel',
        component: () => import('@/views/joblevel/index.vue'),
        meta: { title: '职称管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/nation',
    component: Layout,
    redirect: '/nation/index',
    hidden: role === '2',
    meta: { title: '民族管理', icon: 'form' },
    children: [
      {
        path: 'index',
        name: 'nation',
        component: () => import('@/views/nation/index.vue'),
        meta: { title: '民族管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/politics',
    component: Layout,
    redirect: '/politics/index',
    hidden: role === '2',
    meta: { title: '政治面貌管理', icon: 'form' },
    children: [
      {
        path: 'index',
        name: 'politics',
        component: () => import('@/views/politics/index.vue'),
        meta: { title: '政治面貌管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/position',
    component: Layout,
    redirect: '/position/index',
    hidden: role === '2',
    meta: { title: '职位管理', icon: 'form' },
    children: [
      {
        path: 'index',
        name: 'position',
        component: () => import('@/views/position/index.vue'),
        meta: { title: '职位管理', icon: 'form' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
