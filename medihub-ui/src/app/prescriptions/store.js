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
    REMOVE_PRESCRIPTION(state, id) {
      const index = state.prescriptions.findIndex((element) => element.id === id);
      state.prescriptions.splice(index, 1);
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
    acceptPrescription({ commit, dispatch }, id) {
      return api.acceptPrescription(id)
        .then(() => {
          commit('REMOVE_PRESCRIPTION', id);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    rejectPrescription({ commit, dispatch }, id) {
      return api.rejectPrescription(id)
        .then(() => {
          commit('REMOVE_PRESCRIPTION', id);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },

  },
};
