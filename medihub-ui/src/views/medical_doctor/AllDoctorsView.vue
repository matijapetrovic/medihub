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
      :items="doctors"
      sort-by="firstName"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Medical Doctors</v-toolbar-title>
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
    ],
    editedIndex: -1,
    editedItem: {
      firstName: '',
      lastName: '',
      email: '',
      addressLine: '',
      workingTimeStarts: '',
      workingTimeEnds: '',
      clinicName: '',
    },
    defaultItem: {
      firstName: '',
      lastName: '',
      email: '',
      addressLine: '',
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
  },

  created() {
    this.initialize();
  },

  methods: {
    ...mapActions('medicalDoctor', ['getAllDoctors']),
    initialize() {
      this.getAllDoctors();
    },

    editItem(item) {
      this.editedIndex = this.doctors.indexOf(item);
      this.editedItem = Object.assign(...item);
      this.dialog = true;
    },

    deleteItem(item) {
      const index = this.doctors.indexOf(item);
      return () => window.confirm('Are you sure you want to delete this item?') && this.doctors.splice(index, 1);
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
