<template>
  <div>
    <h1>Search Clinics</h1>
    <ClinicSearchForm
      :search-params="searchParams"
      :appointment-types="appointmentTypes"
      @searched="onSearched($event)"
    />
    <ClinicSearchTable
      :items="clinics"
      v-if="clinics.length"
    />
    <p v-else>No clinics match your search criteria.</p>
  </div>
</template>

<script>
import ClinicSearchTable from '@/app/clinic/_components/ClinicSearchTable.vue';
import ClinicSearchForm from '@/app/clinic/_components/ClinicSearchForm.vue';
import { mapState, mapActions } from 'vuex';

export default {
  name: 'PatientSearchClinics',
  components: {
    ClinicSearchTable,
    ClinicSearchForm,
  },
  methods: {
    ...mapActions('doctor', ['setDoctorSearchParams']),
    ...mapActions('clinic', ['fetchClinics', 'setClinicSearchParams']),
    ...mapActions('appointmentType', ['fetchAppointmentTypes']),
    onSearched(searchParams) {
      this.setClinicSearchParams(searchParams);
      this.setDoctorSearchParams(searchParams);
      this.fetchClinics();
    },
  },
  computed: {
    ...mapState('clinic', ['clinics', 'searchParams']),
    ...mapState('appointmentType', ['appointmentTypes']),
  },
  mounted() {
    this.fetchAppointmentTypes();
  },
};
</script>
