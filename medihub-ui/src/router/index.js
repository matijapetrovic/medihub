import Vue from 'vue';
import VueRouter from 'vue-router';
import utils from '@/utils';
import Home from '@/views/Home.vue';

import clinicAdmin from './routes/clinic-admin';
import clinicCenterAdmin from './routes/clinic-center-admin';
import unregisteredUser from './routes/unregistered';
import registeredUser from './routes/user';
import medicalDoctor from './routes/medical_doctor';
import patient from './routes/patient';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      requiresAuth: true,
    },
  },
  clinicAdmin,
  clinicCenterAdmin,
  unregisteredUser,
  registeredUser,
  medicalDoctor,
  patient,
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  function shouldUserChangePassword(user) {
    if (!user) {
      return false;
    }
    return !user.passwordChanged
    && !user.role.includes('ROLE_PATIENT');
  }

  const loggedIn = localStorage.getItem('user');
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (!loggedIn) {
      next('/login');
    } else {
      const user = JSON.parse(loggedIn);
      console.log(user);
      if (shouldUserChangePassword(user) && !to.matched.some((record) => record.path === '/change-password')) {
        next('/change-password');
      } else if (to.matched.some((record) => record.path === '')) {
        next(utils.roleRootPath(user.role));
      } else if (to.matched.some((record) => record.meta.role
                                              && !user.role.includes(record.meta.role))) {
        next(utils.roleRootPath(user.role));
      } else {
        next();
      }
    }
  } else if (loggedIn) {
    next('/');
  } else {
    next();
  }
});

export default router;
