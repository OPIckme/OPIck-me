<template>
<!-- Script 생성 버튼 -->
<a class="btn btn-primary" data-bs-toggle="modal" href="#SurveyModal" role="button">Script 생성</a>
<!-- script card -->
<ScriptCard v-for="script in scriptList" :key="script.id" :script="script"></ScriptCard>

<SurveyModal></SurveyModal>
</template>

<script>
import ScriptCard from './ScriptCard.vue';
import SurveyModal from './Modal/SurveyModal.vue';
import { mapActions,mapGetters } from 'vuex';

export default {
    name: "Script",
    methods: {
        ...mapActions(['fetchScriptList']),
    },
    created() {
        this.fetchScriptList(this.$store.state.auth.user.username)
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
        ...mapGetters(['scriptList'])
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },

    components: { ScriptCard, SurveyModal }
    }
</script>