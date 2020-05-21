<template>
  <v-container>
    <v-card max-width="1300" max-height="1200" class="mx-auto">
      <v-row>
        <v-col class="d-flex" cols="12" sm="6">
          <v-subheader>
            Doctor:
          </v-subheader>
          <v-select
            :items="doctors"
            item-text="email"
            v-model="predefinedAppointment.doctor"
            return-object=""
            label="Medical doctor"
            dense
            outlined
          ></v-select>
        </v-col>
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
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
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
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            v-model="time"
            label="Time"
            prepend-icon="mdi-timelapse"
            min="1"
            type="number"
            >
          </v-text-field>
        </v-col>
        <v-col>
          <v-select
            :items="doctors"
            item-text="email"
            v-model="predefinedAppointment.appointmentType"
            return-object=""
            label="Appointmet type"
            dense
            outlined
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            v-model="predefinedAppointment.time"
            label="Duration"
            prepend-icon="mdi-timelapse"
            min="1"
            type="number"
            readonly="true"
            >
          </v-text-field>
        </v-col>
        <v-col>
          <v-select
            :items="clinicRooms"
            item-text="name"
            v-model="predefinedAppointment.clinicRoom"
            return-object=""
            label="Clinic room"
            dense
            outlined
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            v-model="predefinedAppointment.price"
            label="Price"
            prepend-icon="mdi-timelapse"
            min="1"
            type="number"
            >
          </v-text-field>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
        <v-card-actions>
          <v-spacer></v-spacer>
          <div class="my-2">
            <v-btn
            rounded
            max-width=""
            color="primary"
            @click="submit()"
            >
              Submit
            </v-btn>
          </div>
          <v-spacer></v-spacer>
        </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AddPredefinedAppointmentForm',
  data: () => ({
    predefinedAppointment: {
      doctor: null,
      date: null,
      time: 1,
      clinicRoom: null,
      price: null,
      appointmentType: null,
    },
    date: new Date().toISOString().substr(0, 10),
    menu: null,
    today: new Date().toISOString().substr(0, 10),
  }),
  mounted() {
    this.getAllDoctors();
  },
  methods: {
    ...mapActions('predefinedAppointment', ['addPredefinedAppointment']),
    ...mapActions('medicalDoctor', ['getAllDoctors']),
    ...mapActions('clinicRooms', ['fetchClinicRooms']),

    submit() {
      if (this.validate()) {
        this.addPredefinedAppointment(this.predefinedAppointment);
      }
    },
    clear() {
      this.$refs.form.reset();
    },
    validate() {
      return this.$refs.form.validate();
    },
    isDoctorSelected() {
      if (this.predefinedAppointment.doctor === null) {
        return false;
      }
      return true;
    },
  },
  computed: {
    ...mapState('medicalDoctor', ['doctors']),
    ...mapState('clinicRooms', ['clinicRooms']),
    ...mapState('predefinedAppointment', ['predefinedAppointments']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    minNumberRule() {
      return (value) => value > 0 || 'Number must be positive!';
    },
  },
};
</script>
