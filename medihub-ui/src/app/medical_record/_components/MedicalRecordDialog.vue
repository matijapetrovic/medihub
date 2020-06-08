<style scoped>
  .label {
    font-size: 15px;
  }
  .field {
    width: 47%;
    margin-right: 3px;
  }
  .radio >>> label {
    font-size: 13px;
  }
</style>

<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="800px">
      <v-card>
        <v-card-title
          class="headline grey lighten-2">
          <span class="headline">Medical Record </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- Patient info -->
              <v-col>
                <v-form ref="form">
                <v-row>
                  <div class="field">
                    <div class="label">Height (cm)</div>
                    <v-text-field
                      v-model="model.height"
                      outlined
                      :rules="[requiredRule]"
                      dense
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
                      :rules="[requiredRule]"
                      dense
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                </v-row>
                <v-row style="margin-top: 10px">
                  <div class="field">
                    <div class="label">Blood type</div>
                    <v-select
                      v-model="model.bloodType"
                      outlined
                      :rules="[requiredRule]"
                      :items="bloodTypes"
                    >
                    </v-select>
                  </div>
                  <div style="margin-left: 15px">
                    <v-radio-group
                        v-model="model.rhPositive"
                        x-small
                        dense
                      >
                      <v-radio
                          class="radio"
                          color="primary"
                          label="Positive"
                          :value="true"
                        >
                      </v-radio>
                      <v-radio
                          class="radio"
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
                      :rules="[requiredRule]"
                      dense
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
                      :rules="[requiredRule]"
                      dense
                      hide-details
                      single-line
                      type="number"
                    />
                  </div>
                </v-row>
                </v-form>
              </v-col>
              <hr/>
              <!-- Alergies -->
              <v-col>
                <v-form ref="allergyForm">
                 <v-row>
                    <div class="field"
                        style="margin-left: 40px">
                      <div class="label">Allergy Name</div>
                      <v-text-field
                        outlined
                        v-model="allergyName"
                        :rules="[requiredRule]"
                        dense
                        >
                      </v-text-field>
                    </div>
                    <v-rating
                        style="margin: 30px 0px 0px 10px"
                        length="3"
                        v-model="allergyLevel"
                        dense
                      >
                    </v-rating>
                    <v-btn class="mx-2"
                          fab dark
                          small
                          color="indigo"
                          style="margin: 23px 0px 0px 15px"
                          @click="addAllergy">
                      <v-icon dark>mdi-plus</v-icon>
                    </v-btn>
                  </v-row>
                 </v-form>
                 <v-card
                   class="mx-auto"
                   width="405"
                  >
                  <v-list
                    subheader
                    style="max-height: 177px"
                    class="overflow-y-auto"
                    v-if="model.allergies"
                    dense
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
                        <v-col>
                          <v-btn icon
                            x-small
                            dense
                            @click="deleteAllergy(allergy.name)">
                              <v-icon>mdi-close</v-icon>
                          </v-btn>
                        </v-col>
                      </v-row>
                    </v-list-item>
                  </v-list>
                 </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions
           color="grey lighten-2">
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="close">Close</v-btn>
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
    allergyName: '',
    allergyLevel: 1,
  }),
  computed: {
    ...mapState('medicalRecord', ['bloodTypes', 'patientRecord']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  methods: {
    ...mapActions('medicalRecord', ['getBloodTypes', 'getPatientMedicalRecord', 'changeMedicalRecord', 'printMessage']),
    show() {
      this.model = JSON.parse(JSON.stringify(this.patientRecord));
      this.dialog = true;
    },
    initialize(patientId) {
      this.patientId = patientId;
      this.getPatientMedicalRecord(this.patientId);
    },
    submit() {
      if (this.$refs.form.validate()) {
        this.changeMedicalRecord(this.model);
      }
    },
    addAllergy() {
      if (this.$refs.allergyForm.validate()) {
        if (!this.doAllergyExist()) {
          const newAllergy = {
            name: this.allergyName,
            level: this.allergyLevel,
          };
          this.model.allergies.unshift(newAllergy);
        } else {
          this.printMessage('Allergy already exist!');
        }
      }
    },
    doAllergyExist() {
      let exist = false;
      this.model.allergies.forEach((element) => {
        if (element.name === this.allergyName) {
          exist = true;
        }
      });
      return exist;
    },
    deleteAllergy(allergyName) {
      const index = this.model.allergies.findIndex((element) => element.name === allergyName);
      this.model.allergies.splice(index, 1);
    },
    close() {
      this.$refs.allergyForm.reset();
      this.allergyName = '';
      this.dialog = false;
    },
  },
  mounted() {
    this.getBloodTypes();
  },
};
</script>;
