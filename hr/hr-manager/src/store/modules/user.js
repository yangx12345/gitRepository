import { login, getById } from '@/api/employee'
import { getId, setId, removeId, setRole, removeRole } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    id: getId(),
    name: '',
    username: '',
    deptId: null,
    role: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_USERNAME: (state, username) => {
    state.username = username
  },
  SET_ROLE: (state, role) => {
    state.role = role
  },
  SET_DEPTID: (state, deptId) => {
    state.deptId = deptId
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const data = response.data
        commit('SET_ID', data.id)
        setId(data.id)
        setRole(data.role)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getById(state.id).then(response => {
        const data = response.data

        if (!data) {
          return reject('Verification failed, please Login again.')
        }
        const { name, username, role, departmentId } = data
        commit('SET_NAME', name)
        commit('SET_USERNAME', username)
        commit('SET_ROLE', role)
        commit('SET_DEPTID', departmentId)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      removeId() // must remove id first
      removeRole()
      resetRouter()
      commit('RESET_STATE')
      resolve()
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeId() // must remove  token  first
      removeRole()
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

