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
            <PrescriptionsCard/>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <DiagnosesCard/>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import PatientDetailsCard from './PatientDetailsCard.vue';
import PrescriptionsCard from './PrescriptionsCard.vue';
import DiagnosesCard from './DiagnosesCard.vue';

export default {
  name: 'MedicalRecord',
  components: {
    PatientDetailsCard,
    PrescriptionsCard,
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
