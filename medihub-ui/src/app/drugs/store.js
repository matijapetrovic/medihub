import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  actions: {
    addDrug({ dispatch }, name) {
      return api.addDrug(name)
        .then(() => {
          const message = 'Drug added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
