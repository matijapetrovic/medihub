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
      <v-toolbar-title>Register</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-form
        ref="form"
      >
        <EmailInput
          v-model="email"
        />
        <PasswordInput
          v-model="password"
        />

        <v-text-field
          v-model="firstName"
          label="First Name"
          name="firstName"
          prepend-icon="person"
          type="text"
          required
        ></v-text-field>

        <v-text-field
          v-model="lastName"
          label="Last Name"
          name="lastName"
          prepend-icon="person"
          type="text"
          required
        ></v-text-field>

        <v-text-field
          v-model="address"
          label="Address"
          name="address"
          prepend-icon="house"
          type="text"
          required
        ></v-text-field>

        <v-text-field
          v-model="city"
          label="City"
          name="city"
          prepend-icon="mdi-city"
          type="text"
          required
        ></v-text-field>

        <v-text-field
          v-model="country"
          label="Country"
          name="country"
          prepend-icon="mdi-earth"
          type="text"
          required
        ></v-text-field>

        <v-text-field
          v-model="telephoneNum"
          label="Tel. Number"
          name="telephoneNum"
          prepend-icon="phone"
          type="text"
          required
        ></v-text-field>

        <v-text-field
          v-model="securityNum"
          label="Soc. Security Number"
          name="securityNum"
          prepend-icon="mdi-shield-account"
          type="text"
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
        Register
      </v-btn>
    </v-card-actions>
    </v-card>
  </v-col>
</template>

<script>
import { mapActions } from 'vuex';
import EmailInput from '@/app/shared/_components/_forms/EmailInput.vue';
import PasswordInput from '@/app/shared/_components/_forms/PasswordInput.vue';

export default {
  name: 'PatientRegisterForm',
  components: {
    EmailInput,
    PasswordInput,
  },
  data: () => ({
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    country: '',
    telephoneNum: '',
    securityNum: '',
    error: null,
  }),
  methods: {
    ...mapActions('auth', ['register']),
    submit() {
      if (this.validate()) {
        this.register({
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,
          address: this.address,
          city: this.city,
          country: this.country,
          telephoneNum: this.telephoneNum,
          securityNum: this.securityNum,
        })
          .then(() => {
            this.$router.push('/')
              .catch(() => {}); // https://github.com/vuejs/vue-router/issues/2881#issuecomment-520554378
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
