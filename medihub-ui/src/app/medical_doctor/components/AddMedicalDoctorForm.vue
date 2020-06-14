<template>
  <v-container>
    <v-card max-width="1200" max-height="1200" class="mx-auto">
      <v-toolbar
        color="primary"
        dark
        flat
      >
        <v-toolbar-title>Add medical doctor</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form
          ref="form"
        >
          <v-row>
            <v-col
              md="5"
            >
              <v-row>
                <v-col>
                  <EmailInput
                    v-model="email"
                  />
                </v-col>
                <v-col>
                  <v-select
                    :items="appointmentTypes"
                    v-model="appointmentType"
                    label="Specialization"
                    item-text="name"
                    return-object
                    prepend-icon="mdi-account-cog"
                  ></v-select>
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
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-select
                    :items="dayHours"
                    v-model="from"
                    label="Start of working hours"
                    :rules="[requiredRule]"
                    prepend-icon="mdi-arrow-down-bold-circle-outline"
                  ></v-select>
                </v-col>
                <v-col>
                  <v-select
                    :items="dayHours"
                    v-model="to"
                    label="End of working hours"
                    :rules="[requiredRule]"
                    prepend-icon="mdi-arrow-up-bold-circle-outline"
                  ></v-select>
                </v-col>
              </v-row>
            </v-col>
            <v-spacer></v-spacer>
            <v-col
              md="5"
            >
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
          Add
        </v-btn>
        <v-spacer></v-spacer>
      </v-card-actions>
      </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import EmailInput from '@/app/shared/_components/_forms/EmailInput.vue';
import PasswordInput from '@/app/shared/_components/_forms/PasswordInput.vue';
import CountrySelect from '@/app/country/_components/CountrySelect.vue';

export default {
  name: 'AddMedicalDoctorForm',
  components: {
    EmailInput,
    PasswordInput,
    CountrySelect,
  },
  data: () => ({
    email: '',
    appointmentType: '',
    password: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    country: '',
    telephoneNum: '',
    from: '',
    to: '',
    dayHours: [
      '00:00', '01:00', '02:00', '03:00', '04:00', '05:00',
      '06:00', '07:00', '08:00', '09:00', '10:00', '11:00',
      '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
      '18:00', '19:00', '20:00', '21:00', '22:00', '23:00',
    ],
  }),
  created() {
    this.initialize();
  },
  methods: {
    ...mapActions('medicalDoctor', ['addMedicalDoctor']),
    ...mapActions('appointmentType', ['fetchAppointmentTypes']),
    initialize() {
      this.fetchAppointmentTypes();
    },
    submit() {
      if (this.validate()) {
        this.addMedicalDoctor({
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,
          address: this.address,
          city: this.city,
          country: this.country,
          telephoneNum: this.telephoneNum,
          from: this.from,
          to: this.to,
          appointmentTypeId: this.appointmentType.id,
        })
          .then(() => {
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
    ...mapState('appointmentType', ['appointmentTypes']),
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
