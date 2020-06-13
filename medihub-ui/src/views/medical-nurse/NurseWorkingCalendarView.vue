<template>
  <NurseWorkingCalendar :nurseId="null"></NurseWorkingCalendar>
</template>

<script>
import NurseWorkingCalendar from '@/app/medical_nurse/components/NurseWorkingCalendar.vue';

export default {
  name: 'NurseWorkingCalendarView',
  components: {
    NurseWorkingCalendar,
  },
};
</script>
