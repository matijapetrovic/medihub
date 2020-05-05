import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinics: [],
  },
  mutations: {
    SET_CLINICS(state, clinics) {
      state.clinics = clinics;
    },
  },
  actions: {
    fetchClinics({ commit, dispatch }) {
      return api.fetchClinics()
        .then((response) => {
          commit('SET_CLINICS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
