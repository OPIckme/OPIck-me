<template>
<div class="card text-center">
  <div class="card-body">
    <i class="bi bi-trash3" style="margin-top:0.2rem; float: right; font-size: 17px;" data-bs-toggle="modal" :data-bs-target="feedbackDeleteModalId"></i>
    <p style="margin-top: 0.5rem; text-align: left; font-size:13px;">{{ feedback.created_at.slice(0,10) }}</p>
    <p class="topic">{{ feedback.script.question.topic }}</p>
    <button @click="routingDetail" class="btn">Review</button>
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
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
.card{
  width: 18rem; 
  height: 12rem; 
  margin: 1rem; 
  margin-left: 1.25rem; 
  margin-right: 1.25rem;
  border-radius: 15px;
}
.topic{
  margin-top: 0.5rem; 
  font-size:20px; 
  font-weight: bold;
}
/* CSS */
button {
  margin-top: 1.2rem;
  background-color: #F2CB05;
  border-radius: 12px;
  color: white;
  cursor: pointer;
  font-weight: bold;
  text-align: center;
  transition: 200ms;
  width: 100%;
  box-sizing: border-box;
  border: 0;
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