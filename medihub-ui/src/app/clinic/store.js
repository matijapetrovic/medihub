import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinicNames: [],
    clinics: [],
  },
  mutations: {
    SET_CLINIC_NAMES(clinicNames) {
      this.clinicNames = clinicNames;
    },
    SET_CLINICS(state, clinics) {
      state.clinics = clinics;
    },
  },
  actions: {
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
    fetchClinics({ commit, dispatch }) {
      return api.fetchClinics()
        .then((response) => {
          commit('SET_CLINICS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
