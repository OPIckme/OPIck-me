<template>
<div @click="routingDetail" class="card text-center" style="width: 15.5rem; height: 10.5rem; margin: 0.5rem; margin-bottom: 2rem;">
  <div  >
    <svg style="margin-top:1rem; float: right;" data-bs-toggle="modal" :data-bs-target="modalId" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="gray" class="bi bi-trash3-fill" viewBox="0 0 16 16">
      <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
    </svg>
    <!-- <svg style="margin-top:1rem; float: right;" data-bs-toggle="modal" :data-bs-target="modalId" xmlns="http://www.w3.org/2000/svg" width="30" height="20" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
      <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
      <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
    </svg> -->
  </div>
  <p>
    {{ script.createdAt.slice(0,10) }} <br> {{ script.question.topic }}
  </p>
</div>
<ScriptDeleteModal :scriptId="script.id"></ScriptDeleteModal>
</template>

<script>
import ScriptDeleteModal from './Modal/ScriptDeleteModal.vue';
export default {
    name: "ScriptCard",
    data() {
      return {
        modalId:"#delete"+this.script.id
      }
    },
    props : {
      script : Object
    },
    setup() {
    },
    components: { ScriptDeleteModal },
    methods: {
        routingDetail(){
        this.$router.push({
        name:'scriptdetail',
        params:{scriptId:this.script.id}
        })
    },
    },
}
</script>

<style scoped>
p {
  background-color:#0742F2;
  color : white;
  font-size: 17px;
  border-radius: 5px;
  margin-top: 1.5rem;
  line-height: 1.75rem;
}
.card:not(:disabled):hover,
.card:not(:disabled):focus {
  outline: 0;
  /* background: #0742F2; */
  box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
}
.bi-trash3-fill{
  color:black;
}
</style>