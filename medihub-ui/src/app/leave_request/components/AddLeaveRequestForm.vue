<template>
  <v-container>
    <v-form
      ref="form"
    >
      <v-card max-width="1300" max-height="1300" class="mx-auto">
        <v-card-title color="indigo  darken-2" dark>
          Leave request
        </v-card-title>
        <v-divider></v-divider>
        <v-row>
          <v-col cols="12" sm="6">
            <v-date-picker
              v-model="dates"
              multiple
              :min="today"
            ></v-date-picker>
          </v-col>
          <v-col cols="12" sm="6">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="dates"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-select
                  :items="types"
                  v-model="type"
                  prepend-icon="mdi-format-list-bulleted-type"
                  label="Type of leave request"
                  dense
                  outlined
                  :rules="[requiredRule]"
                ></v-select>
                <v-combobox
                  v-model="dates"
                  multiple
                  chips
                  small-chips
                  label="Multiple picker in menu"
                  prepend-icon="event"
                  readonly
                  v-on="on"
                ></v-combobox>
              </template>
              <v-date-picker
              v-model="dates"
              multiple
              no-title scrollable
              :min="today"
              >
                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(dates)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-card-actions>
          <v-spacer></v-spacer>
          <div class="my-2">
            <v-btn
            rounded
            max-width=""
            color="primary"
            @click="submit()"
            >
              Submit
            </v-btn>
          </div>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-form>
  </v-container>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'AddLeaveRequest',
  data: () => ({
    allowedDatesList: [],
    items: [],
    dates: [],
    menu: null,
    types: ['Vacation', 'Leave'],
    type: null,
    today: new Date().toISOString().substr(0, 10),
  }),
  methods: {
    ...mapActions('leaveRequest', ['addLeaveRequest']),
    ...mapActions('medicalDoctor', ['getWorkindCalendarByDoctorId']),
    setItems() {
      return true;
    },
    submit() {
      if (this.validate()) {
        const request = {
          dates: this.dates,
          type: this.type,
        };
        this.addLeaveRequest(request);
        this.clear();
      }
    },
    clear() {
      this.dates = [];
      this.type = null;
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
