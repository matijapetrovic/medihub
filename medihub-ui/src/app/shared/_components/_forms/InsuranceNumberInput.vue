<template>
  <v-text-field
    label="Insurance number"
    type="text"
    prepend-icon="mdi-shield-account"
    :value="value"
    @input="$emit('input', $event)"
    :rules="rules"
    required
  >
  </v-text-field>
</template>

<script>
export default {
  name: 'InsuranceNumberInput',
  props: {
    value: String,
  },
  data: () => ({
    rules: [
      (value) => !!value || 'Insurance number is required',
      (value) => value.length === 11 || 'Insurance number must have 11 digits',
      (value) => /[0-9]{11}/.test(value) || 'Insurance number must contain only digits',
    ],
  }),
};
</script>
