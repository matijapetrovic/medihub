import MedicalDoctor from '@/views/medical_doctor/MedicalDoctor.vue';
import AllPatientsView from '@/views/patient/AllPatientsView.vue';
import WorkingCalendar from '@/views/medical_doctor/WorkingCalendar.vue';
import MedicalDoctorHome from '@/views/medical_doctor/MedicalDoctorHome.vue';
import PatientInfoPage from '@/views/medical_doctor/PatientInfoPage.vue';
import AddLeaveRequestForm from '@/app/leave_request/components/AddLeaveRequestForm.vue';
import PreviousPatients from '@/views/medical_doctor/PreviousPatients.vue';
import PatientsFinishedAppointments from '@/app/finished_appointment/_components/PatientFinishedAppointments.vue';

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
    {
      path: '/leave-request/add',
      component: AddLeaveRequestForm,
    },
    {
      path: '/patient-info/:id',
      component: PatientInfoPage,
    },
    {
      path: '/previous-patients',
      component: PreviousPatients,
    },
    {
      path: '/patients-finished-appointments/:param',
      component: PatientsFinishedAppointments,
    },
  ],
};
