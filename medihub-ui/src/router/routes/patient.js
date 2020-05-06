import Patient from '@/views/patient/Patient.vue';
import PatientSearchClinics from '@/views/patient/PatientSearchClinics.vue';
import PatientHome from '@/views/patient/PatientHome.vue';

export default {
  path: '/patient',
  name: 'patient',
  component: Patient,
  meta: {
    requiresAuth: true,
    role: 'ROLE_PATIENT',
  },
  children: [
    {
      path: '/search-clinics',
      component: PatientSearchClinics,
    },
    {
      path: '',
      component: PatientHome,
    },
  ],
};