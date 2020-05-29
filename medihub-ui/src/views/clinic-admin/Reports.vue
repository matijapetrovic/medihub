<template>
  <v-container>
    <v-row>
      <v-col cols=8>
        <v-subheader>
          Clinic rating: <v-rating readonly v-model="clinic.rating"></v-rating>
        </v-subheader>
      </v-col>
      <v-col>
        <v-subheader>
          Number of votes: {{clinic.count}}
        </v-subheader>
      </v-col>
    </v-row>
    <v-spacer></v-spacer>
    <v-row>
      <v-row>
        <v-sheet height="64">
          <v-toolbar flat color="white">
            <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
              Today
            </v-btn>
            <v-btn fab text small color="grey darken-2" :disabled="type === 'year'" @click="prev">
              <v-icon small>mdi-chevron-left</v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" :disabled="type==='year'" @click="next">
              <v-icon small>mdi-chevron-right</v-icon>
            </v-btn>
            <v-toolbar-title> {{ title }} </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-menu bottom right>
              <template v-slot:activator="{ on }">
                <v-btn
                  outlined
                  color="grey darken-2"
                  v-on="on"
                >
                  <span>{{ typeToLabel[type] }}</span>
                  <v-icon right>mdi-menu-down</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item @click="type = 'day'">
                  <v-list-item-title>Day</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = 'month'">
                  <v-list-item-title>Month</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = 'year'">
                  <v-list-item-title>Year</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-toolbar>
        </v-sheet>
      </v-row>
      <v-row>
        <VueApexCharts
        width="700"
        type="line"
        :options="options"
        :series="series">
        </VueApexCharts>
      </v-row>
    </v-row>
    <v-data-table
      :headers="headers"
      :items="doctors"
      class="elevation-1"
    >
      <template v-slot:item.rating="{ item }">
        <v-rating readonly v-model="item.rating"></v-rating>
      </template>
    </v-data-table>
    <v-btn @click="a()"></v-btn>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import VueApexCharts from 'vue-apexcharts';

export default {
  name: 'Reports',
  components: {
    VueApexCharts,
  },
  data: () => ({
    monthNames: [
      'January', 'February', 'March', 'April', 'May', 'June',
      'July', 'August', 'September', 'October', 'November', 'December',
    ],
    type: 'day',
    date: new Date(),
    typeToLabel: {
      year: 'Year',
      month: 'Month',
      day: 'Day',
    },
    request: {
      type: 'day',
      date: new Date().toISOString().substr(0, 10),
    },
    headers: [
      {
        text: 'Email',
        align: 'start',
        value: 'email',
      },
      { text: 'Name', value: 'firstName' },
      { text: 'Surname', value: 'lastName' },
      { text: 'Rating', value: 'rating' },
    ],
    options: {
      chart: {
        id: 'vuechart-example',
        width: '100%',
        toolbar: {
          tools: {
            download: false,
          },
        },
      },
    },
    series: [{
      name: 'Number of appointments',
      data: [],
    }],
  }),
  mounted() {
    this.getCurrentClinic();
    this.getAllDoctors();
    this.fetchClinicFinishedAppointments(this.request)
      .then(() => {
        this.updateChart();
      });
  },
  methods: {
    ...mapActions('clinic', ['getCurrentClinic']),
    ...mapActions('medicalDoctor', ['getAllDoctors']),
    ...mapActions('finishedAppointment', ['fetchClinicFinishedAppointments']),
    updateChart() {
      const arr = [];
      this.finishedAppointments.forEach((element) => {
        arr.push({
          x: element.type === 'day' ? element.time : element.date,
          y: element.count,
        });
      });
      this.series = [{
        data: arr,
      }];
    },
    setToday() {
      this.date = new Date();
    },
    prev() {
      if (this.type === 'day') {
        this.date.setDate(this.date.getDate() - 1);
      } else if (this.type === 'month') {
        this.date.setMonth(this.date.getMonth() - 1);
      }
    },
    next() {
      console.log(this.date.setDate(this.date.getDate() + 1));
      if (this.type === 'day') {
        this.date.setDate(this.date.getDate() + 1);
      } else if (this.type === 'month') {
        this.date.setMonth(this.date.getMonth() + 1);
      }
    },
    a() {
      console.log(this.date);
    },
  },
  computed: {
    ...mapState('clinic', ['clinic']),
    ...mapState('medicalDoctor', ['doctors']),
    ...mapState('finishedAppointment', ['finishedAppointments']),
    title() {
      switch (this.type) {
        case 'year':
          return `${this.date.getUTCFullYear()}`;
        case 'month':
          return `${this.monthNames[this.date.getUTCMonth()]} ${this.date.getUTCFullYear()}`;
        case 'day':
          return `${this.date.getUTCDate()} ${this.monthNames[this.date.getUTCMonth()]} ${this.date.getUTCFullYear()}`;
        default:
          return '';
      }
    },
  },
};
</script>
