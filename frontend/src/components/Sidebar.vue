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
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">영화보기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">나이트클럽가기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">공연보기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">콘서트보기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">박물관가기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">공원가기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">캠핑하기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">해변가기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">스포츠 관람</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">주거 개선</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
          <i class="bi bi-chevron-right"></i>취미/관심사
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">아이에게 책 읽어주기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">음악 감상하기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">악기 연주하기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">혼자 노래부르거나 합창하기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">춤추기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">글쓰기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">그림 그리기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">요리하기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">애완동물 기르기</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          <i class="bi bi-chevron-right"></i>운동
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">농구</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">야구</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">축구</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">미식축구</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">하키</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">크리켓</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">골프</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">배구</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">테니스</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">배드민턴</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">탁구</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">수영</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">자전거</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">스노우보드</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">아이스 스케이트</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">조깅</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">걷기</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">요가</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">하이킹</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">낚시</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">헬스</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          <i class="bi bi-chevron-right"></i>휴가/출장
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">국내출장</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">해외출장</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">집에서 보내는 휴가</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">국내여행</a></li>
            <li class="topic_li"><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">해외여행</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</template>
<style scoped>
.topic_li{
  margin: 1rem;
}
</style>