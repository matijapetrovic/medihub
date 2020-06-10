<template>
  <v-container>
    <v-card>
      <v-col>
      <v-row>
        <v-spacer></v-spacer>
        <v-col>
          <v-text-field
          v-if="patient!==null"
          label="Email"
          v-model="patient.email"
          readonly>
          </v-text-field>
        </v-col>
        <v-col>
          <v-text-field
          v-if="patient!==null"
          label="Name"
          v-model="patient.firstName"
          readonly>
          </v-text-field>
        </v-col>
        <v-col>
          <v-text-field
          v-if="patient!==null"
          label="Surname"
          v-model="patient.lastName"
          readonly>
          </v-text-field>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-col>
          <v-text-field
          v-if="patient!==null"
          label="Addressline"
          v-model="patient.addressLine"
          readonly>
          </v-text-field>
        </v-col>
        <v-col>
          <v-text-field
          v-if="patient!==null"
          label="Insurance number"
          v-model="patient.insuranceNumber"
          readonly>
          </v-text-field>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
      </v-col>
      <v-col v-if="permission">
        <PatientDetailsCard
          :medical-record="patientRecord"
        />
        <DiagnosesCard
        v-if="patientRecord.finishedAppointments"
        :finished-appointments="patientRecord.finishedAppointments"/>
      </v-col>
      <v-col lg v-else>
        You have no permission to access patient's medical record!
      </v-col>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
        class="primary"
        :disabled="appointmentFinished"
        rounded
        @click="showAppointment()"
        >
          Show appointment
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
        rounded
        fab
        @click="routeBack()"
        >
          <v-icon>
            mdi-keyboard-backspace
          </v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
    <appointment-dialog
      ref="dialog"
      @disableFinishApppointment="setFinishedFlag($event)"
    />
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import PatientDetailsCard from '@/app/medical_record/_components/PatientDetailsCard.vue';
import DiagnosesCard from '@/app/medical_record/_components/DiagnosesCard.vue';
import AppointmentDialog from '@/app/medical_doctor/components/AppointmentDialog.vue';

export default {
  components: {
    PatientDetailsCard,
    DiagnosesCard,
    AppointmentDialog,
  },
  data: () => ({
    date: null,
    time: null,
    appointmentFinished: false,
  }),

  computed: {
    ...mapState('patient', ['patient', 'patientId']),
    ...mapState('medicalDoctor', ['permission', 'scheduleItem']),
    ...mapState('medicalRecord', ['patientRecord']),
    ...mapState('appointment', ['appointment']),
    isAppointmentPresent() {
      return this.appointmentFinished;
    },
  },

  mounted() {
    this.getPatientById(this.$route.params.id);
    this.hasMedicalRecordPermission(this.$route.params.id);
    this.getPatientMedicalRecord(this.$route.params.id);
    this.getCurrentAppointment(this.$route.params.id)
      .then(() => {
        if (this.appointment) {
          this.getAppointmentScheduleItem(this.appointment.id)
            .then(() => this.setDateAndTimeObject());
        } else {
          this.appointmentFinished = true;
        }
      });
  },

  methods: {
    ...mapActions('patient', ['getPatientById']),
    ...mapActions('medicalDoctor', ['hasMedicalRecordPermission', 'getAppointmentScheduleItem']),
    ...mapActions('medicalRecord', ['getPatientMedicalRecord']),
    ...mapActions('appointment', ['getCurrentAppointment']),
    showAppointment() {
      this.$refs.dialog.show(this.makeAppointment());
    },
    setFinishedFlag(flag) {
      this.appointmentFinished = flag;
    },
    setDateAndTimeObject() {
      this.time = this.makeNumberTwoDigit(this.appointment.time[0]).toString().concat(':00:00');
      this.date = new Date(this.appointment.date).toISOString().substr(0, 10);
    },
    routeBack() {
      this.$router.push('/patients');
    },
    makeAppointment() {
      return {
        itemId: this.scheduleItem.id,
        itemDate: new Date(this.appointment.date).toISOString().substr(0, 10),
        appointment: {
          id: this.appointment.id,
          doctor: {
            id: this.appointment.doctor.id,
            firstName: this.appointment.doctor.account.firstName,
            secondName: this.appointment.doctor.account.lastName,
          },
          patient: {
            id: this.appointment.patient.id,
            firstName: this.appointment.patient.account.firstName,
            lastName: this.appointment.patient.account.lastName,
          },
          clinicRoom: {
            name: this.appointment.clinicRoom.name,
            number: this.appointment.clinicRoom.number,
          },
          date: this.date,
          time: this.time,
        },
      };
    },
    makeNumberTwoDigit(number) {
      if (number < 10) {
        return '0'.concat(number.toString());
      }
      return number.toString();
    },
  },
};
</script>
