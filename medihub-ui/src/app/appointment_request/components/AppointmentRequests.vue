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
        <div v-if="!selectedRoom(item)" class="my-2">
          <v-btn
          rounded
          small
          color="primary"
          dark
          @click="searchRoom(item)"
          >
            Search room
          </v-btn>
        </div>
        <div v-else>
          {{item.clinicRoom.name}}
        </div>
      </template>
        <template v-slot:item.schedule="{ item }">
        <div class="my-2">
          <v-btn
          rounded
          small
          color="success"
          :disabled="!selectedRoom(item)"
          @click="setEditedItemAndToggleDialog(item)"
          >
            Schedule appointment
          </v-btn>
        </div>
      </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      max-width="420"
    >
      <v-card>
        <v-card-title class="headline">
          Are you sure you want to schedule appointment and reserve selected room?
        </v-card-title>
        <v-card-text>
          Appointment info: <br><br>
          Doctor's email: {{this.editedItem !== null? this.editedItem.doctorEmail : ''}}<br>
          Patient's email: {{this.editedItem !== null? this.editedItem.patientEmail : ''}}<br>
          Date and time: {{this.editedItem !== null? this.editedItem.date : ''}}
          {{this.editedItem !== null? this.editedItem.time : ''}}<br>
          Clinic room:
          {{this.editedItem !== null? this.editedItem.clinicRoom.number : ''}}
          ({{this.editedItem !== null? this.editedItem.clinicRoom.name : ''}})<br>
          Price: {{this.editedItem !== null? this.editedItem.price : ''}}<br>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="red darken-1"
            text
            @click="dialog = false"
          >
            Disagree
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="scheduleRoomForAppointment()"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AppointmentRequests',
  data: () => ({
    dialog: false,
    scheduled: {
      id: null,
      clinicRoom: null,
      doctor: null,
    },
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
      { text: 'Clinic room', value: 'room', sortable: false },
      { text: 'Schedule appointment', value: 'schedule', sortable: false },
    ],
  }),
  computed: {
    ...mapState('appointmentRequest', ['appointmentRequests']),
    ...mapState('clinicRooms', ['clinicRooms']),
  },
  mounted() {
    this.fetchAppointmentRequests()
      .then(() => {
        this.fetchParams();
      });
    this.fetchClinicRooms();
  },
  methods: {
    ...mapActions('appointmentRequest', ['fetchAppointmentRequests', 'deleteAppointmentRequest', 'getAppointmentRequests']),
    ...mapActions('clinicRooms', ['fetchClinicRooms', 'scheduleRoom']),
    ...mapActions('appointment', ['addAppointment']),

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
    selectedRoom(item) {
      if (item.clinicRoom === null) {
        return false;
      }
      return true;
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
      this.setPathParams(JSON.stringify({
        id: item.id,
        doctor: item.doctor,
        date: item.date,
        time: item.time,
      }));
    },
    fetchParams() {
      this.params = JSON.parse(this.$route.params.param);
      if (this.params !== null) {
        this.mapParams();
      }
      this.setItems();
    },
    mapParams() {
      this.scheduled.id = this.params.id;
      this.scheduled.clinicRoom = this.params.clinicRoom;
      this.scheduled.doctor = this.params.doctor;
    },
    setEditedItem(item) {
      this.editedItem = item;
    },
    setItems() {
      this.appointmentRequests.forEach((item) => this.items.push({
        id: item.id,
        clinicRoom: this.setClinicRoom(item.id),
        doctorId: item.doctor.id,
        doctorEmail: item.doctor.email,
        doctor: item.doctor,
        patientEmail: item.patient.email,
        patient: item.patient,
        date: item.date,
        time: item.time,
        price: item.price,
      }));
    },
    setEditedItemAndToggleDialog(item) {
      this.setEditedItem(item);
      this.toggleDialog();
    },
    scheduleRoomForAppointment() {
      const addAppointmentRequest = {
        date: this.editedItem.date,
        time: this.editedItem.time,
        patientId: this.editedItem.patient.id,
        doctorId: this.editedItem.doctorId,
        clinicRoomId: this.editedItem.clinicRoom.id,
      };
      this.addAppointment(addAppointmentRequest);
      this.deleteItem(this.editedItem);
      this.dialog = false;
      this.resetPathParams(null);
    },
    deleteItem(item) {
      const index = this.items.map((e) => e.id).indexOf(item.id);
      this.items.splice(index, 1);
      this.deleteAppointmentRequest(item.id);
    },
    setClinicRoom(appointmentId) {
      return appointmentId === this.scheduled.id ? this.scheduled.clinicRoom : null;
    },
    setPathParams(params) {
      this.$router.push(`/search-clinic-rooms/${params}`);
    },
    resetPathParams(params) {
      this.$router.push(`/appointment-request/${params}`);
    },
    toggleDialog() {
      this.dialog = !this.dialog;
    },
  },
};
</script>
