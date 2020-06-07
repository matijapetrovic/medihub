import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    medicalRecord: {},
    bloodTypes: {},
  },
  mutations: {
    SET_MEDICAL_RECORD(state, medicalRecord) {
      state.medicalRecord = medicalRecord;
    },
    SET_BLOOD_TYPES(state, bloodTypes) {
      state.bloodTypes = bloodTypes;
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
    getBloodTypes({ commit, dispatch }) {
      return api.getBloodTypes()
        .then((response) => {
          commit('SET_BLOOD_TYPES', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
