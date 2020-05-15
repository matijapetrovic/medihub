import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    name: null,
    doctors: [],
  },
  mutations: {
    SET_NAME(state, name) {
      state.name = name;
    },
    SET_DOCTORS(state, doctors) {
      state.doctors = doctors;
    },
    SET_WORKING_CALENDAR(state, workindCalendar) {
      state.workindCalendar = workindCalendar;
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
    getWorkindCalendar({ commit }) {
      return api.getWorkindCalendar()
        .then((response) => {
          commit('SET_WORKING_CALENDAR', response.data);
        });
    },
  },
  getters: {
  },
};
