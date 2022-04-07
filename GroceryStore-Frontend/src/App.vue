<template>
  <div id="app" >
    
    <router-view></router-view>

  </div>

</template>

<script>
export default {
  name: 'app'
}
</script>

<style>

/*------------------------ App Styles -------------------- */

@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Merriweather:wght@900&display=swap');

#app {
  font-family: 'Nunito', sans-serif;
  font-weight: 400;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  border-bottom: 1px;
}

.bg-color-gradient {
    background-image: linear-gradient(white,antiquewhite);
}

.background-img, .background-img2, .background-img3{
    /* background: url("assets/orange5.jpg") no-repeat center fixed;  */
    height: 100vh;
    filter: blur(8px);
    -webkit-filter: blur(8px);
  }

  .background-img>img, .background-img2>img .background-img3>img {
    width:100%;
    height: 100%;
  }

ul {
  list-style-type: none;
}


/*------------------------ Navigation Bar Styles -------------------- */

.navbar-brand {
  font-family: 'Merriweather', serif;
  margin-top: 10px;
  margin-bottom: 10px;
  color: #f7a851;

}
.navbar {
  font-family: 'Nunito', sans-serif;
  background-color: white;
  border-bottom: 3px solid #f7a851;
  padding-right: 80px;
  padding-left: 80px;
  padding-top: 0;
  padding-bottom: 0;
  box-sizing: border-box;
}

.nav-item {
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 15px;
  padding-right: 15px;
  transition: 0.3s;
  box-sizing: border-box;
 }

 .nav-item:hover {
   background-color: #f7a851;
   color:black;
   transition: 0.3s;
 }

.navbar-light .navbar-nav .nav-link { 
  /* color:black; */
  transition: 0.3s;
}

.navbar-light:hover .navbar-nav:hover .nav-link:hover { 
  color:black;
  transition: 0.3s;
}

.navBar {
  
  list-style-type: none;
  margin: 0;
  padding-right: 80px;
  padding-left: 80px;
  overflow: hidden;
  background-color: white;
  border-bottom: 3px solid #f7a851;
  position: fixed;
  top: 0;
  width: 100%;
}

.dropdown-item:active {
  background-color:#f7a851;
}

/*------------------------ Old Navigation Bar Styles -------------------- */


.page {
  float: right;
}


.logo {
  font-family: 'Merriweather', serif;
  float: left;
  display: block;
  color: black;
  text-align: center;
  padding: 14px 16px;
}

.page a{
  display: block;
  color: black;
  text-align: center;
  font-size: 14px;
  padding: 14px 16px;
  text-decoration: none;
  transition: 0.5s;
}

.page a:hover {
  background-color: #EAEDEA;
  color: black;
  text-decoration: none;
  transition: 0.5s;

}

/*------------------------ Show Inventory Items Styles -------------------- */


.edit {
    margin-top: 20px;
    font-size: 12px;
    color: black;
    transition: 0.3s;
    margin-top: 10px;
}
.edit:hover {
  color:#111;
}

.info {
  font-weight: 700;
}

.item-name {
  font-size: 25px;
  margin-bottom: 20px;
}

.add-item {
  margin-top: 30px;
}

.item{
  padding: 50px 0px 50px 0px;
  margin: 0;
}

.grid-item {
  box-shadow: 0 0 15px 5px rgb(0,0,0,0.2);
  margin: 10px;
  padding-left: 7%;
  padding-right: 7%;
  border-radius: 10px;
  transition: 0.3s;
  background-color: white;
  max-height: 300px;
}

.grid-item:hover {
  box-shadow: 0 0 10px 2px rgb(0,0,0,0.2);
  transition: 0.3s;
}

.grid-container {
  background-image: linear-gradient(rgb(255, 254, 254),rgb(255, 251, 246));
  display: grid;
  row-gap: 70px;
  column-gap: 90px;
  padding-left: 150px;
  padding-right: 150px;
  grid-template-columns: auto auto auto;
  padding-top: 150px;
  padding-bottom: 100px;
  min-height: 100vh;
}

/*------------------------ Input Form Styles -------------------- */

.verticalandhorizontal-center{
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }


/*------------------------ Button Styles -------------------- */


.btn-success {
  background-color:#f7a851;
  color:#111;
  transition: 0.5s;
  border-color: #f7a851;
  border-left: 1px solid white;
  box-shadow: none;
}

.btn-success:hover, .btn-success:focus, .btn-success.focus {
  background-color:#ff8a0c;
  color:#111;
  transition: 0.5s;  
  border-color: #f7a851;
  border-left: 1px solid white;
  box-shadow: none;
}

.button_delete{
  font-size: 12px;
  margin-top: 10px;
  background-color: white;
  color: red;
  border: 1px solid #111;
  border-radius: 12px;
  transition: 0.5s;

}

.button {
  font-size: 12px;
}

.largeButton {
  font-size: 18px;
  max-height: 100px;
}

.mediumButton {
  font-size: 15px;
}

.button, .largeButton, .mediumButton, .dropdown-list{
  margin-bottom: 15px;
  margin-right: 10px;
  margin-left: 10px;
  background-color: white;
  box-shadow: 0px 0px 5px 1px #c7c7c7;
  color: black;
  border: none;
  border-radius: 12px;
  transition: 0.5s;
}
.mediumButton:hover, .largeButton:hover, .button:hover{
  box-shadow: 0px 0px 8px 2px #c7c7c7;
  border-color: #f7a851;
  color: white;
  background-color: #f7a851;
  transition: 0.3s;
}

.disabled {
  color: rgb(132, 132, 132);
  background-color: white;  
  box-shadow: 0px 0px 3px 1px #c7c7c7;
}
.disabled:hover {
  color: rgb(132, 132, 132);
  background-color: white;  
  box-shadow: 0px 0px 3px 1px #c7c7c7;
}

.dropdown-list {
  width: 3em;
  border-radius: 10px;
}

.btn-success.disabled {
  color: rgb(132, 132, 132);
  background-color: rgb(226, 226, 226);  
  border-color: rgb(207, 206, 206);
}


/*------------------------ popup styles -------------------- */

.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  opacity: 100%;
  z-index: 100;
}

.popup {
  margin: auto;
  margin-top: 40vh;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  transition: all 5s ease-in-out;
}


/*------------------------ Responsive changes -------------------- */

@media (max-width: 1170px){
  .grid-container {
    grid-template-columns: auto auto;
  }
}


@media (max-width: 875px){
  .grid-container {
    grid-template-columns: auto;
  }

}

</style>
