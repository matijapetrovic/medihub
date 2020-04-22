<template>
  <v-app-bar
    app
  >
    <v-toolbar-title>MediHub</v-toolbar-title>
    <v-spacer></v-spacer>
    <div class="main-nav" v-if="loggedIn">
    <router-link to="/">
      <v-btn text>Home</v-btn>
    </router-link>
    <router-link to="/profile">
      <v-btn text>Profile</v-btn>
    </router-link>
    <router-link to="/clinic_admin_registration">
      <v-btn text>Clinic admin registration</v-btn>
    </router-link>
    </div>
    <router-link to="/register" v-if="!loggedIn">
      <v-btn>Register</v-btn>
    </router-link>
    <router-link to="/login" v-if="!loggedIn">
      <v-btn color="primary">Log in</v-btn>
    </router-link>
    <v-btn v-else color="primary" @click="logout">Log out</v-btn>
  </v-app-bar>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'NavBar',
  computed: {
    ...mapGetters('auth', ['loggedIn']),
  },
  methods: {
    ...mapActions('auth', ['logout']),
  },
};
</script>
