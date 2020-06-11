import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinicsForReview: [],
    doctorsForReview: [],
  },
  mutations: {
    SET_CLINICS_FOR_REVIEW(state, clinics) {
      state.clinicsForReview = clinics;
    },
    SET_DOCTORS_FOR_REVIEW(state, doctors) {
      state.doctorsForReview = doctors;
    },
    REMOVE_CLINIC_FOR_REVIEW(state, clinicId) {
      const idx = state.clinicsForReview.findIndex((clinic) => clinic.id === clinicId);
      state.clinicsForReview.splice(idx, 1);
    },
    REMOVE_DOCTOR_FOR_REVIEW(state, doctorId) {
      const idx = state.clinicsForReview.findIndex((doctor) => doctor.id === doctorId);
      state.doctorsForReview.splice(idx, 1);
    },
  },
  actions: {
    fetchClinicsForReview({ commit, dispatch }) {
      return api.fetchClinicsForReview()
        .then((response) => {
          commit('SET_CLINICS_FOR_REVIEW', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchDoctorsForReview({ commit, dispatch }) {
      return api.fetchDoctorsForReview()
        .then((response) => {
          commit('SET_DOCTORS_FOR_REVIEW', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    addClinicReview({ dispatch, commit }, payload) {
      return api.addClinicReview(payload)
        .then(() => {
          const message = 'Clinic review added successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
          commit('REMOVE_CLINIC_FOR_REVIEW', payload.id);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
          throw err;
        });
    },
    addDoctorReview({ dispatch, commit }, payload) {
      return api.addDoctorReview(payload)
        .then(() => {
          const message = 'Doctor review added successfully.';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
          commit('REMOVE_DOCTOR_FOR_REVIEW', payload.id);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
          throw err;
        });
    },
  },
};
