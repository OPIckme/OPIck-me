<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/" class="navbar-brand">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <!-- HTML !-->
    <router-link to="/login" class="nav-link">
      <button class="Login" role="button">Login</button>
      <!-- <button class="btn" style="color:white; background-color:#F2CB05;">Login</button> -->
    </router-link>
  </div>
</nav>
<SiteIntroduce></SiteIntroduce>

</template>
<script>
import SiteIntroduce from "../components/SiteIntroduce.vue"

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
    components : { SiteIntroduce }
};
</script>

<style>
/* login button CSS */
.navbar{
  z-index: 1;
}
.Login {
  align-items: center;
  background-color: #F2CB05;
  border: 1px solid #111;
  border-radius: 50px;
  box-sizing: border-box;
  color: white;
  cursor: pointer;
  display: flex;
  font-family: Inter,sans-serif;
  font-size: 16px;
  height: 48px;
  justify-content: center;
  line-height: 24px;
  max-width: 100%;
  padding: 0 25px;
  position: relative;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.Login:after {
  background-color: #111;
  border-radius: 50px;
  content: "";
  display: block;
  height: 48px;
  left: 0;
  width: 100%;
  position: absolute;
  top: -2px;
  transform: translate(8px, 8px);
  transition: transform .2s ease-out;
  z-index: -1;
}

.Login:hover:after {
  transform: translate(0, 0);
}

.Login:active {
  background-color: #F2CB05;
  outline: 0;
}

.Login:hover {
  outline: 0;
}

@media (min-width: 768px) {
  .Login {
    padding: 0 20px;
  }
}

</style>