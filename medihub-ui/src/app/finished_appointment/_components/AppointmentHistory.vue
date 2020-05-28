<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      class="elevation-1"
    >
      <template v-slot:body="{ items }">
          <tbody>
            <tr v-for="(item,index) in items" :key="item.id">
              <td>{{ item.name }}</td>
              <td>{{ item.doctorFullName }}</td>
              <td>{{ item.clinicName }}</td>
              <td>{{ item.date }}</td>
              <td>{{ item.time }}</td>
              <td>
                <v-rating
                  v-if="item.clinicRating"
                  :value="item.clinicRating"
                  readonly
                  half-increments
                >
                </v-rating>
                <v-btn
                  v-else
                  @click.stop="openClinicDialog(item, index)"
                >
                  Rate Now
                </v-btn>
              </td>
              <td>
                <v-rating
                  v-if="item.doctorRating"
                  :value="item.doctorRating"
                  readonly
                  half-increments
                >
                </v-rating>
                <v-btn
                  v-else
                  @click.stop="openDoctorDialog(item, index)"
                >
                  Rate Now
                </v-btn>
              </td>
            </tr>
          </tbody>
        </template>
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
      { text: 'Clinic rating', value: 'clinicRating' },
      { text: 'Doctor rating', value: 'doctorRating' },
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
