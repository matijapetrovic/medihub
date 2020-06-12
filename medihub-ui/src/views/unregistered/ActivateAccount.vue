<template>
  <div class="mx-auto">
    <p>Activating account...</p>
  </div>
</template>

<script>
import utils from '@/utils';
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
        const message = 'Account activated successfully';
        this.add(utils.successNotification(message));
      })
      .catch((err) => {
        this.$router.push('/login');
        this.add(utils.errorNotification(err));
      });
  },
};
</script>
