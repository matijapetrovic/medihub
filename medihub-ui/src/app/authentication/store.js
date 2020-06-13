import utils from '@/utils';
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
    register({ commit, dispatch }, payload) {
      return api.register(payload)
        .then(() => {
          commit('SET_REGISTER_SUCCESS', true);
          const message = 'Registration request successful';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          commit('SET_REGISTER_SUCCESS', false);
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    login({ commit, dispatch }, credentials) {
      return api.login(credentials)
        .then(({ data }) => {
          commit('SET_USER_DATA', data);
          const message = 'Log in successful';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          console.log(err.response);
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    logout({ commit, dispatch }) {
      commit('CLEAR_USER_DATA');
      const message = 'Log out successful';
      dispatch('notifications/add', utils.successNotification(message), { root: true });
    },
    changePassword({ commit, dispatch }, credentials) {
      return api
        .changePassword(credentials)
        .then(() => {
          commit('SET_PASSWORD_CHANGED');
          const message = 'Password change successful';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    activateAccount({ dispatch }, accountId) {
      return api.activateAccount(accountId)
        .then(() => {
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
          throw err;
        });
    },
  },
  getters: {
    loggedIn(state) {
      return !!state.user;
    },
    passwordChanged(state) {
      return state.user && state.user.passwordChanged;
    },
  },
};
