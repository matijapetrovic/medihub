import ApiClient from '@/services/ApiClient';

export default {
  setToken(token) {
    console.log(token);
    ApiClient.defaults.headers.common.Authorization = `Bearer ${token}`;
  },
  login(credentials) {
    return ApiClient.post('auth/login', credentials);
  },
  changePassword(credentials) {
    return ApiClient.post('auth/password', credentials);
  },
};
