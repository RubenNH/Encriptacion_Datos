import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import NotFoundView from '../views/resources/NotFoundView.vue'
import PlacesView from '../views/customers/PlacesView.vue'
import Profile from '../views/seller/Profile.vue'
import RegisterPlace from '../views/seller/RegisterPlace.vue'
import ManagePlace from '../views/seller/ManagePlace.vue'

import CategoriesTemporal from '../views/customers/CategoriesTemporal.vue'

import HomeSeller from '../views/seller/HomeSeller.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    
    {
      path: '/profile',
      name: 'profile',
      component: Profile
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterPlace
    },
    {
      path: '/manage',
      name: 'manage',
      component: ManagePlace
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: AboutView
    },
    {
      path: '/places',
      name: 'places',
      component: PlacesView
    },
    {
      path: '/homeSeller',
      name: 'homeSeller',
      component: HomeSeller
    },
    {
      path: '/test-encrypt',
      name: 'test-encrypt',
      component: CategoriesTemporal
    },
    {
      path: '/:catchAll(.*)',
      name: 'not-found',
      component: NotFoundView
    }
  ]
})

export default router
