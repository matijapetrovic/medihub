import utils from '@/utils';

export default {
  addPredefinedAppointment(credentials) {
    return utils.apiClient.post('api/predefined-appointment/add', credentials);
  },
};
