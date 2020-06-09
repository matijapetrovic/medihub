import utils from '@/utils';

export default {
  addClinic(credentials) {
    return utils.apiClient.post('api/clinic', credentials);
  },
  getClinicNames() {
    return utils.apiClient.get('api/clinic/names');
  },
  fetchClinics(appointmentTypeId, date) {
    return utils.apiClient.get(`/api/clinic?date=${date}&appointmentTypeId=${appointmentTypeId}`);
  },
  getCurrentClinic() {
    return utils.apiClient.get('/api/clinic/getCurrent');
  },
  updateClinic(clinic) {
    return utils.apiClient.post('/api/clinic/update', clinic);
  },
  fetchPrices() {
    return utils.apiClient.get('api/clinic/prices');
  },
  addPrice(credentials) {
    return utils.apiClient.post('api/clinic/addPrice', credentials);
  },
  fetchClinicProfile(clinicId) {
    return utils.apiClient.get(`api/clinic/${clinicId}`);
  },
};
