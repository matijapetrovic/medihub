<template>
  <div>
    <v-navigation-drawer
      app
      clipped
      color="grey lighten-4"
    >
      <slot name="sidebar">
      </slot>
    </v-navigation-drawer>
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
