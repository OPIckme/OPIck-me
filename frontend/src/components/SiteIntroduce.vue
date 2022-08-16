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

      <section class="mt-3 pt-3">
        <div class="container text-center d-flex flex-column mt-5 mb-5 pt-5">
          <h1 class="ml11 mt-3 mb-3">
            <span class="text-wrapper">
              <span class="line line1"></span>
              <span
                class="letters"
                style="
                  font-size: 80px;
                  color: #ffffff;
                  text-shadow: #fc0 1px 0 10px;
                "
                >OF THE OPIC</span
              >
            </span>
          </h1>
          <h1 class="ml12 mt-3 mb-3">
            <span class="text-wrapper2">
              <span class="line_2 line2"></span>
              <span
                class="letters2"
                style="
                  font-size: 80px;
                  color: #ffffff;
                  text-shadow: #fc0 1px 0 10px;
                "
                >BY THE OPIC</span
              >
            </span>
          </h1>
          <h1 class="ml13 mt-3 mb-3">
            <span class="text-wrapper3">
              <span class="line_3 line3"></span>
              <span
                class="letters3"
                style="
                  font-size: 80px;
                  color: #ffffff;
                  text-shadow: #fc0 1px 0 10px;
                "
                >FOR THE OPIC</span
              >
            </span>
          </h1>
        </div>
      </section>
    </div>
    <div>
      <div class="info">
        <section class="info-section" data-aos="fade-up">
          <div class="container d-flex row" style="height: 30%">
            <div class="container col-4" data-aos="fade-up">
              <p
                class="info-title"
                data-aos="fade-down"
                style="text-align: center"
              >
                스크립트 만들기
              </p>
            </div>
            <div
              class="container col-6"
              data-aos="fade-left"
              data-aos-delay="300"
            >
              <p class="info-content">
                문제를 듣고 녹음을 진행해보세요. Speech-To-Text를 통해 자동으로
                문서로 변환됩니다.
              </p>
            </div>
          </div>
        </section>

        <img
          class="img img-thumbnail"
          src="../assets/스크립트_수정.gif"
          alt=""
          data-aos="fade-up"
        />
      </div>
      <div class="info" style="background-color: #f9f9f9">
        <section
          class="info-section"
          data-aos="fade-up"
          style="background-color: #f9f9f9"
        >
          <div class="container d-flex row" style="height: 30%">
            <div class="container col-4" data-aos="fade-up">
              <h1
                class="info-title"
                data-aos="fade-right"
                style="text-align:center font-weight:bold"
              >
                주제별 문제 제공
              </h1>
            </div>
            <div
              class="container col-6"
              data-aos="fade-left"
              data-aos-delay="300"
            >
              <p class="info-content">
                각 주제별로 다양한 문제가 준비되어 있습니다. 주제와 난이도를
                선택해서 질문에 답변해 보세요.
              </p>
            </div>
          </div>
        </section>
        <div>
          <img
            class="img img-thumbnail"
            src="../assets/주제선택.gif"
            alt=""
            data-aos="fade-up"
          />
        </div>
      </div>
      <div class="info">
        <section class="info-section" data-aos="fade-up">
          <div class="container d-flex row" style="height: 30%">
            <div class="container col-4" data-aos="fade-up">
              <h1
                class="info-title"
                data-aos="fade-up"
                style="text-align: center"
              >
                1대1 상담
              </h1>
            </div>
            <div
              class="container col-6"
              data-aos="fade-left"
              data-aos-delay="300"
            >
              <p class="info-content">
                스크립트를 작성하면서 준비를 하다보면 궁금한 내용이 생길 수
                있습니다. 그럴 때는 1대1 상담을 이용해보세요.
              </p>
            </div>
          </div>
        </section>

        <img
          class="img img-thumbnail"
          src="../assets/주제선택.gif"
          alt=""
          data-aos="fade-up"
        />
      </div>
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
      .timeline({ loop: 1 })
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
        delay: (el, i) => 0.5 * (i + 1),
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
        delay: (el, i) => 0.5 * (i + 1),
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
        delay: (el, i) => 0.5 * (i + 1),
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
        delay: (el, i) => 0.5 * (i + 1),
      });
  },
};
</script>
<style scoped>
.img {
  width: 60%;
  margin-left: 20%;
}
.logo {
  position: absolute;
  translation: 0.05em;
  left: 8%;
  top: 17px;
}
.info {
  padding-top: 10%;
  padding-bottom: 10%;
  height: 60%;
}
.info-title {
  font-size: 50px;
  font-weight: bold;
}
.info-content {
  font-size: 20px;
  letter-spacing: 1px;
  line-height: 2rem;
}
.info-section {
  width: 90%;
  height: 150px;
  background-color: white;
  float: none;
  margin-left: 5%;
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
