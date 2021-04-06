import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/nation/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/nation/getListByCondition',
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
    url: '/nation/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/nation/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/nation/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/nation/batchDelete',
    method: 'delete',
    params: ids
  })
}

export function nationGetlist() {
  return request({
    url: '/nation/getList',
    method: 'get'
  })
}
