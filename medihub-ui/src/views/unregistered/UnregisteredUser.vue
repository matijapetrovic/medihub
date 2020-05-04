<template>
  <BaseLayoutCentered>
    <v-content>
      <router-view></router-view>
    </v-content>
  </BaseLayoutCentered>
</template>

<script>
import BaseLayoutCentered from '@/app/shared/_components/_layouts/BaseLayoutCentered.vue';

export default {
  name: 'UnregisteredUser',
  components: {
    BaseLayoutCentered,
  },
};
</script>
