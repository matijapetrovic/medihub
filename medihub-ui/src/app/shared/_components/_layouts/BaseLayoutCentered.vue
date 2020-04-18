<template>
  <v-container
    fluid
    class="fill-height"
  >
    <v-row
      align="center"
      justify="center"
    >
      <slot></slot>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'BaseLayoutCentered',
};
</script>
