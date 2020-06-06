import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    reports: [],
  },
  mutations: {
    SET_REPORTS(state, reports) {
      state.reports = reports;
    },
  },
  actions: {
    fetchReports({ commit, dispatch }, payload) {
      return api.fetchReports(payload)
        .then((response) => {
          commit('SET_REPORTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
