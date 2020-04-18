import ApiClient from '@/services/ApiClient';

export default {
  setToken(token) {
    console.log(token);
    ApiClient.defaults.headers.common.Authorization = `Bearer ${token}`;
  },
  register(payload) {
    return ApiClient.post('api/patient/registration', payload);
  },
  login(payload) {
    return ApiClient.post('auth/login', payload);
  },
  changePassword(payload) {
    return ApiClient.post('auth/password', payload);
  },
};
