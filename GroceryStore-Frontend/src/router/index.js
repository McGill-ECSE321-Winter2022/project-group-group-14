import Vue from 'vue'
import Router from 'vue-router'
import Shop from '@/components/Shop'
import GroceryStore from '@/components/GroceryStore'
import ModifyInventoryItems from '@/components/ModifyInventoryItems'
import ModifyItems from '@/components/ModifyItems'
import ModifyEmployees from '@/components/ModifyEmployees'
import DeleteOwnerAccount from '@/components/DeleteOwnerAccount'

Vue.use(Router)

export default new Router({
  routes: [
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
    }
  ]
})
