<template>
  <v-container>
    <v-row>
      <v-col>
        <v-text-field
          v-model="name"
          label="Room name"
          prepend-icon="room"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="number"
          label="Room number"
          prepend-icon="format_list_bulleted"
          type="number"
          :rules="[minNumberRule]"
          min="1"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="date"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="date"
              label="Appointment Date"
              prepend-icon="event"
              :readonly="!clinicRoomsEmpty()"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-if="!clinicRoomsEmpty"
            v-model="date"
            :min="today"
            no-title
            scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
          </v-date-picker>
        </v-menu>
      </v-col>
      <v-col>
        <v-text-field
          v-model="time"
          label="Time"
          prepend-icon="mdi-timelapse"
          min="1"
          :readonly="!clinicRoomsEmpty()"
          >
        </v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-subheader>
          Doctor:
          {{this.doctor === null? '': this.doctor.firstName}}
          {{this.doctor === null? '': this.doctor.lastName}}
        </v-subheader>
        <v-select
          :items="doctors"
          label="Doctors"
          v-model="doctor"
          item-text="email"
          dense
          solo
          selected
          :readonly="!clinicRoomsEmpty()"
        >
        </v-select>
      </v-col>
      <v-spacer></v-spacer>
      <v-col>
        <v-container class="px-0" fluid>
          Filter value:
          <v-radio-group v-model="radioGroup">
            <v-radio
              v-for="n in 3"
              :key="n"
              :label="`Radio ${n}`"
              :value="n"
            ></v-radio>
          </v-radio-group>
        </v-container>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-spacer></v-spacer>
      <v-col>
        <v-btn color="primary" medium @click="search">Search</v-btn>
      </v-col>
      <v-col>
        <v-btn medium @click="reset" >Reset search</v-btn>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <v-data-table
      v-if="clinicRooms.length"
      :headers="headers"
      :items="clinicRooms"
      :items-per-page="5"
      class="elevation-1"
      item-key="id"
    >
      <template v-slot:item.scheduleRoom="{ item }">
        <div class="my-2">
          <v-btn
          rounded
          small
          color="primary"
          @click="scheduleRoom(item)"
          >
            Reserve room
          </v-btn>
        </div>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  data: () => ({
    appointmentId: null,
    params: null,
    selectedDoctorEmail: 'a',
    checkbox: true,
    radioGroup: 1,
    switch1: true,
    doctor: null,
    time: null,
    dialog: false,
    name: null,
    number: null,
    date: new Date().toISOString().substr(0, 10),
    menu: null,
    today: new Date().toISOString().substr(0, 10),
    headers: [
      {
        text: 'Name',
        align: 'start',
        sortable: true,
        value: 'name',
      },
      { text: 'Number ', value: 'number' },
      { text: 'First free', value: 'firstFree', sortable: false },
      { text: 'Schedule room', value: 'scheduleRoom', sortable: false },
    ],
  }),
  mounted() {
    this.fetchClinicRooms();
    this.fetchParams();
  },
  methods: {
    ...mapActions('clinicRooms', ['fetchClinicRooms', 'deleteClinicRoom']),
    ...mapActions('medicalDoctor', ['getDoctorsForDateTime']),

    deleteItem(item) {
      this.deleteClinicRoom(item);
    },
    close() {
      this.dialog = false;
    },
    search() {
      this.fetchClinicRooms({
        name: this.name,
        number: this.number,
        date: this.date,
      });
      this.clear();
      this.close();
    },
    reset() {
      this.fetchClinicRooms();
    },
    clear() {
      this.name = null;
      this.number = null;
    },
    fetchParams() {
      this.params = this.getSearchParams();
      this.mapParams();
    },
    mapParams() {
      this.appointmentId = this.params.id;
      this.date = this.params.date;
      this.time = this.params.time;
      this.doctor = this.params.doctor;
      this.doctors.length = 0;
      this.doctors.push(this.doctor);
    },
    clinicRoomsEmpty() {
      if (this.clinicRooms.length === 0) {
        return true;
      }
      return false;
    },
    addDefaultDoctor() {
      this.doctors.push(this.doctor);
    },
    scheduleRoom(item) {
      this.$router.push(`/appointment-request/${JSON.stringify({
        id: this.appointmentId,
        doctor: this.doctor,
        clinicRoom: item,
      })}`);
    },
  },
  computed: {
    ...mapState('clinicRooms', ['clinicRooms']),
    ...mapState('medicalDoctor', ['doctors']),
    minNumberRule() {
      return (value) => value > 0 || 'Number must be positive';
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  watch: {
    dialog(val) {
      return () => val || this.close();
    },
  },
};
</script>
