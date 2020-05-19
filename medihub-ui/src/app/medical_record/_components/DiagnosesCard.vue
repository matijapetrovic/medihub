<template>
  <div>
    <v-card>
      <v-card-title>
          Diagnoses
        </v-card-title>
        <v-list
          two-line
        >
          <v-list-item
            v-for="(fa, idx) in finishedAppointments"
            :key="idx"
            @click="openDiagnosis(idx)"
          >
            <v-list-item-content>
              <v-list-item-title v-text="fa.diagnosis"></v-list-item-title>
              <v-list-item-subtitle v-text="fa.date"></v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
              <v-btn icon>
                <v-icon color="grey lighten-1">mdi-information</v-icon>
              </v-btn>
            </v-list-item-action>
          </v-list-item>
        </v-list>
    </v-card>
    <v-dialog
      v-model="dialog"
      max-width="500"
    >
      <v-card>
        <v-card-title>
          {{ diagnosis.diagnosis }}
        </v-card-title>
        <v-card-subtitle>
          {{ diagnosis.doctor }} <span class="font-italic">{{ diagnosis.date }}</span>
        </v-card-subtitle>
        <v-card-text>
          {{ diagnosis.description }}
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: 'DiagnosesCard',
  data: () => ({
    diagnosis: {},
    dialog: false,
  }),
  props: {
    finishedAppointments: {
      type: Array,
      required: true,
    },
  },
  methods: {
    openDiagnosis(idx) {
      this.diagnosis = this.finishedAppointments[idx];
      this.dialog = true;
    },
  },
};
</script>
