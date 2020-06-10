import utils from '@/utils';

export default {
  fetchClinicFinishedAppointments(payload) {
    return utils.apiClient.post('/api/finished_appointment/getForClinic', payload);
  },
  fetchFinishedAppointments() {
    return utils.apiClient.get('/api/finished_appointment');
  },
  getProfit(payload) {
    return utils.apiClient.post('/api/finished_appointment/getProfit', payload);
  },
  getFinishedAppointments(patientId) {
    return utils.apiClient.get(`/api/finished_appointment/${patientId}`);
  },
  changeFinishedAppointment(changeItem) {
    return utils.apiClient.post('/api/finished_appointment/change', changeItem);
  },
};
