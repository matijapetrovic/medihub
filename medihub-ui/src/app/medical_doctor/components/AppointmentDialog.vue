<style scoped>
  .areaTitle {
    font-size: 20px;
    font-weight: bold;
  }
  .select {
    width: 45%;
  }
</style>

<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          Enter Diagnosis
          <v-spacer></v-spacer>
          <v-btn icon
            @click="dialog=false">
              <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>

        <v-card-text>
         <br/>
         <div class="areaTitle">Patient</div>
          <v-container>
            <v-row>
              <v-text-field
                filled
                label="First name:"
                v-model="model.patient.firstName"
                disabled="true"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                filled
                label="Last name:"
                v-model="model.patient.lastName"
                disabled="true"
              ></v-text-field>
            </v-row>
          </v-container>
          <div class="areaTitle">Clinic room</div>
          <v-container>
            <v-row>
              <v-text-field
                filled
                label="Name:"
                v-model="model.clinicRoom.name"
                disabled="true"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                filled
                label="Number:"
                v-model="model.clinicRoom.number"
                disabled="true"
              ></v-text-field>
            </v-row>
          </v-container>
          <div class="areaTitle">Diagnosis</div>
          <v-form ref="form">
            <v-container>
              <v-row>
                <v-select
                  class="select"
                  outlined
                  label="Diagnosis"
                  :rules="[requiredRule]"
                ></v-select>
                <v-spacer></v-spacer>
                <v-select
                  class="select"
                  outlined
                  label="Drugs"
                  :rules="[requiredRule]"
                ></v-select>
              </v-row>
              <v-row>
                <v-textarea
                  label="Description"
                  v-model="description"
                  color="teal"
                  no-resize="true"
                  :rules="[requiredRule]"
                  rows="3"
                >
                  <template v-slot:label>
                    <div>
                      Description
                    </div>
                  </template>
                </v-textarea>
              </v-row>
            </v-container>
          </v-form>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="success"
            rounded
            width="150"
          >
            Finish
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      model: null,
      description: '',
    };
  },
  computed: {
    requiredRule() {
      return (value) => !!value || 'Required';
    },
  },
  methods: {
    show(model) {
      this.model = model;
      this.dialog = true;
    },
  },
};
</script>
