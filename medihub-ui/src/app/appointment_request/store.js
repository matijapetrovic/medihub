import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    appointmentRequsts: [],
  },
  mutations: {
    SET_APPOINTMENT_REQUESTS(state, appointmentRequsts) {
      state.appointmentRequsts = appointmentRequsts;
    },
  },
  actions: {
    fetchAppointmentRequests({ commit, dispatch }) {
      return api.fetchAppointmentRequests()
        .then((response) => {
          commit('SET_APPOINTMENT_REQUESTS', response.data);
        }).catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
