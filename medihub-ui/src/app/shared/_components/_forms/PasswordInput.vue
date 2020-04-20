<template>
  <v-text-field
    label="Password"
    type="password"
    prepend-icon="lock"
    :value="value"
    @input="$emit('input', $event)"
    :rules="rules"
    required
  >
  </v-text-field>
</template>

<script>
export default {
  name: 'PasswordInput',
  props: {
    value: {
      type: String,
      default: '',
    },
  },
  data: () => ({
    rules: [
      (value) => !!value || 'Password is required',
      (value) => value.length >= 8 || 'Password must be more than 8 characters long',
      (value) => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(value) || 'Password must contain at least one letter and one number',
    ],
  }),
};
</script>
