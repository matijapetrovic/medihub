import utils from '@/utils';

export default {
  addClinicReview(payload) {
    return utils.apiClient.post('/api/review/clinic', payload);
  },
  addDoctorReview(payload) {
    return utils.apiClient.post('/api/review/doctor', payload);
  },
  fetchClinicsForReview() {
    return utils.apiClient.get('/api/review/clinic');
  },
  fetchDoctorsForReview() {
    return utils.apiClient.get('/api/review/doctor');
  },
};
