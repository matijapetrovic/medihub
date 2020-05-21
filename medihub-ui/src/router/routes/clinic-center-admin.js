import ClinicCenterAdmin from '@/views/clinic-center-admin/ClinicCenterAdmin.vue';
import ClinicCenterAdminHome from '@/views/clinic-center-admin/ClinicCenterAdminHome.vue';
import ClinicAdminRegistration from '@/views/clinic-center-admin/ClinicAdminRegistration.vue';
import AddClinic from '@/views/clinic-center-admin/AddClinic.vue';
import AddAppointmentTypeForm from '@/app/appointment_type/components/AddAppointmentTypeForm.vue';
import AppointmentTypeTable from '@/app/appointment_type/components/AppointmentTypeTable.vue';
import AddDrug from '@/views/clinic-center-admin/AddDrug.vue';
import AddDiagnosis from '@/views/clinic-center-admin/AddDiagnosis.vue';

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
      path: '/register-clinic-admin',
      component: ClinicAdminRegistration,
    },
    {
      path: '/add-clinic',
      component: AddClinic,
    },
    {
      path: '/add-appointment-type',
      component: AddAppointmentTypeForm,
    },
    {
      path: '/appointment-types',
      component: AppointmentTypeTable,
    },
    {
      path: '/add-drug',
      component: AddDrug,
    },
    {
      path: '/add-diagnosis',
      component: AddDiagnosis,
    },
  ],
};
