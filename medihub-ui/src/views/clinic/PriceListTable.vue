<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="items"
      class="elevation-1"
    >
      <template v-slot:item.price="{ item }">
        <div v-if="item.price === undefined" class="my-2">
          <v-btn
          rounded
          small
          color="success"
          @click="setItem(item)"
          >
            Add price
          </v-btn>
        </div>
        <div v-else>
          <v-btn
          rounded
          small
          color="success"
          @click="setItem(item)"
          >
            Edit price ({{item.price}} $)
          </v-btn>
        </div>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="fetchAppointmentTypes()">Reset</v-btn>
      </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      max-width="420"
    >
      <v-card>
        <v-card-title class="headline">
          Appointment type price
        </v-card-title>
        <v-card-text>
          <v-text-field
          v-if="editedItem.appointmentType"
          v-model="editedItem.appointmentType.name"
          label="Appointment type"
          readonly>
          </v-text-field>
          <v-text-field
          v-model="editedItem.price"
          label="Price"
          type="number"
          min="1"
          :rules="[requiredRule,]"
          >
          </v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="red darken-1"
            text
            @click="dialog=false"
          >
            Close
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="addAppointmentTypePricePrice()"
          >
            Add
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  data: () => ({
    dialog: false,
    headers: [
      {
        text: 'Appointment type',
        align: 'start',
        sortable: false,
        value: 'appointmentType.name',
      },
      { text: 'Price', value: 'price' },
    ],
    items: [],
    editedIndex: -1,
    editedItem: {
      appointmentType: null,
      price: null,
    },
  }),

  mounted() {
    this.fetchAppointmentTypes()
      .then(() => {
        this.fetchPrices()
          .then(() => {
            this.setItems();
          });
      });
  },
  methods: {
    ...mapActions('clinic', ['fetchPrices', 'addPrice']),
    ...mapActions('appointmentType', ['fetchAppointmentTypes']),
    editItem(item) {
      this.editedIndex = this.items.map((e) => e.id).indexOf(item.id);
      this.editedItem = item;
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.items.indexOf(item);
      window.confirm('Are you sure you want to delete this item?');
      this.items.splice(index, 1);
    },
    setItem(item) {
      this.editedItem = item;
      this.dialog = true;
    },
    setItems() {
      this.appointmentTypes.forEach((item) => this.items.push({
        appointmentType: item,
        price: this.prices[(item.id).toString()],
      }));
    },
    addAppointmentTypePricePrice() {
      this.addPrice({
        appointmentTypeId: this.editedItem.appointmentType.id,
        price: this.editedItem.price,
      });
      this.dialog = false;
    },
  },
  computed: {
    ...mapState('clinic', ['prices']),
    ...mapState('appointmentType', ['appointmentTypes']),
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
};
</script>
