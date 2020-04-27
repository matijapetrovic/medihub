import apiClient from '@/utils';

export default {
  updateProfile(payload) {
    return apiClient.post('api/profile', payload);
  },
  fetchProfile() {
    return apiClient.get('api/profile');
  },
};
