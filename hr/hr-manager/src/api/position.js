import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/position/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/position/getListByCondition',
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
    url: '/position/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/position/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/position/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/position/batchDelete',
    method: 'delete',
    params: ids
  })
}

export function positionGetlist() {
  return request({
    url: '/position/getList',
    method: 'get'
  })
}
