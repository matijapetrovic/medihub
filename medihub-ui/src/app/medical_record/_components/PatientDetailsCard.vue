<template>
  <v-card>
    <v-card-title>
      Patient Details
    </v-card-title>
    <v-list>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            {{ medicalRecord.height ? medicalRecord.height : 'N/A' }}cm
          </v-list-item-title>
          <v-list-item-subtitle>
            Height
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            {{ medicalRecord.weight ? medicalRecord.weight : 'N/A' }}kg
          </v-list-item-title>
          <v-list-item-subtitle>
            Weight
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            {{ medicalRecord.bloodType ? medicalRecord.bloodType : 'N/A' }}
          </v-list-item-title>
          <v-list-item-subtitle>
            Blood Type
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            {{ medicalRecord.rhPositive !== null ? (medicalRecord.rhPositive ? '+' : '-')
              : 'N/A' }}
          </v-list-item-title>
          <v-list-item-subtitle>
            Rh Factor
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            {{ `${medicalRecord.leftDioptry ? medicalRecord.leftDioptry : 'N/A'}
            /${medicalRecord.rightDioptry ? medicalRecord.rightDioptry : 'N/A'}` }}
          </v-list-item-title>
          <v-list-item-subtitle>
            Dioptry
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
    <v-divider inset></v-divider>
    <v-list
      subheader
      v-if="medicalRecord.allergies"
    >
      <v-subheader>Allergies</v-subheader>
      <v-list-item
        v-for="item of Object.keys(medicalRecord.allergies)"
        :key="item"
      >
        <v-list-item-title>{{ item }}</v-list-item-title>
        <v-list-item-subtitle>{{ medicalRecord.allergies[item] }}</v-list-item-subtitle>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {
  name: 'PatientDetailsCard',
  props: {
    medicalRecord: {
      required: true,
      type: Object,
    },
  },
};
</script>
