<template>
<ConsultCard v-for="waitingconsult in waitingConsultList" :key="waitingconsult.id" :waitingconsult="waitingconsult"></ConsultCard>
</template>

<script>
import ConsultCard from './ConsultCard.vue';
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/consult';
export default {
    name:'Consultant',
    data (){
        return {
            waitingConsultList : [],
        };
    },
    methods: {
        getWaitingConsultList() {
            axios.get(API_URL)
            .then(response => {
                console.log(response.data.consultList)
                this.waitingConsultList = response.data.consultList
                })
        }
    },
    created() {
        this.getWaitingConsultList()
    },
    components: { ConsultCard }
}
</script>