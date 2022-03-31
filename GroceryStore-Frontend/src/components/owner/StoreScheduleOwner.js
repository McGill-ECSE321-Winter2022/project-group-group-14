function StoreScheduleDto (openingTime,closingTime,dayOpen) {
    this.dayOpen = dayOpen
    this.openingTime = openingTime
    this.closingTime = closingTime
}
  

export default {
name: 'storeschedule',
data () {
    return {
    storeschedules: [],
    newStoreSchedule: '',
    errorSchedule: '',
    response: []
    }
},

created: function () {
    // Test data
    const i1 = new StoreScheduleDto('09:30','16:00','Sunday')
    const i2 = new StoreScheduleDto('08:30','17:30','Monday')
    const i3 = new StoreScheduleDto('08:30','17:30','Tuesday')
    const i4 = new StoreScheduleDto('08:30','17:30','Wednesday')
    const i5 = new StoreScheduleDto('08:30','17:30','Thursday')
    const i6 = new StoreScheduleDto('08:30','16:00','Friday')
    const i7 = new StoreScheduleDto('09:30','16:00','Saturday')

    // Sample initial content
    this.storeSchedules = [i1, i2, i3, i4, i5, i6, i7]
},
methods: {
    createStoreSchedule: function (openingTime,closingTime,dayOpen) {
        // Create a new person and add it to the list of people
        var i = new StoreScheduleDto(openingTime,closingTime,dayOpen)
        this.storeSchedules.push(i)
        // Reset the name field for new people
        this.newStoreSchedule = ''
    }
}
//...
}

  