<template>
  <nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/" class="navbar-brand">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <a class="nav-link" @click.prevent="logOut">
      <button class="btn" style="color:white; background-color:#F2CB05;">Logout</button>
    </a>
      
  </div>
</nav>
</template>

<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_ADMIN');
      }
      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_MODERATOR');
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/');
    }
  }
};
</script>