import api from './api';

export default {
  namespaced: true,
  state: {
    patients: null,
  },
  mutations: {
    SET_PATIENTS(state, patients) {
      state.patients = patients;
    },
  },
  actions: {
    getAllPatients({ commit }) {
      commit('SET_PATIENTS', api.getAllPatients());
      return api.getAllPatients();
    },
  },
  getters: {
  },
};
