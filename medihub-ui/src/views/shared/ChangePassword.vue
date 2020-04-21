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
          <v-card class="elevation-12">
            <v-toolbar
              color="primary"
              dark
              flat
            >
              <v-toolbar-title>Log In</v-toolbar-title>
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
                  required
                ></v-text-field>

                <v-text-field
                  v-model="newPassword"
                  label="New password"
                  name="newPassword"
                  prepend-icon="lock"
                  type="password"
                  required
                ></v-text-field>

                <v-text-field
                  v-model="confirmNewPassword"
                  label="Confirm new password"
                  name="confirmNewPassword"
                  prepend-icon="lock"
                  type="password"
                  required
                ></v-text-field>
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
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'ChangePassword',
  data: () => ({
    oldPassword: '',
    newPassword: '',
    confirmNewPassword: '',
    error: null,
  }),
  methods: {
    ...mapActions('auth', ['changePassword']),
    submit() {
      if (this.validate()) {
        this.changePassword({
          oldPassword: this.oldPassword,
          newPassword: this.newPassword,
        }).then(() => {
          this.$router.push('/');
        })
          .catch((err) => {
            this.error = err.response.data.error;
          });
      }
    },
    validate() {
      if (!this.$refs.form.validate()) {
        return false;
      }
      return this.newPassword === this.confirmNewPassword;
    },
  },
};
</script>

<style lang="scss" scoped>
p.error {
  color: red;
}
</style>
