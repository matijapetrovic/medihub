import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinicRooms: [],
    params: null,
    workingCalendar: null,
    presentDoctors: [],
  },
  mutations: {
    SET_CLINIC_ROOMS(state, clinicRooms) {
      state.clinicRooms = clinicRooms;
    },
    DELETE_CLINIC_ROOM(state, clinicRoom) {
      const idx = state.clinicRooms.indexOf(clinicRoom);
      state.clinicRooms.splice(idx, 1);
    },
    SET_SEARCH_PARAMS(state, params) {
      state.params = params;
    },
    GET_SEARCH_PARAMS(state) {
      return state.params;
    },
    SET_WORKING_CALENDAR(state, workingCalendar) {
      state.workingCalendar = workingCalendar;
    },
    SET_PRESENT_DOCTORS(state, presentDoctors) {
      state.presentDoctors = presentDoctors;
    },
  },
  actions: {
    addClinicRoom({ dispatch }, payload) {
      return api.addClinicRoom(payload)
        .then(() => {
          const message = 'Clinic room added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    deleteClinicRoom({ dispatch }, id) {
      return api.deleteClinicRoom(id)
        .then(() => {
          const message = 'Clinic room deleted successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    updateClinicRoom({ commit, dispatch }, payload) {
      return api.updateClinicRoom(payload)
        .then((response) => {
          commit('SET_CLINIC_ROOMS', response.data);
          const message = 'Clinic room updated successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchClinicRooms({ commit, dispatch }, payload) {
      return api.fetchClinicRooms(payload)
        .then((response) => {
          commit('SET_CLINIC_ROOMS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getWorkindCalendarByClinicRoomId({ commit, dispatch }, id) {
      return api.getWorkindCalendarByClinicRoomId(id)
        .then((response) => {
          commit('SET_WORKING_CALENDAR', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    scheduleRoom({ dispatch }, payload) {
      return api.scheduleRoom(payload).then(() => {
        const message = 'Clinic room scheduled successfully';
        dispatch('notifications/add', utils.successNotification(message), { root: true });
      })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchAllClinicRooms({ commit, dispatch }) {
      return api.fetchAllClinicRooms()
        .then((response) => {
          commit('SET_CLINIC_ROOMS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    setSearchParams({ commit }, params) {
      commit('SET_SEARCH_PARAMS', params);
    },
    getSearchParams({ commit }) {
      commit('GET_SEARCH_PARAMS');
    },
    savePresentDoctors({ commit }, presentDoctors) {
      commit('SET_PRESENT_DOCTORS', presentDoctors);
    },
  },
};
