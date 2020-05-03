<template>
  <div>
    <v-card class="elevation-12">
      <v-toolbar
        color="primary"
        dark
        flat
      >
        <v-toolbar-title>Update profile</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form
          ref="form"
        >
          <v-row>
            <v-col>
              <v-text-field
                v-model="profile.firstName"
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
                v-model="profile.lastName"
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
                v-model="profile.address"
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
                v-model="profile.city"
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
                v-model="profile.country"
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
                v-model="profile.telephoneNum"
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
          Update
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'UpdateProfileForm',
  computed: {
    profile: {
      get() {
        return this.$store.state.profile.profile;
      },
      set(value) {
        this.$store.commit('profile/SET_PROFILE', value);
      },
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    telephoneNumRule() {
      return (value) => /((\+381)|0)6[0-9]{7,8}/.test(value) || 'Telephone number must be valid';
    },
  },
  methods: {
    ...mapActions('profile', ['fetchProfile', 'updateProfile']),
    submit() {
      if (this.validate()) {
        this.updateProfile();
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  mounted() {
    this.fetchProfile();
  },
};
</script>
