<template>
  <div>
    <v-data-table
        :headers="headers"
        :items="patientFAs"
        :items-per-page="5"
        class="elevation-1"
      >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-btn @click="back"
              x-small
              dense
              fab
            >
            <v-icon>mdi-arrow-left</v-icon>
          </v-btn>
          <v-toolbar-title style="margin-left: 30px">
            {{firstName}} {{lastName}}'s Finished Appointments
          </v-toolbar-title>
        </v-toolbar>
      </template>
      <template v-slot:item.edit="{ item }">
        <v-btn
            rounded
            color="warning"
            small
            dense
            @click="changeFinishedAppointment(item)"
          >
          Edit
        </v-btn>
      </template>
    </v-data-table>
    <ChangeFinishedAppointment ref="changeDialog"></ChangeFinishedAppointment>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import ChangeFinishedAppointment from './ChangeFinishedAppointment.vue';

export default {
  data: () => ({
    patientId: null,
    firstName: '',
    lastName: '',
    headers: [
      {
        text: 'Date',
        align: 'start',
        value: 'date',
      },
      {
        text: 'Time',
        value: 'time',
      },
      {
        text: 'Diagnosis',
        value: 'diagnosisStr',
      },
      {
        text: 'Edit',
        align: 'end',
        value: 'edit',
      },
    ],
  }),
  components: {
    ChangeFinishedAppointment,
  },
  name: 'PatientsFinishedAppointments',
  mounted() {
    this.setParameters();
    this.getFinishedAppointments(this.patientId);
  },
  computed: {
    ...mapState('finishedAppointment', ['patientFAs']),
  },
  methods: {
    ...mapActions('finishedAppointment', ['getFinishedAppointments']),
    setParameters() {
      const params = JSON.parse(this.$route.params.param);
      this.mapParams(params);
    },
    mapParams(params) {
      this.patientId = params.patientId;
      this.firstName = params.firstName;
      this.lastName = params.lastName;
    },
    changeFinishedAppointment(finishedAppointment) {
      const changeItem = {
        id: finishedAppointment.id,
        diagnosis: { id: finishedAppointment.diagnosis, name: finishedAppointment.diagnosisStr },
        description: finishedAppointment.description,
      };
      this.$refs.changeDialog.show(changeItem);
    },
    back() {
      this.$router.push('/previous-patients');
    },
  },
};
</script>
