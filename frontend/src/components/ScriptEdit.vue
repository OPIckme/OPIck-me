<template>
<nav class="navbar sticky-top" style="background-color:#004ACC; height: 80px;">
  <div class="container">
    <router-link class="position-absolute top-50 start-50 translate-middle  " to="/mainpage">
      <img src="../assets/logo.png" alt="" style="width:120px">
    </router-link>
    <button class="Logout" style="color:white; background-color:#F2CB05; text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Logout" >Logout</button>
  </div>
</nav>

<div class="scriptdetail container">
  <!-- 나가기 -->
  <div @click="back" class="close-container">
    <div class="leftright"></div>
    <div class="rightleft"></div>
    <label class="close">back</label>
  </div>
  <div class="editcontainer">
    <hr>
    <h5>{{ script.question.topic }}</h5>
    <h2>Q. {{ script.question.questionContent }}</h2>
    <p>{{ script.createdAt.slice(0,10) }}</p>
    <hr>
    <script-edit-form :script="script"></script-edit-form>
  </div>
  <FeedbackModal :scriptId="scriptId" :script="script"></FeedbackModal>
  <LogoutModal></LogoutModal>
</div>
</template>

<script>
import LogoutModal from './Modal/LogoutModal.vue';
import ScriptEditForm from './ScriptEditForm.vue';
import axios from 'axios';
import {API_URL} from '@/api/http.js';

export default {
    name: "ScriptEdit",
    data() {
        return{
            script: {},
            scriptId: parseInt(this.$route.params.scriptId),
            username: this.$store.state.auth.user.username,
        }
    },
    methods: {
        back() {
            this.$router.push({
                name:'scriptdetail',
                params:{
                    username:this.username,
                    scriptId:this.scriptId}
            })
        },
        getScript(){
          axios.get(API_URL + `/script/${this.username}/${this.scriptId}`)
          .then(res => {
            console.log(res.data.script)
            this.script = res.data.script
            }
          )
        },
    },
    created() {
        this.getScript()
    },
    components: { LogoutModal, ScriptEditForm }
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
.editcontainer{
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