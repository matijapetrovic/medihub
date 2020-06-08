import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    prescriptions: [],
  },
  mutations: {
    SET_PRESCRIPTIONS(state, prescriptions) {
      state.prescriptions = prescriptions;
    },
  },
  actions: {
    getPrescriptions({ commit, dispatch }) {
      return api.getPrescriptions()
        .then((response) => {
          commit('SET_PRESCRIPTIONS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
