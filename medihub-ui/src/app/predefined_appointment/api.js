import utils from '@/utils';

export default {
  addPredefinedAppointment(credentials) {
    return utils.apiClient.post('api/predefined-appointment/add', credentials);
  },
  fetchPredefinedAppointments(clinicId) {
    return utils.apiClient.get(`api/predefined-appointment/?clinicId=${clinicId}`);
  },
  schedulePredefinedAppointment(appointmentId) {
    return utils.apiClient.post(`api/predefined-appointment/${appointmentId}/schedule`);
  },
};
