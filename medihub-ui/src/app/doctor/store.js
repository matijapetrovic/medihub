import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    doctors: [],
    availableTimes: [],
    searchParams: {},
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
    SET_SEARCH_PARAMS(state, params) {
      state.searchParams = params;
    },
  },
  actions: {
    fetchDoctors({ commit, dispatch, state }, clinicId) {
      return api.fetchDoctors({
        clinicId,
        date: state.searchParams.date ? state.searchParams.date : null,
        appointmentTypeId: state.searchParams.appointmentType
          ? state.searchParams.appointmentType.id : null,
      }).then((response) => {
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
    setDoctorSearchParams({ commit }, params) {
      commit('SET_SEARCH_PARAMS', params);
    },
  },
};
