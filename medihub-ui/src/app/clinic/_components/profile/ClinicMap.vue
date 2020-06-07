<template>
  <yandex-map
    :coords="coords"
    :zoom="15"
    :settings="settings"
  >
    <ymap-marker
      :coords="coords"
      marker-id="123"
    />
  </yandex-map>
</template>

<script>
import { yandexMap, ymapMarker } from 'vue-yandex-maps';

export default {
  name: 'ClinicMap',
  components: {
    yandexMap,
    ymapMarker,
  },
  data: () => ({
    settings: {
      apiKey: 'daf8ca4b-3c4e-4396-9bff-8c6b22f7e69a',
      lang: 'en',
      coordorder: 'longlat',
    },
  }),
  props: {
    coords: {
      required: true,
      type: Array,
    },
  },
};
</script>

<style scoped>
.ymap-container {
  height: 300px;
}
</style>
