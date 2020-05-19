import utils from '@/utils';

export default {
  fetchAppointmentRequests() {
    return utils.apiClient.get('api/appointment-request');
  },
  deleteAppointmentRequest(id) {
    return utils.apiClient.post('api/appointment-request/delete', id);
  },
};
