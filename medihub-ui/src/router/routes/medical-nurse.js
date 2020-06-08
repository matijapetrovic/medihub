import MedicalNurse from '@/views/medical-nurse/MedicalNurse.vue';
import Prescriptions from '@/views/medical-nurse/Prescriptions.vue';

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
  ],
};
