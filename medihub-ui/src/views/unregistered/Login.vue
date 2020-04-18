<template>
  <BaseLayoutCentered>
    <LoginForm/>
  </BaseLayoutCentered>
</template>

<script>
import LoginForm from '@/app/authentication/_components/LoginForm.vue';
import BaseLayoutCentered from '@/app/shared/_components/_layouts/BaseLayoutCentered.vue';

export default {
  name: 'Login',
  components: {
    LoginForm,
    BaseLayoutCentered,
  },
};
</script>
