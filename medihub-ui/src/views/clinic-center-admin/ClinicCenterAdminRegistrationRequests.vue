<template>
  <div>
    <h1>Registration requests</h1>
    <RegistrationRequests
      v-if="registrationRequests.length"
      :registration-requests="registrationRequests"
    ></RegistrationRequests>
    <p v-else>
      No registration requests available.
    </p>
  </div>
</template>

<script>
import RegistrationRequests from '@/app/registration_requests/components/RegistrationRequests.vue';
import { mapActions, mapState } from 'vuex';

export default {
  components: {
    RegistrationRequests,
  },
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
