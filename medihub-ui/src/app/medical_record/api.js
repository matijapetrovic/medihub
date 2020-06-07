import utils from '@/utils';

export default {
  fetchMedicalRecord() {
    return utils.apiClient.get('api/medical_record');
  },
  getBloodTypes() {
    return utils.apiClient.get('api/medical_record/blood_types');
  },
};
