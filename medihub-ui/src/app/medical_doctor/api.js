import utils from '@/utils';

export default {
  addMedicalDoctor(credentials) {
    return utils.apiClient.post('api/medical-doctor/add', credentials);
  },
  getAllDoctors() {
    return utils.apiClient.get('api/medical-doctor/getAll');
  },
  getDoctorsForDateTime(payload) {
    return utils.apiClient.get(`api/medical-doctor/${payload.date}/${payload.time}`);
  },
};
