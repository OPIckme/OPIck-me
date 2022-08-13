<template>
  <!-- Modal -->
<div class="modal text-center" :id="feedbackModalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <button type="button" class="btn-close position-absolute top-0 end-0" data-bs-dismiss="modal" aria-label="Close"></button>
      <p class="position-absolute top-50 start-50 translate-middle">대기중인 강사와 연결하시겠습니까?</p> 
      <button type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x" data-bs-dismiss="modal" @click="createConsult(), feedBack()">Yes</button>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
import {API_URL} from '@/api/http.js';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { mapActions } from 'vuex';


export default {
  data() {
    return {
      feedbackModalId: "create" + this.scriptId,
      roomId: Math.random().toString(36)
    }
  },
  props: {
    scriptId: String,
    script: Object,
  },
  methods: {
    ...mapActions(['fetchWaitingConsultMap','fetchConsultId']),
    feedBack() {
      this.$router.push({name : "webrtcstudent", params: {
        room: this.roomId,
        script: this.script.scriptContent,
        topic: this.script.question.topic,
        question: this.script.question.questionContent
        }});
    },
    createConsult() {
      axios.post(API_URL + '/consult', {
        room: this.roomId,
        scriptId: this.scriptId
      }).then(res => {
        console.log(res)
        const consultId=res.data.consult.id
        this.fetchConsultId(consultId)
        this.fetchWaitingConsultMap()
      })
      var socket = new SockJS('https://i7b202.p.ssafy.io/ws');
      var stompClient = Stomp.over(socket);
      stompClient.connect({}, () => {
        stompClient.send("/topic/public/",
          JSON.stringify({
            topic : this.script.question.topic,
            questionContent : this.script.question.questionContent,
          })
        )}, () => {});
    }
  }
}
</script>

<style scoped>
p{
  font-size: 20px;
  /* font-weight: bold; */
  cursor : default;
  letter-spacing: 4px;
  width: 100%;
}
.modal-content{
  height: 20rem;
  border-radius: 15px;
}
.btn{
  color: white;
  background-color: #0742F2;
  width: 8rem;
  margin-bottom: 2rem;
  border-radius: 50px;
}

.btn-close{
  margin: 3rem;
  margin-bottom: 0;
  float: right;
}
</style>
