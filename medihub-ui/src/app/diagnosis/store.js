import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  actions: {
    addDiagnosis({ dispatch }, name) {
      return api.addDiagnosis(name)
        .then(() => {
          const message = 'Diagnosis added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
