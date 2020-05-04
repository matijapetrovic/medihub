<template>
  <div>
    <h1>Search Clinics</h1>
    <ClinicSearchForm/>
    <ClinicSearchTable
      :items="clinics"
    />
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
    ...mapActions('clinic', ['fetchClinics']),
  },
  computed: {
    ...mapState('clinic', ['clinics']),
  },
  mounted() {
    this.fetchClinics();
  },
};
</script>
