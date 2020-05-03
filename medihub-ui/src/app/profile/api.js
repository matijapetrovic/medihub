import utils from '@/utils';

export default {
  updateProfile(payload) {
    return utils.apiClient.post('api/profile', payload);
  },
  fetchProfile() {
    return utils.apiClient.get('api/profile');
  },
};
