import utils from '@/utils';

export default {
  addLeaveRequest(credentials) {
    return utils.apiClient.post('api/leave-request/add', credentials);
  },
  getAllLeaveRequests() {
    return utils.apiClient.get('api/leave-request');
  },
  deleteLeaveRequest(id, reason) {
    return utils.apiClient.post(`api/leave-request/${id}/delete`, reason);
  },
  approveLeaveRequest(credentials) {
    return utils.apiClient.post('api/leave-request/approve', credentials);
  },
};
