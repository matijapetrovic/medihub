<template>
  <v-container>
    <v-form>
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
            v-if="!clinicRoomsEmpty()"
            v-model="time"
            label="Time"
            prepend-icon="mdi-timelapse"
            min="1"
            :readonly="!clinicRoomsEmpty()"
            >
          </v-text-field>
          <v-select
            v-else
            :items="availableTimes"
            label="Time"
            v-model="time"
            dense
            solo
            selected
          >
          </v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-subheader>
            Main Doctor:
            {{this.doctor === null? '': this.doctor.firstName}}
            {{this.doctor === null? '': this.doctor.lastName}},
            telephone number: {{this.doctor === null? '': this.doctor.telephone}}
          </v-subheader>
          <v-select
            :items="doctors"
            label="Doctors"
            v-model="doctor"
            item-text="email"
            dense
            outlined
            selected
            return-object=""
            :readonly="!clinicRoomsEmpty()"
            @input="setDoctorParams()"
          >
          </v-select>
          <v-subheader v-if="isOperation">
            Present Doctors:
          </v-subheader>
          <v-form ref="docForm">
            <v-select
                v-if="isOperation"
                multiple
                :items="otherDoctors"
                item-text="email"
                item-value="id"
                dense
                v-model="presentDoctors"
                outlined
                :rules="[requiredRule]"
              >
              <template v-slot:selection="{ item, index }">
                <v-chip v-if="index === 0">
                  <span>{{ item.email }}</span>
                </v-chip>
                <v-chip v-if="index === 1">
                  <span>{{ item.email }}</span>
                </v-chip>
                <v-chip v-if="index === 2">
                  <span>{{ item.email }}</span>
                </v-chip>
                <v-chip v-if="index === 3">
                  <span>{{ item.email }}</span>
                </v-chip>
                <span
                  v-if="index === 4"
                  class="grey--text caption"
                >(+{{ doctors.length - 3 }} others)</span>
              </template>
            </v-select>
          </v-form>
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
        <template v-slot:item.calendar="{ item }">
          <v-btn small rounded @click="editItem(item)"> Working calendar</v-btn>
        </template>
      </v-data-table>
    </v-form>
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          Working calendar
        </v-card-title>
        <v-card-text>
          <WorkingCalendar :clinicRoomId="id"></WorkingCalendar>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import WorkingCalendar from '@/app/shared/_components/ClinicRoomCalendar.vue';
import { mapState, mapActions } from 'vuex';

export default {
  components: {
    WorkingCalendar,
  },
  data: () => ({
    presentDoctors: [],
    type: '',
    appointmentId: null,
    params: null,
    id: null,
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
      { text: 'Available calendar', value: 'calendar', sortable: false },
      { text: 'Schedule room', value: 'scheduleRoom', sortable: false },
    ],
  }),
  mounted() {
    this.fetchParams();
  },
  methods: {
    ...mapActions('clinicRooms', ['fetchClinicRooms', 'deleteClinicRoom', 'savePresentDoctors']),
    ...mapActions('medicalDoctor', ['getDoctorsForDateTime', 'getAllDoctors']),
    ...mapActions('doctor', ['fetchAvailableTimesWithoutState']),

    search() {
      this.clinicRooms.length = 0;
      this.fetchClinicRooms({
        name: this.name,
        number: this.number,
        date: this.date,
        time: this.time,
      });
      if (!this.clinicRoomsEmpty()) {
        this.clear();
      }
    },
    reset() {
      this.fetchClinicRooms({
        name: null,
        number: null,
        date: this.params.date,
        time: this.params.time,
      });
      this.date = this.params.date;
      this.time = this.params.time;
    },
    clear() {
      this.name = null;
      this.number = null;
    },
    validate() {
      return this.$refs.form.validate();
    },
    fetchParams() {
      this.params = JSON.parse(this.$route.params.param);
      this.mapParams();
      this.search();
      if (this.clinicRoomsEmpty()) {
        this.noResultsSearch();
      }
    },
    mapParams() {
      this.appointmentId = this.params.id;
      this.date = this.params.date;
      this.time = this.params.time;
      this.doctor = this.params.doctor;
      this.date = this.params.date;
      this.doctors.length = 0;
      this.type = this.params.type;
      this.doctors.push(this.doctor);
    },
    noResultsSearch() {
      this.fetchAvailableTimesWithoutState({ doctorId: this.doctor.id, date: this.date });
      this.getAllDoctors();
      this.doctors.length = 0;
    },
    setDoctorParams() {
      this.fetchAvailableTimesWithoutState({ doctorId: this.doctor.id, date: this.date });
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
      if (this.validateDocsForm()) {
        this.$router.push(`/appointment-request/${JSON.stringify({
          id: this.appointmentId,
          doctor: this.doctor,
          clinicRoom: item,
        })}`);
        this.savePresentDoctors(this.presentDoctors);
      }
    },
    validateDocsForm() {
      const valid = this.$refs.docForm.validate();
      if (!valid && this.type === 'OPERATION') {
        return false;
      }

      return true;
    },
    editItem(item) {
      this.id = item.id;
      this.dialog = true;
    },
  },
  computed: {
    ...mapState('clinicRooms', ['clinicRooms']),
    ...mapState('medicalDoctor', ['doctors']),
    ...mapState('doctor', ['availableTimes']),
    minNumberRule() {
      return (value) => value > 0 || 'Number must be positive';
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    otherDoctors() {
      return this.doctors.filter((element) => element.email !== this.doctor.email);
    },
    isOperation() {
      return this.type === 'OPERATION';
    },
  },
};
</script>
