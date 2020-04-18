import AuthService from '@/services/AuthService';

export default {
  namespaced: true,
  state: {
    user: null,
    success: false,
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      state.user = userData;
      localStorage.setItem('user', JSON.stringify(userData));
      AuthService.setToken(userData.token);
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
      return AuthService.register(payload)
        .then(() => {
          commit('SET_REGISTER_SUCCESS', true);
        })
        .catch((err) => {
          console.log(err);
          commit('SET_REGISTER_SUCCESS', false);
        });
    },
    login({ commit }, credentials) {
      return AuthService.login(credentials)
        .then(({ data }) => {
          commit('SET_USER_DATA', data);
        });
    },
    logout({ commit }) {
      commit('CLEAR_USER_DATA');
    },
    changePassword({ commit }, credentials) {
      return AuthService
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
