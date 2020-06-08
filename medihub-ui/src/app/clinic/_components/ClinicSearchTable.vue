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
        :coords="coords"
      />
    </v-dialog>
  </div>
</template>

<script>
import ClinicProfile from '@/app/clinic/_components/profile/ClinicProfile.vue';
import { mapActions } from 'vuex';
import axios from 'axios';

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
    apiKey: 'daf8ca4b-3c4e-4396-9bff-8c6b22f7e69a',
    dialog: false,
    clinic: {},
    coords: [],
  }),
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  methods: {
    ...mapActions('predefinedAppointment', ['fetchPredefinedAppointments']),
    openClinicProfile(clinic) {
      this.clinic = clinic;
      this.fetchPredefinedAppointments(this.clinic.id);
      this.geocodeAddress()
        .then((response) => {
          this.coords = this.parseCoords(response.data.response.GeoObjectCollection
            .featureMember[0].GeoObject.Point.pos);
        });
      this.dialog = true;
    },
    geocodeAddress() {
      return axios.get(`https://geocode-maps.yandex.ru/1.x/?apikey=${this.apiKey}&geocode=${this.formatAddress()}&format=json`);
    },
    formatAddress() {
      return `${this.clinic.country} ${this.clinic.address} ${this.clinic.city}`.replace(' ', '+');
    },
    parseCoords(coords) {
      const tokens = coords.split(' ');
      return [+tokens[0], +tokens[1]];
    },
  },
};
</script>
