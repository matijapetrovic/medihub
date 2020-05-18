<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      item-key="id"
      class="elevation-1"
    >
      <template v-slot:item.room="{ item }">
        <div v-if="item.clinicRoom===null" class="my-2">
          <v-btn
          rounded
          small
          color="primary"
          dark
          @click="searchRoom(item)"
          >
            Search rooms
          </v-btn>
        </div>
        <div v-else>
          {{item.name}}
        </div>
      </template>
      <template v-slot:item.reserve="{ item }">
        <div class="my-2">
          <v-btn
          rounded
          small
          color="success"
          :disabled="reserveButtonDisabled(item)"
          @click="scheduleRoomForAppointment(item)"
          >
            Reserve room
          </v-btn>
        </div>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AppointmentRequests',
  data: () => ({
    items: [],
    clinicRoom: null,
    editedIndex: -1,
    editedItem: {
      id: null,
      clinicRoom: {},
      doctorEmail: null,
      patientEmail: null,
      date: null,
      time: null,
      price: null,
    },
    headers: [
      {
        text: 'Doctor',
        align: 'start',
        sortable: true,
        value: 'doctorEmail',
      },
      { text: 'Patient', value: 'patientEmail' },
      { text: 'Price', value: 'price' },
      { text: 'Date', value: 'date' },
      { text: 'Time', value: 'time' },
      { text: 'Search room', value: 'room', sortable: false },
      { text: 'Reserve room', value: 'reserve', sortable: false },
    ],
  }),
  computed: {
    ...mapState('appointmentRequest', ['appointmentRequests']),
    ...mapState('clinicRooms', ['clinicRooms']),
  },
  mounted() {
    this.fetchAppointmentRequests()
      .then(() => {
        this.setItems();
      });
    this.fetchClinicRooms();
  },
  methods: {
    ...mapActions('appointmentRequest', ['fetchAppointmentRequests']),
    ...mapActions('clinicRooms', ['fetchClinicRooms', 'scheduleRoom']),

    filterRooms(rooms, date, time) {
      const retList = [];
      let i;
      for (i = 0; i < rooms.length; i += 1) {
        if (this.isAvailable(rooms[i].simpleSchedule, date, time)) {
          retList.push(rooms[i]);
        }
      }
      return retList;
    },
    reserveButtonDisabled(item) {
      if (item.clinicRoom === null) {
        return true;
      }
      return false;
    },
    isAvailable(schedules, date, time) {
      let i;
      for (i = 0; i < schedules.length; i += 1) {
        if (schedules[i].date === date && schedules[i].time === time) {
          return false;
        }
      }
      return true;
    },
    searchRoom(item) {
      this.$router.push(`/search-clinic-rooms/${item.date}/${item.time}/${item.doctorId}`);
    },
    setItems() {
      this.appointmentRequests.forEach((item) => this.items.push({
        id: item.id,
        clinicRoom: null,
        doctorId: item.doctor.id,
        doctorEmail: item.doctor.email,
        doctor: item.doctor,
        patientEmail: item.patientEmail,
        date: item.date,
        time: item.time,
        price: item.price,
      }));
    },
    scheduleRoomForAppointment(item) {
      const request = {
        id: item.clinicRoom.id,
        date: item.date,
        time: item.time,
      };
      this.scheduleRoom(request);
    },
  },
};
</script>
