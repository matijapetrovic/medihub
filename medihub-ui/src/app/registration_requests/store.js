import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    registrationRequests: [],
  },
  mutations: {
    SET_REGISTRATION_REQUESTS(state, registrationRequests) {
      state.registrationRequests = registrationRequests;
    },
  },
  actions: {
    getRegistrationRequests({ commit, dispatch }) {
      return api.getRegistrationRequests()
        .then((response) => {
          commit('SET_REGISTRATION_REQUESTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
