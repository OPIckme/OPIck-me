<template>
  <!-- Modal -->
<div class="modal fade" :id="feedbackDeleteModalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Feedback을 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
        <button @click="deleteFeedback" data-bs-dismiss="modal" aria-label="Close" type="button" class="btn btn-primary">네</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/feedback';
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
      axios.delete(API_URL + `/${this.feedbackId}`
      ).then(res => {
        console.log(res)
        this.fetchFeedbackList()
      })
    }
    },
}
</script>

<style scoped>
p {
  color : white;
}
</style>