import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
  created() {
    const userString = localStorage.getItem('user');
    if (userString) {
      const userData = JSON.parse(userString);
      this.$store.commit('auth/SET_USER_DATA', userData);
    }
  },
}).$mount('#app');
