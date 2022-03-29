<template>
  <div id="app">
    
    <router-view></router-view>

  </div>

</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
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

ul {
  list-style-type: none;
}

.navbar-brand {
  font-family: 'Merriweather', serif;
}
.navbar {
  font-family: 'Nunito', sans-serif;
  background-color: white;
  border-bottom: 1px solid;
  padding-right: 80px;
  padding-left: 80px;
  padding-top: 0;
  padding-bottom: 0;
}

.nav-item {
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 15px;
  padding-right: 15px;
  transition: 0.3s;
 }

 .nav-item:hover {
   background-color: #EAEDEA;
   color:black;
   transition: 0.3s;
 }

.navbar-light .navbar-nav .nav-link { 
  color:black;
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
  border-bottom: 1px solid;
  position: fixed;
  top: 0;
  width: 100%;
}

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

.verticalandhorizontal-center{
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }


.button {
  font-size: 12px;
  margin-top: 10px;
  background-color: white;
  color: black;
  border: 1px solid #111;
  border-radius: 12px;
  transition: 0.5s;
}

.largeButton {
  font-size: 18px;
  margin-top: 10px;
  background-color: white;
  color: black;
  border: 1px solid #111;
  border-radius: 12px;
  transition: 0.5s;
}

.button:hover {
  /*background-color: #EAEDEA;*/
  box-shadow: 0px 0px 3px 1px #888888;
  color: black;
  transition: 0.3s;
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

.grid-item{
  margin: 10px;
  padding-left: 50px;
  padding-right: 50px;
  border: 1px solid black;
  border-radius: 10px;
}

.grid-container {
  display: grid;
  row-gap: 70px;
  column-gap: 90px;
  margin-left: 150px;
  margin-right: 150px;
  grid-template-columns: auto auto auto;
  margin-top: 150px;
  margin-bottom: 100px;
}
</style>
