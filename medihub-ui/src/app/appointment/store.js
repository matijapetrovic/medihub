import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
  },
  actions: {
    scheduleAppointment({ dispatch }, appointmentRequest) {
      return api.scheduleAppointment(appointmentRequest)
        .then(() => {
          const message = 'Appointment schedule request sent.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
