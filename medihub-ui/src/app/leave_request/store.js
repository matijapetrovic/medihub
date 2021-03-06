import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    leaveRequests: [],
    nurseLeaveRequests: [],
  },
  mutations: {
    SET_LEAVE_REQUESTS(state, leaveRequests) {
      state.leaveRequests = leaveRequests;
    },
    SET_NURSE_REQUESTS(state, nurseLeaveRequests) {
      state.nurseLeaveRequests = nurseLeaveRequests;
    },
  },
  actions: {
    addLeaveRequest({ dispatch }, payload) {
      return api.addLeaveRequest(payload)
        .then(() => {
          const message = 'Leave request sent successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    addNurseLeaveRequest({ dispatch }, payload) {
      return api.addNurseLeaveRequest(payload)
        .then(() => {
          const message = 'Leave request sent successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getAllLeaveRequests({ commit }) {
      return api.getAllLeaveRequests()
        .then((data) => {
          commit('SET_LEAVE_REQUESTS', data.data);
        });
    },
    deleteLeaveRequest({ dispatch }, payload) {
      return api.deleteLeaveRequest(payload.id, payload.reason)
        .then(() => {
          const message = 'Leave request rejected!';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    approveLeaveRequest({ dispatch }, payload) {
      return api.approveLeaveRequest(payload)
        .then(() => {
          const message = 'Request approved successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getNurseLeaveRequests({ commit, dispatch }) {
      return api.getNurseLeaveRequests()
        .then((response) => {
          commit('SET_NURSE_REQUESTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    deleteNurseLeaveRequest({ dispatch }, payload) {
      return api.deleteNurseLeaveRequest(payload)
        .then(() => {
          const message = 'Leave request deleted successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    approveNurseLeaveRequest({ dispatch }, payload) {
      return api.approveNurseLeaveRequest(payload)
        .then(() => {
          const message = 'Request approved successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
};
