<template>
    <div class="fill-height">
        <v-container fluid class="fill-height">
            <v-row align="center" justify="center">
                <v-col cols="12" sm="8" md="4">
                    <v-card class="elevation-12">
                        <v-toolbar color="primary" dark flat>
                            <v-toolbar-title>Add clinic room</v-toolbar-title>
                        </v-toolbar>
                        <v-card-text>
                            <v-form ref="form">
                                <v-text-field v-model="name" label="Name"
                                name="name" type="text"
                                :rules="[requiredRule,]">
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
  name: 'AddClinicRoomForm',
  data: () => ({
    name: null,
  }),
  methods: {
    ...mapActions('clinicRooms', ['addClinicRoom']),
    submit() {
      if (this.validate()) {
        this.addClinicRoom({
          name: this.name,
        });
      }
    },
    clear() {
      this.name = null;
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
