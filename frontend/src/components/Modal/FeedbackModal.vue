<template>
  <!-- Modal -->
<div class="modal" :id="feedbackModalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        대기중인 강사와 연결하시겠습니까?
      </div>
      <div class="modal-footer">
        <!-- <a href="https://i7b202.p.ssafy.io:8443/?">
          <button type="button" class="btn btn-primary"  @click="feedBack">Yes</button>
        </a> -->
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="createConsult(), feedBack()">Yes</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      feedbackModalId: "create" + this.scriptId,
    }
  },
  props: {
    scriptId: String,
    script: Object
  },
  methods: {
    ...mapActions(['fetchWaitingConsultList']),
        feedBack() {
            this.$router.push("/webrtcstudent");
        },
        createConsult() {
          axios.post(API_URL + '/consult', {
            room: "www.naver.com",
            scriptId: this.scriptId
          }).then(res => {
            console.log(res)
            this.fetchWaitingConsultList()
          })
          var socket = new SockJS('http://3.34.51.116:8080/ws');
          var stompClient = Stomp.over(socket);
          stompClient.connect({}, () => {
            stompClient.send("/topic/public/",
                JSON.stringify({
                  topic : this.script.question.topic,
                  questionContent : this.script.question.questionContent,
                })
            )
            }, () => {});
        }
    },
}
</script>