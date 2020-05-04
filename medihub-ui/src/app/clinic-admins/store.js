import api from './api';

export default {
  namespaced: true,
  state: {
    name: null,
  },
  mutations: {
    SET_NAME(state, admin) {
      state.name = admin.email;
    },
  },
  actions: {
    registerClinicAdmin({ commit }, admin) {
      return api.addClinicAdmin(admin)
        .then(() => {
          commit('SET_NAME', admin);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
