<template>
    <div class="form-check form-check-inline">
        <input :id="item" @input="selectTopic" v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" :value="item">
        <label class="form-check-label" :for="item">{{item}}</label>
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

</style>