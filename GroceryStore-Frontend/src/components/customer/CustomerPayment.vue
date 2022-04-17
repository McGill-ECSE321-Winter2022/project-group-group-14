<template>
  <div>
    <CustomerNavigationBar></CustomerNavigationBar>
  </div>
</template>

<script>
</script>

<style scoped>
</style>

<script>
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
export default{
    name:'CustomerPayment',
    data()
    {
        return {

            
            curremail : this.$route.params.email,
        }

    },
    components:{
        CustomerNavigationBar
    }
    
}
</script>

