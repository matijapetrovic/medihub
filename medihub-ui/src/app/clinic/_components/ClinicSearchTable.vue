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
            <td>{{ item.rating }}</td>
            <td>{{ item.appointmentPrice }}</td>
            <td>
              <v-dialog
                v-model="dialog"
                width="500"
              >
                <template v-slot:activator="{ on }">
                  <v-btn
                    color="red lighten-2"
                    dark
                    v-on="on"
                  >
                    Profile
                  </v-btn>
                </template>
                <ClinicProfile
                  :clinic="item"
                />
              </v-dialog>
              </td>
          </tr>
        </tbody>
      </template>
  </v-data-table>
</template>

<script>
import ClinicProfile from '@/app/clinic/_components/ClinicProfile.vue';

export default {
  name: 'ClinicSearchTable',
  components: {
    ClinicProfile,
  },
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
    dialog: false,
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
};
</script>
