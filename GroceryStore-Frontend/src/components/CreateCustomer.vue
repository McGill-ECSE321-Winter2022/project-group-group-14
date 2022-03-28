<template>
  <div>
      <div class="row">
      <div class="col-md-3">     </div>
      <div class="col-md-6">

        <br>
        <br>

        <h2 class="heading">Create Customer Account</h2>
        <h5 class="subheading"></h5>

        <br>
        <br>

        <h6 class="subheading">Emails must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Email"
            class="form-control"
            id="floatingInput"
            placeholder="Email"
            required
          />
        </div>

        <h6 class="subheading">Usernames must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Username"
            class="form-control"
            id="floatingInput"
            placeholder="Username"
            required
          />
        </div>

        <h6 class="subheading">Passwords must contain both a capital letter and numerical character</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
            required
          />
        </div>

        <h6 class="subheading">Addresses must contain ___ and be ___ </h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Address"
            class="form-control"
            id="floatingInput"
            placeholder="Address"
            required
          />
        </div>

        <h6 class="subheading">Phone numbers must contain exactly 10 numerical characters </h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="PhoneNumber"
            class="form-control"
            id="floatingInput"
            placeholder="Phone Number"
            required
          />
        </div>

        <br>
        <br>

        <div>

            <router-link to="/">
              <button class="button" type="CreateButton">
                Create Account
              </button>
            </router-link>
            <br>          
            <button class="button" @click="cancel(userId, password)" type="CancelButton">
              Cancel
            </button>

        </div>

    <br />
    <br />

    </div>
    </div>

  <div class="col-md-3"></div>
  </div>
</template>






<style>

</style>