import utils from '@/utils';

export default {
  addClinic(credentials) {
    return utils.apiClient.post('api/clinic', credentials);
  },
};
