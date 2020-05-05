import utils from '@/utils';

export default {
  fetchClinics() {
    return utils.apiClient.get('/api/clinic');
  },
};
