import utils from '@/utils';

export default {
  actions: {
    getPrescriptions() {
      return utils.apiCliend.get('api/prescription');
    },
  },
};
