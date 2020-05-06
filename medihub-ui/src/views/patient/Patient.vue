<template>
  <BaseLayoutSidebar>
    <template v-slot:sidebar>
      <SideNav
        :items="navItems"
      />
    </template>
    <v-content>
      <router-view></router-view>fa
    </v-content>
  </BaseLayoutSidebar>
</template>

<script>
import BaseLayoutSidebar from '@/app/shared/_components/_layouts/BaseLayoutSidebar.vue';
import SideNav from '@/app/shared/_components/_navigation/SideNav.vue';

export default {
  name: 'Patient',
  components: {
    SideNav,
    BaseLayoutSidebar,
  },
  data: () => ({
    navItems: [
      {
        text: 'Search Clinics',
        path: '/search-clinics',
        icon: 'mdi-doctor',
      },
    ],
  }),
};
</script>
