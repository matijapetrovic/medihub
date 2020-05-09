<template>
  <v-data-table
    :headers="headers"
    :items="clinicRooms"
    :items-per-page="5"
    class="elevation-1"
  >
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
  name: 'ClinicRoomTable',
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
  }),
  methods: {
    ...mapActions('clinicRooms', ['fetchClinicRooms', 'deleteClinicRoom']),
    deleteItem(item) {
      this.deleteClinicRoom(item);
    },
  },
  computed: {
    ...mapState('clinicRooms', ['clinicRooms']),
  },
  mounted() {
    this.fetchClinicRooms();
  },
};
</script>
