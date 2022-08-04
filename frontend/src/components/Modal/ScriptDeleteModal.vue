<template>
  <!-- Modal -->
<div class="modal fade" :id="modalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Script를 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
        <button @click="deleteScript" data-bs-dismiss="modal" aria-label="Close" type="button" class="btn btn-primary">네</button>
      </div>
    </div>
  </div>
</div>
</template>


<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/script';

export default {
    name: "ScriptDeleteModal",
    data() {
      return {
        modalId:"delete"+this.scriptId
      }
    },
    props : {
      scriptId : String,
    },
    setup() {
    },
    methods: {
    deleteScript(){
      console.log(this.scriptId)
      axios.delete(API_URL + `/${this.scriptId}`)
    }
    },
}
</script>


<style scoped>
p {
  color : white;
}
</style>