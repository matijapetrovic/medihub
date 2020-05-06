import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    name: null,
  },
  mutations: {
    SET_NAME(state, name) {
      state.name = name;
    },
  },
  actions: {
    addClinicRoom({ dispatch }, payload) {
      return api.addClinicRoom(payload)
        .then(() => {
          const message = 'Clinic room added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    deleteClinicRoom({ dispatch }, payload) {
      return api.deleteClinicRoom(payload)
        .then(() => {
          const message = 'Clinic room deleted successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
  getters: {
  },
};
