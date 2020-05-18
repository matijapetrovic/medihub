<template>
  <BaseLayoutSidebar>
    <template v-slot:sidebar>
      <SideNav
        :items="navItems"
      />
    </template>
    <v-content>
      <router-view></router-view>
    </v-content>
  </BaseLayoutSidebar>
</template>

<script>
import BaseLayoutSidebar from '@/app/shared/_components/_layouts/BaseLayoutSidebar.vue';
import SideNav from '@/app/shared/_components/_navigation/SideNav.vue';

export default {
  name: 'ClinicAdmin',
  components: {
    SideNav,
    BaseLayoutSidebar,
  },
  data: () => ({
    navItems: [
      {
        text: 'Doctors',
        path: '/medical-doctors',
        icon: 'mdi-doctor',
        children: [
          { text: 'Add', path: '/addMedicalDoctor', icon: 'add' },
          { text: 'All doctors', path: '/medical-doctors', icon: 'mdi-account-multiple-outline' },
        ],
      },
      {
        text: 'Clinic Rooms',
        icon: 'mdi-store-24-hour',
        children: [
          { text: 'Add', path: '/addClinicRoom', icon: 'add' },
          { text: 'Search', path: '/clinic-rooms', icon: 'search' },
        ],
      },
      {
        text: 'Predefined appointments',
        icon: 'add',
        path: '/predefined-appointment',
      },
    ],
  }),
};
</script>
