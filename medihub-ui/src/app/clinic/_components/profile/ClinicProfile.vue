<template>
  <div>
    <v-row>
      <v-col>
        <ClinicMap
          :coords="coords"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col md="4">
        <v-rating
          :value="clinic.rating"
          color="amber"
          dense
          half-increments
          readonly
          class="float-left"
        >
        </v-rating>
        <span class="grey--text ml-4">{{ clinic.rating }} ({{ clinic.ratingCount }})</span>
        <div class="mt-4">{{ clinic.description }}</div>
      </v-col>
      <v-col md="8">
        <h3>
          Predefined appointments
        </h3>
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
import ScheduleAppointmentDialog from '@/app/appointment/_components/ScheduleAppointmentDialog.vue';
import ClinicMap from './ClinicMap.vue';

export default {
  name: 'ClinicProfile',
  components: {
    ClinicMap,
    ScheduleAppointmentDialog,
  },
  data: () => ({
    appointment: {},
    dialog: false,
  }),
  props: {
    clinic: {
      required: true,
      type: Object,
    },
    coords: {
      required: true,
      type: Array,
    },
  },
  methods: {
    ...mapActions('predefinedAppointment', ['schedulePredefinedAppointment']),
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
    schedule() {
      this.schedulePredefinedAppointment(this.appointment.id)
        .then(() => {
          this.closeConfirmScheduleDialog();
        })
        .catch((err) => {
          this.closeConfirmScheduleDialog();
          if (err.response.status === 404) {
            const location = this.$route.fullPath;
            this.$router.replace('/');
            this.$nextTick(() => this.$router.replace(location));
          }
        });
    },
  },
  computed: {
    ...mapState('predefinedAppointment', ['predefinedAppointments']),
  },
};
</script>

<style scoped>
.appointment-list {
  max-height: 300px;
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
