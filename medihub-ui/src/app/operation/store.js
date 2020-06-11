import utils from '@/utils';
import api from './api';

export default {
  data: () => ({
    operation: {},
  }),
  namespaced: true,
  mutations: {
    SET_OPERATION(state, operation) {
      state.operation = operation;
    },
  },
  actions: {
    addOperation({ commit, dispatch }, payload) {
      return api.addOperation(payload)
        .then((response) => {
          commit('SET_OPERATION', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
