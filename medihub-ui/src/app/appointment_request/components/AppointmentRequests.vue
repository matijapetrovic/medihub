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
        return-object
        v-model="clinicRoom"
        dense
        >
      </v-select>
      </template>
      <template v-slot:item.schedule="{ item }">
        <div class="my-2">
          <v-btn
          rounded
          small
          color="primary"
          dark
          @click.stop="setEditedItem(item)"
          >
            Schedule appointment
          </v-btn>
        </div>
      </template>
    </v-data-table>

    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        max-width="470"
      >
        <v-card>
          <v-card-title class="headline">
            Are you sure you want to schedule this appointment?
          </v-card-title>
          <v-card-text>
            Appointment info:<br><br>
            Doctor's email: {{editedItem.doctorEmail}} <br>
            Patient's email: {{editedItem.patientEmail}} <br>
            Date and time: {{editedItem.date}} {{editedItem.time}}<br>
            Clinic room name: {{editedItem.clinicRoom.name}} <br>
            Price: {{editedItem.price}}
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
              @click="scheduleRoom"
            >
              Agree
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AppointmentRequests',
  data: () => ({
    dialog: false,
    numberOfRooms: null,
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
    isAvailable(schedules, date, time) {
      let i;
      for (i = 0; i < schedules.length; i += 1) {
        if (schedules[i].date === date && schedules[i].time === time) {
          return false;
        }
      }
      return true;
    },
    setEditedItem(item) {
      this.editedItem = item;
      this.editedItem.clinicRoom = this.clinicRoom;
      this.dialog = true;
    },
    scheduleRoom() {
      const request = {
        id: this.editedItem.clinicRoom.id,
        date: this.editedItem.date,
        time: this.editedItem.time,
      };
      this.dialog = false;
      this.scheduleRoom(request);
    },
  },
};
</script>
