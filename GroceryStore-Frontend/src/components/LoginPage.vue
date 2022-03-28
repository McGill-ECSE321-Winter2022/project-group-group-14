<template>
  <div >

    <div class="row">
      <div class="col-md-3">      </div>
      <div class="col-md-6">

        <br>
        <br>

        <h2 class="heading">Log In</h2>
        <h5 class="subheading"></h5>

        <br>
        <br>

        <label for="floatingInput">Username</label>
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
        <label for="floatingPassword">Password</label>
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

        <form action="/action_page.php">
          <label for="cars">Account Type:</label>
          <select name="accountType" id="accountType">
            <option value="customer">Customer</option>
            <option value="employee">Employee</option>
            <option value="owner">Owner</option>
          </select>
          <!-- <input type="submit" value="Submit"> -->
        </form>

        <br>
        <br>

            <button @click="loginUser(userId, password)" type="LoginButton" class="button">
              Log In
            </button>

            <br>
          
            <router-link to="/">
              <button type="CreateButton" class="button">
                Don't have an account? Create an account
              </button>
            </router-link>


      </div>
      <div class="col-md-3"></div>
    </div>
    <br />
    <br />
  </div>
</template>



<style scoped>
.register-href {
  padding: 20px 1px;
  font-size: 25px;
  position: absolute;
  top: 0;
  right: 0;
  width: 120px;
  text-align: left;
}

</style>