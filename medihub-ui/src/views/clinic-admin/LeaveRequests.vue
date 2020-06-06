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
          @click="rejectRequest(item)"
          >
          Reject
        </v-btn>
      </template>
    </v-data-table>
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          Working calendar
        </v-card-title>
        <v-card-text>
          <WorkingCalendar :doctorId="editedItem.doctorId"></WorkingCalendar>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import WorkingCalendar from '@/app/shared/_components/DoctorWorkingCalendar.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'LeaveRequests',
  components: {
    WorkingCalendar,
  },
  data: () => ({
    headers: [
      {
        text: 'Doctor',
        align: 'start',
        value: 'doctorEmail',
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
    this.getAllLeaveRequests()
      .then(() => this.setItems());
  },
  methods: {
    ...mapActions('leaveRequest', ['getAllLeaveRequests', 'deleteLeaveRequest', 'approveLeaveRequest']),
    ...mapActions('medicalDoctor', ['workingCalendar']),
    setItems() {
      this.leaveRequests.forEach((item) => this.items.push({
        id: item.id,
        type: item.type,
        doctorId: item.medicalDoctor.id,
        medicalDoctor: item.medicalDoctor,
        doctorEmail: item.medicalDoctor.account.email,
        dates: item.dates,
      }));
    },
    approveRequest(item) {
      this.approveLeaveRequest({
        id: item.id,
        medicalDoctorId: item.medicalDoctor.id,
      });
      this.deleteItem(item);
    },
    rejectRequest(item) {
      this.deleteItem(item);
      this.deleteLeaveRequest(item.id);
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
  },
  computed: {
    ...mapState('leaveRequest', ['leaveRequests']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
