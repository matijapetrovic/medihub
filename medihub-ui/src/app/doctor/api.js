import apiClient from './node_modules/@/utils';

export default {
  addMedicalDoctor(credentials) {
    return apiClient.post('api/medical-doctor/add', credentials);
  },
};
