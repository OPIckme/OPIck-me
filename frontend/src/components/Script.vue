<template>
<!-- Script 생성 버튼 -->
<button style="margin-top:2.5rem; margin-bottom: 2rem; width: 200px; height: 45px;" class="btn btn-primary scriptbutton" data-bs-toggle="modal" href="#SurveyModal" role="button">Script 생성</button>
<!-- script card -->
<div  class="row">
    <ScriptCard 
    class="col-lg-2 col-md-3 col-sm-4" 
    v-for="script in scriptList" 
    :key="script.id" 
    :script="script">
    </ScriptCard>
</div>

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

<style scoped>
.scriptbutton{
    display: inline-block;
    outline: 0;
    border: none;
    box-shadow: none;
    cursor: pointer;
    padding: 9px 22px;
    font-size: 20px;
    height: 50px;
    font-weight: 400;
    color: #fff;
    text-align: center;
    line-height: normal;
    background: #0742F2;
    border-radius: 50px;
    transition: 200ms;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
}
.scriptbutton:not(:disabled):hover,
.scriptbutton:not(:disabled):focus{
    outline: 0;
    background: #0742F2;
    box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
}
                
                
</style>