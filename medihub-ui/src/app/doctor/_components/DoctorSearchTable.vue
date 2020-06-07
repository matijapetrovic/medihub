<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      @item-expanded="getAvailableTimes"
      item-key="id"
      show-expand
      single-expand
      class="elevation-1"
    >
    <template
      v-slot:expanded-item="{ headers, item }">
      <td
        :colspan="headers.length"
      >
        <tr
          v-for="time in item.availableTimes"
          :key="time"
        >
          <td>{{ time }}</td>
          <td><v-btn @click="schedule(item.id, time)">Schedule Appointment</v-btn></td>
        </tr>
      </td>
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
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import ScheduleAppointmentDialog from '@/app/appointment/_components/ScheduleAppointmentDialog.vue';

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
    ],
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  computed: {
    ...mapState('doctor', ['availableTimes']),
  },
  methods: {
    ...mapActions('appointment', ['scheduleAppointment']),
    ...mapActions('doctor', ['fetchAvailableTimes']),
    schedule(doctorId, time) {
      this.scheduleAppointment({
        doctorId,
        time,
      });
    },
    getAvailableTimes({ item }) {
      this.fetchAvailableTimes(item.id);
    },
  },
};
</script>
