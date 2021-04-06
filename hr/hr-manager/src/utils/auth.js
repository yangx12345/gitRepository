import Cookies from 'js-cookie'

const IdKey = 'id'

export function getId() {
  return Cookies.get(IdKey)
}

export function setId(id) {
  return Cookies.set(IdKey, id)
}

export function removeId() {
  return Cookies.remove(IdKey)
}

const RoleKey = 'role'

export function getRole() {
  return Cookies.get(RoleKey)
}

export function setRole(role) {
  return Cookies.set(RoleKey, role)
}

export function removeRole() {
  return Cookies.remove(RoleKey)
}
