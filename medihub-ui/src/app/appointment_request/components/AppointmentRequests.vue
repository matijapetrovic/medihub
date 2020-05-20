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
          {{item.id}}
          {{item.clinicRoom.name}}
        </div>
      </template>
      <template v-slot:item.schedule="{ item }">
        <div class="my-2">
          <v-btn
          rounded
          small
          color="success"
          :readonly="fun(item)"
          :disabled="!selectedRoom(item)"
          @click="dialog = true"
          >
            Schedule appointment
          </v-btn>
          <v-dialog
            v-model="dialog"
            max-width="420"
          >
            <v-card>
              <v-card-title class="headline">
                Are you sure you want to schedule appointment and reserve selected room?
              </v-card-title>
              <v-card-text>
                {{item}}
                Appointment info: <br><br>
                Doctor's email: {{item.doctor.email}}<br>
                Patient's email: {{item.patientEmail}}<br>
                Price: {{item.price}}<br>
                Date and time: {{item.date}} {{item.time}}<br>
                Clinic room's name and number:
                {{item.clinicRoom === null? '' : item.clinicRoom.name}}
                {{item.clinicRoom === null? '' : item.clinicRoom.number}}
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
                  @click="scheduleRoomForAppointment(item)"
                >
                  Agree
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
        value: 'id',
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
    scheduleRoomForAppointment(item) {
      // const request = {
      //   id: item.clinicRoom.id,
      //   date: item.date,
      //   time: item.time,
      // };
      const addAppointmentRequest = {
        date: item.date,
        time: item.time,
        patientId: item.patientId,
        doctorId: item.doctorId,
        clinicRoomId: item.clinicRoom.id,
      };
      this.addAppointment(addAppointmentRequest);
      this.deleteItem(item);
      // this.scheduleRoom(request);
      this.dialog = false;
      this.resetPathParams(null);
    },
    deleteItem(item) {
      const index = this.appointmentRequests.indexOf(item);
      this.appointmentRequests.splice(index, 1);
      this.deleteAppointmentRequest(item.id);
    },
    setClinicRoom(appointmentId) {
      return appointmentId === this.scheduled.id ? this.scheduled.clinicRoom : null;
    },
    setPathParams(params) {
      this.$router.push(`/search-clinic-rooms/${params}`);
    },
    resetPathParams(params) {
      this.$router.push(`/search-clinic-rooms/${params}`);
    },
    fun(item) {
      console.log(item);
      return false;
    },
  },
};
</script>
