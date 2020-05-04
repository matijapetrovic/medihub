import RegisteredUser from '@/views/user/RegisteredUser.vue';
import UpdateProfileForm from '@/app/profile/_components/UpdateProfileForm.vue';
import ChangePassword from '@/views/shared/ChangePassword.vue';

export default {
  path: '/user',
  component: RegisteredUser,
  children: [
    {
      path: '/profile',
      name: 'Profile',
      component: UpdateProfileForm,
      meta: {
        requiresAuth: true,
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
  ],
};
