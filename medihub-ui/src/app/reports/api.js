import utils from '@/utils';

export default {
  fetchReports(payload) {
    return utils.apiClient.get(`/api/reports/?type=${payload.type}&date=${payload.date}`);
  },
};
