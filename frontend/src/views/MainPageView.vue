<template>
  <LoginNavbar></LoginNavbar>
  <Profile></Profile>
</template>
<script>
import Profile from '@/components/Profile.vue';
import LoginNavbar from '@/components/LoginNavbar.vue';
export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
        showAdminBoard() {
            if (this.currentUser && this.currentUser["roles"]) {
                return this.currentUser["roles"].includes("ROLE_ADMIN");
            }
            return false;
        },
        showModeratorBoard() {
            if (this.currentUser && this.currentUser["roles"]) {
                return this.currentUser["roles"].includes("ROLE_MODERATOR");
            }
            return false;
        }
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
        }
    },
    components: { Profile, LoginNavbar }
};
</script>