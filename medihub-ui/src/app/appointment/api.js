import utils from '@/utils';

export default {
  scheduleAppointment(appointmentRequest) {
    return utils.apiClient.post('api/appointment-request', appointmentRequest);
  },
  addAppointment(appointment) {
    return utils.apiClient.post('api/appointment/add', appointment);
  },
};
