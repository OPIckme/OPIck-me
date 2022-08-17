<template>
  <!-- Modal -->
<div class="modal text-center" id="Loading" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="d-flex justify-content-center">
        <div class="spinner-border text-primary" style="width: 6rem; height: 6rem; margin-top:7rem;" role="status"></div>
      </div>
      <button v-show="false" id="close-btn" type="button" class="btn-close col-2" data-bs-dismiss="modal" aria-label="Close"></button>  
    </div>
  </div>
</div>
</template>

<script>

export default {
  data(){
    return{
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