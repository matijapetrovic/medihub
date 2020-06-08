<style scoped>
  .label {
    font-size: 15px;
  }
  .field {
    width: 47%;
    margin-right: 3px;
  }
</style>

<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="700px">
      <v-card>
        <v-card-title>
          <span class="headline">Medical Record </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- Patient info -->
              <v-col>
                <v-row>
                  <div class="field">
                    <div class="label">Height (cm)</div>
                    <v-text-field
                      v-model="model.height"
                      outlined
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                  <div class="field">
                    <div class="label">Weight (kg)</div>
                    <v-text-field
                      v-model="model.weight"
                      outlined
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                </v-row>
                <v-row>
                  <div class="field">
                    <div class="label">Blood type</div>
                    <v-select
                      v-model="model.bloodType"
                      outlined
                      :items="bloodTypes"
                    >
                    </v-select>
                  </div>
                  <div style="margin-left: 15px">
                    <v-radio-group
                        v-model="model.rhPositive"
                      >
                      <v-radio
                          color="primary"
                          label="Positive"
                          :value="true"
                        >
                      </v-radio>
                      <v-radio
                          color="warning"
                          label="Negative"
                          :value="false"
                        >
                      </v-radio>
                    </v-radio-group>
                  </div>
                </v-row>
                <v-row>
                  <div class="field">
                    <div>Left dioptry</div>
                    <v-text-field
                      v-model="model.leftDioptry"
                      outlined
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                  <div class="field">
                    <div>Right dioptry</div>
                    <v-text-field
                      v-model="model.rightDioptry"
                      outlined
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                </v-row>
              </v-col>
              <hr/>
              <!-- Alergies -->
              <v-col>
                 <v-list
                  subheader
                  style="max-height: 400px"
                  class="overflow-y-auto"
                  v-if="model.allergies"
                  >
                    <v-subheader>Allergies</v-subheader>
                    <v-list-item
                      v-for="(allergy, idx) in model.allergies"
                      :key="idx"
                    >
                    <v-row>
                      <v-col
                        md="8"
                      >
                        <v-list-item-title>{{ allergy.name }}</v-list-item-title>
                      </v-col>
                      <v-col>
                        <v-list-item-subtitle>
                          <v-rating
                            :value="allergy.level"
                            length="3"
                            small
                            dense
                            readonly
                          >
                          </v-rating>
                        </v-list-item-subtitle>
                      </v-col>
                    </v-row>
                  </v-list-item>
                </v-list>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="submit">Save</v-btn>
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
    model: {},
  }),
  computed: {
    ...mapState('medicalRecord', ['bloodTypes', 'patientRecord']),
  },
  methods: {
    ...mapActions('medicalRecord', ['getBloodTypes', 'getPatientMedicalRecord', 'changeMedicalRecord']),
    show() {
      this.model = JSON.parse(JSON.stringify(this.patientRecord));
      this.dialog = true;
    },
    initialize(patientId) {
      this.patientId = patientId;
      this.getPatientMedicalRecord(this.patientId);
    },
    submit() {
      this.changeMedicalRecord(this.model);
    },
  },
  mounted() {
    this.getBloodTypes();
  },
};
</script>;
