import apiClient from '@/utils';

export default {
  getAllPatients() {
    return apiClient.get('api/patient/get/all');
  },
};
