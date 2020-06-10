import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    scheduledAppointments: [],
    appointment: null,
  },
  mutations: {
    SET_SCHEDULED_APPOINTMENTS(state, scheduledAppointments) {
      state.scheduledAppointments = scheduledAppointments;
    },
    SET_APPOINTMENT(state, appointment) {
      state.appointment = appointment;
    },
  },
  actions: {
    scheduleAppointment({ dispatch, rootState }, appointmentRequest) {
      return api.scheduleAppointment({
        ...appointmentRequest,
        date: rootState.doctor.searchParams.date,
      })
        .then(() => {
          const message = 'Appointment schedule request sent.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    scheduleDoctorsAppointment({ dispatch }, appointmentRequest) {
      return api.scheduleDoctorsAppointment(appointmentRequest)
        .then(() => {
          const message = 'Appointment schedule request sent.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    addAppointment({ dispatch }, payload) {
      return api.addAppointment(payload)
        .then(() => {
          const message = 'Appointment saved successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchScheduledAppointments({ commit, dispatch }) {
      return api.fetchScheduledAppointments()
        .then((response) => {
          commit('SET_SCHEDULED_APPOINTMENTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getCurrentAppointment({ commit, dispatch }, payload) {
      return api.getCurrentAppointment(payload)
        .then((response) => {
          commit('SET_APPOINTMENT', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
