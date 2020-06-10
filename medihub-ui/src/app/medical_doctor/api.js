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
  getWorkindCalendar() {
    return utils.apiClient.get('api/medical-doctor/schedule');
  },
  getWorkindCalendarByDoctorId(id) {
    return utils.apiClient.get(`api/medical-doctor/schedule/:${id}`);
  },
  addLeaveRequest(credentials) {
    return utils.apiClient.post('api/medical-doctor/addLeaveRequest', credentials);
  },
  finishAppointment(appointment) {
    return utils.apiClient.post('api/finished_appointment/add', appointment);
  },
  hasMedicalRecordPermission(request) {
    return utils.apiClient.get(`api/medical-doctor/medical-record-permission/${request.doctorId}/${request.patientId}`);
  },
  getAppointmentScheduleItem(appointmentId) {
    return utils.apiClient.get(`api/medical-doctor/getScheduleItem/${appointmentId}`);
  },
};
