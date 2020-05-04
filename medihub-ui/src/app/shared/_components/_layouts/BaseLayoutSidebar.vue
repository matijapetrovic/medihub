<template>
  <div>
    <slot name="sidebar">
    </slot>
    <v-container
      fluid
      class="fill-height"
    >
      <slot>
      </slot>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'BaseLayoutSidebar',
};
</script>
