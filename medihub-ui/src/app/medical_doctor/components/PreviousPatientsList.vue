<template>
  <v-data-table
      :headers="headers"
      :items="previousPatients"
      :items-per-page="5"
      class="elevation-1"
    >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Previous Patients</v-toolbar-title>
      </v-toolbar>
    </template>
    <template v-slot:item.appointments="{ item }">
      <v-btn
          rounded
          color="success"
          small
          dense
          @click="viewFinishedAppointments(item)"
        >
        Finished Appointments
      </v-btn>
    </template>
  </v-data-table>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  data: () => ({
    headers: [
      {
        text: 'First name',
        align: 'start',
        value: 'firstName',
      },
      { text: 'Last name', value: 'lastName' },
      { text: 'Email', value: 'email' },
      { text: 'Address', value: 'addressLine' },
      { text: 'Insurance number', value: 'insuranceNumber' },
      {
        align: 'end',
        value: 'appointments',
      },
    ],
  }),
  name: 'PreviousPatientsList',
  methods: {
    ...mapActions('medicalDoctor', ['getPreviousPatients']),
    viewFinishedAppointments(patient) {
      const params = JSON.stringify({
        patientId: patient.id,
        firstName: patient.firstName,
        lastName: patient.lastName,
      });
      this.$router.push(`/patients-finished-appointments/${params}`);
    },
  },
  computed: {
    ...mapState('medicalDoctor', ['previousPatients']),
  },
  mounted() {
    this.getPreviousPatients();
  },
};
</script>
