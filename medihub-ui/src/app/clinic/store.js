import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinicNames: [],
    clinics: [],
    searchParams: null,
    prices: null,
  },
  mutations: {
    SET_CLINIC_NAMES(state, clinicNames) {
      state.clinicNames = clinicNames;
    },
    SET_CLINICS(state, clinics) {
      state.clinics = clinics;
    },
    SET_SEARCH_PARAMS(state, params) {
      state.searchParams = params;
    },
    SET_PRICES(state, prices) {
      state.prices = prices;
    },
  },
  actions: {
    fetchPrices({ commit, dispatch }) {
      return api.fetchPrices()
        .then((response) => {
          commit('SET_PRICES', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    addClinic({ dispatch }, clinic) {
      return api.addClinic(clinic)
        .then(() => {
          const message = 'Clinic added successfuly!';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getClinicNames({ commit, dispatch }) {
      return api.getClinicNames()
        .then((response) => {
          commit('SET_CLINIC_NAMES', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchClinics({ commit, dispatch, state }) {
      return api.fetchClinics(state.searchParams.appointmentTypeId, state.searchParams.date)
        .then((response) => {
          commit('SET_CLINICS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    setSearchParams({ commit }, params) {
      commit('SET_SEARCH_PARAMS', params);
    },
  },
};
