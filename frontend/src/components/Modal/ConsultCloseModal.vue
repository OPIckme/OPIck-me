<template>
  <!-- Modal -->
<div class="modal text-center" id="Consultclose" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" >
  <div class="modal-dialog">
    <div class="modal-content">
      <button type="button" class="btn-close position-absolute top-0 end-0" data-bs-dismiss="modal" aria-label="Close"></button>
      <p class="position-absolute top-50 start-50 translate-middle">상담을 종료하시겠습니까?</p> 
      <button v-if="role==='student'" type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x" data-bs-dismiss="modal"  @click="consultCloseStudent(),completeConsult()">Yes</button>
      <button v-else type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x" data-bs-dismiss="modal"  @click="consultCloseConsult">Yes</button>
    </div>
  </div>
</div>
</template>

<script>
import {API_URL} from '@/api/http.js';
import axios from 'axios';
import { mapGetters,mapActions } from 'vuex';
export default {
  data(){
    return{
      role:this.$store.state.auth.user.role
    }
  },
  props : {
    modify:String,
    scriptId:String,
  },
  computed:{
    ...mapGetters(['consultId']),
  },
  methods: {
    ...mapActions(['fetchHere2']),
    consultCloseStudent() {
      this.$emit("change",1)
      this.fetchHere2()
      this.$router.push("/feedback");
    },
    async consultCloseConsult() {
      axios.post(API_URL+ `/feedback`,{
        content: this.modify,
        scriptId: this.scriptId
      })
      .then(res => {
        console.log(res)
      })
      this.$emit("change",1)
      this.$router.push("/mainpage");
    },
    completeConsult(){
      console.log(this.modify)
      axios.delete(API_URL+ `/consult/complete/${this.consultId}`)
      .then(res => {
        console.log(res)
      })
    },
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
  background-color: #F2CB05;
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