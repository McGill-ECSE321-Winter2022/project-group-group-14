import Vue from 'vue'
import Router from 'vue-router'
import Shop from '@/components/Shop'
import GroceryStore from '@/components/GroceryStore'
import ModifyInventoryItems from '@/components/ModifyInventoryItems'
import ModifyItems from '@/components/ModifyItems'
import ModifyEmployees from '@/components/ModifyEmployees'
import DeleteOwnerAccount from '@/components/DeleteOwnerAccount'
import LoginPage from '@/components/LoginPage'
import CreateCustomer from '@/components/CreateCustomer'
import ModifyStoreSchedule from '@/components/ModifyStoreSchedule'
import ViewStoreScheduleOwner from '@/components/ViewStoreScheduleOwner'
import Report from '@/components/Report'
import ModifyCustomers from '@/components/ModifyCustomers'
import EmployeeSchedules from '@/components/EmployeeSchedules'


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
    },
    {
      path: '/modifyItems',
      name: 'ModifyItems',
      component: ModifyItems
    },
    {
      path: '/modifyEmployees',
      name: 'ModifyEmployees',
      component: ModifyEmployees
    },
    {
      path: '/deleteOwnerAccount',
      name: 'DeleteOwnerAccount',
      component: DeleteOwnerAccount
    },
    {
      path: '/modifyStoreSchedule',
      name: 'ModifyStoreSchedule',
      component: ModifyStoreSchedule
    },
    {
      path: '/viewStoreScheduleOwner',
      name: 'ViewStoreScheduleOwner',
      component: ViewStoreScheduleOwner
    },
    {
      path: '/report',
      name: 'Report',
      component: Report
    },
    {
      path: '/modifyCustomers',
      name: 'ModifyCustomers',
      component: ModifyCustomers
    },
    {
      path: '/employeeSchedules',
      name: 'EmployeeSchedules',
      component: EmployeeSchedules
    }
  ]
})
