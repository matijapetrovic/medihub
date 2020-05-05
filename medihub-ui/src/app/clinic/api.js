import utils from '@/utils';

export default {
  addClinic(credentials) {
    return utils.apiClient.post('api/clinic', credentials);
  },
  getClinicNames() {
    return utils.apiClient.get('');
  },
  fetchClinics() {
    return utils.apiClient.get('/api/clinic');
  },
};
