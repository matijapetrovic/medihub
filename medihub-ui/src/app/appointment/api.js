import utils from '@/utils';

export default {
  scheduleAppointment(appointmentRequest) {
    return utils.apiClient.post('api/appointment-request', appointmentRequest);
  },
  scheduleDoctorsAppointment(appointmentRequest) {
    return utils.apiClient.post('api/appointment-request/addForDoctor', appointmentRequest);
  },
  addAppointment(appointment) {
    return utils.apiClient.post('api/appointment/add', appointment);
  },
  fetchScheduledAppointments() {
    return utils.apiClient.get('api/appointment');
  },
  getCurrentAppointment(patientId) {
    return utils.apiClient.get(`api/appointment/getCurrent/${patientId}`);
  },
};
