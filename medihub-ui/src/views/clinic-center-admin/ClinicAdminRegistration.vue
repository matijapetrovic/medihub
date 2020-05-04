<template>
  <v-container
    fluid
  >
    <v-row
      align="center"
      justify="center"
    >
      <RegistrationForm></RegistrationForm>
    </v-row>
  </v-container>
</template>

<script>
import ClinicAdminReg from '@/app/clinic-admins/_components/ClinicAdminRegForm.vue';

export default {
  components: {
    RegistrationForm: ClinicAdminReg,
  },
};
</script>
