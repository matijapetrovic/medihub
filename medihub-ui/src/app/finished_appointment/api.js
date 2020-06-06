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
  addClinicReview(payload) {
    return utils.apiClient.post('/api/review/clinic', payload);
  },
  addDoctorReview(payload) {
    return utils.apiClient.post('/api/review/doctor', payload);
  },
};
