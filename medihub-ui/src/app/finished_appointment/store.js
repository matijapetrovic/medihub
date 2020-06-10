import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    finishedAppointments: [],
    profit: null,
  },
  mutations: {
    SET_FINISHED_APPOINTMENTS(state, finishedAppointments) {
      state.finishedAppointments = finishedAppointments;
    },
    SET_PROFIT(state, profit) {
      state.profit = profit;
    },
  },
  actions: {
    fetchClinicFinishedAppointments({ commit, dispatch }, payload) {
      return api.fetchClinicFinishedAppointments(payload)
        .then((response) => {
          commit('SET_FINISHED_APPOINTMENTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchFinishedAppointments({ commit, dispatch }) {
      return api.fetchFinishedAppointments()
        .then((response) => {
          commit('SET_FINISHED_APPOINTMENTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getProfit({ commit, dispatch }, profit) {
      return api.getProfit(profit)
        .then((response) => {
          commit('SET_PROFIT', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
