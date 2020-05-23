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
        text: 'Clinics',
        icon: 'mdi-hospital-box-outline',
        children: [
          { text: 'Add', path: '/add-clinic', icon: 'add' },
        ],
      },
      {
        text: 'Clinic Admins',
        icon: 'mdi-account-cowboy-hat',
        children: [
          { text: 'Add', path: '/register-clinic-admin', icon: 'add' },
        ],
      },
      {
        text: 'Appointment Types',
        icon: 'mdi-clipboard-pulse-outline',
        children: [
          { text: 'Add', path: '/add-appointment-type', icon: 'add' },
          { text: 'Search', path: '/appointment-types', icon: 'search' },
        ],
      },
      {
        text: 'Drugs codebook',
        icon: 'mdi-pill',
        children: [
          { text: 'Add', path: '/add-drug', icon: 'add' },
        ],
      },
      {
        text: 'Diagnosis codebook',
        icon: 'mdi-virus-outline',
        children: [
          { text: 'Add', path: '/add-diagnosis', icon: 'add' },
        ],
      },
    ],
  }),
};
</script>
