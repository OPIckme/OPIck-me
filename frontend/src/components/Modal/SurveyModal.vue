<template>
  <!-- Survey 모달 -->
<div class="modal" id="SurveyModal" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel">Survey(Topic과 Level을 선택해주세요.)</h5>
        <!-- select level -->
        <div class="d-flex flex-column">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit()"></button>
          <span></span>
          <select v-model="level" class="form-select" aria-label="Default select example" style="width:200px">
            <option disabled value="">Open this select Level</option>
            <option value="AL">AL</option>
            <option value="IH">IH</option>
            <option value="IM">IM</option>
            <option value="IL">IL</option>
          </select>
        </div>
      </div>
      <p>Topic : {{ topic }} / Level : {{ level }}</p>
      <div class="modal-body">
        <div class="leisure-activities">
          <h1>여가활동</h1>
          <div class="form-check form-check-inline">
            <input v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" id="leisure1" value="영화보기">
            <label class="form-check-label" for="영화보기">영화보기</label>
          </div>
          <div class="form-check form-check-inline">
            <input v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" id="leisure2" value="나이트클럽 가기">
            <label class="form-check-label" for="나이트클럽 가기">나이트클럽 가기</label>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button @click="getQuestion(topic,level)" class="btn btn-primary" :data-bs-target="surveyCheck()" data-bs-toggle="modal">START</button>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal2" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel2">문제듣기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="stopSound(), surveyinit()"></button>
      </div>
      <div class="modal-body">
      <!-- 듣기 -->
      <svg @click="playSound(audioUrl)" version="1.1" id="problem_listen" width="160" height="160" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 392.663 392.663" style="enable-background:new 0 0 392.663 392.663;" xml:space="preserve">
      <polyline style="fill:#FFFFFF;" points="93.091,248.377 230.853,337.524 230.853,55.019 93.091,144.166 "/>
      <polygon style="fill:#56ACE0;" points="114.877,236.546 114.877,155.997 209.067,95.1 209.067,297.443 "/>
      <g>
        <path style="fill:#FFC10D;" d="M252.638,160.781v70.917c15.127-4.655,26.182-18.747,26.182-35.491S267.83,165.5,252.638,160.781z"
          />
        <rect x="21.851" y="149.144" style="fill:#FFC10D;" width="49.39" height="94.255"/>
      </g>
      <g>
        <path style="fill:#194F82;" d="M252.768,138.413V34.979c0-8.663-9.568-13.834-16.873-9.115L78.933,127.358H10.925
          C4.913,127.358,0,132.207,0,138.284v116.04c0,6.012,4.848,10.925,10.925,10.925h68.008l156.962,101.56
          c7.24,4.719,16.873-0.517,16.873-9.115V254.13c27.216-5.172,47.968-29.156,47.968-57.859S280.048,143.585,252.768,138.413z
          M71.24,243.399h-49.39v-94.255h49.39V243.399z M230.853,337.524L93.091,248.377v-19.394h21.786
          c6.012,0,10.925-4.848,10.925-10.925c0-6.012-4.848-10.925-10.925-10.925H93.091v-21.786h43.636
          c6.012,0,10.925-4.848,10.925-10.925c0-6.012-4.848-10.925-10.925-10.925H93.091v-19.394l137.762-89.147L230.853,337.524
          L230.853,337.524z M252.638,231.762v-70.982c15.127,4.655,26.182,18.747,26.182,35.491S267.83,227.043,252.638,231.762z"/>
        <path style="fill:#194F82;" d="M348.509,89.54c-4.267-4.267-11.184-4.267-15.451,0s-4.202,11.119,0.065,15.386
          c23.337,23.402,37.689,55.725,37.689,91.281s-14.481,67.814-37.689,91.216c-4.267,4.267-4.331,11.119-0.065,15.386
          c4.267,4.267,11.184,4.331,15.451,0c27.216-27.281,44.154-64.97,44.154-106.537S375.661,117.015,348.509,89.54z"/>
        <path style="fill:#194F82;" d="M315.475,121.54c-4.008-3.943-10.925-3.426-14.933,0.517c-4.073,4.073-4.331,11.119-0.129,15.192
          c15.063,15.127,24.501,35.943,24.501,58.958s-9.374,43.83-24.501,58.958c-4.073,4.073-3.943,11.119,0.129,15.192
          c4.008,4.008,10.925,4.59,14.933,0.517c19.265-19.071,31.224-45.576,31.224-74.731C346.634,167.051,334.739,140.611,315.475,121.54
          z"/>
      </g>
      </svg>
        <!-- 듣기
        <svg @click="playSound(audioUrl)" xmlns="http://www.w3.org/2000/svg" width="160" height="160" fill="currentColor" class="bi bi-volume-up-fill" viewBox="0 0 16 16" id="problem_listen">
          <path d="M11.536 14.01A8.473 8.473 0 0 0 14.026 8a8.473 8.473 0 0 0-2.49-6.01l-.708.707A7.476 7.476 0 0 1 13.025 8c0 2.071-.84 3.946-2.197 5.303l.708.707z"/>
          <path d="M10.121 12.596A6.48 6.48 0 0 0 12.025 8a6.48 6.48 0 0 0-1.904-4.596l-.707.707A5.483 5.483 0 0 1 11.025 8a5.483 5.483 0 0 1-1.61 3.89l.706.706z"/>
          <path d="M8.707 11.182A4.486 4.486 0 0 0 10.025 8a4.486 4.486 0 0 0-1.318-3.182L8 5.525A3.489 3.489 0 0 1 9.025 8 3.49 3.49 0 0 1 8 10.475l.707.707zM6.717 3.55A.5.5 0 0 1 7 4v8a.5.5 0 0 1-.812.39L3.825 10.5H1.5A.5.5 0 0 1 1 10V6a.5.5 0 0 1 .5-.5h2.325l2.363-1.89a.5.5 0 0 1 .529-.06z"/>
        </svg> -->
        <!-- 문제 듣기 안되는 스피커
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="160"
            height="160"
            fill="currentColor"
            class="bi bi-volume-up-fill"
            viewBox="0 0 16 16"
            id="problem_nolisten"
            display="none"
          >
            <path
              d="M11.536 14.01A8.473 8.473 0 0 0 14.026 8a8.473 8.473 0 0 0-2.49-6.01l-.708.707A7.476 7.476 0 0 1 13.025 8c0 2.071-.84 3.946-2.197 5.303l.708.707z"
            />
            <path
              d="M10.121 12.596A6.48 6.48 0 0 0 12.025 8a6.48 6.48 0 0 0-1.904-4.596l-.707.707A5.483 5.483 0 0 1 11.025 8a5.483 5.483 0 0 1-1.61 3.89l.706.706z"
            />
            <path
              d="M8.707 11.182A4.486 4.486 0 0 0 10.025 8a4.486 4.486 0 0 0-1.318-3.182L8 5.525A3.489 3.489 0 0 1 9.025 8 3.49 3.49 0 0 1 8 10.475l.707.707zM6.717 3.55A.5.5 0 0 1 7 4v8a.5.5 0 0 1-.812.39L3.825 10.5H1.5A.5.5 0 0 1 1 10V6a.5.5 0 0 1 .5-.5h2.325l2.363-1.89a.5.5 0 0 1 .529-.06z"
            />
          </svg> -->
          <!-- 문제 듣기 안되는 스피커 -->
