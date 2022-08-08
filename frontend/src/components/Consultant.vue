<template>
<ConsultCard v-for="waitingconsult in waitingConsultMap" :key="waitingconsult.id" :waitingconsult="waitingconsult"></ConsultCard>
</template>

<script>
import { mapActions,mapGetters } from 'vuex';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import ConsultCard from './ConsultCard.vue';

export default {
    name:'Consultant',
    methods: {
        ...mapActions(['fetchWaitingConsultMap']),
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

        var socket = new SockJS('http://3.34.51.116:8080/ws');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/public/', (payload) => {
                var message = JSON.parse(payload.body);
                console.log({script : {
                    id : message.id,
                    question : {
                        topic : message.topic,
                        questionContent : message.questionContent
                        }}})
                this.waitingConsultMap.push({script : {
                    id : message.id,
                    question : {
                        topic : message.topic,
                        questionContent : message.questionContent
                    }}})
            })}, () =>{});
    },
    components: { ConsultCard }
}
</script>