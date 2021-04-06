import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/employeetrain/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/employeetrain/getListByCondition',
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
    url: '/employeetrain/add',
    method: 'post',
    data: data
  })
}

export function batchSave(data) {
  return request({
    url: '/employeetrain/batchSave',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/employeetrain/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/employeetrain/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/employeetrain/batchDelete',
    method: 'delete',
    params: { ids: ids }
  })
}
