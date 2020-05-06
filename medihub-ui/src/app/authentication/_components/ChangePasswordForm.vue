<template>
  <div>
    <v-card class="elevation-12">
      <v-toolbar
        color="primary"
        dark
        flat
      >
        <v-toolbar-title>Change password</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form
          ref="form"
        >
          <v-text-field
            v-model="oldPassword"
            label="Old password"
            name="oldPassword"
            prepend-icon="lock"
            type="password"
            :rules="[requiredRule]"
            required
          ></v-text-field>

          <PasswordInput
              v-model="newPassword"
            />
          <v-text-field
              v-model="confirmNewPassword"
              label="Confirm Password"
              type="password"
              prepend-icon="lock"
              :rules="[passwordConfirmRule,]"
              required
            >
            </v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          @click="submit"
          color="primary"
          name="button"
        >
          Change
        </v-btn>
        <v-spacer></v-spacer>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import PasswordInput from '@/app/shared/_components/_forms/PasswordInput.vue';

export default {
  name: 'ChangePasswordForm',
  components: {
    PasswordInput,
  },
  data: () => ({
    oldPassword: '',
    newPassword: '',
    confirmNewPassword: '',
  }),
  methods: {
    ...mapActions('auth', ['changePassword']),
    submit() {
      if (this.validate()) {
        this.changePassword({
          oldPassword: this.oldPassword,
          newPassword: this.newPassword,
        }).then(() => {
          this.clear();
        });
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
    clear() {
      this.$refs.form.reset();
    },
  },
  computed: {
    passwordConfirmRule() {
      return () => this.newPassword === this.confirmNewPassword || 'Passwords must match';
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
