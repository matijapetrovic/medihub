import utils from '@/utils';

export default {
  addClinicRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/add', credentials);
  },
  deleteClinicRoom(id) {
    return utils.apiClient.post('api/clinic-room/delete', id);
  },
  fetchClinicRooms(payload) {
    return utils.apiClient.get('api/clinic-room', { params: payload });
  },
  scheduleRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/schedule', credentials);
  },
};
