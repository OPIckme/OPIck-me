<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/script" class="navbar-brand ">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#exampleModal">Logout</button>
  </div>
</nav>
<div class="menu" style="background-color:white;">
  <a href="/script">Script</a>
  <a href="/feedback">Feedback</a>
</div>

<!-- Modal -->
<div class="modal" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        로그아웃하시겠습니까?
      </div>
      <div class="modal-footer">
        <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
        <button type="button" class="btn btn-primary"  @click.prevent="logOut">Yes</button>
      </div>
    </div>
  </div>
</div>
</template>
<script scope>
export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/");
        },
        
    },
    
};
</script>

