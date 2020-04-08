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
      <router-link to="/login">
        <v-btn text>Log in</v-btn>
      </router-link>
    </v-app-bar>

    <v-content>
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
