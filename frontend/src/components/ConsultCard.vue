<template>
<div class="container text-center border border-dark border-start-0 border-end-0" style="background-color:white;">
  <div class="row">
    <p class="col">{{ waitingconsult.script.question.topic }}</p>
    <p class="col-6">{{ waitingconsult.script.question.questionContent }}</p>
    <button @click="changeState" class="btn col" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" :data-bs-target="waitingconsultId">상담하기</button>
  </div>
</div>
<ConsultStartModal :waitingconsult="waitingconsult"></ConsultStartModal>
</template>

<script>
import ConsultStartModal from './Modal/ConsultStartModal.vue'
import { mapActions } from 'vuex';


export default {
    name: "ConsultCard",
    data() {
      return {
        waitingconsultId : '#consult' + this.waitingconsult.id
      }
    },
    props : {
      waitingconsult : Object
    },
    setup() {
    },
    methods: {
      ...mapActions(['fetchWaitingConsultMap']),
    },
    components : { ConsultStartModal }
}
</script>
