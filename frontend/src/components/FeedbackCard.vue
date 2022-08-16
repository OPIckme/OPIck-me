<template>
<div class="card" data-aos="fade-up" @click="routingDetail">
  <div class="card-img">
    <img :src="getImg()" class="card-img-top" alt="...">
  </div>
  <div class="card-body">
    <i class="bi bi-trash3 delete" data-bs-toggle="modal" :data-bs-target="feedbackDeleteModalId"></i>
    <span class="tag" :class="`tag-${feedback.script.question.level}`">{{ feedback.script.question.level }}</span>
    <h4 class="topic">{{ feedback.script.question.topic }}</h4>
    <p class="created-at"><small class="text-muted">{{ createdAt.slice(0,-3) }}</small></p>
    <!-- <button @click="routingDetail" class="btn">Review</button> -->
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
        feedbackDeleteModalId:"#delete"+this.feedback.id,
        createdAt: ''
      }
    },
    props : {
      feedback : Object
    },
    components: { FeedbackDeleteModal },
    methods: {
        routingDetail(){
        this.$router.push({
        name:'feedbackdetail',
        params:{feedbackId:this.feedback.id}
        })
      },
    getImg(){
        return require(`@/assets/card/${this.feedback.script.question.topic}.jpg`)
      },
    },
    mounted() {
      const d = new Date(this.feedback.created_at)
      this.createdAt = d.toLocaleString()
    }
}
</script>

<style scoped>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
.card{
  width: 18rem; 
  height: 20rem; 
  margin: 2.05rem; 
  border-radius: 15px;
  border-width: 0px;
  box-shadow: 0 4px 4px -4px gray;
  cursor: pointer;
}
.card-img-top{
    width: 100%;
    height: 11rem;
    object-fit: cover;
}
.delete{
  margin-top:0.2rem; 
  float: right; 
  font-size: 17px;
}
.topic{
  margin-top: 0.5rem; 
  font-size:18px; 
  font-weight: bold;
}
.created-at{
  margin-top: 2.3rem; 
  text-align: right; 
  font-size:15px;
}
.tag {
  background: #cccccc;
  border-radius: 50px;
  font-size: 12px;
  margin: 0;
  color: #fff;
  padding: 2px 10px;
  text-transform: uppercase;
}
.tag-AL {
  background-color: #47bcd4;
}
.tag-IH {
  background-color: #5e76bf;
}
.tag-IM {
  background-color: #cd5b9f;
}
.tag-IL {
  background-color: #c2d553;
}
/* CSS */
/* button {
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
} */
</style>