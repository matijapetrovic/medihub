import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    medicalRecord: {},
    bloodTypes: {},
    patientRecord: {},
  },
  mutations: {
    SET_MEDICAL_RECORD(state, medicalRecord) {
      state.medicalRecord = medicalRecord;
    },
    SET_BLOOD_TYPES(state, bloodTypes) {
      state.bloodTypes = bloodTypes;
    },
    SET_PATEINET_RECORD(state, patientRecord) {
      state.patientRecord = patientRecord;
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
    getPatientMedicalRecord({ commit, dispatch }, patientId) {
      return api.getPatientMedicalRecord(patientId)
        .then((response) => {
          commit('SET_PATEINET_RECORD', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    changeMedicalRecord({ commit, dispatch }, medicalRecord) {
      return api.changeMedicalRecord(medicalRecord)
        .then(() => {
          commit('SET_PATEINET_RECORD', medicalRecord);
          const message = 'Medical record saved successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
