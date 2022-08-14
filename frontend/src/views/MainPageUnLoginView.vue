<template>
  <nav class="navbar" style="background-color: #ffffff; height: 80px">
    <div class="container">
      <router-link class="position-absolute top-50 translate-middle" to="/">
        <img src="../assets/logo.png" alt="" style="width: 120px" />
      </router-link>
      <router-link to="/login" class="nav-link">
        <button class="Login" role="button">Login</button>
      </router-link>
    </div>
  </nav>
  <SiteIntroduce></SiteIntroduce>
</template>
<script>
import SiteIntroduce from "../components/SiteIntroduce.vue";

export default {
  data() {
    return {
      isHidden: false,
    };
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    },
  },
  created() {
    if (this.$store.state.auth.user) {
      this.$router.push("/mainpage");
    }
  },
  components: { SiteIntroduce },
};
</script>

<style>
/* login button CSS */
.navbar {
  z-index: 1;
}
.Login {
  align-items: center;
  background-color: #f2cb05;
  border: 2px solid #111;
  border-radius: 50px;
  box-sizing: border-box;
  color: white;
  cursor: pointer;
  display: flex;
  font-family: Inter, sans-serif;
  font-size: 14px;
  font-weight: bold;
  height: 36px;
  justify-content: center;
  line-height: 24px;
  max-width: 100%;

  position: absolute;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  left: 85%;
  top: 22px;
}

.Login:after {
  background-color: #111;
  border-radius: 50px;
  content: "";
  display: block;
  height: 36px;
  left: 0;
  width: 100%;
  position: absolute;
  top: -2px;
  transform: translate(6px, 6px);
  transition: transform 0.2s ease-out;
  z-index: -1;
}

.Login:hover:after {
  transform: translate(0, 0);
}

.Login:active {
  background-color: #f2cb05;
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
