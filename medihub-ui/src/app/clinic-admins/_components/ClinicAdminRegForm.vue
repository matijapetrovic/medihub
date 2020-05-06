<template>
   <v-col
  >
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
          <v-select
            label="Clinic"
            prepend-icon="mdi-hospital-box-outline"
            v-model="clinic"
            :items="clinicNames"
            item-text="name"
            :rules="[requiredRule]"
            return-object
            ref="clinic">
          </v-select>
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
          <CountrySelect
            v-model="country"
          />
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
import { mapActions, mapState } from 'vuex';
import EmailInput from '@/app/shared/_components/_forms/EmailInput.vue';
import PasswordInput from '@/app/shared/_components/_forms/PasswordInput.vue';
import CountrySelect from '@/app/country/_components/CountrySelect.vue';

export default {
  name: 'ClinicAdminReg',
  components: {
    EmailInput,
    PasswordInput,
    CountrySelect,
  },
  data: () => ({
    email: '',
    password: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    country: '',
    telephoneNum: '',
    clinic: null,
  }),
  methods: {
    ...mapActions('clinicAdmin', ['registerClinicAdmin']),
    ...mapActions('clinic', ['getClinicNames']),
    submit() {
      if (this.validate()) {
        this.registerClinicAdmin({
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,
          address: this.address,
          city: this.city,
          country: this.country,
          telephoneNum: this.telephoneNum,
          clinic: this.clinic.id,
        });
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  mounted() {
    this.getClinicNames();
  },
  computed: {
    ...mapState('clinic', ['clinicNames']),
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
