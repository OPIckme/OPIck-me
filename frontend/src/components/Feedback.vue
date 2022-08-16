<template>
<MainPageNavbar></MainPageNavbar>
<!-- feedback card -->
<div class="container d-flex flex-row">
  <Sidebar style="width:20%" @selectCategory="categoryUpdate"></Sidebar>
  <div  class="row" style="margin-top:4rem; width:80%;">
      <FeedbackCard 
      class="col-lg-3 col-md-4 col-sm-6" 
      v-for="feedback in feedbackList" 
      :key="feedback.id" 
      :feedback="feedback">
      </FeedbackCard>
    </div>
</div>
<FooterNav></FooterNav>
</template>

<script>
import FooterNav from './FooterNav.vue';
import FeedbackCard from './FeedbackCard.vue';
import MainPageNavbar from './MainPageNavbar.vue';
import { mapActions,mapGetters } from 'vuex';
import Sidebar from './Sidebar.vue';

export default {
    name: "Feedback",
    methods: {
        ...mapActions(['fetchFeedbackList','fetchCategory']),
        categoryUpdate(category){
            this.fetchCategory(category)
            console.log(category)
            this.fetchFeedbackList(this.$store.state.auth.user.username)
        },
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
    components: { FeedbackCard, MainPageNavbar, Sidebar, FooterNav }
};
</script>