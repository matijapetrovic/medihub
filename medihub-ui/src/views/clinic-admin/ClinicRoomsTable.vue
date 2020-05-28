<template>
  <v-container>
    <v-row
      class="mb-6"
      no-gutters
    >
    <v-col
      cols="12"
      lg="10"
      offset-md="1"
    >
    <v-data-table
      :headers="headers"
      :items="clinicRooms"
      :search="search"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Clinic Rooms</v-toolbar-title>
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
          <v-dialog v-model="dialog" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.name" label="Name">
                      </v-text-field>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.number" label="Number">
                      </v-text-field>
                    </v-col>
                    <v-spacer></v-spacer>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="save">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
      <template v-slot:no-data>
        <v-btn color="primary" @click="initialize">Reset</v-btn>
      </template>
    </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  data: () => ({
    dialog: false,
    search: '',
    headers: [
      {
        text: 'Name',
        align: 'start',
        value: 'name',
      },
      { text: 'Number', value: 'number' },
      { text: 'Update', value: 'actions' },
    ],
    editedIndex: -1,
    editedItem: {
      name: '',
      number: '',
    },
    defaultItem: {
      name: '',
      number: '',
    },
  }),

  computed: {
    ...mapState('clinicRooms', ['clinicRooms']),
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Clinic Room';
    },
  },

  watch: {
    dialog(val) {
      return () => val || this.close();
    },
    search(val) {
      return () => val && val !== this.select && this.querySelections(val);
    },
  },

  created() {
    this.initialize();
  },

  methods: {
    ...mapActions('clinicRooms', ['fetchAllClinicRooms', 'updateClinicRoom', 'deleteClinicRoom']),
    initialize() {
      this.fetchAllClinicRooms();
    },
    editItem(item) {
      this.editedIndex = this.clinicRooms.indexOf(item);
      this.editedItem = item;
      this.dialog = true;
    },
    deleteItem(item) {
      if (window.confirm('Are you sure you want to delete this item?')) {
        const index = this.clinicRooms.map((e) => e.id).indexOf(item.id);
        this.deleteClinicRoom(item.id).then(() => {
          this.clinicRooms.splice(index, 1);
        });
      }
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        this.clinicRooms[this.editedIndex] = this.editedItem;
        this.updateClinicRoom(this.editedItem);
      } else {
        this.clinicRooms.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>
