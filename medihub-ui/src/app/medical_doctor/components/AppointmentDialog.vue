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
            :disabled="!requireParamsValid"
          >
            Finish
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <recordDialog ref="medicalRecord">
    </recordDialog>
    <v-dialog v-model="requestDialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">
            Do you want to schedule another appointment?
          </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-spacer></v-spacer>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                label="Doctor's name:"
                v-model="doctorFullName"
                readonly>
                </v-text-field>
              </v-col>
              <v-spacer></v-spacer>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                label="Patient's name:"
                v-model="patientFullName"
                readonly>
                </v-text-field>
              </v-col>
              <v-spacer></v-spacer>
            </v-row>
            <v-row>
              <v-spacer></v-spacer>
              <v-col cols="12" sm="6" md="4">
                <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="date"
                      label="Date"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                  v-model="date"
                  no-title scrollable
                  :min="today"
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="setTimeSubmitAndCloseDialog()">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
              <v-spacer></v-spacer>
              <v-col cols="12" sm="6" md="4">
                <v-select
                  :items="availableTimes"
                  v-model="time"
                  prepend-icon="mdi-timelapse"
                  label="Time"
                  dense
                  outlined
                ></v-select>
              </v-col>
              <v-spacer></v-spacer>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="submitAndCloseDialog()">No</v-btn>
          <v-btn
          :disabled="isTimeSelected"
          color="blue darken-1"
          text @click="sendRequest()"
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
  name: 'AppointmentDialog',
  components: {
    recordDialog: MedicalRecordDialog,
  },
  data() {
    return {
      requestDialog: false,
      menu: null,
      dialog: false,
      today: new Date().toISOString().substr(0, 10),
      model: null,
      firstName: '',
      lastName: '',
      patientFullName: '',
      clinicRoom: '',
      number: '',
      description: '',
      tempDiagnosis: null,
      tempDrugs: [],
      doctorId: null,
      doctorName: null,
      doctorSurname: null,
      doctorFullName: '',
      patientId: null,
      date: null,
      time: null,
    };
  },
  computed: {
    ...mapState('diagnosis', ['diagnosis']),
    ...mapState('drugs', ['drugs']),

    ...mapState('doctor', ['availableTimes']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    isTimeSelected() {
      return this.time === null;
    },
    requireParamsValid() {
      if (!this.tempDiagnosis || this.tempDrugs.length === 0 || this.description === '') {
        return false;
      }
      return true;
    },
  },
  methods: {
    ...mapActions('diagnosis', ['getDiagnosis']),
    ...mapActions('drugs', ['getDrugs']),
    ...mapActions('medicalDoctor', ['finishAppointment']),
    ...mapActions('appointment', ['scheduleDoctorsAppointment']),
    ...mapActions('doctor', ['fetchAvailableTimesWithoutState']),
    show(model) {
      this.model = model;
      this.date = model.appointment.date;
      this.doctorId = model.appointment.doctor.id;
      this.doctorName = model.appointment.doctor.firstName;
      this.doctorSurname = model.appointment.doctor.secondName;
      this.doctorFullName = this.doctorName.concat(' ').concat(this.doctorSurname);
      this.patientId = model.appointment.patient.id;
      this.firstName = model.appointment.patient.firstName;
      this.lastName = model.appointment.patient.lastName;
      this.patientFullName = this.firstName.concat(' ').concat(this.lastName);
      this.clinicRoom = model.appointment.clinicRoom.name;
      this.number = model.appointment.clinicRoom.number;
      this.date = model.appointment.date;
      this.dialog = true;
      this.$refs.medicalRecord.initialize(this.model.appointment.patient.id);
    },
    close() {
      this.$refs.form.reset();
      this.dialog = false;
    },
    fetchTimes() {
      this.fetchAvailableTimesWithoutState({
        doctorId: this.doctorId,
        date: this.date,
      });
    },
    openRequestDialog() {
      this.fetchTimes();
      this.requestDialog = true;
    },
    sendRequest() {
      this.scheduleDoctorsAppointment({
        patientId: this.patientId,
        date: this.date,
        time: this.time,
        doctor: this.doctorId,
        clinicRoomId: this.clinicRoom.id,
      });
      this.submitAndCloseDialog();
      this.close();
    },
    setTimeSubmitAndCloseDialog() {
      this.$refs.menu.save(this.date);
      this.fetchTimes();
    },
    submitAndCloseDialog() {
      this.submit();
      this.requestDialog = false;
      this.close();
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
            this.$emit('disableFinishApppointment', true),
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
