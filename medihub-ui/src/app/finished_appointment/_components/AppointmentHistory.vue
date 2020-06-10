<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      class="elevation-1"
    >
    </v-data-table>
    <v-dialog
      v-model="clinicDialog"
      width="300"
    >
      <ReviewDialog
        @closed="closeClinicDialog"
        @rated="rateClinic($event)"
      >
        <template v-slot:title>
          Rate {{ itemToRate.clinicName }}
        </template>
        Please take a second to rate your experience at {{ itemToRate.clinicName }}
      </ReviewDialog>
    </v-dialog>
    <v-dialog
      v-model="doctorDialog"
      width="300"
    >
      <ReviewDialog
        @closed="closeDoctorDialog"
        @rated="rateDoctor($event)"
      >
        <template v-slot:title>
          Rate {{ itemToRate.doctorFullName }}
        </template>
        Please take a second to rate your experience with {{ itemToRate.doctorFullName }}
      </ReviewDialog>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import ReviewDialog from './ReviewDialog.vue';

export default {
  name: 'AppointmentHistory',
  components: {
    ReviewDialog,
  },
  data: () => ({
    itemToRate: {},
    clinicDialog: false,
    doctorDialog: false,
    headers: [
      {
        text: 'Appointment',
        align: 'start',
        value: 'name',
      },
      { text: 'Doctor', value: 'doctorFullName' },
      { text: 'Clinic', value: 'clinicName' },
      { text: 'Date', value: 'date' },
      { text: 'Time', value: 'time' },
    ],
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  computed: {
    ...mapState('finishedAppointment', ['finishedAppointments']),
  },
  methods: {
    ...mapActions('finishedAppointment', ['addClinicReview', 'addDoctorReview']),
    closeClinicDialog() {
      this.clinicDialog = false;
    },
    openClinicDialog(item, idx) {
      this.setItemToRate(item, idx);
      this.clinicDialog = true;
    },
    closeDoctorDialog() {
      this.doctorDialog = false;
    },
    openDoctorDialog(item, idx) {
      this.setItemToRate(item, idx);
      this.doctorDialog = true;
    },
    setItemToRate(item, idx) {
      this.itemToRate = { ...item, idx };
    },
    rateClinic(rating) {
      this.addClinicReview({
        appointmentId: this.itemToRate.id,
        rating,
      })
        .then(() => {
          this.finishedAppointments[this.itemToRate.idx].clinicRating = rating;
          this.closeClinicDialog();
        });
    },
    rateDoctor(rating) {
      this.addDoctorReview({
        appointmentId: this.itemToRate.id,
        rating,
      })
        .then(() => {
          this.finishedAppointments[this.itemToRate.idx].doctorRating = rating;
          this.closeDoctorDialog();
        });
    },
  },
};
</script>
