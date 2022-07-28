<template>  
<!-- Script 생성 버튼 -->
<a class="btn btn-primary" data-bs-toggle="modal" href="#exampleModalToggle" role="button">Script 생성</a>
<!-- Survey 모달 -->
<div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel">Survey</h5>
        <div class="d-flex flex-column">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          <span></span>
          <select class="form-select" aria-label="Default select example" style="width:200px">
            <option selected>Open this select Level</option>
            <option value="1">AL</option>
            <option value="2">IH</option>
            <option value="3">IM</option>
            <option value="4">IL</option>
          </select>
        </div>
      </div>
      <div class="modal-body">
        Show a second modal and hide this one with the button below.
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal">Open second modal</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="exampleModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel2">Modal 2</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Hide this modal and show the first with the button below.
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">Back to first</button>
      </div>
    </div>
  </div>
</div>
</template>
<script>
export default {
  name: 'Script',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/');
    }
  }
};
</script>