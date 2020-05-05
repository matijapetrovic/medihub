<template>
  <v-form>
    <v-row>
      <v-col>
        <v-select
          v-model="appointmentType"
          :items="appointmentTypes"
          item-text="name"
          label="Appointment Type"
          :rules="[requiredRule]"
          return-object
          ref="form"
        ></v-select>
      </v-col>
      <v-col>
        <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="date"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="date"
              label="Appointment Date"
              prepend-icon="event"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="date"
            :min="today"
            no-title
            scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
          </v-date-picker>
        </v-menu>
      </v-col>
    </v-row>
    <v-row
      align="center"
      justify="center"
      class="text-center"
    >
      <v-col>
        <v-btn
          :disabled="!appointmentType || !date"
          color="primary"
          @click="search"
          >Search
        </v-btn>
      </v-col>
    </v-row>
  </v-form>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'ClinicSearchForm',
  data: () => ({
    appointmentType: null,
    date: new Date().toISOString().substr(0, 10),
    menu: null,
    today: new Date().toISOString().substr(0, 10),
  }),
  methods: {
    ...mapActions('appointmentType', ['fetchAppointmentTypes']),
    ...mapActions('clinic', ['fetchClinics']),
    search() {
      if (this.validate()) {
        this.fetchClinics();
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  computed: {
    ...mapState('appointmentType', ['appointmentTypes']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  mounted() {
    this.fetchAppointmentTypes();
  },
};
</script>
