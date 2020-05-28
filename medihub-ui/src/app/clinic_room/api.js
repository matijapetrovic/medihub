import utils from '@/utils';

export default {
  addClinicRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/add', credentials);
  },
  fetchAllClinicRooms() {
    return utils.apiClient.get('api/clinic-room');
  },
  deleteClinicRoom(id) {
    return utils.apiClient.post('api/clinic-room/delete', id);
  },
  fetchClinicRooms(payload) {
    return utils.apiClient.get('api/clinic-room/search', { params: payload });
  },
  scheduleRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/schedule', credentials);
  },
  updateClinicRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/update', credentials);
  },
};
