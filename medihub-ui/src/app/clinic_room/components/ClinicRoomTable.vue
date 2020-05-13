<template>
  <v-container>
    <v-dialog v-model="dialog" max-width="500px">
      <template v-slot:activator="{ on }">
        <v-btn
        color="primary"
        dark v-on="on">Search Clinic Room</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Search Clinic Room</span>
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-row>
                <v-text-field
                  v-model="number"
                  label="Room number"
                  prepend-icon="event"
                  type="number"
                  :rules="[requiredRule, minNumberRule,]"
                ></v-text-field>
                <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="date"
                      label="Appointment Date"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="date"
                    :min="today"
                    no-title
                    scrollable
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
            </v-row>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
          <v-btn color="blue darken-1" text @click="save"
          :disabled="!number || !date">Search</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'ClinicRoomTable',
  data: () => ({
    dialog: false,
    number: null,
    date: new Date().toISOString().substr(0, 10),
    menu: null,
    today: new Date().toISOString().substr(0, 10),
    headers: [
      {
        text: 'Name',
        align: 'start',
        sortable: true,
        value: 'name',
      },
      { text: 'Number ', value: 'number' },
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
    close() {
      this.dialog = false;
    },

    save() {
      this.close();
    },
  },
  computed: {
    ...mapState('clinicRooms', ['clinicRooms']),
    minNumberRule() {
      return (value) => value > 0 || 'Number must be positive';
    },
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  mounted() {
    this.fetchClinicRooms();
  },
  watch: {
    dialog(val) {
      return () => val || this.close();
    },
  },
};
</script>
