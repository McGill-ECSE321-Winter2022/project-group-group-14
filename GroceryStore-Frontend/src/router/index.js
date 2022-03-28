import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/components/LoginPage'
import CreateCustomer from '@/components/CreateCustomer'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: LoginPage
    },
    {
      path: '/createCustomer',
      name: 'CreateCustomer',
      component: CreateCustomer
    }
  ]
})
