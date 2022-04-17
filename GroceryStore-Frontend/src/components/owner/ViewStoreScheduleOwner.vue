<template>
  <div>
  <owner-navigation-bar></owner-navigation-bar>
    <table>
      <caption style="text-align:center">Weekly Schedule</caption>
    <tr>
        <th scope="row">Day</th>
        <th scope="row">Opening Hours</th>
        <th scope="row">Closing Hours</th>
        <th colspan="3" scope="row">Active Workers <br>(Morning, Afternoon, Night)<br></th>

    </tr>
    <tr class="schedule" v-for="storeSchedule in storeSchedules" :key=storeSchedule.dayOpen>

        <td class="news">{{ storeSchedule.dayOpen }}</td>
        <td class="news">{{ storeSchedule.openingTime }}</td>
        <td class="news">{{ storeSchedule.closingTime }}</td>
        <td class="news"></td>
        <td class="news"></td>
        <td class="news"></td>
    </tr>
</table>
  </div>

</template>

<script src="./StoreScheduleOwner.js">
</script>




<style scoped>
h1, h2 {
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  font-weight: bold;
}

td,th {
    border: 1px solid rgb(190, 190, 190);
    padding: 10px;
}

td {
    text-align: center;
}

tr:nth-child(even) {
    background-color: #eee;
}

th[scope="row"] {
    background-color: #d7d9f2;
}

caption {
    padding: 10px;
    caption-side: top;
}

table {
    
    margin-left: auto;
    margin-right: auto;
    margin-top: 6%;
    
    width: 75%;
    table-layout: auto;
    border-collapse: separate;
    border: 2px solid rgb(200, 200, 200);
    letter-spacing: 1px;
    font-family: sans-serif;
    font-size: .8rem;
}

.news {
  width: 16.6%;
}
</style>
