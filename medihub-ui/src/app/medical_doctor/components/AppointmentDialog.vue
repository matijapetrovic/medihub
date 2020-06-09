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
      persistent
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
         <div
          style="margin: 25px 0px 0px 0px"
          class="areaTitle">
            Patient
            <v-btn
              x-small
              rounded
              color="purple lighten-3"
              class="white--text"
              style="margin: 0px 0px 0px 5px"
              @click="showMedicalRecordDialog">
              Medical record
            </v-btn>
         </div>
          <v-container>
            <v-row>
              <v-text-field
                outlined
                label="First name:"
                dense
                v-model="firstName"
                :disabled="true"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                outlined
                dense
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
                outlined
                dense
                label="Name:"
                v-model="clinicRoom"
                :disabled="true"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                outlined
                dense
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
                  dense
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
                  dense
                  v-model="tempDrugs"
                  :items="drugs"
                  item-text="name"
                  label="Drugs"
                  :rules="[requiredRule]"
                  return-object
                  multiple
                >
                  <template v-slot:selection="{ item, index }">
                    <v-chip v-if="index === 0">
                      <span>{{ item.name }}</span>
                    </v-chip>
                    <v-chip v-if="index === 1">
                      <span>{{ item.name }}</span>
                    </v-chip>
                    <span
                      v-if="index === 2"
                      class="grey--text caption"
                    >(+{{ tempDrugs.length - 2 }})</span>
                  </template>
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
            dense
            small
            width="150"
            @click="openRequestDialog"
          >
            Finish
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <recordDialog ref="medicalRecord">
    </recordDialog>
    <v-dialog
      v-model="requestDialog"
      max-width="290"
    >
      <v-card max-width="800px">
        <v-card-title class="headline">Do you want to schedule another appointment?</v-card-title>
        <v-card-text>
          Doctor's name: {{this.doctorName}} <br>
          Patient's name: {{this.firstName}} {{this.lastName}} <br>
          Date: {{this.date}} <br>
          Time: {{this.time}} <br>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="red darken-1"
            text
            @click="requestDialog = false"
          >
            No
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="requestDialog = false"
          >
            Yes
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import MedicalRecordDialog from '@/app/medical_record/_components/MedicalRecordDialog.vue';

export default {
  components: {
    recordDialog: MedicalRecordDialog,
  },
  data() {
    return {
      requestDialog: false,
      dialog: false,
      model: null,
      firstName: '',
      lastName: '',
      clinicRoom: '',
      number: '',
      description: '',
      tempDiagnosis: null,
      tempDrugs: [],
      doctorId: null,
      doctorName: null,
      patientId: null,
      date: null,
      time: null,
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
    ...mapActions('appointmentRequest', ['scheduleDoctorsAppointment']),
    show(model) {
      this.model = model;
      this.doctorId = model.appointment.doctor.id;
      this.doctorName = model.appointment.doctor.firstName;
      this.patientId = model.appointment.patient.id;
      this.date = model.appointment.date;
      this.time = model.appointment.time;
      this.firstName = model.appointment.patient.firstName;
      this.lastName = model.appointment.patient.lastName;
      this.clinicRoom = model.appointment.clinicRoom.name;
      this.number = model.appointment.clinicRoom.number;
      this.dialog = true;
      this.$refs.medicalRecord.initialize(this.model.appointment.patient.id);
    },
    close() {
      this.$refs.form.reset();
      this.dialog = false;
    },
    openRequestDialog() {
      this.requestDialog = true;
    },
    sendRequest() { 
      
      this.submitAndCloseDialog();
    },
    submitAndCloseDialog() {
      this.submit();
      this.requestDialog = false;
    },
    submit() {
      if (this.validate()) {
        const drugList = [];
        this.tempDrugs.forEach((element) => {
          drugList.push(element.id);
        });
        this.finishAppointment({
          itemId: this.model.itemId,
          itemDate: this.model.itemDate,
          appointment: this.model.appointment.id,
          diagnosis: this.tempDiagnosis.id,
          drugs: drugList,
          description: this.description,
        })
          .then(
            this.$emit('appointmentFinished', this.model.itemId),
          );
      }
    },
    showMedicalRecordDialog() {
      this.$refs.medicalRecord.show();
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
