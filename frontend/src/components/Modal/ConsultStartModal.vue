<template>
  <!-- Modal -->
<div class="modal text-center" :id="consultStartModalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" >
  <div class="modal-dialog">
    <div class="modal-content">
      <button type="button" class="btn-close position-absolute top-0 end-0" data-bs-dismiss="modal" aria-label="Close"></button>
      <p class="position-absolute top-50 start-50 translate-middle">상담을 시작하시겠습니까?</p>
      <button type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x" data-bs-dismiss="modal"  @click="feedBack(), completeConsult()">Yes</button>
    </div>
  </div>
</div>
</template>

<script>
import {API_URL} from '@/api/http.js';
import axios from 'axios';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  data() {
    return {
      consultStartModalId : 'consult' + this.waitingconsult.id
    }
  },
  props : {
    waitingconsult : Object
  },
  computed:{

  },
  methods : {

    feedBack() {
      console.log(this.waitingconsult)
      this.$router.push({name : "webrtcstudent", params: {
        room: this.waitingconsult.room,
        script: this.waitingconsult.script.scriptContent,
        topic: this.waitingconsult.script.question.topic,
        question: this.waitingconsult.script.question.questionContent,
        scriptId:this.waitingconsult.script.id
        }});
    },
    completeConsult(){
      axios.delete(API_URL+ `/consult/complete/${this.waitingconsult.id}`)
      .then(res => {
        console.log(res)
        this.fetchWaitingConsultMap()
      })
      var socket = new SockJS('https://i7b202.p.ssafy.io/ws');
      var stompClient = Stomp.over(socket);
      let id = this.waitingconsult.id
      stompClient.connect({}, () => {
          stompClient.send("/topic/public/",
            JSON.stringify({
              id : id,
              method : 'delete'
            })
          )
          }, () => {});
    },
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