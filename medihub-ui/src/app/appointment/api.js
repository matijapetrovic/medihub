import utils from '@/utils';

export default {
  scheduleAppointment(appointmentRequest) {
    return utils.apiClient.post('api/appointment-request', appointmentRequest);
  },
};
