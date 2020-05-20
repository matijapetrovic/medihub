import utils from '@/utils';

export default {
  addDrug(credentials) {
    return utils.apiClient.post('api/drugs/add', credentials);
  },
};
