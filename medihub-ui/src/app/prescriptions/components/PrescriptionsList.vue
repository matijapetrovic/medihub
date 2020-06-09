<template>
    <v-data-table
    :headers="headers"
    :items="prescriptions"
    :items-per-page="5"
    class="elevation-1"
  >
    <template v-slot:item.validation="{ item }">
        <v-btn
          style="margin-right: 15px"
          small
          color="primary"
          @click="accept(item)"
        >
          Accept
        </v-btn>
    </template>
  </v-data-table>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'PrescriptionsList',
  data: () => ({
    headers: [
      {
        text: 'Drug',
        align: 'start',
        sortable: true,
        value: 'drug',
      },
      {
        text: 'Validate prescription',
        value: 'validation',
        align: 'end',
        sortable: false,
      },
    ],
  }),
  computed: {
    ...mapState('prescriptions', ['prescriptions']),
  },
  methods: {
    ...mapActions('prescriptions', ['getPrescriptions', 'acceptPrescription']),
    accept(item) {
      this.acceptPrescription(item.id);
    },
  },
  mounted() {
    this.getPrescriptions();
  },
};
</script>
