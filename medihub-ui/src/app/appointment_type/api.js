import utils from '@/utils';

export default {
  addAppointmentType(credentials) {
    return utils.apiClient.post('api/appointment-type/add', credentials);
  },
};
