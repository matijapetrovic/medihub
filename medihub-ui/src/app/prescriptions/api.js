import utils from '@/utils';

export default {
  getPrescriptions() {
    return utils.apiClient.get('api/prescription');
  },
  acceptPrescription(id) {
    return utils.apiClient.post('api/prescription/accept', id);
  },
};
