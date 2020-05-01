import apiClient from '@/utils';

export default {
  addAppointmentType(credentials) {
    return apiClient.post('api/appointment-type/add', credentials);
  },
};
