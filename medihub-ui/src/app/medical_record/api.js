import utils from '@/utils';

export default {
  fetchMedicalRecord() {
    return utils.apiClient.get('api/medical_record');
  },
  getBloodTypes() {
    return utils.apiClient.get('api/medical_record/blood_types');
  },
  getPatientMedicalRecord(patientId) {
    return utils.apiClient.get(`api/medical_record/${patientId}`);
  },
  changeMedicalRecord(medicalRecord) {
    return utils.apiClient.post('api/medical_record/change', medicalRecord);
  },
};
