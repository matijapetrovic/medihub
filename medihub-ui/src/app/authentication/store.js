import api from './api';

export default {
  namespaced: true,
  state: {
    user: null,
    success: false,
    account: null,
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      state.user = userData;
      localStorage.setItem('user', JSON.stringify(userData));
      api.setToken(userData.token);
    },
    CLEAR_USER_DATA() {
      localStorage.removeItem('user');
      window.location.reload();
    },
    SET_PASSWORD_CHANGED(state) {
      state.user.passwordChanged = true;
      localStorage.setItem('user', JSON.stringify(state.user));
    },
    SET_REGISTER_SUCCESS(state, success) {
      state.success = success;
    },
  },
  actions: {
    register({ commit }, payload) {
      return api.register(payload)
        .then(() => {
          commit('SET_REGISTER_SUCCESS', true);
        })
        .catch((err) => {
          console.log(err);
          commit('SET_REGISTER_SUCCESS', false);
        });
    },
    login({ commit }, credentials) {
      return api.login(credentials)
        .then(({ data }) => {
          commit('SET_USER_DATA', data);
        });
    },
    logout({ commit }) {
      commit('CLEAR_USER_DATA');
    },
    changePassword({ commit }, credentials) {
      return api
        .changePassword(credentials)
        .then(() => {
          commit('SET_PASSWORD_CHANGED');
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {
    loggedIn(state) {
      return !!state.user;
    },
  },
};
