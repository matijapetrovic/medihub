import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  actions: {
    registerClinicAdmin({ dispatch }, admin) {
      return api.addClinicAdmin(admin)
        .then(() => {
          const message = 'Registration successful';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
