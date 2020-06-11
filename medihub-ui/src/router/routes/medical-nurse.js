import MedicalNurse from '@/views/medical-nurse/MedicalNurse.vue';
import Prescriptions from '@/views/medical-nurse/Prescriptions.vue';
import AllPatientsView from '@/views/patient/AllPatientsView.vue';
import AddLeaveRequestForm from '@/app/leave_request/components/AddLeaveRequestForm.vue';

export default {
  path: '/medical-nurse',
  name: 'medical-nurse',
  component: MedicalNurse,
  meta: {
    requiresAuth: true,
    role: 'ROLE_NURSE',
  },
  children: [
    {
      path: '/prescriptions',
      component: Prescriptions,
    },
    {
      path: '/nurse-patients',
      component: AllPatientsView,
    },
    {
      path: '/nurse-vacation/add',
      component: AddLeaveRequestForm,
    },
  ],
};
