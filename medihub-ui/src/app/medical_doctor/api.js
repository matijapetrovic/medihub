import utils from '@/utils';

export default {
  addMedicalDoctor(credentials) {
    return utils.apiClient.post('api/medical-doctor/add', credentials);
  },
  getAllDoctors() {
    return utils.apiClient.get('api/medical-doctor/getAll');
  },
  getWorkindCalendar() {
    return utils.apiClient.get('api/medical-doctor/schedule');
  },
};
