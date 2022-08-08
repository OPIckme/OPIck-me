<template>
<nav class="navbar" style="background-color:#0742F2; height: 80px;">
  <div class="container-fluid">
    <router-link to="/mainpage" @click.native="fetchHere1" :id="here1">
      <img src="../assets/logo.png" alt="" style="width:100px">
    </router-link>
    <button class="Logout" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button>
  </div>
</nav>
<div v-if="role==='student'" class="menu d-flex justify-content-evenly" style="background-color:white;">
  <router-link to="/mainpage" @click.native="fetchHere1" class="script" :id="here1">Script</router-link>
  <router-link to="/feedback" @click.native="fetchHere2" class="feedback" :id="here2">Feedback</router-link>
</div>
<div v-else class="container text-center" style="background-color:white;">
  <div class="row">
    <p class="col">Topic</p>
    <p class="col-6">Question</p>
    <p class="col">State</p>
  </div>
</div>
<LogoutModal></LogoutModal>
</template>
<script scope>
import LogoutModal from './Modal/LogoutModal.vue';
import { mapActions,mapGetters } from 'vuex';
export default {
  data(){
        return{
            role:this.$store.state.auth.user.role,

        }
    },
    methods : {
      ...mapActions(['fetchHere1','fetchHere2']),
    },
    computed:{
      ...mapGetters(['here1','here2']),
    },
    components: { LogoutModal }
};
</script>

<style scoped>
/* Logout button */
.navbar{
  z-index: 1;
}
.Logout {
  align-items: center;
  background-color: #F2CB05;
  border: 2px solid #111;
  border-radius: 50px;
  box-sizing: border-box;
  color: white;
  cursor: pointer;
  display: flex;
  font-family: Inter,sans-serif;
  font-size: 16px;
  height: 36px;
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

.Logout:after {
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
  transition: transform .2s ease-out;
  z-index: -1;
}

.Logout:hover:after {
  transform: translate(0, 0);
}

.Logout:active {
  background-color: #F2CB05;
  outline: 0;
}

.LogLogoutin:hover {
  outline: 0;
}

@media (min-width: 768px) {
  .Logout {
    padding: 0 20px;
  }
}

/* Script/Feedback */
.menu{
  margin:0;
  display:flex;
  align-items:center;
  justify-content: center;
  height: 120px;
}

a{
  text-decoration: none;
}
#on{
  /* position:relative; */
  color:#262626;
  text-decoration: underline;
}
.script{
  margin:0;
  padding:0;
  font-size:50px;
  font-family: Inter,sans-serif;
  text-transform:uppercase;
  position:relative;
  color:#ccc;
}
.script:before{
  content:"script";
  position:absolute;
  color:#262626;
  top:0;
  left:0;
  width:0%;
  overflow:hidden;
  transition:all 0.5s;
  text-decoration: underline;
}
.script:hover:before{
  width:100%;
}



.feedback{
  margin:0;
  padding:0;
  font-size:50px;
  text-transform:uppercase;
  position:relative;
  color:#ccc;
}
.feedback:before{
  content:"feedback";
  position:absolute;
  color:#262626;
  top:0;
  left:0;
  width:0%;
  overflow:hidden;
  transition:all 0.5s;
  text-decoration: underline;
}
.feedback:hover:before{
  width:100%;
}
</style>