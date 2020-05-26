import utils from '@/utils';

export default {
  addLeaveRequest(credentials) {
    return utils.apiClient.post('api/leave-request/add', credentials);
  },
  getAllLeaveRequests() {
    return utils.apiClient.get('api/leave-request');
  },
  deleteLeaveRequest(id) {
    return utils.apiClient.post('api/leave-request/delete', id);
  },
  approveLeaveRequest(credentials) {
    return utils.apiClient.post('api/leave-request/approve', credentials);
  },
};
