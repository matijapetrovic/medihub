import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue';
import Profile from '@/views/Profile.vue';
import Register from '@/views/unregistered/Register.vue';
import Login from '@/views/unregistered/Login.vue';
import ChangePassword from '@/views/shared/ChangePassword.vue';
import AddClinicRoomForm from '../app/clinic_room/components/AddClinicRoomForm.vue';
import AddMedicalDoctorForm from '../app/medical_doctor/components/AddMedicalDoctorForm.vue';
import AddAppointmentTypeForm from '../app/appointment_type/components/AddAppointmentTypeForm.vue';

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
    path: '/addAppointmentType',
    name: 'AddAppointmentType',
    component: AddAppointmentTypeForm,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/addMedicalDoctor',
    name: 'AddMedicalDoctorForm',
    component: AddMedicalDoctorForm,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/addClinicRoom',
    name: 'AddClinicRoomForm',
    component: AddClinicRoomForm,
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
    path: '/profile',
    name: 'Profile',
    component: Profile,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
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
