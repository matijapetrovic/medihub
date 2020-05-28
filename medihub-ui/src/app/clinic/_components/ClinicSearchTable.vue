<template>
  <v-data-table
    :headers="headers"
    :items="items"
    :items-per-page="5"
    class="elevation-1"
  >
    <template v-slot:body="{ items }">
        <tbody>
          <tr v-for="item in items" :key="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.address }}</td>
            <td>{{ item.city }}</td>
            <td>{{ item.country }}</td>
            <td>{{ item.rating }}({{ item.ratingCount}})</td>
            <td>{{ item.appointmentPrice }}</td>
            <td><v-btn @click="routeDoctors(item.id)">See doctors -></v-btn></td>
          </tr>
        </tbody>
      </template>
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
  methods: {
    routeDoctors(clinicId) {
      this.$router.push(`/search-doctors/${clinicId}`);
    },
  },
};
</script>
