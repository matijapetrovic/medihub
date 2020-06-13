<template>
  <v-dialog v-model="dialog" persistent max-width="400px">
      <v-card>
        <v-card-title
          class="headline grey lighten-2">
          <span class="headline">Appointment type</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="form">
              <v-text-field
                v-model="name"
                label="name"
                :rules="[requiredRule]">
              </v-text-field>
            </v-form>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1"
            :disabled="this.name === this.model.name"
            text @click="change">
            Change
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  data: () => ({
    dialog: false,
    model: {},
    name: '',
  }),
  name: 'AppointmentEditDialog',
  computed: {
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  methods: {
    ...mapActions('appointmentType', ['changeAppointmentType']),
    show(model) {
      this.model = model;
      this.name = this.model.name;
      this.dialog = true;
    },
    change() {
      if (this.$refs.form.validate()) {
        this.changeAppointmentType({ id: this.model.id, name: this.name });
      }
    },
  },
};
</script>
