import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    doctors: [],
  },
  mutations: {
    SET_DOCTORS(state, doctors) {
      state.doctors = doctors;
    },
  },
  actions: {
    fetchDoctors({ commit, dispatch, rootState }, clinicId) {
      return api.fetchDoctors(
        clinicId,
        rootState.clinic.searchParams.date,
        rootState.clinic.searchParams.appointmentTypeId,
      ).then((response) => {
        commit('SET_DOCTORS', response.data);
      }).catch((err) => {
        dispatch('notifications/add', utils.errorNotification(err), { root: true });
      });
    },
  },
};
