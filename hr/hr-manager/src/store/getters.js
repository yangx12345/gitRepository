const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  id: state => state.user.id,
  username: state => state.user.username,
  name: state => state.user.name,
  role: state => state.user.role,
  deptId: state => state.user.deptId
}
export default getters
