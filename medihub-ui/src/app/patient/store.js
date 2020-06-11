import api from './api';

export default {
  namespaced: true,
  state: {
    patients: [],
    patient: null,
    patientId: null,
  },
  mutations: {
    SET_PATIENTS(state, patients) {
      state.patients = patients;
    },
    SET_PATIENT(state, patient) {
      state.patient = patient;
    },
    SET_PATIENT_ID(state, patientId) {
      state.patientId = patientId;
    },
  },
  actions: {
    getAllPatients({ commit }) {
      return api.getAllPatients()
        .then((data) => {
          commit('SET_PATIENTS', data.data);
        });
    },
    getPatientById({ commit }, id) {
      return api.getPatientById(id)
        .then((data) => {
          commit('SET_PATIENT', data.data);
        });
    },
    setPatientId({ commit }, patientId) {
      commit('SET_PATIENT_ID', patientId);
    },
  },
  getters: {
  },
};
