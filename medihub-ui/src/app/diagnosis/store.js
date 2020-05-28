import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    diagnosis: [],
  },
  mutations: {
    SET_DIAGNOSIS(state, diagnosis) {
      state.diagnosis = diagnosis;
    },
  },
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
    getDiagnosis({ commit, dispatch }) {
      return api.getDiagnosis()
        .then((response) => {
          commit('SET_DIAGNOSIS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
