<template>
  <v-card
    class="elevation-16 mx-auto"
  >
    <v-card-title
      class="headline"
      primary-title
    >
      Reject registration request
    </v-card-title>
    <v-card-text>
      <v-form>
        <v-textarea
          v-model="text"
          label="Rejection reason"
        >
        </v-textarea>
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions class="justify-space-between">
      <v-btn text @click="emitCancelled">Cancel</v-btn>
      <v-btn
        :disabled="text.length === 0"
        color="primary"
        text
        @click="emitSubmit"
      >
        Submit
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'RejectionDialog',
  data: () => ({
    text: '',
  }),
  methods: {
    emitSubmit() {
      this.$emit('submit', this.text);
      this.text = '';
    },
    emitCancelled() {
      this.$emit('cancel');
      this.text = '';
    },
  },
};
</script>
