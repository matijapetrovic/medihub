<template>
    <v-form ref="form">
        <br>
        <h1>Clinic admin registration</h1>
        <br>
        <v-text-field
        v-model="username"
        label="Username"
        required
        ></v-text-field>

        <v-text-field
        v-model="password"
        label="Password"
        required
        ></v-text-field>

         <v-text-field
        v-model="firstname"
        label="First name"
        required
        ></v-text-field>

         <v-text-field
        v-model="secondname"
        label="Second name"
        required
        ></v-text-field>

        <v-btn
        color="success"
        class="mr-4"
        v-on:click="submit">
        Register
        </v-btn>
  </v-form>
</template>


<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'ClinicAdminReg',

  data: () => ({
    username: '',
    password: '',
    firstname: '',
    secondname: '',
  }),
  methods: {
    ...mapActions(['registerClinicAdmin']),
    async submit() {
      await this.registerClinicAdmin({
        username: this.username,
        password: this.password,
        firstname: this.firstname,
        secondname: this.secondname,
      });
    },
  },
  computed: mapGetters(['getClinicAdminReg']),
};
</script>
