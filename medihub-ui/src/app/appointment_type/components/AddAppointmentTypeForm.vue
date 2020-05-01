<template>
    <div class="fill-height">
        <v-container fluid class="fill-height">
            <v-row align="center" justify="center">
                <v-col cols="12" sm="8" md="4">
                    <v-card class="elevation-12">
                        <v-toolbar color="primary" dark flat>
                            <v-toolbar-title>Add appointment type</v-toolbar-title>
                        </v-toolbar>
                        <v-card-text>
                            <v-form ref="form">
                                <v-text-field v-model="price" label="Price"
                                price="price" type="number"
                                :rules="[requiredRule, loadMin]">
                                </v-text-field>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn @click="submit" color="primary" name="submit">
                                Submit
                            </v-btn>
                            <v-btn @click="clear" color="secondary" name="clear">
                                Clear
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'AddAppointmentTypeForm',
  data: () => ({
    price: null,
  }),
  methods: {
    ...mapActions('appointmentType', ['addAppointmentType']),
    submit() {
      if (this.validate()) {
        this.addAppointmentType({
          price: this.price,
        });
      }
    },
    clear() {
      this.price = null;
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  computed: {
    loadMin() {
      return (value) => value >= 0 || 'Value must be positive number!';
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
