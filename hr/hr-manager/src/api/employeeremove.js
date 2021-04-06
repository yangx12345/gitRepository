import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/employeeremove/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/employeeremove/getListByCondition',
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
    url: '/employeeremove/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/employeeremove/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/employeeremove/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/employeeremove/batchDelete',
    method: 'delete',
    params: { ids: ids }
  })
}
