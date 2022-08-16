<template>
<article class="card" data-aos="fade-up">
  <header class="card__thumb">
    <img :src="getImg()" class="card-img-top" alt="...">
  </header>
  <div class="card__body">
    <div class="d-flex justify-content-between">
      <div>
        <span class="tag" :class="`tag-${script.question.level}`">{{ script.question.level }}</span>
        <h4 class="topic">{{ script.question.topic }}</h4>
      </div>
      <div>
        <i class="bi bi-trash3 delete" data-bs-toggle="modal" :data-bs-target="modalId"></i>
      </div>
    </div>
    <p class="card__description" @click="routingDetail">Q. {{script.question.questionContent}}</p>
  </div>
  <footer class="card__footer">
    <p class="created-at"><small class="text-muted">{{ createdAt.slice(0,-3) }}</small></p>
  </footer>
</article>
<ScriptDeleteModal :scriptId="script.id"></ScriptDeleteModal>
</template>

<script>
import ScriptDeleteModal from './Modal/ScriptDeleteModal.vue';
export default {
    name: "ScriptCard",
    data() {
      return {
        modalId:"#delete"+this.script.id,
        createdAt: ''
      }
    },
    props : {
      script : Object,
    },
    components: { ScriptDeleteModal },
    methods: {
        routingDetail(){
        this.$router.push({
        name:'scriptdetail',
        params:{scriptId:this.script.id}
        })
      },
      getImg(){
        return require(`@/assets/card/${this.script.question.topic}.jpg`)
      }
    },
    mounted() {
      const d = new Date(this.script.createdAt)
      this.createdAt = d.toLocaleString()
      console.log(this.script)
    }
}
</script>

<style scoped>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
@import url(http://fonts.googleapis.com/css?family=Open+Sans:400,700);
.card{
  width: 20rem; 
  height: 25rem; 
  margin: 1rem; 
  border-radius: 15px;
  border-width: 0px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  overflow: hidden;
  -webkit-transition: box-shadow 0.5s;
  transition: box-shadow 0.5s;
}
.card-img-top{
  width: 100%;
  height: 12rem;
  object-fit: cover;
  display: block;
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
  -webkit-transition: opacity 0.5s, -webkit-transform 0.5s;
  transition: opacity 0.5s, -webkit-transform 0.5s;
  transition: opacity 0.5s, transform 0.5s;
  transition: opacity 0.5s, transform 0.5s, -webkit-transform 0.5s;
}
.delete{
  margin-top:0.2rem; 
  float: right; 
  font-size: 17px;
  margin:0;
  z-index: -1;
  cursor: pointer;
}
.topic{
  margin-top: 0.5rem; 
  font-size:18px; 
  font-weight: bold;
  cursor: default;
}
.created-at{
  margin-top: 2.3rem; 
  text-align: right; 
  font-size:15px;
}
.tag {
  background: #cccccc;
  border-radius: 50px;
  font-size: 12px;
  margin: 0;
  color: #fff;
  padding: 2px 10px;
  text-transform: uppercase;
  cursor: default;
}
.tag-AL {
  background-color: #47bcd4;
}
.tag-IH {
  background-color: #5e76bf;
}
.tag-IM {
  background-color: #cd5b9f;
}
.tag-IL {
  background-color: #c2d553;
}

.card__thumb {
  margin-top: 0.5rem;
  border-radius: 15px;
  height: 12rem;
  overflow: hidden;
  background-color: #000;
  -webkit-transition: height 0.5s;
  transition: height 0.5s;
  cursor: default;
}

.card:hover .card__thumb {
  height: 130px;
}

.card:hover .card-img-top {
  opacity: 0.6;
  -webkit-transform: scale(1.2);
  transform: scale(1.2);
}

.card__body {
  position: relative;
  height: 185px;
  padding: 20px;
  -webkit-transition: height 0.5s;
  transition: height 0.5s;
}

.card:hover .card__body {
  height: 300px;
}

.card__description {
  position: absolute;
  left: 20px;
  right: 20px;
  margin: 0;
  padding: 0;
  color: #666C74;
  line-height: 27px;
  opacity: 0;
  -webkit-transform: translateY(45px);
  transform: translateY(45px);
  -webkit-transition: opacity 0.3s, -webkit-transform 0.3s;
  transition: opacity 0.3s, -webkit-transform 0.3s;
  transition: opacity 0.3s, transform 0.3s;
  transition: opacity 0.3s, transform 0.3s, -webkit-transform 0.3s;
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  cursor: pointer;
}

.card:hover .card__description {
  opacity: 1;
  -webkit-transform: translateY(0px);
  transform: translateY(0px);
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}

.card__footer {
  position: absolute;
  bottom: 2px;
  left: 20px;
  right: 20px;
  font-size: 11px;
  color: #A3A9A2;
  cursor: default;
}
</style>