import utils from '@/utils';

export default {
  addClinicAdmin(credentials) {
    return utils.apiClient.post('api/clinicAdmin/add', credentials);
  },
};
