<template>
  <v-card
  >
    <v-card-title>
      Patient Details
    </v-card-title>
    <v-list>
      <v-row>
        <v-col>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>
                {{ medicalRecord.insuranceNumber }}
              </v-list-item-title>
              <v-list-item-subtitle>
                Insurance number
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
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
                {{ medicalRecord.bloodType && medicalRecord.rhPositive !== null ?
                `${medicalRecord.bloodType}${medicalRecord.rhPositive ? '+' : '-'}` : 'N/A' }}
              </v-list-item-title>
              <v-list-item-subtitle>
                Blood Group
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-col>
        <v-col>
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
                {{ medicalRecord.leftDioptry && medicalRecord.rightDioptry ?
                `${medicalRecord.leftDioptry}/${medicalRecord.rightDioptry}` : 'N/A' }}
              </v-list-item-title>
              <v-list-item-subtitle>
                Dioptry
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-col>
      </v-row>
    </v-list>
    <v-divider inset></v-divider>
    <v-list
      subheader
      v-if="medicalRecord.allergies"
    >
      <v-subheader>Allergies</v-subheader>
      <v-list-item
        v-for="(allergy, idx) in medicalRecord.allergies"
        :key="idx"
      >
        <v-row>
          <v-col
            md="8"
          >
            <v-list-item-title>{{ allergy.name }}</v-list-item-title>
          </v-col>
          <v-col>
            <v-list-item-subtitle>
              <v-rating
                :value="allergy.level"
                length="3"
                small
                dense
                readonly
              >
              </v-rating>
            </v-list-item-subtitle>
          </v-col>
        </v-row>
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
