import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    predefinedAppointments: [],
  },
  mutations: {
    SET_PREDEFINED_APPOINTMENTS(state, predefinedAppointments) {
      state.predefinedAppointments = predefinedAppointments;
    },
    REMOVE_PREDEFINED_APPOINTMENT(state, appointmentId) {
      const idx = state.predefinedAppointments
        .findIndex((appointment) => appointment.id === appointmentId);
      state.predefinedAppointments.splice(idx, 1);
    },
  },
  actions: {
    addPredefinedAppointment({ dispatch }, payload) {
      return api.addPredefinedAppointment(payload)
        .then(() => {
          const message = 'Appointment is defined successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchPredefinedAppointments({ commit, dispatch }, clinicId) {
      return api.fetchPredefinedAppointments(clinicId)
        .then((response) => {
          commit('SET_PREDEFINED_APPOINTMENTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    schedulePredefinedAppointment({ commit, dispatch }, appointmentId) {
      return api.schedulePredefinedAppointment(appointmentId)
        .then(() => {
          const message = 'Appointment scheduled successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
          commit('REMOVE_PREDEFINED_APPOINTMENT', appointmentId);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
