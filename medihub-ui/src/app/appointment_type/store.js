import api from './api';

export default {
  namespaced: true,
  state: {
    price: null,
  },
  mutations: {
    SET_PRICE(state, price) {
      state.price = price;
    },
  },
  actions: {
    addAppointmentType({ commit }, payload) {
      return api.addAppointmentType(payload)
        .then(() => {
          commit('SET_PRICE', payload);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {
  },
};
