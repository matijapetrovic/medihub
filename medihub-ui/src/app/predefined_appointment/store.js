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
  },
};
