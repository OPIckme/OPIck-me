<template>
<nav class="navbar" style="background-color:#0742F2;">
    <div class="container-fluid">
        <img src="../assets/logo.png" alt="" style="width:150px">
        <!-- 나가기 -->
        <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Consultclose">상담종료</button>
    </div>
</nav>
<div class="container">
    <h1>1대1상담</h1>
</div>
<ConsultCloseModal></ConsultCloseModal>
</template>
<script>
import ConsultCloseModal from '@/components/Modal/ConsultCloseModal.vue';

export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    components: { ConsultCloseModal }
};
</script>
