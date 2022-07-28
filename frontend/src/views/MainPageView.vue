<template>
<MainPageNavbar></MainPageNavbar>
<Script></Script>
</template>
<script>
import Script from '@/components/Script.vue';
import MainPageNavbar from '../components/MainPageNavbar.vue';
export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
        }
    },
    components: { Script, MainPageNavbar }
};
</script>