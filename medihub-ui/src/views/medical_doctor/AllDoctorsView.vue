<template>
  <v-container>
    <v-row
      class="mb-6"
      no-gutters
    >
    <v-col
      cols="12"
      lg="12"
    >
    <v-data-table
      :headers="headers"
      :items="doctors"
      :search="search"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Medical Doctors</v-toolbar-title>
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
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.firstName" label="First name">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.lastName" label="Last name">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.email" label="Email">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.addressLine" label="Address">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.telephone" label="Telephone number">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.workingTimeStarts" label="Star of work">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.workingTimeEnds" label="End of work">
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.clinicName" label="Clinic name">
                      </v-text-field>
                    </v-col>
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
        text: 'First name',
        align: 'start',
        value: 'firstName',
      },
      { text: 'Last name', value: 'lastName' },
      { text: 'Email', value: 'email' },
      { text: 'Address', value: 'addressLine' },
      { text: 'Telephone', value: 'telephone' },
      { text: 'Start of work', value: 'workingTimeStarts' },
      { text: 'End of work', value: 'workingTimeEnds' },
      { text: 'Clinic name', value: 'clinicName' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    editedIndex: -1,
    editedItem: {
      firstName: '',
      lastName: '',
      email: '',
      addressLine: '',
      telephone: '',
      workingTimeStarts: '',
      workingTimeEnds: '',
      clinicName: '',
    },
    defaultItem: {
      firstName: '',
      lastName: '',
      email: '',
      addressLine: '',
      telephone: '',
      workingTimeStarts: '',
      workingTimeEnds: '',
      clinicName: '',
    },
  }),

  computed: {
    ...mapState('medicalDoctor', ['doctors']),
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item';
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
    ...mapActions('medicalDoctor', ['getAllDoctorsForClinic', 'deleteDoctor']),
    initialize() {
      this.getAllDoctorsForClinic();
    },
    editItem(item) {
      this.editedIndex = this.doctors.indexOf(item);
      this.editedItem = Object.assign(...item);
      this.dialog = true;
    },

    deleteItem(item) {
      this.deleteDoctor(item.id);
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign(...this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.doctors[this.editedIndex], this.editedItem);
      } else {
        this.doctors.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>
