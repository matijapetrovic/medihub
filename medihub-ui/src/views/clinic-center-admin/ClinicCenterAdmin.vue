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
        text: 'Clinic Admins',
        icon: 'mdi-account-cowboy-hat',
        children: [
          { text: 'Add', path: '/clinic_admin_registration', icon: 'add' },
          { text: 'Delete', path: '', icon: 'delete' },
        ],
      },
      {
        text: 'Clinics',
        icon: 'mdi-hospital-box-outline',
        children: [
          { text: 'Add', path: '/add_clinic', icon: 'add' },
          { text: 'Delete', path: '', icon: 'delete' },
        ],
      },
    ],
  }),
};
</script>
