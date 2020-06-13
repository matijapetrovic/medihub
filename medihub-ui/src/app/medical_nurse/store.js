import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  data: () => ({
    workingCalendar: {},
  }),
  mutations: {
    SET_WORKING_CALENDAR(state, workingCalendar) {
      state.workingCalendar = workingCalendar;
    },
  },
  actions: {
    getNurseWorkingCalendar({ commit, dispatch }, id) {
      return api.getNurseWorkingCalendar(id)
        .then((response) => {
          commit('SET_WORKING_CALENDAR', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