<svg version="1.1" id="problem_nolisten"  width="160" height="160" display="none" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
	 viewBox="0 0 392.663 392.663" style="enable-background:new 0 0 392.663 392.663;" xml:space="preserve">
<polyline style="fill:#FFFFFF;" points="93.091,248.377 230.853,337.524 230.853,55.019 93.091,144.166 "/>
<polygon style="fill:#56ACE0;" points="114.877,236.546 114.877,155.997 209.067,95.1 209.067,297.443 "/>
<g>
	<path style="fill:#FFC10D;" d="M252.638,160.781v70.917c15.127-4.655,26.182-18.747,26.182-35.491S267.83,165.5,252.638,160.781z"
		/>
	<rect x="21.851" y="149.144" style="fill:#FFC10D;" width="49.39" height="94.255"/>
</g>
<g>
	<path style="fill:#194F82;" d="M252.768,138.413V34.979c0-8.663-9.568-13.834-16.873-9.115L78.933,127.358H10.925
		C4.913,127.358,0,132.207,0,138.284v116.04c0,6.012,4.848,10.925,10.925,10.925h68.008l156.962,101.56
		c7.24,4.719,16.873-0.517,16.873-9.115V254.13c27.216-5.172,47.968-29.156,47.968-57.859S280.048,143.585,252.768,138.413z
		 M71.24,243.399h-49.39v-94.255h49.39V243.399z M230.853,337.524L93.091,248.377v-19.394h21.786
		c6.012,0,10.925-4.848,10.925-10.925c0-6.012-4.848-10.925-10.925-10.925H93.091v-21.786h43.636
		c6.012,0,10.925-4.848,10.925-10.925c0-6.012-4.848-10.925-10.925-10.925H93.091v-19.394l137.762-89.147L230.853,337.524
		L230.853,337.524z M252.638,231.762v-70.982c15.127,4.655,26.182,18.747,26.182,35.491S267.83,227.043,252.638,231.762z"/>
	<path style="fill:#194F82;" d="M348.509,89.54c-4.267-4.267-11.184-4.267-15.451,0s-4.202,11.119,0.065,15.386
		c23.337,23.402,37.689,55.725,37.689,91.281s-14.481,67.814-37.689,91.216c-4.267,4.267-4.331,11.119-0.065,15.386
		c4.267,4.267,11.184,4.331,15.451,0c27.216-27.281,44.154-64.97,44.154-106.537S375.661,117.015,348.509,89.54z"/>
	<path style="fill:#194F82;" d="M315.475,121.54c-4.008-3.943-10.925-3.426-14.933,0.517c-4.073,4.073-4.331,11.119-0.129,15.192
		c15.063,15.127,24.501,35.943,24.501,58.958s-9.374,43.83-24.501,58.958c-4.073,4.073-3.943,11.119,0.129,15.192
		c4.008,4.008,10.925,4.59,14.933,0.517c19.265-19.071,31.224-45.576,31.224-74.731C346.634,167.051,334.739,140.611,315.475,121.54
		z"/>
