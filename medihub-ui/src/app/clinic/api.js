import utils from '@/utils';

export default {
  addClinic(credentials) {
    return utils.apiClient.post('api/clinic', credentials);
  },
  getClinicNames() {
    return utils.apiClient.get('api/clinic/names');
  },
  fetchClinics(searchParams) {
    return utils.apiClient.get('/api/clinic', { params: searchParams });
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
