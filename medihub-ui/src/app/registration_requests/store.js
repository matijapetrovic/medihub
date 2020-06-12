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
    REMOVE_REQUEST(state, requestId) {
      const idx = state.registrationRequests.findIndex((request) => request.id === requestId);
      state.registrationRequests.splice(idx, 1);
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
    acceptRegistrationRequest({ commit, dispatch }, requestId) {
      return api.acceptRegistrationRequest(requestId)
        .then(() => {
          const message = 'Registration request accepted successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
          commit('REMOVE_REQUEST', requestId);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    rejectRegistrationRequest({ commit, dispatch }, payload) {
      return api.rejectRegistrationRequest(payload)
        .then(() => {
          const message = 'Registration request rejected successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
          commit('REMOVE_REQUEST', payload.requestId);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
