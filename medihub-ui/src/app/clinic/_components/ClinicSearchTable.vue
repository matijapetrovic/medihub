<template>
  <v-data-table
    :headers="headers"
    :items="items"
    :items-per-page="5"
    class="elevation-1"
  >
  </v-data-table>
</template>

<script>
export default {
  name: 'ClinicSearchTable',
  data: () => ({
    headers: [
      {
        text: 'Name',
        align: 'start',
        sortable: true,
        value: 'name',
      },
      { text: 'Address', value: 'address' },
      { text: 'City', value: 'city' },
      { text: 'Country', value: 'country' },
      { text: 'Rating', value: 'rating' },
      { text: 'Price', value: 'appointmentPrice' },
    ],
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
};
</script>
