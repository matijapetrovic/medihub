<template>
  <div class="home">
    <h2>{{ getMessage }}</h2>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'Home',
  methods: {
    ...mapActions(['test']),
  },
  computed: mapGetters(['getMessage']),
  created() {
    this.test();
  },
};
</script>
