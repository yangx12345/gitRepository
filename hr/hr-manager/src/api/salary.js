import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/salary/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/salary/getListByCondition',
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
    url: '/salary/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/salary/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/salary/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/salary/batchDelete',
    method: 'delete',
    params: { ids: ids }
  })
}

export function getList() {
  return request({
    url: '/salary/getList',
    method: 'get'
  })
}
