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
          <input-topic v-for="item in category" :key="item" :item="item" @topic="changeTopic"></input-topic>
        </div>
      </div>
      <div class="modal-footer">
        <button @click="getQuestion(topic,level)" class="custom-btn btn-3" :data-bs-target="surveyCheck()" data-bs-toggle="modal"><span>START</span></button>
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
      <div style="text-align:center">
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
      </div>
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
        <!-- <h1>
          <svg class="bi bi-record-circle" @click="start" data-bs-target="#SurveyModal3" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" viewBox="0 0 16 16" display="none" id="record_script">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          </svg>
        </h1> -->
        <h1>
        <svg @click="start" data-bs-target="#SurveyModal3" data-bs-toggle="modal" width="160" height="160" viewBox="0 0 72 72" xmlns="http://www.w3.org/2000/svg" display="none" id="record_script">
        <g id="color"/>
        <g id="line">
          <circle cx="36" cy="36" r="20" fill="none" stroke="#000000" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="2"/>
          <circle cx="36" cy="36" r="7" fill="#000000" stroke="none"/>
          </g>
          <g id="color-foreground">
            <circle cx="36" cy="36" r="7" fill="#D22F27" stroke="none"/>
            </g>
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
      <button class="custom-btn btn-3" @click="toggleOnOff" v-if="!isStatusOn"><span>문제 보기</span></button>
      <button class="custom-btn btn-3" @click="toggleOnOff" v-if="isStatusOn"><span>문제 숨기기</span></button>
      <div class="modal-body">
        <h5 class="modal-title" id="exampleModalToggleLabel3" v-if="isStatusOn">
         Q. {{ questionInfo.questionContent }}
        </h5>
        <!-- soundwave -->
        <svg xmlns="http://www.w3.org/2000/svg" width="300" height="300" fill="currentColor" class="bi bi-soundwave" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8.5 2a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-1 0v-11a.5.5 0 0 1 .5-.5zm-2 2a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm4 0a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm-6 1.5A.5.5 0 0 1 5 6v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm8 0a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm-10 1A.5.5 0 0 1 3 7v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5zm12 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5z"/>
        </svg>
        <!-- 일시정지 -->
        <p style="text-align:center">
          <!-- <svg @click="stop" data-bs-target="#SurveyModal4" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-pause-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M5 6.25a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5zm3.5 0a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5z"/>
          </svg> -->
          <svg @click="stop" data-bs-target="#SurveyModal4" data-bs-toggle="modal" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 473.931 473.931" style="enable-background:new 0 0 473.931 473.931;" xml:space="preserve" width="160" height="160">
            <circle style="fill:#E84849;" cx="236.966" cy="236.966" r="236.966"/>
            <path style="fill:#FFFFFF;" d="M338.771,324.568c0,7.846-6.361,14.207-14.215,14.207H149.345c-7.85,0-14.211-6.361-14.211-14.207 V149.349c0-7.854,6.361-14.215,14.211-14.215H324.56c7.854,0,14.215,6.361,14.215,14.215v175.219H338.771z"/>
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
        <div style="text-align:center">
          <!-- 녹음 -->
          <!-- <svg class="bi bi-record-circle" data-bs-target="#SurveyModal2" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          </svg> -->
          <svg data-bs-target="#SurveyModal2" data-bs-toggle="modal" width="160" height="160" viewBox="0 0 72 72" xmlns="http://www.w3.org/2000/svg">
            <g id="color"/>
            <g id="line">
              <circle cx="36" cy="36" r="20" fill="none" stroke="#000000" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="2"/>
              <circle cx="36" cy="36" r="7" fill="#000000" stroke="none"/>
            </g>
            <g id="color-foreground">
              <circle cx="36" cy="36" r="7" fill="#D22F27" stroke="none"/>
            </g>
          </svg>
          <!-- save -->
          <svg @click="s3Upload()" data-bs-dismiss="modal" aria-label="Close" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
          </svg>
          <p>다시 녹음하기    스크립트 저장</p>
          <!-- save -->
          <!-- <svg @click="s3Upload()" data-bs-dismiss="modal" aria-label="Close" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
          </svg> -->
          <p>Script savse</p>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';

