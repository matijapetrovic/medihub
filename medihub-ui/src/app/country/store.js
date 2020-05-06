import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    countries: [],
  },
  mutations: {
    SET_COUNTRIES(state, countries) {
      state.countries = countries;
    },
  },
  actions: {
    fetchCountries({ commit, dispatch }) {
      return api.fetchCountries()
        .then((response) => {
          commit('SET_COUNTRIES', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        })
    },
  },
};
