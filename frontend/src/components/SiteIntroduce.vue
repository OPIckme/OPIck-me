<template>
  <div class="page-background">
    <div class="top-section">
      <nav class="navbar" style="background-color: transparent; height: 80px">
        <div class="container">
          <router-link class="logo" to="/">
            <img src="../assets/logo.png" alt="" style="width: 200px" />
          </router-link>
          <router-link to="/login" class="nav-link">
            <button class="Login" role="button">Login</button>
          </router-link>
        </div>
      </nav>

      <section>
        <div class="container text-center d-flex flex-column mt-5 mb-5">
          <h1 class="ml11 p-2 mt-5">
            <span class="text-wrapper">
              <span class="line line1"></span>
              <span class="letters">OF THE OPIC</span>
            </span>
          </h1>
          <h1 class="ml12 p-2">
            <span class="text-wrapper2">
              <span class="line_2 line2"></span>
              <span class="letters2">BY THE OPIC</span>
            </span>
          </h1>
          <h1 class="ml13 p-2">
            <span class="text-wrapper3">
              <span class="line_3 line3"></span>
              <span class="letters3">FOR THE OPIC</span>
            </span>
          </h1>
        </div>
      </section>
    </div>
    <div>
      <section class="info mt-5" data-aos="fade-up">
        <div class="container d-flex">
          <div
            class="text-container container col-5 p-5 mt-5"
            data-aos="fade-up"
          >
            <h1 class="saction-text" data-aos="fade-up">스크립트 만들기</h1>
            <br />
            <br />
            <h4>문제를 듣고 녹음을 진행해보세요.</h4>
            <h4>Speech-To-Text를 통해 자동으로</h4>
            <h4>문서로 변환됩니다.</h4>
          </div>
          <div
            class="img-container container col-5 p-5 mt-5"
            data-aos="fade-up"
          >
            <img
              class="img-thumbnail"
              src="../assets/스크립트.png"
              alt=""
              data-aos="fade-up"
            />
          </div>
        </div>
      </section>
      <section id="info2" class="info mt-5" style="" data-aos="fade-up">
        <div class="container d-flex">
          <div class="container col-4 p-5 mt-5" data-aos="fade-up">
            <img
              class="img-thumbnail"
              src="../assets/what.png"
              alt=""
              data-aos="fade-up"
            />
          </div>
          <div class="container col-5 p-5 mt-5" data-aos="fade-up">
            <h1 class="saction-text" data-aos="fade-up">주제별 문제 제공</h1>
            <br />
            <br />
            <h4>
              각 주제별로 다양한 문제가 준비되어 있습니다. 주제와 난이도를
              선택해서 질문에 답변해보세요.
            </h4>
          </div>
        </div>
      </section>
      <section class="info mt-5" data-aos="fade-up">
        <div class="container d-flex">
          <div
            class="text-container container col-5 p-5 mt-5"
            data-aos="fade-up"
          >
            <h1 class="saction-text" data-aos="fade-up">1대1 상담</h1>
            <br />
            <br />
            <h4>
              스크립트를 작성하면서 준비를 하다보면 궁금한 내용이 생길 수
              있습니다. 그럴 때는 1대1 상담을 이용해보세요.
            </h4>
          </div>
          <div
            class="img-container container col-4 p-2 mt-5"
            data-aos="fade-up"
          >
            <img
              class="img-thumbnail"
              src="../assets/consult.png"
              alt=""
              data-aos="fade-up"
            />
          </div>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
