import api from './api';

export default {
  namespaced: true,
  state: {
    profile: {
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      country: '',
      telephoneNum: '',
    },
  },
  mutations: {
    SET_PROFILE(state, profile) {
      state.profile = profile;
    },
  },
  actions: {
    updateProfile({ state }) {
      return api
        .updateProfile(state.profile)
        .then(() => {
        })
        .catch((err) => {
          console.log(err);
        });
    },
    fetchProfile({ commit }) {
      return api
        .fetchProfile()
        .then((data) => {
          commit('SET_PROFILE', data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
