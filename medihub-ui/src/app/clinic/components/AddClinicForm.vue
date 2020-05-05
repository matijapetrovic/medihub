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
      <v-toolbar-title>Add Clinic</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-form
        ref="form"
      >
      <v-row>
          <v-text-field
            v-model="name"
            label="Name"
            name="name"
            prepend-icon="mdi-hospital-box-outline"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
      </v-row>
      <v-row>
       <v-text-field
        v-model="address"
        label="Address"
        name="address"
        prepend-icon="house"
        :rules="[requiredRule]"
        type="text"
        required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
            v-model="city"
            label="City"
            name="city"
            prepend-icon="mdi-city"
            :rules="[requiredRule]"
            type="text"
            required
          ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="country"
          label="Country"
          name="country"
          prepend-icon="mdi-earth"
          :rules="[requiredRule]"
          type="text"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="description"
          label="Description"
          name="description"
          prepend-icon="mdi-book-open"
          type="text"
          required
        ></v-text-field>
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
  </v-col>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'AddClinicForm',
  data: () => ({
    name: '',
    address: '',
    city: '',
    country: '',
    description: '',
  }),
  methods: {
    ...mapActions('clinic', ['addClinic']),
    submit() {
      if (this.validate()) {
        this.addClinic({
          name: this.name,
          address: this.address,
          city: this.city,
          country: this.country,
          description: this.description,
        });
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  computed: {
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
