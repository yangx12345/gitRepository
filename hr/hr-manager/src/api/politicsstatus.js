import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/politicsstatus/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/politicsstatus/getListByCondition',
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
    url: '/politicsstatus/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/politicsstatus/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/politicsstatus/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/politicsstatus/batchDelete',
    method: 'delete',
    params: ids
  })
}

export function politiceGetlist() {
  return request({
    url: '/politicsstatus/getList',
    method: 'get'
  })
}
