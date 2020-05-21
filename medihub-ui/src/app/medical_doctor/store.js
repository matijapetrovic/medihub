import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    name: null,
    doctors: [],
    workingCalendar: null,
  },
  mutations: {
    SET_NAME(state, name) {
      state.name = name;
    },
    SET_DOCTORS(state, doctors) {
      state.doctors = doctors;
    },
    SET_WORKING_CALENDAR(state, workingCalendar) {
      state.workingCalendar = workingCalendar;
    },
  },
  actions: {
    addMedicalDoctor({ dispatch }, payload) {
      return api.addMedicalDoctor(payload)
        .then(() => {
          const message = 'Added doctor successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getAllDoctors({ commit }) {
      return api.getAllDoctors()
        .then((data) => {
          commit('SET_DOCTORS', data.data);
        });
    },
    getWorkindCalendar({ commit, dispatch }) {
      return api.getWorkindCalendar()
        .then((response) => {
          commit('SET_WORKING_CALENDAR', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
  getters: {
  },
};
