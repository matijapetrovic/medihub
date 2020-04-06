import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    message: '',
  },
  getters: {
    getMessage: (state) => state.message,
  },
  mutations: {
    setMessage: (state, message) => { state.message = message; },
  },
  actions: {
    async test({ commit }) {
      const response = await axios.get('http://localhost:8081/');
      commit('setMessage', response.data);
    },
  },
  modules: {
  },
});
