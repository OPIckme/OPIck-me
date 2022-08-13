<template>
  <!-- Modal -->
<div class="modal fade text-center" :id="modalId" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <button type="button" class="btn-close position-absolute top-0 end-0" data-bs-dismiss="modal" aria-label="Close"></button>
      <p class="position-absolute top-50 start-50 translate-middle">Script를 삭제하시겠습니까?</p>
      <button @click="deleteScript" data-bs-dismiss="modal" aria-label="Close" type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x">Yes</button>
    </div>
  </div>
</div>
</template>


<script>
import axios from 'axios';
import { mapActions } from 'vuex';
import {API_URL} from '@/api/http.js';

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
    ...mapActions(['fetchScriptList']),
    deleteScript(){
      console.log(this.scriptId)
      axios.delete(API_URL + `/script/${this.scriptId}`)
      .then(res => {
        console.log(res)
        this.fetchScriptList(this.$store.state.auth.user.username)
      })
      .catch(e =>{
        console.log(e.response.data.message)
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