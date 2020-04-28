import apiClient from '@/utils';

export default {
  addMedicalDoctor(credentials) {
    return apiClient.post('api/medical-doctor/add', credentials);
  },
};
