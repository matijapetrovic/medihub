import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    finishedAppointments: [],
    profit: null,
    patientFAs: [],
  },
  mutations: {
    SET_FINISHED_APPOINTMENTS(state, finishedAppointments) {
      state.finishedAppointments = finishedAppointments;
    },
    SET_PROFIT(state, profit) {
      state.profit = profit;
    },
    SET_PATIENT_FAs(state, finishedAppointments) {
      state.patientFAs = finishedAppointments;
    },
    UPDATE_PATIENT_FAs(state, updateItem) {
      const fa = state.patientFAs.find((element) => element.id === updateItem.id);
      fa.diagnosis = updateItem.diagnosis.id;
      fa.diagnosisStr = updateItem.diagnosis.name;
      fa.description = updateItem.description;
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
    getFinishedAppointments({ commit, dispatch }, patientId) {
      return api.getFinishedAppointments(patientId)
        .then((response) => {
          commit('SET_PATIENT_FAs', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    changeFinishedAppointment({ commit, dispatch }, changeItem) {
      const apiItem = {
        id: changeItem.id,
        description: changeItem.description,
        diagnosis: changeItem.diagnosis.id,
      };
      return api.changeFinishedAppointment(apiItem)
        .then(() => {
          commit('UPDATE_PATIENT_FAs', changeItem);
          const message = 'Finished appointment changed successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
