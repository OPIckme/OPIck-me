<template>
<div class="container text-center border border-dark border-start-0 border-end-0" style="background-color:white;">
  <div class="row">
    <p class="col">{{ waitingconsult.script.question.topic }}</p>
    <p class="col-6">{{ waitingconsult.script.question.questionContent }}</p>
    <button @click="changeState" class="btn col" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Consultstart">상담하기</button>
  </div>
</div>
<ConsultStartModal></ConsultStartModal>
</template>

<script>
import ConsultStartModal from './Modal/ConsultStartModal.vue'
import axios from 'axios';
import { mapActions } from 'vuex';
const API_URL = 'http://localhost:8080/api/v1/consult/complete';

export default {
    name: "ConsultCard",
    props : {
      waitingconsult : Object
    },
    setup() {
    },
    methods: {
      ...mapActions(['fetchWaitingConsultList']),
        changeState(){
          axios.put(API_URL+ `/${this.waitingconsult.id}`
          ).then(res => {
            console.log(res)
            this.fetchWaitingConsultList()
          })
    },
    },
    components : { ConsultStartModal }
}
</script>