import Vue from 'vue'
import VueRouter from 'vue-router'

import CategoriesTemporal from '../views/customers/CategoriesTemporal.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: CategoriesTemporal
    }
  ]
})

export default router
