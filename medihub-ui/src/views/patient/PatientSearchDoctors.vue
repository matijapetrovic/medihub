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

export default {
  name: 'PatientSearchDoctors',
  components: {
    DoctorSearchTable,
  },
};
</script>
