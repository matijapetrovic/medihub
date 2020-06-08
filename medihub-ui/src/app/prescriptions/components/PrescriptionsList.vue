<template>
    <div>Prescriptions List</div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'PrescriptionsList',
  computed: {
    ...mapState('prescriptions', ['prescriptions']),
  },
  methods: {
    ...mapActions('prescriptions', ['getPrescriptions']),
  },
};
</script>
