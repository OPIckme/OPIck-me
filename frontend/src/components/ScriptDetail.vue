<template>
<MainPageNavbar></MainPageNavbar>
<!-- 나가기 -->
<svg @click="back" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-backspace" viewBox="0 0 16 16">
  <path d="M5.83 5.146a.5.5 0 0 0 0 .708L7.975 8l-2.147 2.146a.5.5 0 0 0 .707.708l2.147-2.147 2.146 2.147a.5.5 0 0 0 .707-.708L9.39 8l2.146-2.146a.5.5 0 0 0-.707-.708L8.683 7.293 6.536 5.146a.5.5 0 0 0-.707 0z"/>
  <path d="M13.683 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7.08a2 2 0 0 1-1.519-.698L.241 8.65a1 1 0 0 1 0-1.302L5.084 1.7A2 2 0 0 1 6.603 1h7.08zm-7.08 1a1 1 0 0 0-.76.35L1 8l4.844 5.65a1 1 0 0 0 .759.35h7.08a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1h-7.08z"/>
</svg>
<h1>{{ script.createdAt }}</h1>
<p>{{ script.question.topic }}</p>
<h5>{{ script.question.questionContent }}</h5>
<p>[Script]</p>
<p>{{ script.scriptContent }}</p>
<!-- 재생 -->
<svg @click="clickCaret(),play(script.audioUrl)" v-if="click" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16">
  <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/>
</svg>
<!-- 정지 -->
<svg @click="clickCaret(),pause(script.audioUrl)" v-if="!click" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pause-fill" viewBox="0 0 16 16">
  <path d="M5.5 3.5A1.5 1.5 0 0 1 7 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5zm5 0A1.5 1.5 0 0 1 12 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5z"/>
</svg>
<button data-bs-toggle="modal" :data-bs-target="feedbackModalId" class="btn btn-primary">피드백 신청하기</button>
{{ feedbackModalId }}
<FeedbackModal :scriptId="scriptId"></FeedbackModal>
</template>

<script>
import MainPageNavbar from './MainPageNavbar.vue';
import FeedbackModal from './Modal/FeedbackModal.vue';
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/script';

export default {
    name: "ScriptDetail",
    data() {
    return {
      click:true,
      script: {},
      scriptId: parseInt(this.$route.params.scriptId),
      feedbackModalId: "",
    };
  },
    setup() {
    },
    methods: {
        back() {
            this.$router.push("/mainpage");
        },
        clickCaret(){
            this.click = !this.click;
        },
        getScript(){
          axios.get(API_URL + `/${this.scriptId}`)
          .then(res => {
            console.log(res.data.script)
            this.script = res.data.script
            this.feedbackModalId="#create" + this.script.id
          })
        },
        play(sound) {
          if (sound) {
            var audio = new Audio(sound);
            audio.play();
          }
        },
        pause(sound) {
          if (sound) {
            var audio = new Audio(sound);
            audio.pause();
          }
        },
    },
    created() {
        this.getScript()
    },
    components: { MainPageNavbar, FeedbackModal }
}
</script>