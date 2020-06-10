<template>
    <v-dialog v-model="dialog" persistent max-width="450px">
      <v-card>
        <v-card-title class="headline grey lighten-2">
          <span class="headline">Change Finished Appointment</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-container>
              <v-row>
                <v-select
                    style="margin-top: 10px"
                    outlined
                    dense
                    v-model="tempDiagnosis"
                    :items="diagnosis"
                    item-text="name"
                    label="Diagnosis"
                    :rules="[requiredRule]"
                    return-object
                  >
                </v-select>
              </v-row>
              <v-row>
                <v-textarea
                    dense
                    label="Description"
                    v-model="description"
                    color="teal"
                    :no-resize="true"
                    :rules="[requiredRule]"
                    rows="5"
                  >
                    <template v-slot:label>
                      <div>
                        Description
                      </div>
                    </template>
                  </v-textarea>
              </v-row>
            </v-container>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="save">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'ChangeFinishedAppointment',
  data: () => ({
    description: '',
    tempDiagnosis: null,
    dialog: false,
  }),
  methods: {
    ...mapActions('diagnosis', ['getDiagnosis']),
    ...mapActions('finishedAppointment', ['changeFinishedAppointment']),
    show(model) {
      this.model = model;
      this.description = model.description;
      this.tempDiagnosis = { ...model.diagnosis };
      this.dialog = true;
    },
    save() {
      this.changeFinishedAppointment({
        id: this.model.id,
        diagnosis: this.tempDiagnosis,
        description: this.description,
      });
    },
  },
  computed: {
    ...mapState('diagnosis', ['diagnosis']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  mounted() {
    this.getDiagnosis();
  },
};
</script>
