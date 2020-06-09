<template>
  <div>
    <h1>{{ this.clinic.name }}</h1>
    <v-btn @click="goBack">Back</v-btn>
    <ClinicProfile
      :clinic="clinic"
      :coords="coords"
    />
    <v-divider class="my-5"></v-divider>
    <h2>Doctors</h2>
    <ClinicSearchForm
      :appointment-types="appointmentTypes"
      @searched="onSearched($event)"
    />
    <DoctorSearchTable
      :items="doctors"
    />
  </div>
</template>

<script>
import ClinicProfile from '@/app/clinic/_components/profile/ClinicProfile.vue';
import DoctorSearchTable from '@/app/doctor/_components/DoctorSearchTable.vue';
import ClinicSearchForm from '@/app/clinic/_components/ClinicSearchForm.vue';

import { mapActions, mapState } from 'vuex';
import axios from 'axios';

export default {
  name: 'PatientClinicProfile',
  components: {
    ClinicProfile,
    DoctorSearchTable,
    ClinicSearchForm,
  },
  data: () => ({
    apiKey: 'daf8ca4b-3c4e-4396-9bff-8c6b22f7e69a',
    coords: [],
  }),
  computed: {
    ...mapState('clinic', ['clinic']),
    ...mapState('doctor', ['doctors']),
    ...mapState('appointmentType', ['appointmentTypes']),
  },
  methods: {
    ...mapActions('predefinedAppointment', ['fetchPredefinedAppointments']),
    ...mapActions('clinic', ['fetchClinicProfile']),
    ...mapActions('doctor', ['fetchDoctors']),
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
    goBack() {
      this.$router.push('/search-clinics');
    },
  },
  mounted() {
    this.fetchClinicProfile(this.$route.params.clinic_id)
      .then(() => {
        this.geocodeAddress()
          .then((response) => {
            this.coords = this.parseCoords(response.data.response.GeoObjectCollection
              .featureMember[0].GeoObject.Point.pos);
          });
      });
    this.fetchPredefinedAppointments(this.$route.params.clinic_id);
    this.fetchDoctors(this.$route.params.clinic_id);
  },
};
</script>
