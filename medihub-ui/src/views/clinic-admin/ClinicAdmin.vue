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
        text: 'Clinic information',
        icon: 'mdi-hospital-building',
        path: '/clinicInfo',
      },
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
        icon: 'mdi-book-open-page-variant',
        path: '/predefined-appointment',
      },
      {
        text: 'Appointment requests',
        icon: 'mdi-message-plus',
        path: `/appointment-request/${null}`,
      },
      {
        text: 'Doctor Leave requests',
        icon: 'mdi-arrow-left-bold-circle-outline',
        path: '/leave-request',
      },
      {
        text: 'Nurse Leave requests',
        icon: 'mdi-arrow-left-bold-circle-outline',
        path: '/nurse-leave-request',
      },
      {
        text: 'Pricelist',
        icon: 'mdi-book-open',
        path: '/pricelist',
      },
      {
        text: 'Reports',
        icon: 'mdi-bookmark-check',
        path: '/reports',
      },
    ],
  }),
};
</script>
