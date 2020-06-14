<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="items"
      class="elevation-1"
    >
      <template v-slot:item.type="{ item }">
        <v-chip :color="getColor(item.type)" small dark>{{ item.type }}</v-chip>
      </template>
      <template v-slot:item.calendar="{ item }">
        <v-btn small rounded @click="editItem(item)"> Working calendar</v-btn>
      </template>
      <template v-slot:item.approve="{ item }">
        <v-btn
          rounded
          small
          color="success"
          dark
          @click="approveRequest(item)"
        >Approve
        </v-btn> /
        <v-btn
          rounded
          small
          color="error"
          dark
          @click="openRejectionDialog(item)"
          >
          Reject
        </v-btn>
      </template>
    </v-data-table>
    <v-dialog v-model="dialog" max-width="700px">
      <v-card>
        <v-card-title>
          Working calendar
        </v-card-title>
        <v-card-text >
          <NurseWorkingCalendar :nurseId="editedItem.nurseId"></NurseWorkingCalendar>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog
      v-model="rejectDialog"
      max-width="500"
    >
      <RejectionDialog
        @submit="rejectRequest($event)"
        @cancel="closeRejectionDialog"
      />
    </v-dialog>
  </v-container>
</template>

<script>
import NurseWorkingCalendar from '@/app/medical_nurse/components/NurseWorkingCalendar.vue';
import { mapActions, mapState } from 'vuex';
import RejectionDialog from './RejectDialog.vue';

export default {
  name: 'LeaveRequests',
  request: null,
  components: {
    NurseWorkingCalendar,
    RejectionDialog,
  },
  data: () => ({
    rejectDialog: false,
    headers: [
      {
        text: 'Nurse',
        align: 'start',
        value: 'nurseEmail',
      },
      { text: 'Type', value: 'type' },
      { text: 'Working calendar', value: 'calendar' },
      { text: 'Approval / Rejection', value: 'approve' },
    ],
    editedIndex: -1,
    editedItem: {
      id: null,
      type: null,
      doctorId: null,
      doctorEmail: null,
    },
    dialog: false,
    approve: false,
    menu: null,
    today: new Date().toISOString().substr(0, 10),
    items: [],
  }),
  mounted() {
    this.getNurseLeaveRequests()
      .then(() => this.setItems());
  },
  methods: {
    ...mapActions('leaveRequest', ['getNurseLeaveRequests', 'deleteNurseLeaveRequest', 'approveNurseLeaveRequest']),
    ...mapActions('medicalDoctor', ['workingCalendar']),
    setItems() {
      this.nurseLeaveRequests.forEach((item) => this.items.push({
        id: item.id,
        type: item.type,
        nurseId: item.nurse,
        nurseEmail: item.email,
        start: item.start,
        end: item.end,
      }));
    },
    approveRequest(item) {
      this.approveNurseLeaveRequest({
        id: item.id,
        medicalDoctorId: item.nurseId,
      });
      this.deleteItem(item);
    },
    rejectRequest(reason) {
      this.deleteItem(this.request);
      this.deleteNurseLeaveRequest({ id: this.request.id, rejectReason: reason })
        .then(() => {
          this.closeRejectionDialog();
        });
    },
    deleteItem(item) {
      const index = this.items.map((e) => e.id).indexOf(item.id);
      this.items.splice(index, 1);
    },
    getColor(type) {
      if (type === 'VACATION') return 'green';
      return 'deep-purple';
    },
    editItem(item) {
      this.editedIndex = this.items.map((e) => e.id).indexOf(item.id);
      this.editedItem = item;
      this.dialog = true;
    },
    openRejectionDialog(request) {
      this.request = request;
      this.rejectDialog = true;
    },
    closeRejectionDialog() {
      this.rejectDialog = false;
    },
  },
  computed: {
    ...mapState('leaveRequest', ['nurseLeaveRequests']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
