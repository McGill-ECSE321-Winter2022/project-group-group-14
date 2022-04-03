<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Cart</h2>
            <br>
                <h4> Feature List of items and total price here</h4>




            <br>
            <br>
                <h4> Hello {{email}}.If order is a delivery order, feature customer address here</h4>
                <h4> Address is not correct?</h4><router-link to="/modifyAddress">
                        <button class="largeButton">
                            Modify Address
                        </button>
                    </router-link>

                <br>

                <br>

                    <router-link to="/thankYou">
                        <button class="largeButton">
                            Place Order
                        </button>
                    </router-link>
                <br>
                <br>
                <h5> Made a mistake? Delete your order and start again.</h5>
                        <button class="button_delete">
                            Delete Order
                        </button>
        </div>
    </div>
</template>

<style scoped>
</style>
<script>
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
export default{
    name:'CustomerWelcomePage',
    components:{
        CustomerNavigationBar
    },
    data () {
        return {
            email : this.$route.params.email,
        }
    },

}
</script>