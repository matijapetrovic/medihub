<template>
  <v-card max-width="1000"
  >
    <v-card-title>
      Clinic information
    </v-card-title>
    <v-row>
      <v-spacer></v-spacer>
      <v-col>
        <v-text-field
        v-model="clinic.name"
        label="Name"
        prepend-icon="person"
        >
        </v-text-field>
      </v-col>
      <v-col class="col-7"></v-col>
      <v-spacer></v-spacer>
    </v-row>
    <v-row>
      <v-spacer></v-spacer>
      <v-col class="col-4">
        <v-card>
          <v-card-title>
            Address info
          </v-card-title>
          <v-col>
            <v-text-field
            v-model="editedClinic.addressLine"
            label="Address line"
            prepend-icon="mdi-home"
            ></v-text-field>
          </v-col>
          <v-col>
            <CountrySelect
              v-if="editedClinic.country"
              v-model="editedClinic.country"
            />
          </v-col>
          <v-col>
            <v-text-field
            v-model="editedClinic.city"
            label="City"
            prepend-icon="mdi-city"
            ></v-text-field>
          </v-col>
        </v-card>
      </v-col>
      <v-spacer></v-spacer>
      <v-col class="col-5">
        <v-row>
          <v-combobox
            v-model="editedClinic.admins"
            item-text="account.email"
            multiple
            chips
            primary-chips
            label="Admins"
            prepend-icon="mdi-account-multiple"
            readonly
          >
          </v-combobox>
        </v-row>
        <v-spacer></v-spacer>
        <v-row>
          <v-textarea
            v-model="editedClinic.description"
            label="Description"
            auto-grow
            outlined
            rows="5"
            row-height="25"
            shaped
          ></v-textarea>
        </v-row>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <v-row>
      <v-spacer></v-spacer>
      <v-card-actions>
        <v-row>
          <v-btn
          color="primary"
          large
          @click="update()"
          >
          Update
          </v-btn>
        </v-row>
      </v-card-actions>
      <v-spacer></v-spacer>
    </v-row>
  </v-card>
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
    editedClinic: {},
    prices: [],
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
    this.getCurrentClinic().then(() => {
      this.editedClinic = this.clinic;
    });
  },
  methods: {
    ...mapActions('clinic', ['getCurrentClinic', 'updateClinic']),
    setPrices() {
      Object.keys(this.clinic.appointmentPrices).forEach((key) => {
        this.prices.push({ appointmentType: key, price: this.clinic.appointmentPrices[key] });
      });
    },
    infoChanged() {
      return JSON.stringify(this.clinic) === JSON.stringify(this.editedClinic);
    },
    update() {
      if (this.infoChanged()) {
        this.updateClinic({
          name: this.editedClinic.name,
          addressLine: this.editedClinic.addressLine,
          city: this.editedClinic.city,
          country: this.editedClinic.country,
          description: this.editedClinic.description,
        });
      }
    },
  },
  computed: {
    ...mapState('clinic', ['clinic']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
