<template>
<MainPageNavbar></MainPageNavbar>
<!-- feedback card -->
<div class="container d-flex justify-content-between">
  <Sidebar></Sidebar>
  <div class="row" style="margin-top:4rem;">
      <FeedbackCard 
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
import Sidebar from './Sidebar.vue';

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
    components: { FeedbackCard, MainPageNavbar, Sidebar }
};
</script>