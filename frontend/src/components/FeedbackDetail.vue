<template>
<nav class="navbar sticky-top" style="background-color:#052A99; height: 80px;">
  <div class="container">
    <router-link class="position-absolute top-50 start-50 translate-middle  " to="/mainpage">
      <img src="../assets/logo.png" alt="" style="width:120px">
    </router-link>
    <!-- <button class="Logout" style="color:white; background-color:#F2CB05; text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button> -->
  </div>
</nav>
<LogoutModal></LogoutModal>
<div class="scriptdetail container">
  <!-- 나가기 -->
  <div @click="back" class="close-container">
    <div class="leftright"></div>
    <div class="rightleft"></div>
    <label class="close">back</label>
  </div>
  <div class="scriptcontainer">
    <hr>
    <h5>{{ feedback.script.question.topic }}</h5>
    <h2>Q. {{ feedback.script.question.questionContent }}</h2>
    <p>{{ createdAt.slice(0,-3) }}</p>
    <hr>
    <p>[Script]</p>
    <div class="script" id="content" v-html="feedback.content"></div>
  </div>
</div>
</template>

<script>
import LogoutModal from './Modal/LogoutModal.vue';
import axios from 'axios';
import {API_URL} from '@/api/http.js';


export default {
    name: "FeedbackDetail",
    data() {
    return {
      feedback: {},
      feedbackId: parseInt(this.$route.params.feedbackId),
      username: this.$store.state.auth.user.username,
      createdAt: '',
      content:'',
      };
    },
    methods: {
        back() {
            this.$router.push("/feedback");
        },
        getFeedback(){
          axios.get(API_URL + `/feedback/${this.username}/${this.feedbackId}`)
          .then(res => {
            this.feedback = res.data.feedback
            const d = new Date(this.feedback.created_at)
            this.createdAt = d.toLocaleString()
          })
        },
    },
    created() {
      this.getFeedback()
    },
    components: { LogoutModal }
}
</script>
<style lang="scss" scoped>
$softorange: #020E33;
$tomatored: #F25C66;
$mediumblu: #1E272D;

.close-container{
  margin: 1.5rem;
  margin-right: 65px;
  width: 35px;
  height: 35px;
  float: right;
  cursor: pointer;
}

.leftright{
  height: 4px;
  width: 35px;
  position: absolute;
  margin-top: 24px;
  background-color: $softorange;
  border-radius: 2px;
  transform: rotate(45deg);
  transition: all .3s ease-in;
}

.rightleft{
  height: 4px;
  width: 35px;
  position: absolute;
  margin-top: 24px;
  background-color: $softorange;
  border-radius: 2px;
  transform: rotate(-45deg);
  transition: all .3s ease-in;
}

label{
  color: $softorange;
  font-family: Helvetica, Arial, sans-serif; 
  font-size: .6em;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 2px;
  transition: all .3s ease-in;
  opacity: 0;
}
.close{
  margin: 60px 0 0 5px;
  position: absolute;
}

.close-container:hover .leftright{
  transform: rotate(-45deg);
  background-color: $tomatored;
}
.close-container:hover .rightleft{
  transform: rotate(45deg);
  background-color: $tomatored;
}
.close-container:hover label{
  opacity: 1;
}


</style>
<style scoped>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
i{
  margin-right: 0.5rem;
}
.btn{
  margin-left: 0.5rem;
}
h2{
  font-weight: bold;
}
.script{
  font-size: 18px;
  letter-spacing: 1px;
  line-height: 1.5rem;
}
.scriptcontainer{
  margin-top: 3rem;
  padding: 4rem;
}
.scriptdetail{
  background-color: #fff;
  box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
      0 10px 10px rgba(0,0,0,0.22);
  position: relative;
  overflow: hidden;
  width: 1296px;
  max-width: 100%;
  min-height: 600px;
}
p{
  width: 100%;
  margin-top: 2rem;
}
/* Logout button */
.navbar{
  z-index: 1;
}
</style>
