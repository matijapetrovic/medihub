import utils from '@/utils';

export default {
  fetchAppointmentRequests() {
    return utils.apiClient.get('api/appointment-request');
  },
};
