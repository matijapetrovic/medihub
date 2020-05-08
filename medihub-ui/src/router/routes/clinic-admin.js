import ClinicAdmin from '@/views/clinic-admin/ClinicAdmin.vue';
import AddClinicRoomForm from '@/app/clinic_room/components/AddClinicRoomForm.vue';
import AddMedicalDoctorForm from '@/app/medical_doctor/components/AddMedicalDoctorForm.vue';
<<<<<<< HEAD
import AllDoctorsView from '@/views/medical_doctor/AllDoctorsView.vue';
=======
import ClinicRoomTable from '@/app/clinic_room/components/ClinicRoomTable.vue';
>>>>>>> master

import ClinicAdminHome from '@/views/clinic-admin/ClinicAdminHome.vue';

export default {
  path: '/clinic-admin',
  name: 'clinic-admin',
  component: ClinicAdmin,
  meta: {
    requiresAuth: true,
    role: 'ROLE_CLINIC_ADMIN',
  },
  children: [
    {
      path: '/addMedicalDoctor',
      component: AddMedicalDoctorForm,
    },
    {
      path: '/addClinicRoom',
      component: AddClinicRoomForm,
    },
    {
      path: '/medical-doctor/getAll',
      component: AllDoctorsView,
    },
    {
      path: '',
      component: ClinicAdminHome,
    },
    {
      path: '/clinic-rooms',
      component: ClinicRoomTable,
    },
  ],
};
