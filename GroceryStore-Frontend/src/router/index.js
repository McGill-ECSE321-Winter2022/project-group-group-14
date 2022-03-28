import Vue from 'vue'
import Router from 'vue-router'
import GroceryStore from '@/components/GroceryStore'
import ModifyInventoryItems from '@/components/ModifyInventoryItems'
import ModifyEmployees from '@/components/ModifyEmployees'
import DeleteOwnerAccount from '@/components/DeleteOwnerAccount'
import LoginPage from '@/components/LoginPage'
import CreateCustomer from '@/components/CreateCustomer'
import ModifyStoreSchedule from '@/components/ModifyStoreSchedule'
import ViewStoreScheduleOwner from '@/components/ViewStoreScheduleOwner'
import ShowInventoryItemsOwner from '@/components/ShowInventoryItemsOwner'
import Report from '@/components/Report'
import ModifyCustomers from '@/components/ModifyCustomers'
import EmployeeSchedules from '@/components/EmployeeSchedules'
import DeleteCustomerAccount from '@/components/DeleteCustomerAccount'
import UpdateCustomerAccount from '@/components/UpdateCustomerAccount'
import ViewCustomerStoreSchedule from '@/components/ViewCustomerStoreSchedule'
import ShowCustomerInventoryItems from '@/components/ShowCustomerInventoryItems'
import ViewModifyCustomerGroceryOrders from '@/components/ViewModifyCustomerGroceryOrders'
import CustomerPayment from '@/components/CustomerPayment'
import DeleteEmployeeAccount from '@/components/DeleteEmployeeAccount'
import CreateCustomerFromEmployee from '@/components/CreateCustomerFromEmployee'
import UpdateCustomerFromEmployee from '@/components/UpdateCustomerFromEmployee'
import ViewStoreEmployeeScheduleFromEmployee from '@/components/ViewStoreEmployeeScheduleFromEmployee'
import ModifyGroceryOrderStatus from '@/components/ModifyGroceryOrderStatus'
import ShowEmployeeInventoryItems from '@/components/ShowEmployeeInventoryItems'
import ViewModifyEmployeeGroceryOrders from '@/components/ViewModifyEmployeeGroceryOrders'
import EmployeePayment from '@/components/EmployeePayment'

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
      path: '/king',
      name: 'GroceryStore',
      component: GroceryStore
    },
    {
      path: '/modifyItems',
      name: 'ModifyInventoryItems',
      component: ModifyInventoryItems
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
      path: '/showInventoryItemsOwner',
      name: 'ShowInventoryItemsOwner',
      component: ShowInventoryItemsOwner
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
    },
    {
      path: '/deleteEmployeeAccount',
      name: 'Delete Employee Account',
      component: DeleteEmployeeAccount
    },
    {
      path: '/createCustomerFromEmployee',
      name: 'Create Customer From Employee',
      component: CreateCustomerFromEmployee
    },
    {
      path: '/updateCustomerFromEmployee',
      name: 'Update Customer From Employee',
      component: UpdateCustomerFromEmployee
    },
    {
      path: '/viewStoreEmployeeScheduleEmployee',
      name: 'View Store Employee Schedule From Employee',
      component: ViewStoreEmployeeScheduleFromEmployee
    },
    {
      path: '/modifyGroceryOrderStatus',
      name: 'Modify Grocery Order Status',
      component: ModifyGroceryOrderStatus
    },
    {
      path: '/showEmployeeInventoryItems',
      name: 'ShowEmployeeInventoryItems',
      component: ShowEmployeeInventoryItems
    },
    {
      path: '/viewModifyEmployeeGroceryOrders',
      name: 'ViewModifyEmployeeGroceryOrders',
      component: ViewModifyEmployeeGroceryOrders
    },
    {
      path: '/employeePayment',
      name: 'EmployeePayment',
      component: EmployeePayment
    }
  ]
})
