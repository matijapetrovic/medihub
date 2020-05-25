<style scoped>
  .areaTitle {
    font-size: 20px;
    font-weight: bold;
  }
  .select {
    width: 45%;
  }
</style>

<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          Enter Diagnosis
          <v-spacer></v-spacer>
          <v-btn icon
            @click="close">
              <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>

        <v-card-text>
         <br/>
         <div class="areaTitle">Patient</div>
          <v-container>
            <v-row>
              <v-text-field
                filled
                label="First name:"
                v-model="firstName"
                :disabled="true"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                filled
                label="Last name:"
                v-model="lastName"
                :disabled="true"
              ></v-text-field>
            </v-row>
          </v-container>
          <div class="areaTitle">Clinic room</div>
          <v-container>
            <v-row>
              <v-text-field
                filled
                label="Name:"
                v-model="clinicRoom"
                :disabled="true"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                filled
                label="Number:"
                v-model="number"
                :disabled="true"
              ></v-text-field>
            </v-row>
          </v-container>
          <div class="areaTitle">Diagnosis</div>
          <v-form ref="form">
            <v-container>
              <v-row>
                <v-select
                  class="select"
                  outlined
                  v-model="tempDiagnosis"
                  :items="diagnosis"
                  item-text="name"
                  label="Diagnosis"
                  :rules="[requiredRule]"
                  return-object
                ></v-select>
                <v-spacer></v-spacer>
                <v-select
                  class="select"
                  outlined
                  v-model="tempDrug"
                  :items="drugs"
                  item-text="name"
                  label="Drugs"
                  :rules="[requiredRule]"
                  return-object
                ></v-select>
              </v-row>
              <v-row>
                <v-textarea
                  label="Description"
                  v-model="description"
                  color="teal"
                  :no-resize="true"
                  :rules="[requiredRule]"
                  rows="3"
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

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="success"
            rounded
            width="150"
            @click="submit"
          >
            Finish
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  data() {
    return {
      dialog: false,
      model: null,
      firstName: '',
      lastName: '',
      clinicRoom: '',
      number: '',
      description: '',
      tempDiagnosis: null,
      tempDrug: null,
    };
  },
  computed: {
    ...mapState('diagnosis', ['diagnosis']),
    ...mapState('drugs', ['drugs']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  methods: {
    ...mapActions('diagnosis', ['getDiagnosis']),
    ...mapActions('drugs', ['getDrugs']),
    ...mapActions('medicalDoctor', ['finishAppointment']),
    show(model) {
      this.model = model;
      this.firstName = model.appointment.patient.firstName;
      this.lastName = model.appointment.patient.lastName;
      this.clinicRoom = model.appointment.clinicRoom.name;
      this.number = model.appointment.clinicRoom.number;
      this.dialog = true;
    },
    close() {
      this.$refs.form.reset();
      this.dialog = false;
    },
    submit() {
      if (this.validate()) {
        this.finishAppointment({
          appointment: this.model.appointment.id,
          diagnosis: this.tempDiagnosis.id,
          drugs: this.tempDrug.id,
          description: this.description,
        });
      }
    },
    validate() {
      return this.$refs.form.validate();
    },
  },
  mounted() {
    this.getDiagnosis();
    this.getDrugs();
  },
};
</script>
