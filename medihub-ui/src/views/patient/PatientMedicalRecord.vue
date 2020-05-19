<template>
  <div>
    <MedicalRecord/>
  </div>
</template>

<script>
import MedicalRecord from '@/app/medical_record/_components/MedicalRecord.vue';

export default {
  name: 'PatientMedicalRecord',
  components: {
    MedicalRecord,
  },
};
</script>
