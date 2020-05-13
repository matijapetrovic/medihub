<template>
  <v-data-table
    :headers="headers"
    :items="items"
    :items-per-page="5"
    :single-expand="true"
    item-key="id"
    show-expand
    class="elevation-1"
  >
  <template v-slot:expanded-item="{ headers, item }">
    <td :colspan="headers.length">
      <tr
        v-for="time in times(item.from, item.to)"
        :key="time"
      >
        <td>{{ time }}</td>
        <td><v-btn @click="schedule(item.id, time)">Schedule Appointment</v-btn></td>
      </tr>
    </td>
  </template>
  </v-data-table>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'DoctorSearchTable',
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
  methods: {
    ...mapActions('appointment', ['scheduleAppointment']),
    times(from, to) {
      const times = [];
      const start = +from.split(':')[0];
      let end = +to.split(':')[0];
      if (end < start) {
        end += 24;
      }
      for (let i = start; i <= end; i += 1) {
        const time = (i % 24) < 10 ? `0${i % 24}` : `${i % 24}`;
        times.push(`${time}:00`);
      }
      return times;
    },
    schedule(doctorId, time) {
      this.scheduleAppointment({
        doctorId,
        time,
      });
    },
  },
};
</script>
