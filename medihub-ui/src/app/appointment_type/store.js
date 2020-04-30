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
    addClinicRoom({ commit }, payload) {
      return api.addClinicRoom(payload)
        .then(() => {
          commit('SET_PRICE', payload);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {
    clearPrice(price) {
      return price = null;
    },
  },
};
