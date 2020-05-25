import utils from '@/utils';

export default {
  addDiagnosis(credentials) {
    return utils.apiClient.post('api/diagnosis/add', credentials);
  },
  getDiagnosis() {
    return utils.apiClient.get('api/diagnosis');
  },
};
