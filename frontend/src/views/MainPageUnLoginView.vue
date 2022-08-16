<template>
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
  font-size: 20px;
  font-weight: bold;
  height: 50px;
  width: 100px;
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
  top: 40px;
}

.Login:after {
  background-color: #111;
  border-radius: 50px;
  content: "";
  display: block;
  height: 50px;
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
