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
  name: 'DoctorSearchTable',
  data: () => ({
    headers: [
      {
        text: 'First Name',
        align: 'start',
        sortable: true,
        value: 'firstName',
      },
      { text: 'Last Name', value: 'lastName' },
      { text: 'Rating', value: 'rating' },
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
