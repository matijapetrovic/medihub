import ClinicCenterAdmin from '@/views/clinic-center-admin/ClinicCenterAdmin.vue';
import ClinicCenterAdminHome from '@/views/clinic-center-admin/ClinicCenterAdminHome.vue';
import ClinicAdminRegistration from '@/views/clinic-center-admin/ClinicAdminRegistration.vue';
import AddClinic from '@/views/clinic-center-admin/AddClinic.vue';

export default {
  path: '/clinic-center-admin',
  name: 'clinic-center-admin',
  component: ClinicCenterAdmin,
  meta: {
    requiresAuth: true,
    role: 'ROLE_CLINIC_CENTER_ADMIN',
  },
  children: [
    {
      path: '',
      component: ClinicCenterAdminHome,
    },
    {
      path: '/clinic_admin_registration',
      component: ClinicAdminRegistration,
    },
    {
      path: '/add_clinic',
      component: AddClinic,
    },
  ],
};
