<template>
<nav class="navbar sticky-top" style="background-color:#0742F2; height: 80px;">
  <router-link class="position-absolute top-50 start-50 translate-middle  " to="/mainpage">
    <img src="../assets/logo.png" alt="" style="width:120px">
  </router-link>
  <button class="Logout" style="color:white; background-color:#F2CB05; text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button>
</nav>

<div class="scriptdetail">
  <!-- 나가기 -->
  <div @click="back" class="close-container">
    <div class="leftright"></div>
    <div class="rightleft"></div>
    <label class="close">back</label>
  </div>
  <div class="container">
    <hr>
    <h5>{{ script.question.topic }}</h5>
    <h2>Q. {{ script.question.questionContent }}</h2>
    <p>{{ script.createdAt.slice(0,10) }}</p>
    <hr>
    <div class="buttonbar d-flex justify-content-between">
      <audio controls :src=script.audioUrl></audio>
      <div class="buttons">
        <!-- 스크립트 수정하기 -->
        <button class="btn" @click="routingScriptEdit"><i class="bi bi-pencil-fill"></i>수정하기</button>
        <!-- 피드백 신청하기 -->
        <button @click.prevent="createConsult" data-bs-toggle="modal" :data-bs-target="feedbackModalId" class="btn"><i class="bi bi-people-fill"></i>피드백 신청하기</button>
      </div>
    </div>
    <p>[Script]</p>
    <p class="script">{{ script.scriptContent }}</p>
  </div>
  <FeedbackModal :scriptId="scriptId" :script="script"></FeedbackModal>
  <LogoutModal></LogoutModal>
</div>
</template>

<script>
import LogoutModal from './Modal/LogoutModal.vue';
// import MainPageNavbar from './MainPageNavbar.vue';
import FeedbackModal from './Modal/FeedbackModal.vue';
import axios from 'axios';
import { mapActions } from 'vuex';
import {API_URL} from '@/api/http.js';



export default {
    name: "ScriptDetail",
    data() {
    return {
      click:true,
      isPlaying: false,
      script: {},
      scriptId: parseInt(this.$route.params.scriptId),
      feedbackModalId: "",
      audio: {},
      username: this.$store.state.auth.user.username
    };
  },
    setup() {
    },
    methods: {
      ...mapActions(['fetchScript']),
        back() {
            this.$router.push("/mainpage");
        },
        clickCaret(){
            this.click = !this.click;
        },
        getScript(){
          axios.get(API_URL + `/script/${this.username}/${this.scriptId}`)
          .then(res => {
            console.log(res.data.script)
            this.script = res.data.script
            this.feedbackModalId="#create" + this.script.id
            this.audio={
              file: new Audio(res.data.script.audioUrl),
              isPlaying: false
            }
          })
        },
        routingScriptEdit(){
          this.$router.push({
            name:'scriptedit',
            params:{
              scriptId:this.script.id}
        })
        },
        play (audio) {
          audio.isPlaying = true;
          audio.file.play();
        },
        pause (audio) {
          audio.isPlaying = false;
          audio.file.pause();
        }
    },
    created() {
        this.getScript()
    },
    components: { FeedbackModal, LogoutModal }
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
.container{
  margin-top: 7rem;
  width: 1200px;
}
.scriptdetail{
  background-color: #fff;
  /* border-radius: 10px; */
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
  font-size: 14px;
  font-weight: bold;
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
  position: relative;
  left: 85%;
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
</style>