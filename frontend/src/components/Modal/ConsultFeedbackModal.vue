<template>
  <!-- Modal -->
<div class="modal text-center" id="Consultfeedback" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="d-flex justify-content-center">
        <p class="position-absolute top-50 start-50 translate-middle">피드백을 생성중입니다.</p>
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem; margin-top:13rem;" role="status"></div>
      </div>
      <button v-show="false" id="close-btn" type="button" class="btn-close col-2" data-bs-dismiss="modal" aria-label="Close"></button>  
    </div>
  </div>
</div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    props : {
        room : String
    },
    mounted(){
        var socket = new SockJS('https://i7b202.p.ssafy.io/ws');
        var stompClient = Stomp.over(socket);

        console.log(this.room)
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/public/' + this.room, (payload) => {
                var message = JSON.parse(payload.body)
                if (message.content === 'quit' && message.sender != this.$store.state.auth.user.username){
                    this.$router.push('/feedback')
                }
            })}, () =>{});
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