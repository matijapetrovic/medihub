import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import auth from '@/store/modules/auth';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    message: '',
    loggedIn: null,
  },
  getters: {
    getMessage: (state) => state.message,
  },
  mutations: {
    setMessage: (state, message) => { state.message = message; },
    setLoggedIn: (state, user) => { state.loggedIn = user; },
  },
  actions: {
    async test({ commit }) {
      const response = await axios.get('http://localhost:8081/api/hello');
      commit('setMessage', response.data);
    },
  },
  modules: {
    auth,
  },
});
