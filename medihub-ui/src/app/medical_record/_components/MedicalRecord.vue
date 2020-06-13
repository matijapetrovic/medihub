<template>
  <div>
    <v-row>
      <v-col
        md="4"
      >
        <v-row>
          <v-col>
            <PatientDetailsCard
              :medical-record="medicalRecord"
            />
          </v-col>
        </v-row>
      </v-col>
      <v-col>
        <v-row>
          <v-col>
            <DiagnosesCard
            v-if="medicalRecord.finishedAppointments"
            :finished-appointments="medicalRecord.finishedAppointments"/>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import PatientDetailsCard from './PatientDetailsCard.vue';
import DiagnosesCard from './DiagnosesCard.vue';

export default {
  name: 'MedicalRecord',
  components: {
    PatientDetailsCard,
    DiagnosesCard,
  },
  computed: {
    ...mapState('medicalRecord', ['medicalRecord']),
  },
  methods: {
    ...mapActions('medicalRecord', ['fetchMedicalRecord']),
  },
  mounted() {
    this.fetchMedicalRecord();
  },
};
</script>
