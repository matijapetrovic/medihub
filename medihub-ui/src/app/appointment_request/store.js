import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    appointmentRequests: [],
    params: null,
  },
  mutations: {
    SET_APPOINTMENT_REQUESTS(state, appointmentRequests) {
      state.appointmentRequests = appointmentRequests;
    },
    SET_SEARCH_PARAMS(state, params) {
      state.params = params;
    },
    GET_SEARCH_PARAMS(state) {
      return state.params;
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
    deleteAppointmentRequest({ commit, dispatch }, id) {
      return api.deleteAppointmentRequest(id)
        .then((response) => {
          commit('SET_APPOINTMENT_REQUESTS', response.data);
        }).catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getAppointmentRequests() {
      return this.appointmentRequests;
    },
    setSearchParams({ commit }, params) {
      commit('SET_SEARCH_PARAMS', params);
    },
    getSearchParams({ commit }) {
      commit('GET_SEARCH_PARAMS');
    },
  },
};