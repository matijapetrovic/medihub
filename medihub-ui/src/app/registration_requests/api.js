import utils from '@/utils';

export default {
  getRegistrationRequests() {
    return utils.apiClient.get('api/registration');
  },
  acceptRegistrationRequest(requestId) {
    return utils.apiClient.post(`api/registration/${requestId}/accept`);
  },
  rejectRegistrationRequest(payload) {
    return utils.apiClient.post(`api/registration/${payload.requestId}/reject`, { reason: payload.reason });
  },
};
