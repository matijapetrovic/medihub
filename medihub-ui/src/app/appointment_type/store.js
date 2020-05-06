import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    price: null,
    appointmentTypes: [],
  },
  mutations: {
    SET_PRICE(state, price) {
      state.price = price;
    },
    SET_APPOINTMENT_TYPES(state, appointmentTypes) {
      state.appointmentTypes = appointmentTypes;
    },
  },
  actions: {
    addAppointmentType({ dispatch }, payload) {
      return api.addAppointmentType(payload)
        .then(() => {
          const message = 'Appointment type added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchAppointmentTypes({ commit, dispatch }) {
      return api.fetchAppointmentTypes()
        .then((response) => {
          commit('SET_APPOINTMENT_TYPES', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
  getters: {
  },
};
