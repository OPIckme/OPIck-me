<template>
<div class="card text-center" style="width: 15.5rem; height: 12rem; margin: 0.5rem; margin-bottom: 2rem;">
  <div class="card-body">
    <svg style="margin-top:0.5rem; float: right;" data-bs-toggle="modal" :data-bs-target="feedbackDeleteModalId" xmlns="http://www.w3.org/2000/svg" width="30" height="20" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
      <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
      <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
    </svg>
      <p style="margin-top: 0.5rem; text-align: left; font-size:13px;">{{ feedback.created_at.slice(0,10) }}</p>
      <p style="margin-top: 0.5rem; font-size:20px">{{ feedback.script.question.topic }}</p>
      <button @click="routingDetail" class="btn">복습하기</button>
  </div>
</div>
<FeedbackDeleteModal :feedbackId="feedback.id"></FeedbackDeleteModal>
</template>

<script>
import FeedbackDeleteModal from './Modal/FeedbackDeleteModal.vue';

export default {
    name: "FeedbackCard",
    data() {
      return {
        feedbackDeleteModalId:"#delete"+this.feedback.id
      }
    },
    props : {
      feedback : Object
    },
    setup() {
    },
    components: { FeedbackDeleteModal },
    methods: {
        routingDetail(){
        this.$router.push({
        name:'feedbackdetail',
        params:{feedbackId:this.feedback.id}
        })
    },
    },
}
</script>

<style scoped>
/* p {
  font-size: 17px;
  border-radius: 5px;
  margin-top: 3rem;
  line-height: 1.75rem;
} */
/* CSS */
button {
  margin-top: 1.2rem;
  background-color: #F2CB05;
  border-radius: 12px;
  color: white;
  cursor: pointer;
  font-weight: bold;
  /* padding: 10px 15px; */
  text-align: center;
  transition: 200ms;
  width: 100%;
  box-sizing: border-box;
  border: 0;
  /* font-size: 16px; */
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

button:not(:disabled):hover,
button:not(:disabled):focus {
  outline: 0;
  background: #F2CB05;
  box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
}

button:disabled {
  filter: saturate(0.2) opacity(0.5);
  -webkit-filter: saturate(0.2) opacity(0.5);
  cursor: not-allowed;
}
</style>