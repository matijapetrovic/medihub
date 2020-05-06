<template>
  <v-select
    :value="value"
    @input="$emit('input', $event)"
    :items="countries"
    item-text="name"
    label="Country"
    name="country"
    prepend-icon="mdi-earth"
    :rules="[requiredRule]"
  >
  </v-select>
</template>
<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'CountrySelect',
  props: {
    value: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState('country', ['countries']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  methods: {
    ...mapActions('country', ['fetchCountries']),
  },
  mounted() {
    this.fetchCountries();
  },
};
</script>
