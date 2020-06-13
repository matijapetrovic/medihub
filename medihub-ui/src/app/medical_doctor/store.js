import utils from '@/utils';
import api from './api';

export default {
  namespaced: true,
  state: {
    name: null,
    doctors: [],
    workingCalendar: null,
    newRecordItem: null,
    scheduleItem: null,
    previousPatients: [],
  },
  mutations: {
    SET_NAME(state, name) {
      state.name = name;
    },
    SET_DOCTORS(state, doctors) {
      state.doctors = doctors;
    },
    SET_WORKING_CALENDAR(state, workingCalendar) {
      state.workingCalendar = workingCalendar;
    },
    SET_NEW_RECORD_ITEM(state, newRecordItem) {
      state.newRecordItem = newRecordItem;
    },
    SET_SCHEDULE_APPOINTMENT_ITEM(state, scheduleItem) {
      state.scheduleItem = scheduleItem;
    },
    UPDATE_WORKING_CALENDAR(state, update) {
      const index = state.workingCalendar
        .dailySchedules[update.date]
        .scheduleItems
        .findIndex((element) => element.id === update.id);
      state.workingCalendar
        .dailySchedules[update.date]
        .scheduleItems.splice(index, 1);
    },
    SET_PREVIOUS_PATIENTS(state, previousPatients) {
      state.previousPatients = previousPatients;
    },
    REMOVE_DOCTOR(state, doctorId) {
      const idx = state.doctors.findIndex((doctor) => doctor.id === doctorId);
      state.doctors.splice(idx, 1);
    },
  },
  actions: {
    getAppointmentScheduleItem({ commit }, id) {
      return api.getAppointmentScheduleItem(id)
        .then((data) => {
          commit('SET_SCHEDULE_APPOINTMENT_ITEM', data.data);
        });
    },
    addMedicalDoctor({ dispatch }, payload) {
      return api.addMedicalDoctor(payload)
        .then(() => {
          const message = 'Added doctor successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getAllDoctors({ commit }, date) {
      return api.getAllDoctors(date)
        .then((data) => {
          commit('SET_DOCTORS', data.data);
        });
    },
    getAllDoctorsForClinic({ commit }) {
      return api.getAllDoctorsForClinic()
        .then((data) => {
          commit('SET_DOCTORS', data.data);
        });
    },
    getWorkindCalendar({ commit, dispatch }) {
      return api.getWorkindCalendar()
        .then((response) => {
          commit('SET_WORKING_CALENDAR', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getWorkindCalendarByDoctorId({ commit, dispatch }, id) {
      return api.getWorkindCalendarByDoctorId(id)
        .then((response) => {
          commit('SET_WORKING_CALENDAR', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    finishAppointment({ commit, dispatch, state }, appointment) {
      return api.finishAppointment(appointment)
        .then((response) => {
          commit('SET_NEW_RECORD_ITEM', response.data);
          const calendarUpdate = { date: appointment.itemDate, id: appointment.itemId };
          if (state.workingCalendar) {
            commit('UPDATE_WORKING_CALENDAR', calendarUpdate);
          }
          const message = 'Appointment finished successfully';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    getPreviousPatients({ commit, dispatch }) {
      return api.getPreviousPatients()
        .then((response) => {
          commit('SET_PREVIOUS_PATIENTS', response.data);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
    setStateForPatientPage({ commit }, data) {
      commit('SET_PAGE_DATA', data);
    },
    deleteDoctor({ commit, dispatch }, doctorId) {
      return api.deleteDoctor(doctorId)
        .then(() => {
          const message = 'Doctor deleted successfull';
          dispatch('notifications/add', utils.successNotification(message), { root: true });
          commit('REMOVE_DOCTOR', doctorId);
        })
        .catch((err) => {
          dispatch('notifications/add', utils.errorNotification(err), { root: true });
        });
    },
  },
  getters: {
  },
};
