import utils from './node_modules/@/utils';

export default {
  addMedicalDoctor(credentials) {
    return utils.apiClient.post('api/medical-doctor/add', credentials);
  },
};
