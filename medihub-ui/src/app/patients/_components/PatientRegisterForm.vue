<template>
  <v-col
    cols="12"
    sm="8"
    md="4"
  >
  <p :class="{ success: !error, failure: error }">{{ message }}</p>
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
      <v-row>
        <v-col>
          <EmailInput
            v-model="email"
          />
        </v-col>
        <v-col>
          <InsuranceNumberInput
            v-model="insuranceNum"
          />
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <PasswordInput
            v-model="password"
          />
        </v-col>
        <v-col>
          <v-text-field
            v-model="confirmPassword"
            label="Confirm Password"
            type="password"
            prepend-icon="lock"
            :rules="[passwordConfirmRule,]"
            required
          >
          </v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            v-model="firstName"
            label="First Name"
            name="firstName"
            prepend-icon="person"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            v-model="lastName"
            label="Last Name"
            name="lastName"
            prepend-icon="person"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            v-model="address"
            label="Address"
            name="address"
            prepend-icon="house"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            v-model="city"
            label="City"
            name="city"
            prepend-icon="mdi-city"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            v-model="country"
            label="Country"
            name="country"
            prepend-icon="mdi-earth"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            v-model="telephoneNum"
            label="Tel. Number"
            name="telephoneNum"
            prepend-icon="phone"
            :rules="[requiredRule, telephoneNumRule]"
            type="text"
            required
          ></v-text-field>
        </v-col>
      </v-row>
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
      <v-spacer></v-spacer>
    </v-card-actions>
    </v-card>
  </v-col>
</template>

<script>
import { mapActions } from 'vuex';
import EmailInput from '@/app/shared/_components/_forms/EmailInput.vue';
import PasswordInput from '@/app/shared/_components/_forms/PasswordInput.vue';
import InsuranceNumberInput from '@/app/shared/_components/_forms/InsuranceNumberInput.vue';

export default {
  name: 'PatientRegisterForm',
  components: {
    EmailInput,
    PasswordInput,
    InsuranceNumberInput,
  },
  data: () => ({
    email: '',
    insuranceNum: '',
    password: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    country: '',
    telephoneNum: '',
    message: null,
    error: false,
  }),
  methods: {
    ...mapActions('auth', ['register']),
    submit() {
      if (this.validate()) {
        this.register({
          email: this.email,
          password: this.password,
          insuranceNum: this.insuranceNum,
          firstName: this.firstName,
          lastName: this.lastName,
          address: this.address,
          city: this.city,
          country: this.country,
          telephoneNum: this.telephoneNum,
        })
          .then(() => {
            this.error = false;
            this.message = 'Registration request sent successfully.';
          })
          .catch((err) => {
            this.error = true;
            this.message = err.response.data.message;
          });
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  computed: {
    passwordConfirmRule() {
      return () => this.password === this.confirmPassword || 'Passwords must match';
    },
    telephoneNumRule() {
      return (value) => /((\+381)|0)6[0-9]{7,8}/.test(value) || 'Telephone number must be valid';
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>

<style lang="scss">
p {
  .success{
    color: green;
  }
  .failure {
    color: red;
  }
}
</style>
