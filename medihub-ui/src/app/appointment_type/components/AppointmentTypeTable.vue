<template>
    <v-data-table
    :headers="headers"
    :items="appointmentTypes"
    :items-per-page="5"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Appointment Types</v-toolbar-title>
        <v-spacer auto></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
        <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          small
          @click="deleteItem(item)"
        >
          mdi-delete
        </v-icon>
      </template>
  </v-data-table>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'AppoitmentTypeTable',
  data: () => ({
    headers: [
      {
        text: 'Name',
        align: 'start',
        sortable: true,
        value: 'name',
      },
      {
        text: 'Actions',
        value: 'actions',
        sortable: false,
      },
    ],
    search: '',
  }),
  methods: {
    ...mapActions('appointmentType', ['fetchAppointmentTypes']),
  },
  computed: {
    ...mapState('appointmentType', ['appointmentTypes']),
  },
  mounted() {
    this.fetchAppointmentTypes();
  },
};
</script>
