import utils from '@/utils';

export default {
  setToken(token) {
    console.log(token);
    utils.apiClient.defaults.headers.common.Authorization = `Bearer ${token}`;
  },
  register(payload) {
    return utils.apiClient.post('api/registration', payload);
  },
  login(credentials) {
    return utils.apiClient.post('api/auth/login', credentials);
  },
  changePassword(credentials) {
    return utils.apiClient.post('api/auth/password', credentials);
  },
  activateAccount(accountId) {
    return utils.apiClient.post(`api/auth/activate/${accountId}`);
  },
};
