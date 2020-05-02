<template>
  <v-snackbar
    v-model="notificationActive"
    :absolute="false"
    :color="notification.color"
    :top="y === 'top'"
    :bottom="y === 'bottom'"
    :left="x === 'left'"
    :right="x === 'right'"
    :timeout="timeout"
  >
    {{ notification.text }}
    <v-btn
      dark
      text
      @click="remove"
    >
      Close
    </v-btn>
  </v-snackbar>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'NotificationBar',
  props: {
    x: {
      type: String,
      default: 'right',
    },
    y: {
      type: String,
      default: 'bottom',
    },
    timeout: {
      type: Number,
      default: 5000,
    },
  },
  computed: {
    ...mapState('notifications', ['notification']),
    notificationActive: {
      get() {
        return this.$store.state.notifications.notificationActive;
      },
      set(value) {
        this.$store.commit('notifications/SET_ACTIVE', value);
      },
    },
  },
  methods: mapActions('notifications', ['remove']),
};
</script>
