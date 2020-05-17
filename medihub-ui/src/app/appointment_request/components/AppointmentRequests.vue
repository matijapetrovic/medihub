<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="appointmentRequsts"
      :items-per-page="5"
      item-key="id"
      class="elevation-1"
    >
      <template v-slot:item.rooms="{ item }">
      <v-select
        :items="filterRooms(clinicRooms, item.date, item.time)"
        item-text="name"
        dense
        >
      </v-select>
      </template>
      <template v-slot:item.schedule="">
        <div v-if="numberOfRooms" class="my-2">
          <v-btn rounded small color="primary" dark>
            Schedule appointment
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
    numberOfRooms: null,
    editedIndex: -1,
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
      { text: 'Available rooms', value: 'rooms', sortable: false },
      { text: 'Schedule room', value: 'schedule', sortable: false },
    ],
  }),
  computed: {
    ...mapState('appointmentRequest', ['appointmentRequsts']),
    ...mapState('clinicRooms', ['clinicRooms']),
  },
  mounted() {
    this.fetchAppointmentRequests();
    this.fetchClinicRooms();
  },
  methods: {
    ...mapActions('appointmentRequest', ['fetchAppointmentRequests']),
    ...mapActions('clinicRooms', ['fetchClinicRooms']),

    filterRooms(rooms, date, time) {
      const retList = [];
      let i;
      for (i = 0; i < rooms.length; i += 1) {
        if (this.isAvailable(rooms[i].simpleSchedule, date, time)) {
          retList.push(rooms[i]);
        }
      }
      this.numberOfRooms = retList.length;
      return retList;
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
  },
};
</script>
