import apiClient from '@/utils';

export default {
  setToken(token) {
    console.log(token);
    apiClient.defaults.headers.common.Authorization = `Bearer ${token}`;
  },
  register(payload) {
    return apiClient.post('api/patient/registration', payload);
  },
  login(credentials) {
    return apiClient.post('auth/login', credentials);
  },
  changePassword(credentials) {
    return apiClient.post('auth/password', credentials);
  },
};
