<template>
    <div class="v-sheet v-sheet--tile theme--light" flat="" style="transform-origin: center top 0px;">
        <div class="v-card__text">
            <div data-app="true">
                <form>
                    <div class="v-input v-input--has-state theme--light
                    v-text-field v-text-field--is-booted error--text">
                        <div class="v-input__control">
                            <div class="v-input__slot">
                                <div class="v-text-field__slot">
                                    <label for="input-838" class="v-label theme--light
                                    error--text"
                                    style="left: 0px; right: auto; position: absolute;">
                                    Name
                                    </label>
                                    <input required="required" id="input-838" type="text">
                                </div>
                            </div>
                            <div class="v-text-field__details">
                                <div class="v-messages theme--light error--text" role="alert">
                                    <div class="v-messages__wrapper">
                                        <div class="v-messages__message">Name is required.</div>
                                    </div>
                                </div>
                                <div class="v-counter theme--light">0 / 10</div>
                            </div>
                        </div>
                    </div>
                    <div class="v-input v-input--has-state theme--light
                    v-text-field v-text-field--is-booted
                                v-select error--text">
                        <div class="v-input__control">
                            <div role="button" aria-haspopup="listbox" aria-expanded="false"
                            aria-owns="list-844" class="v-input__slot">
                                <div class="v-select__slot">
                                    <label for="input-844" class="v-label theme--light error--text"
                                        style="left: 0px; right: auto; position: absolute;">
                                        Clinic
                                    </label>
                                    <div class="v-select__selections">
                                        <input required="required" id="input-844"
                                        type="text" aria-readonly="false" autocomplete="off">
                                    </div>
                                    <div class="v-input__append-inner">
                                        <div class="v-input__icon v-input__icon--append">
                                        <i aria-hidden="true" class="v-icon notranslate
                                        mdi mdi-menu-down theme--light error--text">
                                        </i>
                                    </div>
                                    </div>
                                    <input type="hidden">
                                </div>
                                <div class="v-menu"></div>
                            </div>
                            <div class="v-text-field__details">
                                <div class="v-messages theme--light error--text" role="alert">
                                    <div class="v-messages__wrapper">
                                        <div class="v-messages__message" style="">
                                        Clinic is required
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="mr-4 v-btn v-btn--contained
                    theme--light v-size--default">
                    <span class="v-btn__content">
                        submit
                    </span>
                </button>
                    <button type="button" class="v-btn v-btn--contained theme--light
                    v-size--default">
                    <span class="v-btn__content">
                    clear
                    </span>
                </button>
                </form>
            </div>
        </div>
    </div>    
</template>

<script>
export default {
  name: 'AddClinicRoomForm',
};
</script>