const datas = {}
export const setRouter = router => {
  datas.router = router
}
export const setStore = store => {
  datas.store = store
}
export const getRouter = () => datas.router
export const getStore = () => datas.store
