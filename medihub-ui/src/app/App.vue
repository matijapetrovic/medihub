<template>
  <v-app>
    <NavBar/>
    <NotificationBar/>
    <router-view></router-view>
  </v-app>
</template>

<script>

import NavBar from '@/app/shared/_components/_navigation/NavBar.vue';
import NotificationBar from '@/app/_notifications/_components/NotificationBar.vue';

export default {
  name: 'App',
  components: {
    NavBar,
    NotificationBar,
  },
};
</script>
