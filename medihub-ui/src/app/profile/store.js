import utils from '@/utils';
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
    updateProfile({ state, dispatch }) {
      return api
        .updateProfile(state.profile)
        .then(() => {
          const message = 'Profile update successful';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchProfile({ commit, dispatch }) {
      return api
        .fetchProfile()
        .then((data) => {
          commit('SET_PROFILE', data.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
