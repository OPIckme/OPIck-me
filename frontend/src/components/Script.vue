<template>

<div class="container d-flex flex-row">
  <Sidebar style="width:20%" @selectCategory="categoryUpdate"></Sidebar>
  <div style="width:80%">
    <!-- script navbar -->
    <div class="container d-flex justify-content-between align-items-center pb-3 mb-3 link-dark text-decoration-none">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0 list-group-horizontal">
          <li class="nav-item">
            <a class="nav-link" href="#" @click="completUpdate(false)">
              <p :class="isSelectComplet(false)" style="font-size:17px; letter-spacing: 1px;">작성중인 스크립트</p></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" @click="completUpdate(true)">
              <p :class="isSelectComplet(true)" style="font-size:17px; letter-spacing: 1px;">완료된 스크립트</p></a>
          </li>
        </ul>
        <span class="nav-item scriptbutton" data-bs-toggle="modal" href="#SurveyModal" style="cursor: pointer;"><i class="bi bi-file-earmark-plus" style="margin-right:0.3rem;"></i> New Script</span>
        <!-- <button style="margin-top:2.5rem; margin-bottom: 1rem; margin-left: 0.5rem; width: 200px; height: 45px;" class="btn scriptbutton" data-bs-toggle="modal" href="#SurveyModal" role="button">Script 생성</button> -->
      </div>
    <!-- script card -->
    <div class="row">
      <ScriptCard 
      class="col-lg-3 col-md-4 col-sm-6" 
      v-for="script in scriptList" 
      :key="script.id" 
      :script="script"
      >
      </ScriptCard>
    </div>
  </div>
</div>

<SurveyModal></SurveyModal>
</template>

<script>
import ScriptCard from './ScriptCard.vue';
import SurveyModal from './Modal/SurveyModal.vue';
import { mapActions,mapGetters } from 'vuex';
import Sidebar from './Sidebar.vue';

export default {
    name: "Script",
    methods: {
      ...mapActions(['fetchScriptList','fetchCategory','fetchComplet']),
      categoryUpdate(category){
        this.fetchCategory(category)
        console.log(category)
        this.fetchScriptList(this.$store.state.auth.user.username)
      },
      completUpdate(complet){
        this.fetchComplet(complet)
        this.fetchScriptList(this.$store.state.auth.user.username)
      },
      isSelectCategory(category){
          return category===this.category?"select":""
        },
      isSelectComplet(complet){
        return complet===this.complet?"select":""
      }
    },
    created() {
        this.fetchScriptList(this.$store.state.auth.user.username)
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
        ...mapGetters(['scriptList','category','complet']),
        
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },

    components: { ScriptCard, SurveyModal, Sidebar }
    }
</script>

<style scoped>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
.scriptbutton{
  display: inline-block;
  outline: 0;
  border: none;
  box-shadow: none;
  cursor: pointer;
  padding: 9px 22px;
  /* font-size: 20px; */
  /* height: 50px; */
  font-weight: bold;
  /* color: rgb(99, 99, 99); */
  text-align: center;
  line-height: normal;
  background: #E3F2FD;
  border-radius: 15px;
  transition: 200ms;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}
.scriptbutton:not(:disabled):hover,
.scriptbutton:not(:disabled):focus{
  outline: 0;
  background: #E3F2FD;
  box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
}
.nav-item{
  margin-top: 2rem;
  margin-right: 2rem;
}
.select{
  /* color: #052A99; */
  font-weight: bold;
}
</style>