<template>
  <div>
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
            @click="accept(item)"
          >
            Accept
          </v-btn>
          <v-btn
            small
            color="error"
            @click="openRejectionDialog(item)"
          >
            Reject
          </v-btn>
      </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      max-width="500"
    >
      <RejectionDialog
        @submit="reject($event)"
        @cancel="closeRejectionDialog"
      />
    </v-dialog>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import RejectionDialog from './RejectionDialog.vue';

export default {
  name: 'RegistrationRequests',
  components: {
    RejectionDialog,
  },
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
    request: null,
    dialog: false,
  }),
  props: {
    registrationRequests: {
      type: Array,
      required: true,
    },
  },
  methods: {
    ...mapActions('registrationRequest', ['acceptRegistrationRequest', 'rejectRegistrationRequest']),
    accept(request) {
      this.acceptRegistrationRequest(request.id);
    },
    reject(reason) {
      this.rejectRegistrationRequest({ requestId: this.request.id, reason })
        .then(() => {
          this.closeRejectionDialog();
        });
    },
    openRejectionDialog(request) {
      this.request = request;
      this.dialog = true;
    },
    closeRejectionDialog() {
      this.dialog = false;
    },
  },
};
</script>
