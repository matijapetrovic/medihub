import apiClient from '@/utils';

export default {
  setToken(token) {
    console.log(token);
    apiClient.defaults.headers.common.Authorization = `Bearer ${token}`;
  },
  register(payload) {
    return apiClient.post('api/registration', payload);
  },
  login(credentials) {
    return apiClient.post('api/auth/login', credentials);
  },
  changePassword(credentials) {
    return apiClient.post('api/auth/password', credentials);
  },
};