</g>
</svg>
        <!-- 녹음 -->
        <h1>
          <svg class="bi bi-record-circle" @click="start" data-bs-target="#SurveyModal3" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" viewBox="0 0 16 16" display="none" id="record_script">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          </svg>
        </h1>
        <h2> Start recording</h2>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal3" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel3" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit()"></button>
      </div>
      <div class="modal-body">
        <h5 class="modal-title" id="exampleModalToggleLabel3">
         Q. {{ questionInfo.questionContent }}
        </h5>
        <!-- soundwave -->
        <svg xmlns="http://www.w3.org/2000/svg" width="300" height="300" fill="currentColor" class="bi bi-soundwave" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8.5 2a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-1 0v-11a.5.5 0 0 1 .5-.5zm-2 2a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm4 0a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm-6 1.5A.5.5 0 0 1 5 6v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm8 0a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm-10 1A.5.5 0 0 1 3 7v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5zm12 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5z"/>
        </svg>
        <!-- 일시정지 -->
        <p>
          <svg @click="stop" data-bs-target="#SurveyModal4" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-pause-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M5 6.25a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5zm3.5 0a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5z"/>
          </svg>
        </p>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal4" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel4" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit()"></button>
      </div>
      <div class="modal-body">
        <h5 class="modal-title" id="exampleModalToggleLabel4">
          Q. {{ questionInfo.questionContent }}
        </h5>
        <!-- soundwave -->
        <svg xmlns="http://www.w3.org/2000/svg" width="300" height="300" fill="currentColor" class="bi bi-soundwave" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8.5 2a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-1 0v-11a.5.5 0 0 1 .5-.5zm-2 2a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm4 0a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm-6 1.5A.5.5 0 0 1 5 6v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm8 0a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm-10 1A.5.5 0 0 1 3 7v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5zm12 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5z"/>
        </svg>
        <!--오디오 시작-->
        <audio controls :src="blobURL">녹음된 소리를 재생할 audio 엘리먼트</audio>
        <!--오디오 끝-->
        <div>
          <!-- 녹음 -->
          <svg class="bi bi-record-circle" data-bs-target="#SurveyModal2" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          </svg>
          <p>Re-Start</p>
          <!-- save -->
          <svg @click="s3Upload()" data-bs-dismiss="modal" aria-label="Close" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
          </svg>
          <p>Script savse</p>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
