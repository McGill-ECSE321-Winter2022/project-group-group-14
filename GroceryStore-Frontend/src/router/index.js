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
import DeleteCustomerAccount from '@/components/DeleteCustomerAccount'
import UpdateCustomerAccount from '@/components/UpdateCustomerAccount'
import ViewCustomerStoreSchedule from '@/components/ViewCustomerStoreSchedule'
import ShowCustomerInventoryItems from '@/components/ShowCustomerInventoryItems'
import ViewModifyCustomerGroceryOrders from '@/components/ViewModifyCustomerGroceryOrders'
import CustomerPayment from '@/components/CustomerPayment'

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
      path: '/deleteCustomerAccount',
      name: 'DeleteCustomerAccount',
      component: DeleteCustomerAccount
    },
    {
      path: '/updateCustomerAccount',
      name: 'UpdateCustomerAccount',
      component: UpdateCustomerAccount
    },
    {
      path: '/viewCustomerStoreSchedule',
      name: 'ViewCustomerStoreSchedule',
      component: ViewCustomerStoreSchedule
    },
    {
      path: '/showCustomerInventoryItems',
      name: 'ShowCustomerInventoryItems',
      component: ShowCustomerInventoryItems
    },
    {
      path: '/viewModifyCustomerGroceryOrders',
      name: 'ViewModifyCustomerGroceryOrders',
      component: ViewModifyCustomerGroceryOrders
    },
    {
      path: '/customerPayment',
      name: 'CustomerPayment',
      component: CustomerPayment
    }
  ]
})
