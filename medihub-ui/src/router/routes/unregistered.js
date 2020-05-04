import UnregisteredUser from '@/views/unregistered/UnregisteredUser.vue';
import Register from '@/views/unregistered/Register.vue';
import Login from '@/views/unregistered/Login.vue';

export default {
  path: '/unregistered',
  name: 'unregistered',
  component: UnregisteredUser,
  meta: {
    requiresAuth: false,
  },
  children: [
    {
      path: '/register',
      component: Register,
    },
    {
      path: '/login',
      component: Login,
    },
  ],
};
