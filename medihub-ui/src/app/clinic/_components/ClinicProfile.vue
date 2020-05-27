<template>
  <v-card
  >
    <v-card-title>{{ clinic.name }}</v-card-title>
    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="clinic.rating"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>
        <div class="grey--text ml-4">{{ clinic.rating }} (413)</div>
      </v-row>
      <div class="mt-4">{{ clinic.description }}</div>
    </v-card-text>
    <v-divider class="mx-4"></v-divider>
    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="seeDoctors"
      >
        Doctors
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'ClinicProfile',
  props: {
    clinic: {
      required: true,
      type: Object,
    },
  },
  methods: {
    seeDoctors() {
      this.$router.push(`/search-doctors/${this.clinic.id}`);
    },
  },
};
</script>
