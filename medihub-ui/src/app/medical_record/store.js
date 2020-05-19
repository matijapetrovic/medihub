import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    medicalRecord: {},
  },
  mutations: {
    SET_MEDICAL_RECORD(state, medicalRecord) {
      state.medicalRecord = medicalRecord;
    },
  },
  actions: {
    fetchMedicalRecord({ commit, dispatch }) {
      return api.fetchMedicalRecord()
        .then((response) => {
          commit('SET_MEDICAL_RECORD', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
