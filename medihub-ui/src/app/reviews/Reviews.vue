<template>
  <div>
    <v-row>
      <v-col md="6">
        <v-select
          v-model="item"
          :items="items"
          item-text="name"
          return-object
          :label="label"
          @change="resetRating"
        >
        </v-select>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-rating
          v-model="rating"
          color="yellow darken-3"
          background-color="grey darken-1"
          empty-icon="$ratingFull"
          half-increments
          hover
        ></v-rating>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn
          @click="emitRated"
        >
          Rate
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: 'Reviews',
  props: {
    items: {
      type: Array,
      required: true,
    },
    label: {
      type: String,
      required: true,
    },
  },
  data: () => ({
    item: {},
    rating: null,
  }),
  methods: {
    emitRated() {
      this.$emit('rated', { id: this.item.id, rating: this.rating });
      this.resetRating();
    },
    resetRating() {
      this.rating = null;
    },
  },
};
</script>
