<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="desserts"
      class="elevation-1"
    >
      <template v-slot:item.calories="{ item }">
        <v-chip :color="getColor(item.calories)" dark>{{ item.calories }}</v-chip>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'LeaveRequests',
  data: () => ({
    menu: null,
    today: new Date().toISOString().substr(0, 10),
  }),
  mounted() {
    this.getAllLeaveRequests();
  },
  methods: {
    ...mapActions('leaveRequest', ['getAllLeaveRequests']),
  },
  computed: {
    ...mapState('leaveRequests', ['leaveRequests']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
