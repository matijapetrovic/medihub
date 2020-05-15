import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    clinicRooms: [],
  },
  mutations: {
    SET_CLINIC_ROOMS(state, clinicRooms) {
      state.clinicRooms = clinicRooms;
    },
    DELETE_CLINIC_ROOM(state, clinicRoom) {
      const idx = state.clinicRooms.indexOf(clinicRoom);
      state.clinicRooms.splice(idx, 1);
    },
  },
  actions: {
    addClinicRoom({ dispatch }, payload) {
      return api.addClinicRoom(payload)
        .then(() => {
          const message = 'Clinic room added successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    deleteClinicRoom({ commit, dispatch }, clinicRoom) {
      return api.deleteClinicRoom(clinicRoom.id)
        .then(() => {
          commit('DELETE_CLINIC_ROOM', clinicRoom);
          const message = 'Clinic room deleted successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    fetchClinicRooms({ commit, dispatch }, payload) {
      return api.fetchClinicRooms(payload)
        .then((response) => {
          commit('SET_CLINIC_ROOMS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
