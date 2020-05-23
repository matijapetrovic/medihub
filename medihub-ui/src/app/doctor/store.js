import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    doctors: [],
    availableTimes: [],
  },
  mutations: {
    SET_DOCTORS(state, doctors) {
      state.doctors = doctors;
    },
    SET_AVAILABLE_TIMES(state, availableTimes) {
      const doctor = state.doctors.find((el) => el.id === availableTimes.doctorId);
      doctor.availableTimes = availableTimes.times;
    },
    SET_STATE_AVAILABLE_TIMES(state, availableTimes) {
      state.availableTimes = availableTimes.times;
    },
  },
  actions: {
    fetchDoctors({ commit, dispatch, rootState }, clinicId) {
      return api.fetchDoctors(
        clinicId,
        rootState.clinic.searchParams.date,
        rootState.clinic.searchParams.appointmentTypeId,
      ).then((response) => {
        commit('SET_DOCTORS', response.data.map((doctor) => ({
          availableTimes: [],
          ...doctor,
        })));
      }).catch((err) => {
        dispatch('notifications/add', utils.errorNotification(err), { root: true });
      });
    },
    fetchAvailableTimes({ commit, dispatch, rootState }, doctorId) {
      return api.fetchAvailableTimes(
        doctorId,
        rootState.clinic.searchParams.date,
      ).then((response) => {
        commit('SET_AVAILABLE_TIMES', { doctorId, times: response.data });
      }).catch((err) => {
        dispatch('notifications/add', utils.errorNotification(err), { root: true });
      });
    },
    fetchAvailableTimesWithoutState({ commit, dispatch }, payload) {
      return api.fetchAvailableTimesWithoutState(
        payload.doctorId,
        payload.date,
      ).then((response) => {
        commit('SET_STATE_AVAILABLE_TIMES', { doctorId: payload.doctorId, times: response.data });
      }).catch((err) => {
        dispatch('notifications/add', utils.errorNotification(err), { root: true });
      });
    },
  },
};
