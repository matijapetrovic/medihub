<style scoped>
  .description {
    font-family:    Georgia, serif;
    font-size:      15px;
    white-space:    pre-line;
  }
</style>

<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
            Today
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>mdi-chevron-right</v-icon>
          </v-btn>
          <v-toolbar-title>{{ title }}</v-toolbar-title>
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
              <v-list-item @click="type = 'week'">
                <v-list-item-title>Week</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>Month</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = '4day'">
                <v-list-item-title>4 days</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-color="getEventColor"
          :now="today"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          @change="updateRange"
        ></v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
          >
            <v-toolbar
              :color="selectedEvent.color"
              dark
            >
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <div class="description">{{selectedEvent.details}}</div>
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                color="secondary"
                @click="selectedOpen = false"
              >
                Cancel
              </v-btn>
              <v-btn
                text
                color="primary"
                @click="openAppointmentModal"
                v-if="selectedEvent.type === 'APPOINTMENT'"
                :disabled="diagnosisDisabled()"
              >
                Enter Diagnosis
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
        <appointment-dialog
          ref="dialog"
          @appointmentFinished="deleteEvent($event)"
        >
        </appointment-dialog>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import AppointmentDialog from './AppointmentDialog.vue';

export default {
  name: 'WorkingCalendar',
  data: () => ({
    focus: '',
    type: 'month',
    typeToLabel: {
      month: 'Month',
      week: 'Week',
      day: 'Day',
      '4day': '4 Days',
    },
    start: null,
    end: null,
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
    today: null,
  }),
  components: {
    AppointmentDialog,
  },
  computed: {
    ...mapState('medicalDoctor', ['workingCalendar']),
    title() {
      const { start, end } = this;
      if (!start || !end) {
        return '';
      }

      const startMonth = this.monthFormatter(start);
      const endMonth = this.monthFormatter(end);
      const suffixMonth = startMonth === endMonth ? '' : endMonth;

      const startYear = start.year;
      const endYear = end.year;
      const suffixYear = startYear === endYear ? '' : endYear;

      const startDay = start.day + this.nth(start.day);
      const endDay = end.day + this.nth(end.day);

      switch (this.type) {
        case 'month':
          return `${startMonth} ${startYear}`;
        case 'week':
        case '4day':
          return `${startMonth} ${startDay} ${startYear} - ${suffixMonth} ${endDay} ${suffixYear}`;
        case 'day':
          return `${startMonth} ${startDay} ${startYear}`;
        default:
          return '';
      }
    },
    monthFormatter() {
      return this.$refs.calendar.getFormatter({
        timeZone: 'UTC', month: 'long',
      });
    },
  },
  mounted() {
    this.$refs.calendar.checkChange();
    this.getWorkindCalendar()
      .then(() => {
        this.setUpEvents();
      });
  },
  methods: {
    ...mapActions('medicalDoctor', ['getWorkindCalendar', 'getWorkindCalendarByDoctorId']),
    viewDay({ date }) {
      this.focus = date;
      this.type = 'day';
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = this.today;
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => { this.selectedOpen = true; return null; }, 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    setUpEvents() {
      const events = [];
      Object.keys(this.workingCalendar.dailySchedules).forEach((key) => {
        const items = this.workingCalendar.dailySchedules[key].scheduleItems;
        items.forEach((item) => {
          events.push(
            this.getEvent(item, key),
          );
        });
      });

      this.events = events;
    },
    getEvent(item, date) {
      let fullName = '';
      switch (item.type) {
        case 'APPOINTMENT':
          fullName = `${item.appointment.patient.firstName} ${item.appointment.patient.lastName}`;
          return {
            name: item.type,
            type: item.type,
            start: `${date} ${item.time}`,
            end: `${date} ${this.incrementTime(item.time)}`,
            color: this.getColorByName(item.type),
            details: `Patient: ${fullName} \nClinic room: ${item.appointment.clinicRoom.name}`,
            itemId: item.id,
            itemDate: date,
            appointment: item.appointment,
          };
        default:
          return null;
      }
    },
    incrementTime(timeStr) {
      const parts = timeStr.split(':');
      let num = parseInt(parts[0], 10);
      let firstPart = '';
      num += 1;
      if (num === 24) {
        num = 0;
      }
      firstPart = num.toString();
      if (firstPart.length === 1) {
        firstPart = `0${firstPart}`;
      }
      return `${firstPart}:${parts[1]}`;
    },
    getColorByName(name) {
      switch (name) {
        case 'APPOINTMENT': return 'blue';
        case 'OPERATION': return 'orange';
        case 'VACATION': return 'green';
        case 'LEAVE': return 'deep-purple';
        default: return 'grey darken-1';
      }
    },
    openAppointmentModal() {
      this.$refs.dialog.show(this.selectedEvent);
    },
    diagnosisDisabled() {
      return false;
    },
    updateRange({ start, end }) {
      this.start = start;
      this.end = end;
    },
    deleteEvent(id) {
      const index = this.events.findIndex((element) => element.itemId === id);
      this.events.splice(index, 1);
    },
    nth(d) {
      return d > 3 && d < 21
        ? 'th'
        : ['th', 'st', 'nd', 'rd', 'th', 'th', 'th', 'th', 'th', 'th'][d % 10];
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    props: {
      doctorId: null,
    },
  },
};
</script>
