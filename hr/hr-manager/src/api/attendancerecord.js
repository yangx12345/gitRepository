import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/attendancerecord/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, pageIndex, pageSize) {
  return request({
    url: '/attendancerecord/getListByCondition',
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
    url: '/attendancerecord/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/attendancerecord/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/attendancerecord/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/attendancerecord/batchDelete',
    method: 'delete',
    params: { ids: ids }
  })
}
