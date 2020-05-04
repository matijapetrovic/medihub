<template>
  <v-navigation-drawer
    v-model="drawer"
    app
    clipped
    color="grey lighten-4"
  >
    <v-list
      dense
      class="grey lighten-4"
    >
      <template v-for="(item, i) in items">
        <v-list-group
          :key="i"
          v-if="item.children"
          :prepend-icon="item.icon"
        >
          <template v-slot:activator>
            <v-list-item-title>{{ item.text }}</v-list-item-title>
          </template>
          <v-list-item
            v-for="(child,i) in item.children"
            :key="i"
            link
            :to="child.path"
          >
            <v-list-item-icon>
              <v-icon v-text="child.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-title v-text="child.text"></v-list-item-title>
          </v-list-item>
        </v-list-group>
        <v-list-item
          v-else
          :key="i"
          link
          :to="item.path"
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>
              {{ item.text }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: 'ClinicAdminSidebar',
  data: () => ({
    drawer: null,
  }),
  props: {
    items: Array,
  },
};
</script>
