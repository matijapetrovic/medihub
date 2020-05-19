import MedicalDoctor from '@/views/medical_doctor/MedicalDoctor.vue';
import AllPatientsView from '@/views/patient/AllPatientsView.vue';
import WorkingCalendar from '@/views/medical_doctor/WorkingCalendar.vue';
import MedicalDoctorHome from '@/views/medical_doctor/MedicalDoctorHome.vue';

export default {
  path: '/medical-doctor',
  name: 'medical-doctor',
  component: MedicalDoctor,
  meta: {
    requiresAuth: true,
    role: 'ROLE_DOCTOR',
  },
  children: [
    {
      path: '/patients',
      component: AllPatientsView,
    },
    {
      path: '',
      component: MedicalDoctorHome,
    },
    {
      path: '/working-calendar',
      component: WorkingCalendar,
    },
  ],
};
