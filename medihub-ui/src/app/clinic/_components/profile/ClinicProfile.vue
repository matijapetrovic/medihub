<template>
  <div>
    <v-card
    >
      <v-container>
        <v-row>
          <v-col>
            <ClinicMap
              :coords="coords"
            />
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card-title>{{ clinic.name }}</v-card-title>
            <v-card-text>
              <v-row
                align="center"
                class="mx-0"
              >
                <v-rating
                  :value="clinic.rating"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="14"
                ></v-rating>
                <div class="grey--text ml-4">{{ clinic.rating }} ({{ clinic.ratingCount }})</div>
              </v-row>
              <div class="mt-4">{{ clinic.description }}</div>
            </v-card-text>
          </v-col>
          <v-divider vertical></v-divider>
          <v-col>
            <v-card-title>
              Predefined appointments
            </v-card-title>
            <v-list
              v-if="predefinedAppointments.length"
              two-line
              class="appointment-list">
              <v-list-item-group>
                <template v-for="(item, index) in predefinedAppointments">
                  <div
                    :key="index"
                  >
                    <v-list-item
                      @click="openConfirmScheduleDialog(item)"
                    >
                      <v-list-item-content>
                        <v-list-item-title v-text="item.name"></v-list-item-title>
                        <v-list-item-subtitle class="text--primary" v-text="item.doctor">
                        </v-list-item-subtitle>
                        <v-list-item-subtitle v-text="item.room"></v-list-item-subtitle>
                      </v-list-item-content>
                      <v-spacer></v-spacer>
                      <v-list-item-action>
                        <v-list-item-title v-text="item.date"></v-list-item-title>
                        <v-list-item-subtitle class="text--primary" v-text="item.time">
                        </v-list-item-subtitle>
                        <v-list-item-subtitle>{{ item.price }}&euro;</v-list-item-subtitle>
                      </v-list-item-action>
                    </v-list-item>
                    <v-divider
                      v-if="index + 1 < predefinedAppointments.length"
                    ></v-divider>
                  </div>
                </template>
              </v-list-item-group>
            </v-list>
            <p
              v-else
            >
              There are currently no predefined appointments.
            </p>
          </v-col>
        </v-row>
      </v-container>
      <v-divider class="mx-4"></v-divider>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          light
          @click="seeDoctors"
        >
          See available doctors
        </v-btn>
      </v-card-actions>
    </v-card>
    <v-dialog
      v-model="dialog"
      max-width="500"
    >
      <ScheduleAppointmentDialog
        :appointment="appointment"
        @cancelled="closeConfirmScheduleDialog"
        @scheduled="schedule"
      />
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import axios from 'axios';
import ScheduleAppointmentDialog from '@/app/appointment/_components/ScheduleAppointmentDialog.vue';
import ClinicMap from './ClinicMap.vue';

export default {
  name: 'ClinicProfile',
  components: {
    ClinicMap,
    ScheduleAppointmentDialog,
  },
  data: () => ({
    coords: [45.264747, 19.836904],
    apiKey: 'daf8ca4b-3c4e-4396-9bff-8c6b22f7e69a',
    appointment: {},
    dialog: false,
  }),
  props: {
    clinic: {
      required: true,
      type: Object,
    },
  },
  methods: {
    ...mapActions('predefinedAppointment', ['fetchPredefinedAppointments', 'schedulePredefinedAppointment']),
    openConfirmScheduleDialog(appointment) {
      this.appointment = appointment;
      this.dialog = true;
    },
    closeConfirmScheduleDialog() {
      this.dialog = false;
    },
    seeDoctors() {
      this.$router.push(`/search-doctors/${this.clinic.id}`);
    },
    geocodeAddress() {
      return axios.get(`https://geocode-maps.yandex.ru/1.x/?apikey=${this.apiKey}&geocode=${this.formatAddress()}&format=json`);
    },
    formatAddress() {
      return `${this.clinic.country} ${this.clinic.address} ${this.clinic.city}`.replace(' ', '+');
    },
    parseCoords(coords) {
      const tokens = coords.split(' ');
      return [+tokens[0], +tokens[1]];
    },
    schedule() {
      this.schedulePredefinedAppointment(this.appointment.id)
        .then(() => {
          this.dialog = false;
        });
    },
  },
  computed: {
    ...mapState('predefinedAppointment', ['predefinedAppointments']),
  },
  mounted() {
    this.fetchPredefinedAppointments(this.clinic.id);
    this.geocodeAddress()
      .then((response) => {
        this.coords = this.parseCoords(response.data.response.GeoObjectCollection
          .featureMember[0].GeoObject.Point.pos);
      });
  },
};
</script>

<style scoped>
.appointment-list {
  height: 200px;
  overflow-y: auto;
}
.appointment-list::-webkit-scrollbar
{
    width: 6px;
    background-color: #F5F5F5;
}

.appointment-list::-webkit-scrollbar-thumb
{
    background-color: #000000;
}
</style>