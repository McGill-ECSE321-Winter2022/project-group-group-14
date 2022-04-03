<template>
  <div>
    <CustomerNavigationBar></CustomerNavigationBar>
  </div>
</template>

<script>
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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

