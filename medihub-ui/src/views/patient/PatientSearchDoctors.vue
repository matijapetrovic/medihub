<template>
  <div>
    <h1>Search Doctors</h1>
    <DoctorSearchTable
      :items="doctors"
    />
  </div>
</template>

<script>
import DoctorSearchTable from '@/app/doctor/_components/DoctorSearchTable.vue';
import { mapState, mapActions } from 'vuex';

export default {
  name: 'PatientSearchDoctors',
  components: {
    DoctorSearchTable,
  },
  methods: {
    ...mapActions('doctor', ['fetchDoctors']),
  },
  computed: {
    ...mapState('doctor', ['doctors']),
  },
  mounted() {
    this.fetchDoctors(this.$route.params.clinic_id);
  },
};
</script>
