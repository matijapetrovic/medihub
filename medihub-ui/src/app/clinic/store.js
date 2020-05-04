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
    addClinic({ commit }, clinic) {
      return api.addClinic(clinic)
        .then(() => {
          commit('SET_NAME', clinic.name);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
