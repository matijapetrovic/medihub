<template>
  <v-app>
    <NavBar/>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import NavBar from '@/app/shared/_components/_navigation/NavBar.vue';

export default {
  name: 'App',
  components: {
    NavBar,
  },
};
</script>
