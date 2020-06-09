import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    price: null,
    appointmentTypes: [],
    lastRemoved: null,
  },
  mutations: {
    SET_PRICE(state, price) {
      state.price = price;
    },
    SET_APPOINTMENT_TYPES(state, appointmentTypes) {
      state.appointmentTypes = appointmentTypes;
    },
    SET_LAST_REMOVED(state, lastRemoved) {
      state.lastRemoved = lastRemoved;
    },
    REMOVE_APPOINTMENT_TYPE(state, item) {
      const index = state.appointmentTypes.indexOf(item);
      state.appointmentTypes.splice(index, 1);
    },
    CHANGE_APPOINTMENT_TYPE(state, item) {
      const at = state.appointmentTypes.find((element) => element.id === item.id);
      at.name = item.name;
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
    removeAppointmentType({ commit, dispatch }, item) {
      return api.removeAppointmentType(item.id)
        .then((response) => {
          commit('SET_LAST_REMOVED', response.data);
          commit('REMOVE_APPOINTMENT_TYPE', item);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    changeAppointmentType({ commit, dispatch }, item) {
      return api.changeAppointmentType(item)
        .then(() => {
          commit('CHANGE_APPOINTMENT_TYPE', item);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
  getters: {
  },
};
