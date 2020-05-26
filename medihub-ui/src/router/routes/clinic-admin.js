import ClinicAdmin from '@/views/clinic-admin/ClinicAdmin.vue';
import AddClinicRoomForm from '@/app/clinic_room/components/AddClinicRoomForm.vue';
import AddMedicalDoctorForm from '@/app/medical_doctor/components/AddMedicalDoctorForm.vue';
import AllDoctorsView from '@/views/medical_doctor/AllDoctorsView.vue';
import AddPredefinedAppointmentForm from '@/app/predefined_appointment/_components/AddPredefinedAppointmentForm.vue';
import SearchClinicRoomTable from '@/app/clinic_room/components/SearchClinicRoomTable.vue';
import AppointmentRequest from '@/app/appointment_request/components/AppointmentRequests.vue';
import LeaveRequests from '@/views/clinic-admin/LeaveRequests.vue';

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
      path: '/medical-doctors',
      component: AllDoctorsView,
    },
    {
      path: '',
      component: ClinicAdminHome,
    },
    {
      path: '/search-clinic-rooms/:param',
      component: SearchClinicRoomTable,
    },
    {
      path: '/appointment-request/:param',
      component: AppointmentRequest,
    },
    {
      path: '/predefined-appointment',
      component: AddPredefinedAppointmentForm,
    },
    {
      path: '/leave-request',
      component: LeaveRequests,
    },
  ],
};
