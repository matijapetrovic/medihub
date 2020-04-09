<template>
  <v-app>
    <v-app-bar
      app
    >
      <v-toolbar-title>MediHub</v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link to="/">
        <v-btn text>Home</v-btn>
      </router-link>
      <router-link to="/about">
        <v-btn text>About</v-btn>
      </router-link>
      <router-link to="/clinic_admin_registration">
        <v-btn text>Clinic admin registration</v-btn>
      </router-link>
    </v-app-bar>
    <v-content class="mx-auto">
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
