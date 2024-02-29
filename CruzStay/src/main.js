import Vue from 'vue'
// importamos pinia
import { createPinia, PiniaVuePlugin } from 'pinia'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
// importamos el plugin de vue-cryptojs
import VueCryptojs from 'vue-cryptojs'

import App from './App.vue'
import router from './router'
// importamos los plugins creados en la carpeta utils
import axiosPlugin from './utils/axiosPlugin';
import encryptionPlugin from './utils/EncryptionPlugin';

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import './assets/main.css'

// hacemos uso todas las librerías y plugins importados
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
