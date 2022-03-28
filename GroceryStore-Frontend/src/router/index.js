import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/components/LoginPage'
import CreateCustomer from '@/components/CreateCustomer'
import Shop from '@/components/Shop'
import GroceryStore from '@/components/GroceryStore'
import ModifyInventoryItems from '@/components/ModifyInventoryItems'

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
    },
    {
      path: '/shop',
      name: 'Shop',
      component: Shop
    },
    {
      path: '/king',
      name: 'GroceryStore',
      component: GroceryStore
    },
    {
      path: '/modifyInvItems',
      name: 'ModifyInventoryItems',
      component: ModifyInventoryItems
    }
  ]
})
