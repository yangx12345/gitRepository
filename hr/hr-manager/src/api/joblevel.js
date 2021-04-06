import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/joblevel/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/joblevel/getListByCondition',
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
    url: '/joblevel/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/joblevel/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/joblevel/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/joblevel/batchDelete',
    method: 'delete',
    params: {
      ids: ids
    }
  })
}

export function joblevelGetlist() {
  return request({
    url: '/joblevel/getList',
    method: 'get'
  })
}
