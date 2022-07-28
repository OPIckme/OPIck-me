<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/" class="navbar-brand">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <router-link to="/login" class="nav-link">
      <button class="btn" style="color:white; background-color:#F2CB05;">Login</button>
    </router-link>
  </div>
</nav>
<Home></Home>
</template>
<script>
import Home from "../components/Home.vue"
export default {
    data() {
        return {
            isHidden: false,
        };
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
        },
    },
    components : { Home }
};
</script>