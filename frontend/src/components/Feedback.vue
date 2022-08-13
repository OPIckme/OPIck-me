<template>
<MainPageNavbar></MainPageNavbar>
<!-- feedback card -->
<div class="container" style="margin-top:4rem">
    <div class="row">
        <FeedbackCard 
        style="margin-top:40px;"
        class="col-lg-2 col-md-3 col-sm-4" 
        v-for="feedback in feedbackList" 
        :key="feedback.id" 
        :feedback="feedback">
        </FeedbackCard>
    </div>
</div>
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
        this.fetchFeedbackList(this.$store.state.auth.user.username)
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