<template>
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
              <v-dialog
                v-else
                v-model="clinicDialog"
                width="300"
              >
                <template v-slot:activator="{ on }">
                  <v-btn
                    @click="setItemToRate(item, index)"
                    v-on="on"
                  >
                    Rate Now
                  </v-btn>
                </template>
                <v-card
                  class="elevation-16 mx-auto"
                >
                  <v-card-title
                    class="headline"
                    primary-title
                  >
                    Rate {{ itemToRate.clinicName }}
                  </v-card-title>
                  <v-card-text>
                    Please take a second to rate your experience at {{ itemToRate.clinicName }}
                    <div class="text-center mt-12">
                      <v-rating
                        v-model="clinicRating"
                        color="yellow darken-3"
                        background-color="grey darken-1"
                        empty-icon="$ratingFull"
                        half-increments
                        hover
                      ></v-rating>
                    </div>
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions class="justify-space-between">
                    <v-btn text @click="closeDialog">No Thanks</v-btn>
                    <v-btn
                      color="primary"
                      text
                      @click="rateClinic()"
                    >
                      Rate Now
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </td>
          </tr>
        </tbody>
      </template>
  </v-data-table>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AppointmentHistory',
  data: () => ({
    itemToRate: {},
    clinicDialog: false,
    clinicRating: null,
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
    ...mapActions('finishedAppointment', ['addClinicReview']),
    closeDialog() {
      this.clinicRating = null;
      this.clinicDialog = false;
    },
    setItemToRate(item, idx) {
      this.itemToRate = { ...item, idx };
    },
    rateClinic() {
      this.addClinicReview({
        appointmentId: this.itemToRate.id,
        rating: this.clinicRating,
      })
        .then(() => {
          this.finishedAppointments[this.itemToRate.idx].clinicRating = this.clinicRating;
          this.closeDialog();
        });
    },
  },
};
</script>
