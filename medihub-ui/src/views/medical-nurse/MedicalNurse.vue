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
  name: 'MedicalDoctor',
  components: {
    SideNav,
    BaseLayoutSidebar,
  },
  data: () => ({
    navItems: [
      {
        text: 'Prescriptions',
        path: '/prescriptions',
        icon: 'mdi-pill',
      },
      {
        text: 'Patients',
        path: '/nurse-patients',
        icon: 'mdi-account-multiple-outline',
      },
      {
        text: 'Leave request',
        path: '/nurse-vacation/add',
        icon: 'mdi-calendar-range',
      },
      {
        text: 'Working calendar',
        path: '/nurse-schedule',
        icon: 'mdi-calendar',
      },
    ],
  }),
};
</script>
