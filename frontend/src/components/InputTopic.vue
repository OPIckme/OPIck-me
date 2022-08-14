<template>
    <!-- 기존 v-for <div class="form-check form-check-inline">
        <input :id="item" @input="selectTopic" v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" :value="item">
        <label class="form-check-label" :for="item">{{item}}</label>
    </div> -->
    
    <div class="mb-3">
        <div class="form_toggle row-vh d-flex flex-row justify-content-between">
            <div class="form_radio_btn">
                <input :id="item" @input="selectTopic" v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" :value="item">
                <label class="form-check-label" :for="item">{{item}}</label>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "InputTopic",

    data() {
        topic: '';
    },

    props : {
      item: String,
    },

    setup() {
    },

    methods: {
        selectTopic(e) {
            this.$emit("topic", e.target.value);
        }
    },
}
</script>

<style scoped>
.form_radio_btn {
    width: 47%;
    height : 45px;
    border: 1px solid #EAE7E7;
    border-radius: 10px;
}

.form_radio_btn input[type=radio] {
    display: none;
}
.form_radio_btn label {
    display: block;
    border-radius: 10px;
    margin: 0 auto;
    text-align: center;
    height: -webkit-fill-available;
    line-height: 45px;
}
/* Checked */
.form_radio_btn input[type=radio]:checked + label {
    background: #184DA0;
    color: #fff;
}
    
/* Hover */
.form_radio_btn label:hover {
    color: #666;
}
    
/* Disabled */
.form_radio_btn input[type=radio] + label {
    background: #F9FAFC;
    color: #666;
}
</style>