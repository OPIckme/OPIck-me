<template>
<ConsultCard v-for="waitingconsult in waitingConsultList" :key="waitingconsult.id" :waitingconsult="waitingconsult"></ConsultCard>
</template>

<script>
import ConsultCard from './ConsultCard.vue';
import { mapActions,mapGetters } from 'vuex';
export default {
    name:'Consultant',
    methods: {
        ...mapActions(['fetchWaitingConsultList'])
    },
    created() {
        this.fetchWaitingConsultList()
    },
    computed: {
        ...mapGetters(['waitingConsultList']),
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
     mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },
    components: { ConsultCard }
}
</script>