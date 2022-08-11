<template>
<li class="table-row text-center">
  <div class="col col-1" data-label="Topic">{{ waitingconsult.script.question.topic }}</div>
  <div class="col col-2" data-label="Question">Q. {{ waitingconsult.script.question.questionContent }}</div>
  <div class="col col-3" data-label="State"><button @click="changeState" class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" :data-bs-target="waitingconsultId">상담하기</button></div>
</li>
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

<style lang="scss" scoped>
li {
    margin-top: 3px;
    border-radius: 3px;
    padding: 25px;
    display: flex;
    justify-content: space-around;
  }
  .table-row {
    background-color: #ffffff;
    box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.1);
  }
  .col-1 {
    flex-basis: 20%;
    font-weight: bold;
    font-size: 20px;
  }
  .col-2 {
    flex-basis: 60%;
  }
  .col-3 {
    flex-basis: 20%;
  }
</style>