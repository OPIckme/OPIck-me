<template>
<button type="button" class="btn btn-primary btn-lg">Script 생성</button>
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