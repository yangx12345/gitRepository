import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/employee/getById/${id}`,
    method: 'get'
  })
}

export function getEmployeeByWordId(wordId) {
  return request({
    url: `/employee/getEmployeeByWordId/${wordId}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/employee/getListByCondition',
    method: 'post',
    data: data,
    params:
      {
        pageIndex: pageIndex,
        pageSize: pageSize
      }
  })
}

export function add(data) {
  return request({
    url: '/employee/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/employee/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/employee/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/employee/batchDelete',
    method: 'delete',
    params: {
      ids: ids
    }
  })
}

export function login(data) {
  return request({
    url: '/employee/login',
    method: 'post',
    data: data
  })
}

export function register(data) {
  return request({
    url: '/employee/register',
    method: 'post',
    data: data
  })
}

export function changePassword(oldPassword, newPassword, userName) {
  return request({
    url: '/employee/changePassword',
    method: 'post',
    params: { oldPassword: oldPassword, newPassword: newPassword, userName: userName }
  })
}

export function restPassword(id) {
  return request({
    url: '/employee/restPassword',
    method: 'post',
    params: { eid: id }
  })
}

export function getEmployeeByCurrentDeptId(deptId) {
  return request({
    url: '/employee/getEmployeeByCurrentDeptId/' + deptId,
    method: 'get'
  })
}

export function getWorkStateChartData() {
  return request({
    url: '/employee/getWorkStateChartData',
    method: 'get'
  })
}

export function getSexChartData() {
  return request({
    url: '/employee/getSexChartData',
    method: 'get'
  })
}

export function getTiptopDegreeChartData() {
  return request({
    url: '/employee/getTiptopDegreeChartData',
    method: 'get'
  })
}

export function getJobLevelChartData() {
  return request({
    url: '/employee/getJobLevelChartData',
    method: 'get'
  })
}

export function getPosChartData() {
  return request({
    url: '/employee/getPosChartData',
    method: 'get'
  })
}
