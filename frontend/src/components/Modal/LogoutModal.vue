<template>
  <!-- Modal -->
<div class="modal text-center" id="Logout" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <button type="button" class="btn-close position-absolute top-0 end-0" data-bs-dismiss="modal" aria-label="Close"></button>
      <p class="position-absolute top-50 start-50 translate-middle">로그아웃하시겠습니까?</p>
      <button type="button" class="btn position-absolute bottom-0 start-50 translate-middle-x" data-bs-dismiss="modal"  @click.prevent="logOut">Yes</button>
    </div>
  </div>
</div>
</template>

<script>
export default {
  setup() {
    
  },
  methods: {
        logOut() {

            this.$store.dispatch("auth/logout");
            this.$router.push("/");
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