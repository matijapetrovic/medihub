<template>
  <v-container>
    <v-row>
      <v-col cols=8>
        <v-row>
          <v-subheader>
            Clinic rating: <v-rating large readonly v-model="clinic.rating"></v-rating>
          </v-subheader>
          <v-avatar color="teal" size="48">
            <span class="white--text headline">{{clinic.rating}}</span>
          </v-avatar>
        </v-row>
      </v-col>
      <v-col cols=2>
        <v-subheader>
            Number of votes: <v-spacer></v-spacer> <v-avatar color="teal" size="48">
            <span class="white--text headline">{{clinic.count}}</span>
          </v-avatar>
        </v-subheader>
      </v-col>
    </v-row>
    <v-spacer></v-spacer>
    <v-row>
      <v-col>
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
              <v-col>
                <v-toolbar-title> {{ title }} </v-toolbar-title>
              </v-col>
              <v-menu bottom right>
                <template v-slot:activator="{ on }">
                  <v-btn
                    outlined
                    color="grey darken-2"
                    v-on="on"
                  >
                    <div>{{ typeToLabel[type] }}</div>
                    <v-icon right>mdi-menu-down</v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item @click="setTypeAndSendRequest('day')">
                    <v-list-item-title>Day</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="setTypeAndSendRequest('month')">
                    <v-list-item-title>Month</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="setTypeAndSendRequest('year')">
                    <v-list-item-title>Year</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-toolbar>
          </v-sheet>
        </v-row>
        <v-row>
          <v-col>
            <VueApexCharts
            width="700"
            type="line"
            :options="options"
            :series="series">
            </VueApexCharts>
          </v-col>
          <v-spacer></v-spacer>
          <v-col cols=4>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-menu
                  ref="menuFrom"
                  v-model="menuFrom"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="dateFrom"
                      label="Date from"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                  v-model="dateFrom"
                  no-title scrollable
                  :min="today"
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menuFrom = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="$refs.menuFrom.save(date)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-menu
                  ref="menuTo"
                  v-model="menuTo"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="dateTo"
                      label="Date to"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                  v-model="dateTo"
                  no-title scrollable
                  :min="dateFrom"
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menuTo = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="$refs.menuTo.save(date)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
              <v-col>
                <v-btn rounded @click="getPrice()">
                  Get price
                </v-btn>
              </v-col>
            </v-row>
            <v-row>
                <VueApexCharts
                width="380"
                type="donut"
                :options="donutOptions"
                :series="donutSeries">
                </VueApexCharts>
            </v-row>
            <v-row>
              <v-col>
                <b>
                <v-chip
                v-if="profit"
                class="ma-2"
                x-large
                color="blue">
                  Profit: {{profit.profit}}$
                </v-chip>
                </b>
              </v-col>
            </v-row>
          </v-col>
          <v-spacer></v-spacer>
        </v-row>
      </v-col>
    </v-row>
    <v-card>
      <v-card-title>
        Doctor's rating
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="doctors"
        class="elevation-1"
      >
        <template v-slot:item.rating="{ item }">
          <v-rating readonly v-model="item.rating"></v-rating>
        </template>
      </v-data-table>
    </v-card>
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
    menuFrom: false,
    menuTo: false,
    today: new Date().toISOString().substr(0, 10),
    dateFrom: new Date().toISOString().substr(0, 10),
    dateTo: new Date().toISOString().substr(0, 10),
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
      type: 'month',
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
    donutOptions: { labels: ['Profit in selected date range'] },
    donutSeries: [{
      name: ' ',
      data: [],
    }],
  }),
  mounted() {
    this.getCurrentClinic();
    this.getAllDoctors();
    this.getProfit({
      from: new Date().toISOString().substr(0, 10),
      to: new Date().toISOString().substr(0, 10),
    }).then(() => {
      this.updateDonut();
    });
    this.fetchReports(this.request)
      .then(() => {
        this.updateChart();
      });
  },
  methods: {
    ...mapActions('clinic', ['getCurrentClinic']),
    ...mapActions('medicalDoctor', ['getAllDoctors']),
    ...mapActions('finishedAppointment', ['getProfit']),
    ...mapActions('reports', ['fetchReports']),
    setTypeAndSendRequest(type) {
      this.type = type;
      this.request.type = type;
      this.request.date = this.date.toISOString().substr(0, 10);
      this.fetchReports(this.request)
        .then(() => {
          this.updateChart();
        });
    },
    updateChart() {
      const arr = [];
      this.reports.forEach((element) => {
        arr.push({
          x: element.date,
          y: element.count,
        });
      });
      this.series = [{
        data: arr,
      }];
    },
    updateDonut() {
      if (this.profit !== null) {
        this.setDonutSeries();
        this.setDonutOptions();
      }
    },
    setDonutSeries() {
      const arr = [];
      if (this.profit.longTermProfit === 0) {
        arr.push(1);
      } else {
        const profitPercentage = this.profit.longTermProfit / this.profit.profit;
        arr.push(profitPercentage, 1 - profitPercentage);
      }
      this.donutSeries = arr;
    },
    setDonutOptions() {
      if (this.donutOptions.labels.length !== 1) {
        this.donutOptions.labels.pop();
      }
      this.donutOptions.labels.push(this.profit.longTermProfitDescription);
    },
    setToday() {
      this.date = new Date();
    },
    prev() {
      if (this.type === 'day') {
        this.date = new Date(this.date.setDate(this.date.getDate() - 1));
      } else if (this.type === 'month') {
        this.date = new Date(this.date.setMonth(this.date.getMonth() - 1));
      }
    },
    next() {
      if (this.type === 'day') {
        this.date = new Date(this.date.setDate(this.date.getDate() + 1));
      } else if (this.type === 'month') {
        this.date = new Date(this.date.setMonth(this.date.getMonth() + 1));
      }
    },
    getPrice() {
      this.getProfit({
        from: this.dateFrom,
        to: this.dateTo,
      }).then(() => {
        this.updateDonut();
      });
    },
    a() {
      console.log(this.fetchReports);
    },
  },
  computed: {
    ...mapState('clinic', ['clinic']),
    ...mapState('medicalDoctor', ['doctors']),
    ...mapState('finishedAppointment', ['finishedAppointments', 'profit']),
    ...mapState('reports', ['reports']),
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
