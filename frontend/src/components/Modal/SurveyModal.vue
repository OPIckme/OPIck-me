<template>
  <!-- Survey 모달 -->
<div class="modal" id="SurveyModal" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel">Survey(Topic과 Level을 선택해주세요.)</h5>
      </div>
      <p>Topic : {{ topic }} / Level : {{ level }}</p>
      <h1>TOPIC</h1>
      <div class="modal-body">
        <input-topic v-for="item in category" :key="item" :item="item" @topic="changeTopic"></input-topic>
      </div>
      <h1>LEVEL</h1>
      <div class="d-flex flex-column">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit()"></button>
        <select v-model="level" class="form-select" aria-label="Default select example" style="width:200px">
          <option disabled value="">Open this select Level</option>
          <option value="AL">AL</option>
          <option value="IH">IH</option>
          <option value="IM">IM</option>
          <option value="IL">IL</option>
        </select>
      </div>
      <div>
        <button @click="getQuestion(topic,level)" class="custom-btn btn-3" :data-bs-target="surveyCheck()" data-bs-toggle="modal"><span>START</span></button>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal2" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content">
        <h5 class="modal-title" id="exampleModalToggleLabel2">문제듣기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="stopSound(), surveyinit()"></button>
      <div class="modal-body">
      <!-- 듣기 -->
      <div style="text-align:center">
      <button @click="playSound(audioUrl)"></button>
      </div>


        <!-- 녹음 시작 버튼 -->
        <svg v-show="record" @click="start" width="160" height="160" viewBox="0 0 72 72" xmlns="http://www.w3.org/2000/svg" id="record_script">
        <g id="color"/>
        <g id="line">
          <circle cx="36" cy="36" r="20" fill="none" stroke="#000000" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="2"/>
          <circle cx="36" cy="36" r="7" fill="#000000" stroke="none"/>
          </g>
          <g id="color-foreground">
            <circle cx="36" cy="36" r="7" fill="#D22F27" stroke="none"/>
            </g>
        </svg>

        <!-- 녹음 중지 버튼 -->
        <svg v-show="!record" @click="stop" data-bs-target="#SurveyModal3" data-bs-toggle="modal" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 473.931 473.931" style="enable-background:new 0 0 473.931 473.931;" xml:space="preserve" width="160" height="160">
            <circle style="fill:#E84849;" cx="236.966" cy="236.966" r="236.966"/>
            <path style="fill:#FFFFFF;" d="M338.771,324.568c0,7.846-6.361,14.207-14.215,14.207H149.345c-7.85,0-14.211-6.361-14.211-14.207 V149.349c0-7.854,6.361-14.215,14.211-14.215H324.56c7.854,0,14.215,6.361,14.215,14.215v175.219H338.771z"/>
        </svg>


      </div>
    </div>
  </div>
</div>

<div class="modal" id="SurveyModal3" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel4" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div >
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit()"></button>
      </div>
      <div >
        <h5 class="modal-title" id="exampleModalToggleLabel4">
          Q. {{ questionInfo.questionContent }}
        </h5>
        

        <audio controls :src="blobURL">녹음된 소리를 재생할 audio 엘리먼트</audio>

        <div style="text-align:center">


          <!-- 다시 녹음 버튼-->
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


          <!-- 저장 버튼 -->
          <svg @click="s3Upload()" data-bs-dismiss="modal" aria-label="Close" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
          </svg>
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
      audio: null,
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
      uuid:"",
      record:true


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
      console.log(this.audio)
      if (sound && !this.audio) {
        this.audio = new Audio(sound); // data에 audio 객체 있음.
        this.audio.play();
        console.log(this.audio)
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
      this.record=!this.record
      this.uuid =v4()
      // this.stopSound(); // 문제를 듣다가 녹음 버튼 누르면 문제 듣기 종료
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
      this.record=!this.record
      this.mediaRecorder.stop();
     },     
  },

}
</script>

<style scoped>
.modal-content {
  background-color: #E3F2FD;
  height: 50rem;
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