<template>
  <v-col
    cols="12"
    sm="8"
    md="4"
  >
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
            v-model="username"
            label="Username"
            name="username"
            prepend-icon="person"
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
    username: '',
    password: '',
  }),
  methods: {
    ...mapActions(['login']),
    async submit() {
      if (this.validate()) {
        const success = await this.login({ username: this.username, password: this.password });
        if (success) {
          alert('Login successful!');
        }
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
};
</script>
