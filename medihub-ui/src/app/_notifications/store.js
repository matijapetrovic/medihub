export default {
  namespaced: true,
  state: {
    notificationActive: false,
    notification: {
      color: 'success',
    },
  },
  getters: {},
  mutations: {
    PUSH(state, notification) {
      state.notificationActive = false;
      setTimeout(() => {
        state.notification = notification;
        state.notificationActive = true;
      }, 250);
    },
    REMOVE(state) {
      state.notificationActive = false;
    },
    SET_ACTIVE(state, active) {
      state.notificationActive = active;
    },
  },
  actions: {
    add({ commit }, notification) {
      commit('PUSH', notification);
    },
    remove({ commit }) {
      commit('REMOVE');
    },
  },
};
