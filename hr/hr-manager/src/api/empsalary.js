import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/empsalary/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/empsalary/getListByCondition',
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
    url: '/empsalary/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/empsalary/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/empsalary/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/empsalary/batchDelete',
    method: 'delete',
    params: { ids: ids }
  })
}

export function exportExcel(data) {
  return request({
    method: 'post',
    url: '/empsalary/excel',
    data: data,
    responseType: 'blob'
  })
}

export function exportStatisticalSalaryExcel(data) {
  return request({
    method: 'post',
    url: '/empsalary/StatisticalSalaryExcel',
    data: data,
    responseType: 'blob'
  })
}
