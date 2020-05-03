import utils from '@/utils';

export default {
  addClinicRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/add', credentials);
  },
  deleteClinicRoom(credentials) {
    return apiClient.post('api/clinic-room/delete', credentials);
  },
};
