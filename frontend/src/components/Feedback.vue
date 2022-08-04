<template>
<MainPageNavbar></MainPageNavbar>
<!-- feedback card -->
<FeedbackCard v-for="feedback in feedbackList" :key="feedback.id" :feedback="feedback"></FeedbackCard>
</template>

<script>
import FeedbackCard from './FeedbackCard.vue';
import axios from 'axios';
import MainPageNavbar from './MainPageNavbar.vue';
const API_URL = 'http://localhost:8080/api/v1/feedback';

export default {
    name: "Feedback",
    data() {
    return {
      feedbackList:[],
    };
  },
    methods: {
        getFeedbackList() {
            axios.get(API_URL)
            .then(response => {
                console.log(response.data)
                this.feedbackList = response.data})
        }
    },
    created() {
        this.getFeedbackList()
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },
    components: { FeedbackCard, MainPageNavbar }
};
</script>