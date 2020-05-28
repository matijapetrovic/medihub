<template>
  <div>
    <h1>Appointment History</h1>
    <AppointmentHistory
      :items="finishedAppointments"
      v-if="finishedAppointments.length"
    />
    <p v-else>You currently have no appointments in your history.</p>
  </div>
</template>

<script>
import AppointmentHistory from '@/app/finished_appointment/_components/AppointmentHistory.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'PatientAppointmentHistory',
  components: {
    AppointmentHistory,
  },
  methods: {
    ...mapActions('finishedAppointment', ['fetchFinishedAppointments']),
  },
  computed: {
    ...mapState('finishedAppointment', ['finishedAppointments']),
  },
  mounted() {
    this.fetchFinishedAppointments();
  },
};
</script>
