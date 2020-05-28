import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    finishedAppointments: [],
  },
  mutations: {
    SET_FINISHED_APPOINTMENTS(state, finishedAppointments) {
      state.finishedAppointments = finishedAppointments;
    },
  },
  actions: {
    fetchFinishedAppointments({ commit, dispatch }) {
      return api.fetchFinishedAppointments()
        .then((response) => {
          commit('SET_FINISHED_APPOINTMENTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    addClinicReview({ dispatch }, payload) {
      return api.addClinicReview(payload)
        .then(() => {
          const message = 'Clinic review added successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
          throw err;
        });
    },
    addDoctorReview({ dispatch }, payload) {
      return api.addDoctorReview(payload)
        .then(() => {
          const message = 'Doctor review added successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
          throw err;
        });
    },
  },
};
