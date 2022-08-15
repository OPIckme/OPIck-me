<template>
<ConsultCard 
    v-for="waitingconsult in waitingConsultMap" 
    :key="waitingconsult.id" 
    :waitingconsult="waitingconsult"
    @consultOpen="consultOpen">
</ConsultCard>
</template>

<script>
import { mapActions,mapGetters } from 'vuex';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import ConsultCard from './ConsultCard.vue';

var socket = new SockJS('https://i7b202.p.ssafy.io/ws');
var stompClient = Stomp.over(socket);

export default {
    name:'Consultant',
    methods: {
        ...mapActions(['fetchWaitingConsultMap']),
        consultOpen(value) {
            stompClient.disconnect()
        }
    },
    created() {
        this.fetchWaitingConsultMap()
    },
    computed: {
        ...mapGetters(['waitingConsultMap']),
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
     
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/public/', (payload) => {
                var message = JSON.parse(payload.body);
                const id = message.id
                if (message.method === 'delete'){
                    delete this.waitingConsultMap[id]
                    console.log(this.waitingConsultMap)
                } else {
                    this.waitingConsultMap[id] = {
                        id : id,
                        script : message.script,
                        room : message.room
                    }
                }
            })}, () =>{});
    },
    components: { ConsultCard }
}
</script>
