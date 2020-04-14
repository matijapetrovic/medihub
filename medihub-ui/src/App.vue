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
      <router-link to="/login" v-if="!loggedIn">
        <v-btn color="primary">Log in</v-btn>
      </router-link>
      <v-btn v-else color="primary" @click="logout">Log out</v-btn>
    </v-app-bar>

    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'App',

  data: () => ({
    //
  }),
  computed: {
    ...mapGetters('auth', ['loggedIn']),
  },
  methods: {
    ...mapActions('auth', ['logout']),
  },
};
</script>
