import utils from '@/utils';

export default {
  addClinicRoom(credentials) {
    return utils.apiClient.post('api/clinic-room/add', credentials);
  },
};
