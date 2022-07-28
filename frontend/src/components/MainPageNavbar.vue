<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/mainpage" class="navbar-brand ">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <a class="nav-link" @click.prevent="logOut">
      <button class="btn" style="color:white; background-color:#F2CB05;">Logout</button>
    </a>
  </div>
</nav>
<div class="menu" style="background-color:white;">
  <a href="/mainpage">Script</a>
  <a href="/feedback">Feedback</a>
</div>
</template>
<script>
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
};
</script>