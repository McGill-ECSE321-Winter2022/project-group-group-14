import Vue from 'vue'
import Router from 'vue-router'
import GroceryStore from '@/components/GroceryStore'
import LoginPage from '@/components/LoginPage'
//owner imports
import EmployeeSchedules from '@/components/owner/EmployeeSchedules'
import OwnerWelcomePage from '@/components/owner/OwnerWelcomePage'
import ViewStoreScheduleOwner from '@/components/owner/ViewStoreScheduleOwner'
import DeleteOwnerAccount from '@/components/owner/DeleteOwnerAccount'
import ShowInventoryItemsOwner from '@/components/owner/ShowInventoryItemsOwner'
import ModifyCustomers from '@/components/owner/ModifyCustomers'
import ModifyInventoryItems from '@/components/owner/ModifyInventoryItems'
import ModifyEmployees from '@/components/owner/ModifyEmployees'
import ModifyStoreSchedule from '@/components/owner/ModifyStoreSchedule'
import Report from '@/components/owner/Report'
import ShowUsers from '@/components/owner/ShowUsers'
import ModifyEmployeeSchedule from '@/components/owner/ModifyEmployeeSchedule'

//employee imports
import EmployeeSchedulesFromEmployee from '@/components/employee/EmployeeSchedulesFromEmployee'
import CreateCustomerFromEmployee from '@/components/employee/CreateCustomerFromEmployee'
import UpdateCustomerFromEmployee from '@/components/employee/UpdateCustomerFromEmployee'
import ViewStoreEmployeeScheduleFromEmployee from '@/components/employee/ViewStoreEmployeeScheduleFromEmployee'
import ModifyGroceryOrderStatus from '@/components/employee/ModifyGroceryOrderStatus'
import ShowEmployeeInventoryItems from '@/components/employee/ShowEmployeeInventoryItems'
import EmployeeWelcomePage from '@/components/employee/EmployeeWelcomePage'
import ViewIncompleteOrders from '@/components/employee/ViewIncompleteOrders'
//customer imports
import CreateCustomer from '@/components/customer/CreateCustomer'
import DeleteCustomerAccount from '@/components/customer/DeleteCustomerAccount'
import UpdateCustomerAccount from '@/components/customer/UpdateCustomerAccount'
import ViewCustomerStoreSchedule from '@/components/customer/ViewCustomerStoreSchedule'
import ShowCustomerInventoryItems from '@/components/customer/ShowCustomerInventoryItems'
import ViewModifyCustomerGroceryOrders from '@/components/customer/ViewModifyCustomerGroceryOrders'
import CustomerPayment from '@/components/customer/CustomerPayment'
import CustomerWelcomePage from '@/components/customer/CustomerWelcomePage'
import ViewOrderStatus from '@/components/customer/ViewOrderStatus'
import ViewCart from '@/components/customer/ViewCart'
import ThankYou from '@/components/customer/ThankYou'


Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/modifyEmployeeSchedule',
      name: 'ModifyEmployeeSchedule',
      component: ModifyEmployeeSchedule
    },
    {
      path:'/viewOrderStatus/:email', 
      name: 'ViewOrderStatus',
      component: ViewOrderStatus
    },
    {
      path:'/viewCustomerStoreSchedule/:email', 
      name: 'ViewCustomerStoreSchedule',
      component: ViewCustomerStoreSchedule
    },
    {
      path:'/updateCustomerAccount/:email', 
      name: 'UpdateCustomerAccount',
      component: UpdateCustomerAccount
    },
    {
      path:'/DeleteCustomerAccount/:email', 
      name: 'DeleteCustomerAccount',
      component: DeleteCustomerAccount
    },
    {
      path:'/customerWelcomePage/:email', 
      name: 'customerWelcomePage',
      component: CustomerWelcomePage
    },
    {
      path:'/employeeWelcomePage/', 
      name: 'employeeWelcomePage',
      component: EmployeeWelcomePage
    },
    {
      path:'/ownerWelcomePage/', 
      name: 'ownerWelcomePage',
      component: OwnerWelcomePage
    },
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
      path: '/employeeSchedulesFromEmployee',
      name: 'EmployeeSchedulesFromEmployee',
      component: EmployeeSchedulesFromEmployee
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
    // {
    //   path: '/showCustomerInventoryItems/:email',
    //   name: 'ShowCustomerInventoryItems',
    //   component: ShowCustomerInventoryItems
    // }, 
    {
      path: '/showCustomerInventoryItems/:email/:orderId',
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
      path: '/showEmployeeInventoryItems/:orderId',
      name: 'ShowEmployeeInventoryItems',
      component: ShowEmployeeInventoryItems
    },
    {
      path: '/customerWelcomePage',
      name: 'CustomerWelcomePage',
      component: CustomerWelcomePage
    },
    {
      path: '/employeeWelcomePage',
      name: 'EmployeeWelcomePage',
      component: EmployeeWelcomePage
    },
    {
      path: '/ownerWelcomePage',
      name: 'OwnerWelcomePage',
      component: OwnerWelcomePage
    },
    {
      path: '/viewOrderStatus/:email',
      name: 'ViewOrderStatus',
      component: ViewOrderStatus
    },
    {
      path: '/viewCart/:email/:orderId',
      name: 'ViewCart',
      component: ViewCart
    },
    {
      path: '/thankYou/:email',
      name: 'ThankYou',
      component: ThankYou
    },
    {
      path: '/viewIncompleteOrders',
      name: 'ViewIncompleteOrders',
      component: ViewIncompleteOrders
    },
    {
      path: '/showUsers',
      name: 'ShowUsers',
      component: ShowUsers
    }
  ]
})
