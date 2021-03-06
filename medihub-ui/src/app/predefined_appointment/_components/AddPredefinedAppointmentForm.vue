<template>
  <v-container>
    <v-form
      ref="form"
    >
      <v-card max-width="1300" max-height="1300" class="mx-auto">
        <v-card-title>
          Define appointment
          <v-spacer></v-spacer>
          <v-subheader>
            Discounted price:
            {{Math.round(predefinedAppointment.price*(1 - predefinedAppointment.discount / 100))}}$
          </v-subheader>
        </v-card-title>
        <v-row>
          <v-spacer></v-spacer>
          <v-col class="d-flex" cols="12" sm="4">
            <v-select
              :items="doctors"
              item-text="email"
              v-model="predefinedAppointment.doctor"
              prepend-icon="mdi-doctor"
              return-object=""
              label="Medical doctor"
              dense
              outlined
              :rules="[requiredRule]"
              @input="setDoctorParams()"
            ></v-select>
          </v-col>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="predefinedAppointment.date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="predefinedAppointment.date"
                  label="Appointment date"
                  prepend-icon="event"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
              v-model="predefinedAppointment.date"
              no-title scrollable
              :min="today"
              >
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                <v-btn text color="primary"
                @click="getDoctorsAndPrices(predefinedAppointment.date)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-spacer></v-spacer>
        </v-row>
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-select
              :items="availableTimes"
              item-text="name"
              v-model="predefinedAppointment.time"
              prepend-icon="mdi-timelapse"
              return-object=""
              label="Time"
              dense
              outlined
              :rules="[requiredRule]"
              :readonly="isDoctorSelected()"
              @input="searchRoom()"
            ></v-select>
          </v-col>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-select
              :items="clinicRooms"
              item-text="name"
              v-model="predefinedAppointment.clinicRoom"
              prepend-icon="room"
              return-object=""
              label="Clinic room"
              dense
              outlined
              :rules="[requiredRule]"
              :readonly="isTimeSelected()"
            ></v-select>
          </v-col>
          <v-spacer></v-spacer>
        </v-row>
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="predefinedAppointment.duration"
              label="Duration"
              prepend-icon="mdi-timer-sand"
              min="1"
              type="number"
              :rules="[requiredRule]"
              :readonly="true"
              >
            </v-text-field>
          </v-col>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="predefinedAppointment.appointmentType"
              item-text="name"
              return-object=""
              label="Appointment type"
              prepend-icon="mdi-format-list-bulleted-type"
              :readonly="true"
              >
            </v-text-field>
          </v-col>
          <v-spacer></v-spacer>
        </v-row>
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="predefinedAppointment.price"
              label="Price"
              prepend-icon="mdi-cash-usd"
              min="1"
              type="number"
              :rules="[requiredRule, minNumberRule]"
              :readonly="!isDoctorSelected()"
              >
            </v-text-field>
          </v-col>
          <v-spacer></v-spacer>
          <v-col>
            <v-subheader>
              Discount: {{predefinedAppointment.discount}}%
            </v-subheader>
          </v-col>
          <v-col>
            <v-slider
              v-model="predefinedAppointment.discount"
              class="align-center"
              :max="100"
              :min="0"
              hide-details
            >
            </v-slider>
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
    </v-form>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AddPredefinedAppointmentForm',
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    predefinedAppointment: {
      doctor: null,
      date: new Date().toISOString().substr(0, 10),
      time: null,
      duration: 1,
      clinicRoom: null,
      price: null,
      appointmentType: null,
      appointmentTypeId: null,
      discount: 0,
    },
    menu: null,
    today: new Date().toISOString().substr(0, 10),
  }),
  mounted() {
    this.getDoctorsAndPrices(new Date().toISOString().substr(0, 10));
  },
  methods: {
    ...mapActions('predefinedAppointment', ['addPredefinedAppointment']),
    ...mapActions('medicalDoctor', ['getAllDoctors']),
    ...mapActions('clinicRooms', ['fetchClinicRooms', 'deleteClinicRoom']),
    ...mapActions('doctor', ['fetchAvailableTimesWithoutState']),
    ...mapActions('clinic', ['fetchPrices']),
    submit() {
      if (this.validate()) {
        const request = {
          doctorId: this.predefinedAppointment.doctor.id,
          start: this.predefinedAppointment.time,
          duration: this.predefinedAppointment.duration,
          clinicRoomId: this.predefinedAppointment.clinicRoom.id,
          appointmentTypeId: this.predefinedAppointment.doctor.appointmentTypeId,
          price: this.predefinedAppointment.price,
          discount: this.predefinedAppointment.discount / 100,
          date: this.predefinedAppointment.date,
        };
        this.addPredefinedAppointment(request);
        this.clear();
      }
    },
    clear() {
      this.predefinedAppointment.doctor = null;
      this.predefinedAppointment.time = null;
      this.predefinedAppointment.clinicRoom = null;
      this.predefinedAppointment.appointmentType = null;
      this.predefinedAppointment.price = null;
      this.predefinedAppointment.date = new Date().toISOString().substr(0, 10);
      this.predefinedAppointment.discount = 0;
    },
    validate() {
      return this.$refs.form.validate();
    },
    isDoctorSelected() {
      if (this.predefinedAppointment.doctor === null) {
        return true;
      }
      return false;
    },
    isTimeSelected() {
      if (this.predefinedAppointment.time === null) {
        return true;
      }
      return false;
    },
    searchRoom() {
      if (!this.isTimeSelected()) {
        this.fetchClinicRooms({
          name: null,
          number: null,
          date: this.predefinedAppointment.date,
          time: this.predefinedAppointment.time,
        });
      }
    },
    getAppointmentId() {
      return this.predefinedAppointment.doctor.appointmentTypeId;
    },
    setDoctorParams() {
      this.searchRoom();
      this.fetchAvailableTimesWithoutState({
        doctorId: this.predefinedAppointment.doctor.id,
        date: this.predefinedAppointment.date,
      });
      this.predefinedAppointment.appointmentType = this.predefinedAppointment.doctor.specialization;
      this.setAppointmentTypePrice();
    },
    getDoctorsAndPrices(date) {
      this.clear();
      this.$refs.menu.save(date);
      this.getAllDoctors(date);
      this.fetchPrices();
    },
    setAppointmentTypePrice() {
      this.predefinedAppointment.price = this.getPriceForAppointmentId(this.getAppointmentId());
    },
    getPriceForAppointmentId(appointmentTypeId) {
      let retVal = null;
      Object.keys(this.prices).forEach((key) => {
        if (Number(key) === appointmentTypeId) {
          retVal = this.prices[key];
        }
        return retVal;
      });
      return retVal;
    },
    saveDateAndLoadDoctor(date) {
      this.$refs.menu.save(date);
      this.getDoctorsAndPrices(date);
    },
  },
  computed: {
    ...mapState('medicalDoctor', ['doctors']),
    ...mapState('predefinedAppointment', ['predefinedAppointments']),
    ...mapState('clinicRooms', ['clinicRooms']),
    ...mapState('doctor', ['availableTimes']),
    ...mapState('clinic', ['prices']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    minNumberRule() {
      return (value) => value > 0 || 'Number must be positive!';
    },
    discountTitle() {
      return `Discount: ${this.discount}%`;
    },
  },
};
</script>
