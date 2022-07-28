<template>
<MainPageNavbar></MainPageNavbar>
<h1>Feedback</h1>
</template>
<script>
import MainPageNavbar from './MainPageNavbar.vue';
export default {
    name: "Feedback",
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        }
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },
    components: { MainPageNavbar }
};
</script>