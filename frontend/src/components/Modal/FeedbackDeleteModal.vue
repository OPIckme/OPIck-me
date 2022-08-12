<template>
  <!-- Modal -->
<div class="modal fade text-center" :id="feedbackDeleteModalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <button type="button" class="btn-close position-absolute top-0 end-0" data-bs-dismiss="modal" aria-label="Close"></button>
      <p class="position-absolute top-50 start-50 translate-middle">Feedback을 삭제하시겠습니까?</p> 
      <button @click="deleteFeedback" data-bs-dismiss="modal" aria-label="Close" type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x">네</button>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
import {API_URL} from '@/api/http.js';
import { mapActions } from 'vuex';

export default {
    name: "feedbackDeleteModal",
    data() {
      return {
        feedbackDeleteModalId:"delete"+this.feedbackId
      }
    },
    props : {
      feedbackId : String,
    },
    setup() {
    },
    methods: {
      ...mapActions(['fetchFeedbackList']),
    deleteFeedback(){
      console.log(this.feedbackId)
      axios.delete(API_URL + `/feedback/${this.feedbackId}`
      ).then(res => {
        console.log(res)
        this.fetchFeedbackList(this.$store.state.auth.user.username)
      })
    }
    },
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
  background-color: #F24A1F;
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