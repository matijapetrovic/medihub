<style scoped>
  .label {
    font-size: 15px;
    margin-top: 5px;
  }
  .dioptry {
    width: 47%;
  }
</style>

<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">Medical Record </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col>
                <div class="label">Height (cm)</div>
                <v-text-field
                  outlined
                  hide-details
                  single-line
                  type="number"
                />
                <div class="label">Weight (kg)</div>
                <v-text-field
                  outlined
                  hide-details
                  single-line
                  type="number"
                />
                <div>
                  <div class="label">Blood type</div>
                  <v-select
                    outlined
                    :items="bloodTypes"
                    item-text="name"
                  >
                    <span>
                      <v-checkbox
                        label="Ph Positiv"
                      ></v-checkbox>
                    </span>
                  </v-select>
                </div>
                <v-row>
                  <div style="margin-left: 12px"
                       class="dioptry">
                    <div>Left dioptry</div>
                    <v-text-field
                      outlined
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                  <div  style="margin-left: 5px"
                        class="dioptry">
                    <div>Right dioptry</div>
                    <v-text-field
                      outlined
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                </v-row>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'MedicalRecordDialog',
  data: () => ({
    dialog: false,
    patientId: null,
  }),
  computed: {
    ...mapState('medicalRecord', ['bloodTypes']),
  },
  methods: {
    ...mapActions('medicalRecord', ['getBloodTypes']),
    show(patientId) {
      this.dialog = true;
      this.patientId = patientId;
    },
  },
  mounted() {
    this.getBloodTypes();
  },
};
</script>;
