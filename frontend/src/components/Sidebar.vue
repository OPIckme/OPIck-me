<template>
  <!-- sidebar -->
  <div class="flex-shrink-0 p-3 bg-white" style="width: 280px; margin-top: 1.3rem;">
    <span class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom fs-5 fw-semibold">Category</span>
    <ul class="list-unstyled ps-0">
      <p class="viewall" @click="selectCategory('')">전체보기</p>
      <li class="mb-1" v-for="category in categorys" :key="category">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true" @click="selectCategory(category)">
          <p :class="isSelectCategory(category)"><i :class="isSelectIcon(category)"></i>{{category}}</p>
        </button>
      </li>
    </ul>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
  
  name: "Sidebar",
  data() {
    return {
      categorys:["여가 활동","취미","운동","휴가출장"]
    };
  },
  methods: {
    selectCategory(category){
      this.$emit("selectCategory",category)
    },
    isSelectCategory(category){
      return category===this.category?"select":""
    },
    isSelectIcon(category){
      return category===this.category?"bi bi-chevron-right":""
    },
  },
  computed:{
    ...mapGetters(['category']),
  }
  
}
</script>

<style scoped>
.topic_li{
  margin: 1rem;
}
.select{
  color: #052A99;
  font-weight: bold;
}
.bi-chevron-right{
  margin-right: 0.5rem;
}
.viewall:hover{
  cursor:pointer;
  color: #052A99;
  font-weight: bold;
}
</style>