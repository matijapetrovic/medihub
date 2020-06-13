import utils from '@/utils';

export default {
  fetchAppointmentRequests() {
    return utils.apiClient.get('api/appointment-request');
  },
  addRequest(id, clinicRoomId) {
    return utils.apiClient.post(`api/appointment-request/${id}/schedule`, { clinicRoomId });
  },
};
