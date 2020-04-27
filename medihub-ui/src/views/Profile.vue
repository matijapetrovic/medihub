<template>
  <BaseLayoutCentered>
    <v-col md="4">
      <UpdateProfileForm/>
    </v-col>
    <v-col md="4">
      <ChangePasswordForm/>
    </v-col>
  </BaseLayoutCentered>
</template>

<script>
import BaseLayoutCentered from '@/app/shared/_components/_layouts/BaseLayoutCentered.vue';
import ChangePasswordForm from '@/app/authentication/_components/ChangePasswordForm.vue';
import UpdateProfileForm from '@/app/profile/_components/UpdateProfileForm.vue';

export default {
  name: 'Profile',
  components: {
    BaseLayoutCentered,
    ChangePasswordForm,
    UpdateProfileForm,
  },
};
</script>
