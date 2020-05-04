import api from './api';

export default {
  namespaced: true,
  actions: {
    registerClinicAdmin({ commit }, admin) {
      return api.addClinicAdmin(admin);
    },
  },
};
