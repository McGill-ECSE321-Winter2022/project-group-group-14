<template>
<div>
  <div class="background-img">
    <img src="../assets/orange5.jpg">
  </div>
    <b-navbar fixed="top">
      <b-navbar-brand>STORIKO</b-navbar-brand>
      
    </b-navbar>
    <div>
      <div class="verticalandhorizontal-center">

          <h2 class="heading">Log In</h2>

          <br>
          <br>

          <div class="form-floating mb-3">
            <input
              type="text"
              v-model="userId"
              class="form-control"
              id="floatingInput"
              placeholder="Username"
              required
            />
          </div>

          <div class="form-floating">
            <input
              type="text"
              v-model="password"
              class="form-control"
              id="floatingPassword"
              placeholder="Password"
              required
            />
          </div>

          <br>
          <br>

          <!-- <form action="/action_page.php">
            <label for="cars">Account Type:</label>
            <select name="accountType" id="accountType">
              <option value="customer">Customer</option>
              <option value="employee">Employee</option>
              <option value="owner">Owner</option>
            </select>
          </form> -->
  <!-- 
          <br>
          <br> -->

              <router-link to="/customerWelcomePage">
                <button class="mediumButton">
                  Customer Log In
                </button>
              </router-link>

              <br>

              <router-link to="/employeeWelcomePage">
                <button class="mediumButton">
                  Employee Log In
                </button>
              </router-link>

              <br>

              <router-link to="/ownerWelcomePage">
                <button class="mediumButton">
                  Owner Log In
                </button>
              </router-link>

              <br>
              <br>
              <br>
              <br>

              <p>Don't have an account?</p>
              <router-link to="/createCustomer">
                <button type="CreateButton" class="button">
                  Create an account
                </button>
              </router-link>

      </div>
    </div>
</div>  
</template>



<style scoped>
  .verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
</style>