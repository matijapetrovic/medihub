<template>
    <v-data-table
    :headers="headers"
    :items="registrationRequests"
    :items-per-page="5"
    class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
        <v-btn
          small
          color="primary"
          @click="editItem(item)"
        >
          Accept
        </v-btn>
        <v-btn
          small
          color="error"
          @click="editItem(item)"
        >
          Reject
        </v-btn>
    </template>
  </v-data-table>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'RegistrationRequests',
  data: () => ({
    headers: [
      {
        text: 'First Name',
        align: 'start',
        sortable: true,
        value: 'firstName',
      },
      {
        text: 'Last Name',
        align: 'start',
        sortable: true,
        value: 'lastName',
      },
      {
        text: 'email',
        align: 'start',
        sortable: true,
        value: 'email',
      },
      {
        text: 'Telephone number',
        align: 'start',
        sortable: true,
        value: 'telephoneNumber',
      },
      {
        text: 'Insurance number',
        align: 'start',
        sortable: true,
        value: 'insuranceNumber',
      },
      {
        text: 'Actions',
        value: 'actions',
        sortable: false,
      },
    ],
  }),
  methods: {
    ...mapActions('registrationRequest', ['getRegistrationRequests']),
  },
  computed: {
    ...mapState('registrationRequest', ['registrationRequests']),
  },
  mounted() {
    this.getRegistrationRequests();
  },
};
</script>