import {uploadFile} from '@/plugins/s3upload';
import { mapActions } from 'vuex';
import { v4 } from 'uuid';
import {API_URL} from '@/api/http.js';
import InputTopic from '../InputTopic.vue';


export default {
  components: { InputTopic },
  data(){
    return{
      topic : '',
      level : '',
      questionInfo : {},
      audio: {},
      audioUrl : '',
      isStatusOn: false,
      userId:this.$store.state.auth.user.id,
      category: ["영화보기", "나이트클럽 가기", "공연보기", "콘서트보기", "박물관가기", "공원가기", "캠핑하기", "해변가기", "스포츠 관람", "주거 개선", "아이에게 책 읽어주기", "음악 감상하기", "악기 연주하기", "혼자 노래부르거나 합창하기", "춤추기", "글쓰기", "그림 그리기", "요리하기", "애완동물 기르기", "농구", "야구", "축구", "미식축구", "하키", "크리켓", "골프", "배구", "테니스", "배드민턴", "탁구", "수영", "자전거", "스노우보드", "아이스 스케이트", "조깅", "걷기", "요가", "하이킹", "낚시", "헬스", "국내출장", "해외출장", "집에서 보내는 휴가", "국내 여행", "해외 여행"],
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

    toggleOnOff() {
    this.isStatusOn = !this.isStatusOn;
  },

    getQuestion(topic,level) {
      if (this.topic == "" && this.level == "") { // topic과 level이 선택되지 않았을 때
        alert("Topic과 Level을 선택해주세요.");
      }
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

     surveyCheck() {
        // survey에서 topic이나 level이 선택 안되면 넘어가지 않는다.
         if (this.topic == "" || this.level == "") { // topic이나 level이 선택되지 않으면
          return 1;
          }

        if (this.topic !== "" && this.level !== "") { // topic과 level이 선택되어야만
          return "#SurveyModal2";
          }
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
    
     changeTopic(topic) {
      this.topic = topic;
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

#record_script {
  transition: all 0.5s linear;
  }
  
#record_script:hover {
  transform: scale( 1.3 )
  }

  .custom-btn {
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
   7px 7px 20px 0px rgba(0,0,0,.1),
   4px 4px 5px 0px rgba(0,0,0,.1);
  outline: none;
}

/* 3 */
.btn-3 {
  background: rgb(0,172,238);
background: linear-gradient(0deg, rgba(0,172,238,1) 0%, rgba(2,126,251,1) 100%);
  width: 130px;
  height: 40px;
  line-height: 42px;
  padding: 0;
  border: none;
  
}
.btn-3 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}
.btn-3:before,
.btn-3:after {
  position: absolute;
  content: "";
  right: 0;
  top: 0;
   background: rgba(2,126,251,1);
  transition: all 0.3s ease;
}
.btn-3:before {
  height: 0%;
  width: 2px;
}
.btn-3:after {
  width: 0%;
  height: 2px;
}
.btn-3:hover{
   background: transparent;
  box-shadow: none;
}
.btn-3:hover:before {
  height: 100%;
}
.btn-3:hover:after {
  width: 100%;
}
.btn-3 span:hover{
   color: rgba(2,126,251,1);
}
.btn-3 span:before,
.btn-3 span:after {
  position: absolute;
  content: "";
  left: 0;
  bottom: 0;
   background: rgba(2,126,251,1);
  transition: all 0.3s ease;
}
.btn-3 span:before {
  width: 2px;
  height: 0%;
}
.btn-3 span:after {
  width: 0%;
  height: 2px;
}
.btn-3 span:hover:before {
  height: 100%;
}
.btn-3 span:hover:after {
  width: 100%;
}
</style>