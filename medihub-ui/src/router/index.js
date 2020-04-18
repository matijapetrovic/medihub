import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue';
import Login from '@/views/unregistered/Login.vue';
import ChangePassword from '@/views/shared/ChangePassword.vue';

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
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/change-password',
    name: 'ChangePassword',
    component: ChangePassword,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/clinic_admin_registration',
    name: 'ClinicAdminRegistration',
    component: () => import('../views/clinic-center-admin/ClinicAdminRegistration.vue'),
    meta: {
      requiresAuth: true,
    },
  },
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
      if (shouldUserChangePassword(user) && !to.matched.some((record) => record.path === '/change-password')) {
        next('/change-password');
      } else {
        next(); // ovo ce se menjati kad u pricu udje autorizacija
      }
    }
  } else if (loggedIn) {
    next('/');
  } else {
    next();
  }
});

export default router;
