import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import auth from '@/store/modules/auth';
import ApiClient from '@/services/ApiClient';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    message: '',
    loggedIn: null,
  },
  getters: {
    getMessage: (state) => state.message,
    getClinicAdminReg: (state) => state.clinic_admin,
  },
  mutations: {
    setMessage: (state, message) => { state.message = message; },
    setLoggedIn: (state, user) => { state.loggedIn = user; },
  },
  actions: {
    async test({ commit }) {
      const response = await ApiClient.get('/api/hello');
      commit('setMessage', response.data);
    },
    async registerClinicAdmin({ commit }, admin) {
      const response = await axios.get('http://localhost:8081/clinicAdmin/add',
        {
          username: admin.username,
          password: admin.password,
          firstname: admin.firstname,
          secondname: admin.secondname,
        });
      commit('setClinicAdmin', response.data);
      alert(response.data);
    },
  },
  modules: {
    auth,
  },
});
