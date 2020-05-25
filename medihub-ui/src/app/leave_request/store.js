import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    leaveRequests: [],
  },
  mutations: {
    SET_LEAVE_REQUESTS(state, leaveRequests) {
      state.leaveRequests = leaveRequests;
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
    getAllLeaveRequests({ commit }) {
      return api.getAllLeaveRequests()
        .then((data) => {
          commit('SET_LEAVE_REQUESTS', data.data);
        });
    },
    deleteLeaveRequest({ dispatch }, id) {
      return api.deleteLeaveRequest(id)
        .then(() => {
          const message = 'Leave request deleted successfully';
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
  },
};
