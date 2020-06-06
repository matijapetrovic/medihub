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
            </tr>
          </tbody>
        </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  name: 'ScheduledAppointments',
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
  },
};
</script>
