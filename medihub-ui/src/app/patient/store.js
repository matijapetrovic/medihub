import api from './api';

export default {
  namespaced: true,
  state: {
    patients: [],
  },
  mutations: {
    SET_PATIENTS(state, patients) {
      state.patients = patients;
    },
  },
  actions: {
    getAllPatients({ commit }) {
      return api.getAllPatients()
        .then((data) => {
          commit('SET_PATIENTS', data.data);
        });
    },
  },
  getters: {
  },
};
