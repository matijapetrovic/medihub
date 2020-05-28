import utils from '@/utils';

export default {
  fetchFinishedAppointments() {
    return utils.apiClient.get('/api/finished_appointment');
  },
  addClinicReview(payload) {
    return utils.apiClient.post('/api/review/clinic', payload);
  },
};
