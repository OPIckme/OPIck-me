<template>
  <!-- Modal -->
<div class="modal" :id="consultStartModalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>상담을 시작합니다.</p>
        <div class="spinner-border text-primary" role="status">
        <button type="button" class="btn btn-primary"  @click="[feedBack(), changeState()]">Yes</button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {API_URL} from '@/api/http.js';
import axios from 'axios';

export default {
  data() {
    return {
      consultStartModalId : 'consult' + this.waitingconsult.id
    }
  },
  props : {
    waitingconsult : Object
  },
  methods : {
    feedBack() {
            console.log(this.waitingconsult)
            this.$router.push({name : "webrtcstudent", params: {room: this.waitingconsult.room}});
        },
    changeState(){
          axios.put(API_URL+ `/consult/complete/${this.waitingconsult.id}`
          ).then(res => {
            console.log(res)
            this.fetchWaitingConsultMap()
          })
      },
  }
}
</script>
