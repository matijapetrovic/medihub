<template>
  <div class="change-password">
    <v-container
      fluid
      class="fill-height"
    >
      <v-row
        align="center"
        justify="center"
      >
        <v-col
          cols="12"
          sm="8"
          md="4"
        >
          <p>You must change your password after logging in for the first time</p>
          <p class="error">{{ error }}</p>
          <ChangePasswordForm/>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ChangePasswordForm from '@/app/authentication/_components/ChangePasswordForm.vue';

export default {
  name: 'ChangePassword',
  components: {
    ChangePasswordForm,
  },
};
</script>

<style lang="scss" scoped>
p.error {
  color: red;
}
</style>
