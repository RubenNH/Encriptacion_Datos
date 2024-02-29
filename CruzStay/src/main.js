import Vue from 'vue'
import { createPinia, PiniaVuePlugin } from 'pinia'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VueCryptojs from 'vue-cryptojs'

import App from './App.vue'
import router from './router'
import axiosPlugin from './utils/axiosPlugin';
import encryptionPlugin from './utils/EncryptionPlugin';

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import './assets/main.css'

Vue.use(PiniaVuePlugin)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueCryptojs)
Vue.use(axiosPlugin)
Vue.use(encryptionPlugin)


new Vue({
  router,
  pinia: createPinia(),
  render: (h) => h(App)
}).$mount('#app')
