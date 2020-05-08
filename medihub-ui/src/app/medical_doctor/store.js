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
  },
  actions: {
    addMedicalDoctor({ commit }, payload) {
      return api.addMedicalDoctor(payload)
        .then(() => {
          commit('SET_NAME', payload);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getAllDoctors({ commit }) {
      return api.getAllDoctors()
        .then((data) => {
          commit('SET_DOCTORS', data.data);
        });
    },
  },
  getters: {
  },
};
