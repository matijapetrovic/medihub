<template>
  <v-row
    align="center"
    justify="center"
  >
    <AddForm></AddForm>
  </v-row>
</template>

<script>
import AddClinicForm from '@/app/clinic/components/AddClinicForm.vue';

export default {
  name: 'AddClinic',
  components: {
    AddForm: AddClinicForm,
  },
};
</script>
