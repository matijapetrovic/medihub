<template>
  <v-text-field
    label="Email"
    type="email"
    prepend-icon="mail"
    :value="value"
    @input="$emit('input', $event)"
    :rules="rules"
    required
  >
  </v-text-field>
</template>

<script>
export default {
  name: 'EmailInput',
  props: {
    value: String,
  },
  data: () => ({
    rules: [
      (value) => !!value || 'Email is required',
      (value) => /.+@.+/.test(value) || 'E-mail must be valid',
    ],
  }),
};
</script>
