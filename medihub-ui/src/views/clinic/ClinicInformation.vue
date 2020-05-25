<template>
  <v-container>
    <v-tabs
      background-color="blue accent-4"
      center-active
      dark
    >
      <v-tab>Clinic Info</v-tab>
      <v-tab>Medical Doctors</v-tab>
      <v-tab>Clinic Rooms</v-tab>
    </v-tabs>
    <v-form>
      <v-row>
        <v-col>
          <v-text-field
          v-model="clinic.name"
          label="Name"
          ></v-text-field>
          <v-combobox
            v-model="editedClinic.admins"
            item-text="account.email"
            multiple
            chips
            primary-chips
            label="Admins"
            prepend-icon="mdi-account"
            readonly
          ></v-combobox>
          <v-card>
            <v-card-title>
              Address info
            </v-card-title>
            <v-col>
              <v-text-field
              v-model="editedClinic.addressLine"
              label="Address line"
              ></v-text-field>
            </v-col>
            <v-col>
              <CountrySelect
                v-model="editedClinic.country"
              />
            </v-col>
            <v-col>
              <v-text-field
              v-model="editedClinic.city"
              label="City"
              ></v-text-field>
            </v-col>
          </v-card>
        </v-col>
        <v-spacer></v-spacer>
        <v-col>
          <v-spacer></v-spacer>
          <v-card  max-width="330">
            <v-card-title>Prices</v-card-title>
            <v-data-table
              :headers="pricesHeaders"
              :items="prices"
            ></v-data-table>
          </v-card>
        </v-col>
      </v-row>
      <v-spacer></v-spacer>
      <v-row>
        <v-col>
          <v-textarea
            v-model="editedClinic.description"
            label="Description"
            auto-grow
            outlined
            rows="5"
            row-height="25"
            shaped
          ></v-textarea>
        </v-col>
      </v-row>
      <v-btn
      color="primary"
      large
      >Update</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import CountrySelect from '@/app/country/_components/CountrySelect.vue';

export default {
  name: 'ClinicInformation',
  components: {
    CountrySelect,
  },
  data: () => ({
    editedClinic: null,
    prices: [],
    pricesHeaders: [
      {
        text: 'Appointment type',
        align: 'start',
        sortable: false,
        value: 'appointmentType',
      },
      { text: 'Price', value: 'price' },
    ],
    clinicRoomHeaders: [
      {
        text: 'Name',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: 'Number', value: 'number' },
    ],
    medicalDoctorsHeaders: [
      {
        text: 'First name',
        align: 'start',
        sortable: false,
        value: 'firstName',
      },
      { text: 'Last name', value: 'lastName' },
      { text: 'Email', value: 'email' },
      { text: 'Price', value: 'price' },
    ],
  }),
  mounted() {
    this.getCurrentClinic().then(() => { this.editedClinic = this.clinic; });
  },
  methods: {
    ...mapActions('clinic', ['getCurrentClinic']),
    setPrices() {
      Object.keys(this.clinic.appointmentPrices).forEach((key) => {
        this.prices.push({ appointmentType: key, price: this.clinic.appointmentPrices[key] });
      });
    },
    infoChanged() {
      return JSON.stringify(this.clinic) === JSON.stringify(this.editedClinic);
    },
  },
  computed: {
    ...mapState('clinic', ['clinic']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
    minNumberRule() {
      return (value) => value > 0 || 'Number must be positive!';
    },
  },
};
</script>
