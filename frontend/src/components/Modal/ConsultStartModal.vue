<template>
  <!-- Modal -->
<div class="modal" id="Consultstart" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>상담을 시작합니다.</p>
        <button type="button" class="btn btn-primary"  @click="startConsult()">Yes</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';
import {API_URL} from '@/api/http.js';

export default {
  name: "ConsultStartModal",
  props: {
    waitingconsult : Object
  },
  methods : {
    ...mapActions(['fetchWaitingConsultMap']),
    startConsult() {
          axios.put(API_URL+ `/consult/complete/${this.waitingconsult.id}`
          ).then(res => {
          this.fetchWaitingConsultMap()
          })
          this.$router.push("/webrtcstudent");
        },
  }
}
</script>