// import InputTopic from "../InputTopic.vue";
import {uploadFile} from '@/plugins/s3upload';
import { mapActions } from 'vuex';
import { v4 } from 'uuid';
import {API_URL} from '@/api/http.js';


export default {
  data(){
    return{
      topic : '',
      level : '',
      questionInfo : {},
      audio: {},
      audioUrl : '',
      userId:this.$store.state.auth.user.id,
      category: ["해외 여행", "국내 여행"],
      //---audio 녹음 data 시작--
      mediaRecorder:null,
      audioArray : [],
      blob: {},
      blobURL:"",
      uploadParams:{},
      uuid:""


      //---audio 녹음 data 끝
    }
  },
  methods : {
    ...mapActions(['fetchScriptList']),
    s3Upload(){
      uploadFile(this.uploadParams,this.saveScript,this.uuid)
    },

    getQuestion(topic,level) {
      if (this.topic == "" && this.level !== "") { // topic이 선택되지 않았을 때
        alert("Topic을 선택해주세요.");
      }

      if (this.topic !== "" && this.level == "") { // level이 선택되지 않았을 때
        alert("Level을 선택해주세요.");
      }

      axios.get(API_URL + '/question/random', {
        params: {
          topic: topic,
          level: level
        }
      })
      .then(response => {
        console.log(response)
        this.questionInfo = response.data
        this.audioUrl = response.data.audioUrl
        console.log(this.questionInfo)
      });
    },


    saveScript(fileName) {
      // setTimeout(
      //   axios.post(API_URL + '/script', {
      //     userId: this.userId,
      //     questionId: this.questionInfo.id,
      //     audioURL: `https://jaeyeong-s3.s3.ap-northeast-2.amazonaws.com/${fileName}.webm`,
      //     keyName: fileName,
      //   }).then(res=>{
      //     console.log(res)
      //     this.fetchScriptList(this.$store.state.auth.user.username)
      //   }),500)
      axios.post(API_URL + '/script', {
          userId: this.userId,
          questionId: this.questionInfo.id,
          audioURL: `https://jaeyeong-s3.s3.ap-northeast-2.amazonaws.com/${fileName}.webm`,
          keyName: fileName,
        }).then(res=>{
          console.log(res)
          this.fetchScriptList(this.$store.state.auth.user.username)
        })
    },

    surveyCheck() {
      // survey에서 topic이나 level이 선택 안되면 넘어가지 않는다.
      if (this.topic !== "" && this.level !== "") { // topic과 level이 선택되어야만
        return "#SurveyModal2";
      }
    },


    surveyinit() { // survey 선택 창에서 x버튼 클릭하면 survey 선택 초기화
      this.topic = ""; // topic 초기화
      this.level = ""; // level 초기화
    },

    playSound(sound) {
      // 문제 듣기
      if (sound) {
        this.audio = new Audio(sound); // data에 audio 객체 있음.
        this.audio.play();

        const problem_listen = document.getElementById("problem_listen"); // 문제 듣기 가져오기
        const problem_nolisten = document.getElementById("problem_nolisten"); // 문제 듣지 않기 가져오기

        // problem_listen 숨기기 (display: none)
        if (problem_listen.style.display !== "none") {
          // none이 아니면 none으로 바꿔서 숨기기
          problem_listen.style.display = "none";
        }

        // problem_nolisten 보이기 (display: block)
        if (problem_nolisten.style.display !== "block") {
          // block 아니면 block 바꿔서 보이기
          problem_nolisten.style.display = "block";
        }

        this.audio.onended = function () {
          // 문제 듣기가 종료되었을 때

          // problem_listen 보이기 (display: block)
          if (problem_listen.style.display !== "block") {
            // block 아니면 block 바꿔서 보이기
            problem_listen.style.display = "block";
          }

          // problem_nolisten 숨기기 (display: none)
          if (problem_nolisten.style.display !== "none") {
            // none이 아니면 none으로 바꿔서 숨기기
            problem_nolisten.style.display = "none";
          }

          const record = document.getElementById("record_script"); // 녹음 아이디 가져오기 v-if로 고치기

          // record 녹음 버튼 보이기 (display: none)
          if (record.style.display !== "block") {
            // 원래 안보였으면
            record.style.display = "block"; // 보이게 하기
          }
        };
      }
    },

    stopSound() {
      // 문제 듣기를 중지한다.
      this.audio.pause(); // 데이터에서 가져와서 사용한다. 오디오를 정지한다.
      this.audio.currentTime = 0; // 오디오 시간 초기화

      const record = document.getElementById("record_script"); // 녹음 아이디 가져오기

      // modal X 버튼 누르면 record 녹음 버튼 숨기기
      if (record.style.display !== "none") {
        // 원래 보였으면
        record.style.display = "none"; // 숨기기
      }

      const problem_listen = document.getElementById("problem_listen"); // 문제 듣기 가져오기
      const problem_nolisten = document.getElementById("problem_nolisten"); // 문제 듣지 않기 가져오기

      // problem_listen 보이기 (display: block)
      if (problem_listen.style.display !== "block") {
        // block 아니면 block 바꿔서 보이기
        problem_listen.style.display = "block";
      }

      // problem_nolisten 숨기기 (display: none)
      if (problem_nolisten.style.display !== "none") {
        // none이 아니면 none으로 바꿔서 숨기기
        problem_nolisten.style.display = "none";
      }
    },

    async start(){
      this.uuid =v4()
      this.stopSound(); // 문제를 듣다가 녹음 버튼 누르면 문제 듣기 종료
      // 마이크 mediaStream 생성: Promise를 반환하므로 async/await 사용
      const mediaStream = await navigator.mediaDevices.getUserMedia({audio: true});

      // MediaRecorder 생성
      this.mediaRecorder = new MediaRecorder(mediaStream);

      // 이벤트핸들러: 녹음 데이터 취득 처리
      this.mediaRecorder.ondataavailable = (event)=>{
          this.audioArray.push(event.data); // 오디오 데이터가 취득될 때마다 배열에 담아둔다.
      }

      // 이벤트핸들러: 녹음 종료 처리 & 재생하기
      this.mediaRecorder.onstop = ()=>{
        const audioType="webm"
        const fileName=this.uuid
        // 녹음이 종료되면, 배열에 담긴 오디오 데이터(Blob)들을 합친다: 코덱도 설정해준다.
        this.blob = new Blob(this.audioArray, {"type":`audio/${audioType}`});
        console.log(this.blob);
        this.audioArray.splice(0); // 기존 오디오 데이터들은 모두 비워 초기화한다.
        
        // Blob 데이터에 접근할 수 있는 주소를 생성한다.
        this.blobURL = window.URL.createObjectURL(this.blob);
        

        // s3에 업로드하기 위한 파라미터
        this.uploadParams = {
          Bucket: "jaeyeong-s3",
          Key: `${fileName}.${audioType}`, // File name you want to save as in S3            
          Body: this.blob,
          ContentType: `audio/${audioType}`
        };     
            
      }

      // 녹음 시작
      this.mediaRecorder.start();
     },
     async stop(){
      this.mediaRecorder.stop();
     },     
  },

}
</script>

<style scoped>
.modal-content {
  background-color: #E3F2FD;
}

#problem_nolisten{
  animation: up-down 1.4s infinite ease-in-out alternate;
}

@keyframes up-down{
  from{
    transform: translatey(0px);
  }
  to{
    transform: translatey(-20px);
  }
}

#problem_listen {
  transition: all 0.5s linear;
  }
  
#problem_listen:hover {
  transform: scale( 1.3 )
  }

/* 박스 위아래로 움직이는 애니메이션 코드 */
/* .box{
  width: 100%;
  height: 120px;
  text-align: center;
  color: #E62200;
  max-width: 300px;
  border: solid 10px #ffa23c;
  background: #fff;
  border-radius: 15px;
  padding-top: 30px;
  animation: up-down 1.4s infinite ease-in-out alternate;
} */

/* 박스 확대 코드 */
/* * {
  box-sizing: border-box;
  }
  
.jb-box {
  width: 100px;
  height: 100px;
  margin: 50px auto;
  padding: 20px;
  background-color: orange;
  transition: all 0.5s linear;
  }
  
  .jb-box:hover {
    transform: scaleX( 1.5 )
    } */
</style>