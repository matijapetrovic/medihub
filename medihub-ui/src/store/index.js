import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

function logError(error) {
  if (error.response) {
    console.log(error.response.data);
    console.log(error.response.status);
    console.log(error.response.headers);
  } else if (error.request) {
    console.log(error.request);
  } else {
    console.log('Error', error.message);
  }
}

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
      const response = await axios.get('http://localhost:8081/');
      commit('setMessage', response.data);
    },
    async login({ commit }, user) {
      const response = await axios.post('http://localhost:8081/api/login',
        {
          username: user.username,
          password: user.password,
        }).catch((error) => {
        logError(error);
        return false;
      });
      commit('setLoggedIn', user);
      return response.data;
    },
  },
  modules: {
  },
});
