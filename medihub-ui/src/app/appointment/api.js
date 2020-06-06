import utils from '@/utils';

export default {
  scheduleAppointment(appointmentRequest) {
    return utils.apiClient.post('api/appointment-request', appointmentRequest);
  },
  addAppointment(appointment) {
    return utils.apiClient.post('api/appointment/add', appointment);
  },
  fetchScheduledAppointments() {
    return utils.apiClient.get('api/appointment');
  },
  cancelScheduledAppointment(appointmentId) {
    return utils.apiClient.post(`/api/appointment/${appointmentId}/cancel`);
  },
};
