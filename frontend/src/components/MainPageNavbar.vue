<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/script" class="navbar-brand ">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Logout">Logout</button>
  </div>
</nav>
<div class="menu" style="background-color:white;">
  <a href="/script">Script</a>
  <a href="/feedback">Feedback</a>
</div>
<LogoutModal></LogoutModal>
</template>
<script scope>
import LogoutModal from './Modal/LogoutModal.vue';
export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    
    components: { LogoutModal }
};
</script>
