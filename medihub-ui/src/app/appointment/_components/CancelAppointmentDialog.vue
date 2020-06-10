<template>
  <v-card
    class="elevation-16 mx-auto"
  >
    <v-card-title
      class="headline"
      primary-title
    >
      Cancel appointment
    </v-card-title>
    <v-card-text>
      <p>Are you sure you want to cancel?</p>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions class="justify-space-between">
      <v-btn text @click="emitCancelled">No</v-btn>
      <v-btn
        color="primary"
        text
        @click="emitOk"
      >
        Yes
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'CancelAppointmentDialog',
  methods: {
    emitOk() {
      this.$emit('ok');
    },
    emitCancelled() {
      this.$emit('cancel');
    },
  },
};
</script>
