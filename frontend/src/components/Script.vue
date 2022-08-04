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
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/script';

export default {
    name: "Script",
    data() {
    return {
      scriptList:[],
    };
  },
    methods: {
        getScriptList() {
            axios.get(API_URL)
            .then(response => {
                console.log(response.data.scriptList)
                this.scriptList = response.data.scriptList})
        }
    },
    created() {
        this.getScriptList()
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },
    components: { ScriptCard, SurveyModal }
};
</script>