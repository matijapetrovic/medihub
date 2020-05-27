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
  fetchPrices() {
    return utils.apiClient.get('api/clinic/prices');
  },
};
