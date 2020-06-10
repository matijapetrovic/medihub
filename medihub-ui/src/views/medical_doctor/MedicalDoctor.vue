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
        text: 'Patients',
        path: '/patients',
        icon: 'mdi-account-multiple-outline',
      },
      {
        text: 'Working calendar',
        path: '/working-calendar',
        icon: 'mdi-calendar',
      },
      {
        text: 'Leave request',
        path: '/leave-request/add',
        icon: 'mdi-calendar-range',
      },
      {
        text: 'Previous Patients',
        path: '/previous-patients',
        icon: 'mdi-account-edit-outline',
      },
    ],
  }),
};
</script>
