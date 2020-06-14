<template>
  <v-card
    class="elevation-16 mx-auto"
  >
    <v-card-title
      class="headline"
      primary-title
    >
      Confirm appointment schedule
    </v-card-title>
    <v-card-text>
      <p>Are you sure you want to schedule:</p>
      <v-list two-line>
        <v-list-item v-for="item in filteredHeaders" :key="item.name">
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>{{ appointment[item.name] }}</v-list-item-title>
            <v-list-item-subtitle>{{ item.text }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="appointment.price">
          <v-list-item-icon>
            <v-icon>mdi-cash</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>
              {{ appointment.discount ? appointment.price * (1 - appointment.discount)
                : appointment.price}}€
              <span v-if="appointment.discount">({{ appointment.discount * 100 }}% off)</span>
            </v-list-item-title>
            <v-list-item-subtitle>Price</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions class="justify-space-between">
      <v-btn text @click="emitCancelled">Cancel</v-btn>
      <v-btn
        color="primary"
        text
        @click="emitScheduled"
      >
        Schedule
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'ScheduleAppointmentDialog',
  data: () => ({
    headers: [
      { name: 'name', icon: 'mdi-stethoscope', text: 'Appointment type' },
      { name: 'doctor', icon: 'mdi-doctor', text: 'Doctor' },
      { name: 'date', icon: 'mdi-calendar', text: 'Date' },
      { name: 'time', icon: 'mdi-clock', text: 'Time' },
      { name: 'room', icon: 'mdi-door', text: 'Room' },
    ],
  }),
  props: {
    appointment: {
      required: true,
      type: Object,
    },
  },
  computed: {
    filteredHeaders() {
      return this.headers.filter((header) => this.appointment[header.name] !== undefined);
    },
  },
  methods: {
    emitScheduled() {
      this.$emit('scheduled');
    },
    emitCancelled() {
      this.$emit('cancelled');
    },
  },
};
</script>
