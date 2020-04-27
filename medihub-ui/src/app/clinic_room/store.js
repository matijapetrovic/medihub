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
    addClinicRoom({ commit }, payload) {
      return api.addClinicRoom(payload)
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
