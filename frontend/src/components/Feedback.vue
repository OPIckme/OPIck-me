<template>
<MainPageNavbar></MainPageNavbar>
<!-- feedback card -->
<FeedbackCard v-for="feedback in feedbackList" :key="feedback.id" :feedback="feedback"></FeedbackCard>
</template>

<script>
import FeedbackCard from './FeedbackCard.vue';
import MainPageNavbar from './MainPageNavbar.vue';
import { mapActions,mapGetters } from 'vuex';

export default {
    name: "Feedback",
    methods: {
        ...mapActions(['fetchFeedbackList']),
    },
    created() {
        this.fetchFeedbackList()
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
        ...mapGetters(['feedbackList'])
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },
    components: { FeedbackCard, MainPageNavbar }
};
</script>