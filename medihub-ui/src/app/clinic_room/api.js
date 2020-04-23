import apiClient from '@/utils';

export default {
  addClinicRoom(credentials) {
    return apiClient.post('api/clinic-room/add', credentials);
  },
};
