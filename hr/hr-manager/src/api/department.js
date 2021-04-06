import request from '@/utils/request'

export function getById(id) {
  return request({
    url: `/department/getById/${id}`,
    method: 'get'
  })
}

export function getListByCondition(data, role) {
  return request({
    url: '/department/getListByCondition',
    method: 'post',
    data: data,
    params:
    {
      role: role
    }
  })
}

export function add(data) {
  return request({
    url: '/department/add',
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: '/department/update',
    method: 'put',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/department/delete/${id}`,
    method: 'delete'
  })
}

export function batchDelete(ids) {
  return request({
    url: '/department/batchDelete',
    method: 'delete',
    params: ids
  })
}
/**
 *
 * @param {*} data 查询条件
 * @param {Integer} id 部门编号
 * @returns
 */
export function getSelectTree(data, id) {
  return request({
    url: '/department/getSelectTree',
    method: 'get',
    data: data,
    params: {
      id: id
    }
  })
}

export function getDeptList() {
  return request({
    url: '/department/getList',
    method: 'get'
  })
}
