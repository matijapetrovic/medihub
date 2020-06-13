<template>
  <div class="mx-auto">
    <p>Activating account...</p>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'ActivateAccount',
  methods: {
    ...mapActions('auth', ['activateAccount']),
    ...mapActions('notifications', ['add']),
  },
  mounted() {
    this.activateAccount(this.$route.params.accountId)
      .then(() => {
        this.$router.push('/login');
      })
      .catch(() => {
        this.$router.push('/login');
      });
  },
};
</script>
