<template>
  <v-card
    class="elevation-16 mx-auto"
  >
    <v-card-title
      class="headline"
      primary-title
    >
    <slot name="title"></slot>
    </v-card-title>
    <v-card-text>
      <slot></slot>
      <div class="text-center mt-12">
        <v-rating
          v-model="rating"
          color="yellow darken-3"
          background-color="grey darken-1"
          empty-icon="$ratingFull"
          half-increments
          hover
        ></v-rating>
      </div>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions class="justify-space-between">
      <v-btn text @click="emitClose">No Thanks</v-btn>
      <v-btn
        color="primary"
        text
        :disabled="rating === null"
        @click="emitRated"
      >
        Rate Now
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'ReviewDialog',
  data: () => ({
    rating: null,
  }),
  methods: {
    emitRated() {
      this.$emit('rated', this.rating);
    },
    emitClose() {
      this.$emit('closed');
    },
  },
};
</script>
