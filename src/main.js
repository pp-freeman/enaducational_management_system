import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
//引入iconfont
import './assets/font/iconfont.css'

import store from './store/index.js'

import KtButton from '@/permission/ktButton.vue'

Vue.component("KtButton",KtButton)
//导入axios
import axios from 'axios'
//挂载axios
Vue.prototype.$http = axios
//设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"
axios.interceptors.request.use(config =>{
  config.headers.Authorization = window.sessionStorage.getItem("token")
  return config
});

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

