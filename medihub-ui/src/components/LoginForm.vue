<template>
  <v-col
    cols="12"
    sm="8"
    md="4"
  >
    <p>{{ error }}</p>
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
            v-model="email"
            label="Email"
            name="email"
            prepend-icon="mail"
            type="text"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            label="Password"
            name="password"
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
          Login
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-col>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'LoginForm',
  data: () => ({
    email: '',
    password: '',
    error: null,
  }),
  methods: {
    ...mapActions('auth', ['login']),
    submit() {
      if (this.validate()) {
        this.login({
          email: this.email,
          password: this.password,
        })
          .then(() => {
            this.$router.push('/');
          })
          .catch((err) => {
            this.error = err.response.data.error;
          });
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
};
</script>

<style lang="scss" scoped>
p {
  color: red;
}
</style>