import AOS from "aos";
import "aos/dist/aos.css";
AOS.init();
export default {
  name: "SiteIntroduce",
  mounted() {
    var textWrapper = document.querySelector(".ml11 .letters");
    textWrapper.innerHTML = textWrapper.textContent.replace(
      /([^\x00-\x80]|\w)/g,
      "<span class='letter'>$&</span>"
    );
    var textWrapper2 = document.querySelector(".ml12 .letters2");
    textWrapper2.innerHTML = textWrapper2.textContent.replace(
      /([^\x00-\x80]|\w)/g,
      "<span class='letter2'>$&</span>"
    );
    var textWrapper3 = document.querySelector(".ml13 .letters3");
    textWrapper3.innerHTML = textWrapper3.textContent.replace(
      /([^\x00-\x80]|\w)/g,
      "<span class='letter3'>$&</span>"
    );

    anime
      .timeline({ loop: true })
      .add({
        targets: ".ml11 .line",
        scaleY: [0, 1],
        opacity: [0.5, 1],
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml11 .line",
        translateX: [
          0,
          document.querySelector(".ml11 .letters").getBoundingClientRect()
            .width + 10,
        ],
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml11 .letter",
        opacity: [0, 1],
        easing: "easeOutExpo",
        offset: "-=775",
        delay: (el, i) => 34 * (i + 1),
      })
      .add({
        targets: ".ml11",
        opacity: 0,
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml12 .line_2",
        scaleY: [0, 1],
        opacity: [0.5, 1],
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml12 .line_2",
        translateX: [
          0,
          document.querySelector(".ml12 .letters2").getBoundingClientRect()
            .width + 10,
        ],
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml12 .letter2",
        opacity: [0, 1],
        easing: "easeOutExpo",
        offset: "-=775",
        delay: (el, i) => 34 * (i + 1),
      })
      .add({
        targets: ".ml12",
        opacity: 0,
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml13 .line_3",
        scaleY: [0, 1],
        opacity: [0.5, 1],
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml13 .line_3",
        translateX: [
          0,
          document.querySelector(".ml13 .letters3").getBoundingClientRect()
            .width + 10,
        ],
        easing: "easeOutExpo",
      })
      .add({
        targets: ".ml13 .letter3",
        opacity: [0, 1],
        easing: "easeOutExpo",
        offset: "-=775",
        delay: (el, i) => 34 * (i + 1),
      })
      .add({
        targets: ".ml13",
        opacity: 0,
        easing: "easeOutExpo",
      })
      .add({
        targets: [".ml11", ".letter", ".ml12", ".letter2", ".ml13", ".letter3"],
        opacity: [0, 1],
        easing: "easeOutExpo",
        offset: "-=775",
        delay: (el, i) => 34 * (i + 1),
      });
  },
};
</script>
<style scoped>
.logo {
  position: absolute;
  translation: 0.05em;
  left: 8%;
  top: 17px;
}
.info {
  width: 90%;
  height: 500px;
  background-color: white;
  float: none;
}
#info2 {
  margin-left: 10%;
}
.top-section {
  width: 100%;
  height: 40%;
  background-image: url("../assets/books.jpg");
  background-size: cover;
}

.page-background {
  background-color: #ffffff;
}
section {
  height: 100vh;
  /* background-color: black ; */
  margin-top: 0;
  /* background-image: url(../assets/intro.png);
  background-size: cover; */
  /* box-shadow: 0 14px 28px rgba(0,0,0,0.25),
      0 10px 10px rgba(0,0,0,0.22); */
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
}
.ml11 {
  font-weight: 800;
  font-size: 4em;
  letter-spacing: 6px;
  /* color:#fff; */
}

.ml11 .text-wrapper {
  position: relative;
  display: inline-block;
  padding-top: 0.1em;
  padding-right: 0.05em;
  padding-bottom: 0.15em;
}

.ml11 .line {
  opacity: 0;
  position: absolute;
  left: 0;
  height: 100%;
  width: 3px;
  background-color: #ffffff;
  transform-origin: 0 50%;
}

.ml11 .line1 {
  top: 0;
  left: 0;
}

.ml11 .letter {
  display: inline-block;
  line-height: 1em;
}

.ml12 {
  font-weight: 800;
  font-size: 4em;
  letter-spacing: 6px;
  /* color:#fff; */
}

.ml12 .text-wrapper2 {
  position: relative;
  display: inline-block;
  padding-top: 0.1em;
  padding-right: 0.05em;
  padding-bottom: 0.15em;
}

.ml12 .line_2 {
  opacity: 0;
  position: absolute;
  left: 0;
  height: 100%;
  width: 3px;
  background-color: #ffffff;
  transform-origin: 0 50%;
}

.ml12 .line2 {
  top: 0;
  left: 0;
}

.ml12 .letter2 {
  display: inline-block;
  line-height: 1em;
}

.ml13 {
  font-weight: 800;
  font-size: 4em;
  letter-spacing: 6px;
  /* color:#fff; */
}

.ml13 .text-wrapper3 {
  position: relative;
  display: inline-block;
  padding-top: 0.1em;
  padding-right: 0.05em;
  padding-bottom: 0.15em;
}

.ml13 .line_3 {
  opacity: 0;
  position: absolute;
  left: 0;
  height: 100%;
  width: 3px;
  background-color: #ffffff;
  transform-origin: 0 50%;
}

.ml13 .line3 {
  top: 0;
  left: 0;
}

.ml13 .letter3 {
  display: inline-block;
  line-height: 1em;
}
</style>
