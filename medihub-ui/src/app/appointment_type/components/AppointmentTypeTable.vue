<template>
  <div>
      <v-data-table
      :headers="headers"
      :items="appointmentTypes"
      :items-per-page="5"
      :search="search"
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
    <AppointmentEditDialog ref="editDialog"></AppointmentEditDialog>
    <ConfirmDialog
    @confirmResponse="setConfirmation($event)"
    ref="confirm"
    />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import ConfirmDialog from '@/app/shared/_components/dialogs/ConfirmDialog.vue';
import AppointmentEditDialog from './AppointmentEditDialog.vue';

export default {
  name: 'AppoitmentTypeTable',
  components: {
    AppointmentEditDialog,
    ConfirmDialog,
  },
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
    ...mapActions('appointmentType', ['fetchAppointmentTypes', 'removeAppointmentType']),
    deleteItem(item) {
      this.editedItem = item;
      this.$refs.confirm.open('Delete confirmation', 'Are you sure you want to delete appointment type', null);
    },
    editItem(item) {
      this.$refs.editDialog.show(item);
    },
    setConfirmation(signal) {
      if (signal) {
        this.removeAppointmentType(this.editedItem);
      }
    },
  },
  computed: {
    ...mapState('appointmentType', ['appointmentTypes']),
  },
  mounted() {
    this.fetchAppointmentTypes();
  },
};
</script>
