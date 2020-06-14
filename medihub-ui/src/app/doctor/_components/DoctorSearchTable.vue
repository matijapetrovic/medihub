<template>
  <v-card>
    <v-card-title>
      Doctors
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Filter"
        single-line
        hide-details
      >
      </v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      :search="search"
      item-key="id"
      class="elevation-1"
    >
      <template v-slot:body="{ items }">
        <tbody>
          <tr v-for="item in items" :key="item.id">
            <td>{{ item.firstName }}</td>
            <td>{{ item.lastName }}</td>
            <td>{{ item.rating }} ({{ item.ratingCount }} reviews)</td>
            <td>{{ item.appointmentPrice ? `${item.appointmentPrice}€` : 'N/A' }}</td>
            <td>
              <v-select
                v-if="item.workingTimes"
                v-model="time[item.id]"
                :items="item.workingTimes"
                label="Time"
              ></v-select>
              <span v-else>N/A</span>
            </td>
            <td
              v-if="canSchedule(item)"
            ><v-btn @click="openConfirmScheduleDialog(item)">Schedule</v-btn></td>
          </tr>
        </tbody>
      </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      max-width="500"
    >
      <ScheduleAppointmentDialog
        :appointment="appointment"
        @cancelled="closeConfirmScheduleDialog"
        @scheduled="schedule"
      />
    </v-dialog>
  </v-card>
</template>

<script>
import ScheduleAppointmentDialog from '@/app/appointment/_components/ScheduleAppointmentDialog.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'DoctorSearchTable',
  components: {
    ScheduleAppointmentDialog,
  },
  data: () => ({
    headers: [
      {
        text: 'First Name',
        align: 'start',
        sortable: true,
        value: 'firstName',
      },
      { text: 'Last Name', value: 'lastName' },
      { text: 'Rating', value: 'rating' },
      { text: 'Appointment price', value: 'appointmentPrice' },
      { text: 'Available times', sortable: false },
      { text: '', sortable: false },
    ],
    time: {},
    dialog: false,
    appointment: {},
    search: '',
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  computed: {
    ...mapState('doctor', ['searchParams']),
  },
  methods: {
    ...mapActions('appointment', ['scheduleAppointment']),
    canSchedule(doctor) {
      return doctor.workingTimes && this.searchParams.appointmentType;
    },
    schedule() {
      this.scheduleAppointment({
        doctorId: this.doctor.id,
        time: this.appointment.time,
        type: 'APPOINTMENT',
      })
        .then(() => {
          this.dialog = false;
          this.time = {};
        });
    },
    openConfirmScheduleDialog(doctor) {
      this.appointment = {
        name: this.searchParams.appointmentType.name,
        doctor: `${doctor.firstName} ${doctor.lastName}`,
        date: this.searchParams.date,
        time: this.time[doctor.id],
        price: doctor.appointmentPrice,
      };
      this.doctor = doctor;
      this.dialog = true;
    },
    closeConfirmScheduleDialog() {
      this.dialog = false;
    },
  },
};
</script>
