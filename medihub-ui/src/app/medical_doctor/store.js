import api from './api';

export default {
  namespaced: true,
  state: {
    name: null,
  },
  mutations: {
    SET_NAME(state, name) {
      state.name = name;
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
  },
  getters: {
  },
};
