<template>
  <div>
    <h1>Scheduled Appointments</h1>
    <ScheduledAppointments
      :items="scheduledAppointments"
      v-if="scheduledAppointments.length"
    />
    <p v-else>You currently have no scheduled appointments.</p>
  </div>
</template>

<script>
import ScheduledAppointments from '@/app/appointment/_components/ScheduledAppointments.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'PatientScheduledAppointments',
  components: {
    ScheduledAppointments,
  },
  methods: {
    ...mapActions('appointment', ['fetchScheduledAppointments']),
  },
  computed: {
    ...mapState('appointment', ['scheduledAppointments']),
  },
  mounted() {
    this.fetchScheduledAppointments();
  },
};
</script>
