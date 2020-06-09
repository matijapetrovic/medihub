import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinicNames: [],
    clinics: [],
    searchParams: {},
    clinic: {},
    prices: null,
  },
  mutations: {
    SET_CLINIC_NAMES(state, clinicNames) {
      state.clinicNames = clinicNames;
    },
    SET_CLINICS(state, clinics) {
      state.clinics = clinics;
    },
    SET_CURRENT_CLINIC(state, clinic) {
      state.clinic = clinic;
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
    addPrice({ dispatch, commit }, payload) {
      return api.addPrice(payload)
        .then((response) => {
          commit('SET_PRICES', response.data);
          const message = 'Price added successfuly!';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
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
      return api.fetchClinics({
        appointmentTypeId: state.searchParams.appointmentType
          ? state.searchParams.appointmentType.id : null,
        date: state.searchParams.date ? state.searchParams.date : null,
      }).then((response) => {
        commit('SET_CLINICS', response.data);
      })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getCurrentClinic({ commit, dispatch }) {
      return api.getCurrentClinic()
        .then((response) => {
          commit('SET_CURRENT_CLINIC', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    updateClinic({ dispatch, commit }, clinic) {
      return api.updateClinic(clinic)
        .then(() => {
          commit('SET_SEARCH_PARAMS', clinic);
          const message = 'Clinic updated successfuly!';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    setClinicSearchParams({ commit }, params) {
      commit('SET_SEARCH_PARAMS', params);
    },
    fetchClinicProfile({ commit, dispatch }, clinicId) {
      return api.fetchClinicProfile(clinicId)
        .then((response) => {
          commit('SET_CURRENT_CLINIC', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
