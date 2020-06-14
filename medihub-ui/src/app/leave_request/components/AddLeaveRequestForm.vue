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
            range
            :min="today">
            </v-date-picker>
          </v-col>
          <v-col cols="12" sm="6">
            <v-select
              :items="types"
              v-model="type"
              prepend-icon="mdi-format-list-bulleted-type"
              label="Type of leave request"
              dense
              outlined
              :rules="[requiredRule]"
            ></v-select>
            <v-text-field
            v-model="dateRangeText"
            label="Date range"
            prepend-icon="event"
            readonly>
            </v-text-field>
          </v-col>
        </v-row>
        <v-card-actions>
          <v-spacer></v-spacer>
          <div class="my-2">
            <v-btn
            rounded
            max-width=""
            color="primary"
            :disabled="!validateFields()"
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
import { mapActions, mapState } from 'vuex';

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
    ...mapActions('leaveRequest', ['addLeaveRequest', 'addNurseLeaveRequest']),
    ...mapActions('medicalDoctor', ['getWorkindCalendarByDoctorId']),
    setItems() {
      return true;
    },
    submit() {
      if (this.validate()) {
        this.dates = this.dates.sort();
        const request = {
          dates: this.dates,
          type: this.type,
        };
        if (this.user.role[0] === 'ROLE_DOCTOR') {
          this.addLeaveRequest(request);
        }
        if (this.user.role[0] === 'ROLE_NURSE') {
          this.addNurseLeaveRequest(request);
        }
        this.clear();
      }
    },
    clear() {
      this.dates = [];
      this.type = null;
    },
    validateFields() {
      if (this.dates && this.type) {
        return true;
      }
      return false;
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  computed: {
    ...mapState('auth', ['user']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    dateRangeText() {
      return this.dates.join(' ~ ');
    },
  },
};
</script>
