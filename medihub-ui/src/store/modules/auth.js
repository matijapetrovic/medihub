import axios from 'axios';

export default {
  namespaced: true,
  state: {
    user: null,
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      state.user = userData;
      localStorage.setItem('user', JSON.stringify(userData));
      axios.defaults.headers.common.Authorization = `Bearer ${userData.token}`;
    },
    CLEAR_USER_DATA() {
      localStorage.removeItem('user');
      window.location.reload();
    },
  },
  actions: {
    login({ commit }, credentials) {
      return axios
        .post('http://localhost:8081/auth/login', credentials)
        .then(({ data }) => {
          commit('SET_USER_DATA', data);
        });
    },
    logout({ commit }) {
      commit('CLEAR_USER_DATA');
    },
  },
  getters: {
    loggedIn(state) {
      return !!state.user;
    },
  },
};
