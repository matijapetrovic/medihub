import utils from '@/utils';

export default {
  addOperation(payload) {
    return utils.apiClient.post('api/operation/add', payload);
  },
};
