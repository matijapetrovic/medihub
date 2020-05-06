<template>
<v-container
      fluid
    >
      <v-row
        align="center"
        justify="center"
      >
      <AddForm></AddForm>
      </v-row>
  </v-container>
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
