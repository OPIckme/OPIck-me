<template>
  <!-- sidebar -->
  <div class="flex-shrink-0 p-3 bg-white" style="width: 280px; margin-top: 1.3rem;">
    <span class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom fs-5 fw-semibold" @click="selectCategory('')">Category</span>
    <ul class="list-unstyled ps-0">
      <li class="mb-1" v-for="category in categorys" :key="category">
        <button class="btn rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true" @click="selectCategory(category)">
          <i class="bi bi-chevron-right"></i>{{category}}
        </button>
      </li>
      
    </ul>
  </div>
</template>
<script>
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
    }
  },
  
}
</script>

<style scoped>
.topic_li{
  margin: 1rem;
}
</style>