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
  name: 'Patient',
  components: {
    SideNav,
    BaseLayoutSidebar,
  },
  data: () => ({
    navItems: [
      {
        text: 'Clinics',
        path: '/search-clinics',
        icon: 'mdi-hospital-building',
      },
      {
        text: 'Medical Record',
        path: '/medical-record',
        icon: 'mdi-clipboard-pulse-outline',
      },
      {
        text: 'Appointment History',
        path: '/appointment-history',
        icon: 'mdi-stethoscope',
      },
      {
        text: 'Scheduled Appointments',
        path: '/scheduled-appointments',
        icon: 'mdi-clock-time-eight-outline',
      },
      {
        text: 'Reviews',
        path: '/reviews',
        icon: 'mdi-star-circle-outline',
      },
    ],
  }),
};
</script>
