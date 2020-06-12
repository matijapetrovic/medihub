<template>
  <v-form>
    <v-row>
      <v-col>
        <v-select
          v-model="appointmentType"
          :items="appointmentTypes"
          item-text="name"
          label="Appointment Type"
          return-object
          ref="form"
          clearable
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
            :min="tomorrow"
            no-title
            scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
          </v-date-picker>
        </v-menu>
      </v-col>
      <v-col md="2">
        <v-btn
          color="primary"
          @click="search"
          class="mt-4"
          >Search
        </v-btn>
      </v-col>
    </v-row>
  </v-form>
</template>

<script>

export default {
  name: 'ClinicSearchForm',
  data: () => ({
    appointmentType: null,
    date: null,
    menu: null,
  }),
  props: {
    appointmentTypes: {
      required: true,
      type: Array,
    },
    searchParams: {
      type: Object,
    },
  },
  methods: {
    search() {
      if (this.validate()) {
        this.$emit('searched', {
          appointmentType: this.appointmentType,
          date: this.date,
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
    tomorrow() {
      function addDays(date, days) {
        const res = new Date(date);
        res.setDate(res.getDate() + days);
        return res;
      }

      return addDays(new Date(), 1).toISOString().substr(0, 10);
    },
  },
  mounted() {
    if (this.searchParams) {
      this.date = this.searchParams.date;
      this.appointmentType = this.searchParams.appointmentType;
    }
  },
};
</script>
