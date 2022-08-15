<template>
<nav v-if="role==='student'" class="navbar sticky-top" style="background-color:#052A99; height: 80px;">
  <div class="container">
    <router-link to="/mainpage" @click.native="fetchHere1" :id="here1">
      <img src="../assets/logo.png" alt="" style="width:120px">
    </router-link>
    <div class="d-flex justify-content-around">
      <router-link to="/mainpage" @click.native="fetchHere1" class="script" :id="here1">Script</router-link>
      <router-link to="/feedback" @click.native="fetchHere2" class="feedback" :id="here2">Feedback</router-link>
      <!-- <button @click.native="fetchHere1" :id="here1" class="Logout" style="color:white; background-color:#F2CB05; text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button> -->
      <button @click.native="fetchHere1"  class="logout" style="text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button>
    </div>
  </div>
</nav>
<div v-else>
  <nav class="navbar sticky-top" style="background-color:#052A99; height: 80px;">
    <div class="container">
      <router-link class="position-absolute top-50 start-50 translate-middle" to="/mainpage" @click.native="fetchHere1" :id="here1">
        <img src="../assets/logo.png" alt="" style="width:120px">
      </router-link>
      <button  @click.native="fetchHere1" :id="here1" class="Logout" style="color:white; background-color:#F2CB05; text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button>
    </div>
  </nav>
  <div class="container text-center" style="background-color:white;">
    <div class="row">
      <p class="col">Topic</p>
      <p class="col-6">Question</p>
      <p class="col">State</p>
    </div>
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
p{
  margin: 1rem;
  font-size: 45px;
  font-weight: bold;
}
/* Logout button */
.navbar{
  z-index: 1;
}
.logout{
  background-color: #F2CB05;
  /* border: 2px solid #111; */
  border-radius: 50px;
  padding: 0 20px;
  line-height: 24px;
  height: 36px;
  font-family: Inter,sans-serif;
  font-size: 14px;
  font-weight: bold;
}
.logout:hover{
  color:white;
}

/* Script/Feedback */
a{
  text-decoration: none;
}
#on{
  color:#fff;
  text-decoration: underline;
  text-underline-position:under;
}
.script{
  margin-right:3rem;
  /* padding:0; */
  font-size:20px;
  /* font-weight: bold; */
  font-family: Inter,sans-serif;
  text-transform:uppercase;
  position:relative;
  color:#A0A0A0;
}
.script:before{
  content:"script";
  position:absolute;
  color:#fff;
  top:0;
  left:0;
  width:0%;
  overflow:hidden;
  transition:all 0.5s;
  text-decoration: underline;
  text-underline-position:under;
}
.script:hover:before{
  width:100%;
}

.feedback{
  margin-right:3rem;
  padding:0;
  font-size:20px;
  /* font-weight: bold; */
  font-family: Inter,sans-serif;
  text-transform:uppercase;
  position:relative;
  color:#A0A0A0;
}
.feedback:before{
  content:"feedback";
  position:absolute;
  color:#fff;
  top:0;
  left:0;
  width:0%;
  overflow:hidden;
  transition:all 0.5s;
  text-decoration: underline;
  text-underline-position:under;
}
.feedback:hover:before{
  width:100%;
}
</style>