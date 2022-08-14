<template>
  <!-- sidebar -->
  <div class="flex-shrink-0 p-3 bg-white" style="width: 280px; margin-top: 1.3rem;">
    <span class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom fs-5 fw-semibold">Topic</span>
    <ul class="list-unstyled ps-0">
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
          <i class="bi bi-chevron-right"></i>여가 활동
        </button>
        <div class="collapse show" id="home-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li" v-for="topic in topics['여가 활동']" :key="topic"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded" @click="selectTopic(topic)">{{topic}}</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
          <i class="bi bi-chevron-right"></i>취미/관심사
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li" v-for="topic in topics['취미/관심사']" :key="topic"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded" @click="selectTopic(topic)">{{topic}}</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          <i class="bi bi-chevron-right"></i>운동
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li" v-for="topic in topics['운동']" :key="topic"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded" @click="selectTopic(topic)">{{topic}}</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          <i class="bi bi-chevron-right"></i>휴가/출장
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li" v-for="topic in topics['휴가/출장']" :key="topic"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded" @click="selectTopic(topic)">{{topic}}</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: "Sidebar",
  data() {
    return {
      topics:{
        "여가 활동":["영화보기", "나이트클럽 가기", "공연보기", "콘서트보기", "박물관가기", "공원가기", "캠핑하기", "해변가기", "스포츠 관람", "주거 개선"],
        "취미/관심사":["아이에게 책 읽어주기", "음악 감상하기", "악기 연주하기", "혼자 노래부르거나 합창하기", "춤추기", "글쓰기", "그림 그리기", "요리하기", "애완동물 기르기"],
        "운동":[ "농구", "야구", "축구", "미식축구", "하키", "크리켓", "골프", "배구", "테니스", "배드민턴", "탁구", "수영", "자전거", "스노우보드", "아이스 스케이트", "조깅", "걷기", "요가", "하이킹", "낚시", "헬스"],
        "휴가/출장":["국내출장", "해외출장", "집에서 보내는 휴가", "국내 여행", "해외 여행"]
      }
    };
  },
  methods: {
    selectTopic(topic){
      this.$emit("selectTopic",topic)
    }
  },
  
}
</script>

<style scoped>
.topic_li{
  margin: 1rem;
}
</style>