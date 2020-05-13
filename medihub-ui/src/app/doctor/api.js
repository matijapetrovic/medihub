import utils from '@/utils';

export default {
  addMedicalDoctor(credentials) {
    return utils.apiClient.post('api/medical-doctor/add', credentials);
  },
  fetchDoctors(clinicId, date, appointmentTypeId) {
    return utils.apiClient.get(`api/medical-doctor/?clinicId=${clinicId}&date=${date}&appointmentTypeId=${appointmentTypeId}`);
  },
};
