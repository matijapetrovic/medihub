import Vue from 'vue';
import Vuex from 'vuex';
import auth from '@/app/authentication/store';
import notifications from '@/app/_notifications/store';
import profile from '@/app/profile/store';
import clinicRooms from '@/app/clinic_room/store';
import medicalDoctor from '@/app/medical_doctor/store';
import appointmentType from '@/app/appointment_type/store';
import clinicAdmin from '@/app/clinic-admins/store';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedIn: null,
  },
  mutations: {
    setLoggedIn: (state, user) => { state.loggedIn = user; },
  },
  modules: {
    auth,
    notifications,
    clinicRooms,
    medicalDoctor,
    appointmentType,
    profile,
    clinicAdmin,
  },
});
