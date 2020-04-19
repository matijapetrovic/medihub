<template>
    <div class="fill-height">
        <v-container fluid class="fill-height">
            <v-row align="center" justify="center">
                <v-col cols="12" sm="8" md="4">
                    <p>{{ error }}</p>
                    <v-card class="elevation-12">
                        <v-toolbar color="primary" dark flat>
                            <v-toolbar-title>Add clinic room</v-toolbar-title>
                        </v-toolbar>
                        <v-card-text>
                            <v-form ref="form">
                                <v-text-field v-model="name" label="Name"
                                name="name" type="text" required>
                                </v-text-field>
                                <v-menu v-model="togglePopover" v-bind="menuProps" :offset-y="true">
                                    <slot slot="activator"></slot>
                                    <slot name="bodyContent"></slot>
                                </v-menu>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn @click="submit" color="primary" name="button">
                                Submit
                            </v-btn>
                            <v-btn @click="submit" color="secondary" name="button">
                                Clear
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
export default {
  name: 'AddClinicRoomForm',

};
</script>
