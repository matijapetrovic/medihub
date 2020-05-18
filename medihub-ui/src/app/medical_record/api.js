import utils from '@/utils';

export default {
  fetchMedicalRecord() {
    return utils.apiClient.get('api/medical_record');
  },
};
