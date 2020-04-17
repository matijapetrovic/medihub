<template>
  <div class="login fill-height">
    <v-container
      fluid
      class="fill-height"
    >
      <v-row
        align="center"
        justify="center"
      >
        <LoginForm/>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue';

export default {
  name: 'Login',
  components: {
    LoginForm,
  },
};
</script>
