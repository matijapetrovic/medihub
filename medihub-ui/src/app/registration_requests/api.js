import utils from '@/utils';

export default {
  getRegistrationRequests() {
    return utils.apiClient.get('api/registration');
  },
};
