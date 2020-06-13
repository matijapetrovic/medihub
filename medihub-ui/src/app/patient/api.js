import utils from '@/utils';

export default {
  getAllPatients() {
    return utils.apiClient.get('api/patient');
  },
  getPatientById(id) {
    return utils.apiClient.get(`api/patient/${id}`);
  },
};
