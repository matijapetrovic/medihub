import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    drugs: [],
  },
  mutations: {
    SET_DRUGS(state, drugs) {
      state.drugs = drugs;
    },
  },
  actions: {
    addDrug({ dispatch }, name) {
      return api.addDrug(name)
        .then(() => {
          const message = 'Drug added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getDrugs({ commit, dispatch }) {
      return api.getDrugs()
        .then((response) => {
          commit('SET_DRUGS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
