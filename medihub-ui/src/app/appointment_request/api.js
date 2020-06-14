import utils from '@/utils';

export default {
  fetchAppointmentRequests() {
    return utils.apiClient.get('api/appointment-request');
  },
  addAppointmentRequest(id, clinicRoomId) {
    return utils.apiClient.post(`api/appointment-request/${id}/schedule`, { clinicRoomId });
  },
  addOperationRequest(requestId, clinicRoomId, presentDoctors) {
    return utils.apiClient.post('api/operation/add', { requestId, clinicRoomId, presentDoctors });
  },
};
