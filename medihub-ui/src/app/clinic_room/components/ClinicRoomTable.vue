<template>
  <v-data-table
    :headers="headers"
    :items="clinicRooms"
    :items-per-page="5"
    class="elevation-1"
  >
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
    ],
  }),
  methods: {
    ...mapActions('clinicRooms', ['fetchClinicRooms']),
  },
  computed: {
    ...mapState('clinicRooms', ['clinicRooms']),
  },
  mounted() {
    this.fetchClinicRooms();
  },
};
</script>
