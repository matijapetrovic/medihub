import api from './api';

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    addClinicRoom(payload) {
      return api.addClinicRoom(payload)
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {
  },
};
