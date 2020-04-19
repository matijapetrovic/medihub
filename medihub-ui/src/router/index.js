import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '@/views/Login.vue';
import Home from '../views/Home.vue';
import ChangePassword from '../views/ChangePassword.vue';
import AddClinicRoomForm from '../views/AddClinicRoomForm.vue';

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
    path: '/addClinicRoom',
    name: 'AddClinicRoomForm',
    component: AddClinicRoomForm,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
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
    component: () => import('../views/ClinicAdminRegistration.vue'),
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
