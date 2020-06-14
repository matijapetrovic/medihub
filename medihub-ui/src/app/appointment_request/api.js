import utils from '@/utils';

export default {
  fetchAppointmentRequests() {
    return utils.apiClient.get('api/appointment-request');
  },
  addAppointmentRequest(payload) {
    return utils.apiClient.post('api/appointment-request/schedule', payload);
  },
  addOperationRequest(payload) {
    return utils.apiClient.post('api/operation/add', payload);
  },
};
