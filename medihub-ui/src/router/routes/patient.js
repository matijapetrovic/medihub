import Patient from '@/views/patient/Patient.vue';
import PatientSearchClinics from '@/views/patient/PatientSearchClinics.vue';
import PatientSearchDoctors from '@/views/patient/PatientSearchDoctors.vue';
import PatientMedicalRecord from '@/views/patient/PatientMedicalRecord.vue';
import PatientAppointmentHistory from '@/views/patient/PatientAppointmentHistory.vue';
import PatientScheduledAppointments from '@/views/patient/PatientScheduledAppointments.vue';
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
      path: '/search-doctors/:clinic_id',
      component: PatientSearchDoctors,
    },
    {
      path: '/medical-record',
      component: PatientMedicalRecord,
    },
    {
      path: '/appointment-history',
      component: PatientAppointmentHistory,
    },
    {
      path: '/scheduled-appointments',
      component: PatientScheduledAppointments,
    },
    {
      path: '',
      component: PatientHome,
    },
  ],
};
