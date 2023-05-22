import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import axios from 'axios';
import VueRouter from 'vue-router';
import router from './router';
import store from "./store";

Vue.prototype.$axios =axios;

Vue.use(VueRouter);
Vue.prototype.$httpUrl='http://localhost:80'
Vue.config.productionTip = false
Vue.use(ElementUI,{size:'small'});
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
