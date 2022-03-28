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

.button {
  font-size: 12px;
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


</style>
