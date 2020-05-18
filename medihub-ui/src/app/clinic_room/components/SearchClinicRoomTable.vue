<template>
  <v-container>
    <v-row>
      <v-col>
        <v-text-field
          v-model="name"
          label="Room name"
          prepend-icon="room"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="number"
          label="Room number"
          prepend-icon="format_list_bulleted"
          type="number"
          :rules="[minNumberRule]"
          min="1"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
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
      </v-col>
      <v-col>
        <v-text-field
          v-model="time"
          label="Time"
          prepend-icon="mdi-timelapse"
          min="1"
          readonly="true">
        </v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-container class="px-0" fluid>
          <v-radio-group v-model="radioGroup">
            <v-radio
              v-for="n in 3"
              :key="n"
              :label="`Radio ${n}`"
              :value="n"
            ></v-radio>
          </v-radio-group>
        </v-container>
      </v-col>
      <v-select
        :items="items"
        label="Solo field"
        dense
        solo
      ></v-select>
      <v-col>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-spacer></v-spacer>
      <v-col>
        <v-btn color="primary" medium @click="search">Search</v-btn>
      </v-col>
      <v-col>
        <v-btn medium @click="reset" >Reset search</v-btn>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <v-data-table
      v-if="clinicRooms.length"
      :headers="headers"
      :items="clinicRooms"
      :items-per-page="5"
      class="elevation-1"
      show-expand
      single-expand
      item-key="id"
    >
      <template v-slot:expanded-item="{ headers, item }">
        <td :colspan="headers.length">First free: {{ item.firstFree }}</td>
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
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  data: () => ({
    checkbox: true,
    radioGroup: 1,
    switch1: true,
    time: null,
    dialog: false,
    name: null,
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
    search() {
      this.fetchClinicRooms({
        name: this.name,
        number: this.number,
        date: this.date,
      });
      this.clear();
      this.close();
    },
    reset() {
      this.fetchClinicRooms();
    },
    clear() {
      this.name = null;
      this.number = null;
      this.date = this.today;
    },
    fetchDateAndTime() {
      this.date = this.$route.params.date;
      this.time = this.$route.params.time;
      this.doctor = this.$route.params.doctor;
    },
  },
  mounted() {
    this.fetchClinicRooms();
    this.fetchDateAndTime();
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
  watch: {
    dialog(val) {
      return () => val || this.close();
    },
  },
};
</script>
