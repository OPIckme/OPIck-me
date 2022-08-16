<template>
  <!-- Survey 모달 -->
<div class="modal" id="SurveyModal" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content text-center">
      <div class="modal-top row justify-content-evenly">
        <h6 class="col-6 text-start" style="cursor:default"><img src="../../assets/check.png" style="width:25px"> Script Record</h6>
        <button type="button" class="btn-close col-2" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit(),fetchSelectTopicIdx('')"></button>
      </div>
      <div class="container">
        <h5 style="cursor:default">TOPIC : {{ topic }}</h5>
        <div class="modal-body topics text-center">
          <input-topic class="topic" v-for="(item,idx) in category" :key="item" :idx="idx" :item="item" @topic="changeTopic"></input-topic>
        </div>
        <h5 style="cursor:default">LEVEL : {{ level }}</h5>
        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
          <input v-model="level" type="radio" class="btn-check" name="btnradio" id="AL" value="AL">
          <label class="btn btn-outline-primary" for="AL">AL</label>
          <input v-model="level" type="radio" class="btn-check" name="btnradio" id="IH" value="IH">
          <label class="btn btn-outline-primary" for="IH">IH</label>
          <input v-model="level" type="radio" class="btn-check" name="btnradio" id="IM" value="IM">
          <label class="btn btn-outline-primary" for="IM">IM</label>
          <input v-model="level" type="radio" class="btn-check" name="btnradio" id="IL" value="IL">
          <label class="btn btn-outline-primary" for="IL">IL</label>
        </div>
      </div>
      <div>
        <button @click="getQuestion(topic,level),fetchSelectTopicIdx('')" class="start_btn btn position-absolute bottom-0 start-50 translate-middle-x" :data-bs-target="surveyCheck()" data-bs-toggle="modal"><span>START</span></button>
      </div>
    </div>
  </div>
</div>

<div class="modal" id="SurveyModal2" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content text-center">
      <div class="modal-top row justify-content-evenly" style="z-index:1">
        <h6 class="col-6 text-start"><img src="../../assets/check.png" style="width:25px"> Script Record</h6>
        <button type="button" class="btn-close col-2" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit(), stopSound() "></button>
      </div>
      <!-- 듣기 -->
      <i @click="playSound(audioUrl)" :class="soundIconClass" class="position-absolute top-0 start-50 translate-middle-x"></i>
      <!-- 녹음 시작 버튼 -->
      <i v-show="record" @click="start(),stopSound()" class="bi bi-record-circle position-absolute bottom-0 start-50 translate-middle-x"></i>
      <!-- 녹음 중지 버튼 -->
      <i v-show="!record" @click="stop" data-bs-target="#SurveyModal3" data-bs-toggle="modal" class="bi bi-stop-circle position-absolute bottom-0 start-50 translate-middle-x"></i>
    </div>
  </div>
</div>


<div class="modal" id="SurveyModal3" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel4" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-top row justify-content-evenly" style="z-index:1">
        <h6 class="col-6 text-start"><img src="../../assets/check.png" style="width:25px"> Script Record</h6>
        <button type="button" class="btn-close col-2" data-bs-dismiss="modal" aria-label="Close" @click="surveyinit(), fetchSelectTopicIdx('')"></button>
      </div>
      <p style="margin-left:3.5rem;">[ {{topic}} ]</p>
      <p class="modal-title" id="exampleModalToggleLabel4">
        Q. {{ questionInfo.questionContent }}
      </p>
      <audio controls :src="blobURL"></audio>
      <!-- 다시 녹음 버튼-->
      <i data-bs-target="#SurveyModal2" data-bs-toggle="modal" class="bi bi-arrow-counterclockwise position-absolute bottom-0 start-0" style="margin-left:3rem"></i>
      <!-- 저장 버튼 -->
      <i @click="s3Upload(),surveyinit()" data-bs-dismiss="modal" aria-label="Close" class="bi bi-download position-absolute bottom-0 end-0" style="margin-right:3rem"></i>
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
      record:true,
      //---audio 녹음 data 끝
      soundIconClass:'bi bi-volume-off'
    }
  },
  methods : {
    ...mapActions(['fetchScriptList','fetchSelectTopicIdx']),
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
      if (sound && !this.audio) {
        this.audio = new Audio(sound); // data에 audio 객체 있음.
        this.audio.play();
        console.log(this.audio)
        this.audio.addEventListener("ended", ()=>{ 
          console.log('asdfxcv')
            this.audio=null
            this.soundIconClass='bi bi-volume-off'
        });
        this.soundIconClass='bi bi-volume-up'
      }
    },

    stopSound() {
      // 문제 듣기를 중지한다.
      this.audio.pause(); // 데이터에서 가져와서 사용한다. 오디오를 정지한다.
      this.audio.currentTime = 0; // 오디오 시간 초기화
      this.soundIconClass='bi bi-volume-off'
      this.audio=null
    },
    
     changeTopic(topic) {
      this.topic = topic;
     },
     changeLevel(level) {
      this.level = level;
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
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
.modal-content {
  height: 40rem;
  border-radius: 50px;
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

.modal-top{
  margin-bottom: 2rem;
}
.modal-title{
  margin-left: 3.5rem;
  margin-right: 3.5rem;
  letter-spacing: 1.5px;
  font-weight: bold;
}
audio{
  margin-left: 3.5rem;
  margin-top: 1rem;
}
h5{
  margin-bottom: 1.5rem;
  letter-spacing: 2px;
  font-weight: bold;
}
h6{
  margin: 3rem;
  margin-bottom: 0;
  letter-spacing: 2px;
}
.btn-close{
  margin: 3rem;
  margin-bottom: 0;
}
.topics{
  height: 15rem;
  overflow-y: scroll;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
  width: 80%;
  left:10%;
  border-radius: 50px;
  margin-bottom: 1.5rem;
}
.topic{
  cursor: pointer;
}
/* 아래의 모든 코드는 영역::코드로 사용 */
.topics::-webkit-scrollbar {
    width: 10px;  /* 스크롤바의 너비 */
    display: none;
}

.btn-outline-primary{
   --bs-btn-hover-bg:#004ACC;
   --bs-btn-hover-color:white;
   --bs-btn-active-bg: #004ACC;
   --bs-btn-active-color: white;
   border-radius: 50px;
}
.btn-group{
  width: 80%;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
  border-radius: 50px;
}
.start_btn{
  margin-bottom: 3rem;
  background-color: #F2CB05;
  border-radius: 12px;
  color: white;
  cursor: pointer;
  font-weight: bold;
  text-align: center;
  transition: 200ms;
  width: 60%;
  box-sizing: border-box;
  border: 0;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}
.start_btn:not(:disabled):hover,
.start_btn:not(:disabled):focus {
  outline: 0;
  background: #F2CB05;
  box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
}

.start_btn:disabled {
  filter: saturate(0.2) opacity(0.5);
  -webkit-filter: saturate(0.2) opacity(0.5);
  cursor: not-allowed;
}
.bi-record-circle{
  font-size: 110px;
  color: red;
  margin-bottom: 5rem;
}
.bi-stop-circle{
  font-size: 110px;
  color: red;
  margin-bottom: 5rem;
}
.bi-volume-off,.bi-volume-up{
  font-size: 330px;
  width: 330px;
  height: 330px;
}
.bi-arrow-counterclockwise,.bi-download{
  font-size: 90px;
  margin-bottom: 4rem;
}
</style>