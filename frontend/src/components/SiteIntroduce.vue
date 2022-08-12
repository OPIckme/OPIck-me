<template>
<div class="text-center" style="background-color:white;">
<h1>OPIck me</h1>
  <h1 class="ml11">
    <span class="text-wrapper">
      <span class="line line1"></span>
      <span class="letters">Of the OPIc</span>
    </span>
  </h1>
  <h1 class="ml12">
    <span class="text-wrapper2">
      <span class="line line2"></span>
      <span class="letters2">By the OPIc</span>
    </span>
  </h1>
  <h1 class="ml13">
    <span class="text-wrapper3">
      <span class="line line3"></span>
      <span class="letters3">For the OPIc</span>
    </span>
  </h1>
</div>
</template>
<script>
export default {
  name: "SiteIntroduce",
  mounted(){
    var textWrapper = document.querySelector('.ml11 .letters');
    textWrapper.innerHTML = textWrapper.textContent.replace(/([^\x00-\x80]|\w)/g, "<span class='letter'>$&</span>");
    var textWrapper2 = document.querySelector('.ml12 .letters2');
    textWrapper2.innerHTML = textWrapper2.textContent.replace(/([^\x00-\x80]|\w)/g, "<span class='letter2'>$&</span>");
    var textWrapper3 = document.querySelector('.ml13 .letters3');
    textWrapper3.innerHTML = textWrapper3.textContent.replace(/([^\x00-\x80]|\w)/g, "<span class='letter3'>$&</span>");

    anime.timeline({loop: true})
      .add({
        targets: '.ml11 .line',
        scaleY: [0,1],
        opacity: [0.5,1],
        easing: "easeOutExpo",
      }).add({
        targets: '.ml11 .line',
        translateX: [0, document.querySelector('.ml11 .letters').getBoundingClientRect().width + 10],
        easing: "easeOutExpo",
      }).add({
        targets: '.ml11 .letter',
        opacity: [0,1],
        easing: "easeOutExpo",
        offset: '-=775',
        delay: (el, i) => 34 * (i+1)
      }).add({
        targets: '.ml11',
        opacity: 0,
        easing: "easeOutExpo",
      }).add({
        targets: '.ml12 .line',
        scaleY: [0,1],
        opacity: [0.5,1],
        easing: "easeOutExpo",
      }).add({
        targets: '.ml12 .line',
        translateX: [0, document.querySelector('.ml12 .letters2').getBoundingClientRect().width + 10],
        easing: "easeOutExpo",
      }).add({
        targets: '.ml12 .letter2',
        opacity: [0,1],
        easing: "easeOutExpo",
        offset: '-=775',
        delay: (el, i) => 34 * (i+1)
      }).add({
        targets: '.ml12',
        opacity: 0,
        easing: "easeOutExpo",
      }).add({
        targets: '.ml13 .line',
        scaleY: [0,1],
        opacity: [0.5,1],
        easing: "easeOutExpo",
      }).add({
        targets: '.ml13 .line',
        translateX: [0, document.querySelector('.ml13 .letters3').getBoundingClientRect().width + 10],
        easing: "easeOutExpo",
      }).add({
        targets: '.ml13 .letter3',
        opacity: [0,1],
        easing: "easeOutExpo",
        offset: '-=775',
        delay: (el, i) => 34 * (i+1)
      }).add({
        targets: '.ml13',
        opacity: 0,
        easing: "easeOutExpo",
      }).add({
        targets: '.ml11 .ml12 .ml13 .letter .letter2 .letter3',
        scaleY: [0,1],
        opacity: [0.5,1],
        easing: "easeOutExpo",
      });
  }

};
</script>

<style scoped>
.ml11 {
  font-weight: 700;
  font-size: 3.5em;
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
  background-color: #fff;
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
  font-weight: 700;
  font-size: 3.5em;
}

.ml12 .text-wrapper2 {
  position: relative;
  display: inline-block;
  padding-top: 0.1em;
  padding-right: 0.05em;
  padding-bottom: 0.15em;
}

.ml12 .line {
  opacity: 0;
  position: absolute;
  left: 0;
  height: 100%;
  width: 3px;
  background-color: #fff;
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
  font-weight: 700;
  font-size: 3.5em;
}

.ml13 .text-wrapper3 {
  position: relative;
  display: inline-block;
  padding-top: 0.1em;
  padding-right: 0.05em;
  padding-bottom: 0.15em;
}

.ml13 .line {
  opacity: 0;
  position: absolute;
  left: 0;
  height: 100%;
  width: 3px;
  background-color: #fff;
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