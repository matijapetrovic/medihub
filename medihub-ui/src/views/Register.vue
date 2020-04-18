<template>
  <div class="login fill-height">
    <v-container
      fluid
      class="fill-height"
    >
      <v-row
        align="center"
        justify="center"
      >
        <PatientRegisterForm/>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import PatientRegisterForm from '@/components/PatientRegisterForm.vue';

export default {
  name: 'Register',
  components: {
    PatientRegisterForm,
  },
};
</script>
