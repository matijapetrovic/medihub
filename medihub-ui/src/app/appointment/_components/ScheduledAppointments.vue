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
            <tr v-for="item in items" :key="item.id">
              <td>{{ item.name }}</td>
              <td>{{ item.doctorFullName }}</td>
              <td>{{ item.clinicName }}</td>
              <td>{{ item.date }}</td>
              <td>{{ item.time }}</td>
              <td><v-btn @click="openCancelDialog(item)">Cancel</v-btn></td>
            </tr>
          </tbody>
        </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      max-width="300"
    >
      <CancelAppointmentDialog
        @ok="cancel"
        @cancel="closeDialog"
      />
    </v-dialog>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import CancelAppointmentDialog from './CancelAppointmentDialog.vue';

export default {
  name: 'ScheduledAppointments',
  components: {
    CancelAppointmentDialog,
  },
  data: () => ({
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
    dialog: false,
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  computed: {
  },
  methods: {
    ...mapActions('appointment', ['cancelScheduledAppointment']),
    cancel() {
      this.cancelScheduledAppointment(this.appointment.id)
        .then(() => {
          this.closeDialog();
        });
    },
    openCancelDialog(appointment) {
      this.appointment = appointment;
      this.dialog = true;
    },
    closeDialog() {
      this.dialog = false;
    },
  },
};
</script>
