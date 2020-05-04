import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import auth from '@/app/authentication/store';
import notifications from '@/app/_notifications/store';
import profile from '@/app/profile/store';
import clinicRooms from '@/app/clinic_room/store';
import medicalDoctor from '@/app/medical_doctor/store';
import appointmentType from '@/app/appointment_type/store';
import patient from '@/app/patient/store';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedIn: null,
  },
  getters: {
    getClinicAdminReg: (state) => state.clinic_admin,
  },
  mutations: {
    setLoggedIn: (state, user) => { state.loggedIn = user; },
  },
  actions: {
    async registerClinicAdmin({ commit }, admin) {
      const response = await axios.get('http://localhost:8081/clinicAdmin/add',
        {
          username: admin.username,
          password: admin.password,
          firstname: admin.firstname,
          secondname: admin.secondname,
        });
      commit('setClinicAdmin', response.data);
    },
  },
  modules: {
    auth,
    notifications,
    clinicRooms,
    medicalDoctor,
    appointmentType,
    patient,
    profile,
  },
});
