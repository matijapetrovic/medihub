<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      class="elevation-1"
    >
      <template v-slot:body="{ items }">
          <tbody>
            <tr v-for="item in items" :key="item.name">
              <td>
                <v-btn
                  color="red lighten-2"
                  dark
                  text
                  @click.stop="openClinicProfile(item)"
                >
                  {{ item.name }}
                </v-btn>
              </td>
              <td>{{ item.address }}</td>
              <td>{{ item.city }}</td>
              <td>{{ item.country }}</td>
              <td>{{ item.rating }}({{ item.ratingCount}})</td>
              <td>{{ item.appointmentPrice }}</td>
            </tr>
          </tbody>
        </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      max-width="800"
    >
      <ClinicProfile
        :clinic="clinic"
      />
    </v-dialog>
  </div>
</template>

<script>
import ClinicProfile from '@/app/clinic/_components/profile/ClinicProfile.vue';

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
    clinic: {},
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  methods: {
    openClinicProfile(clinic) {
      this.clinic = clinic;
      this.dialog = true;
    },
  },
};
</script>
