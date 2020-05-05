import utils from '@/utils';

export default {
  addAppointmentType(credentials) {
    return utils.apiClient.post('api/appointment-type/add', credentials);
  },
  fetchAppointmentTypes() {
    return utils.apiClient.get('api/appointment-type');
  },
};
