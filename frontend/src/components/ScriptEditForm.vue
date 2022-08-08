<template>
    <form @submit.prevent="onSubmit">
        <div class="field padding-bottom--24">
            <textarea v-model="content" cols="50" rows="5" id="content" style=width:700px;height:300px;></textarea>
        </div>
        <div class="field padding-bottom--24">
            <button class="btn btn-primary">수정하기</button>
        </div>
    </form>

</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: "ScriptEditForm",
    props: {
        script: Object
    },
    data() {
        return {
            content: this.script.scriptContent
        }
    },
    methods: {
        ...mapActions(['modifyScript', 'fetchScript']),
        onSubmit() {
            const payload = {
            scriptId: this.script.id,
            scriptContent: this.content
            }
            this.modifyScript(payload)
            this.fetchScript(this.script.id)
            this.$router.push({
            name:'scriptdetail',
            params:{scriptId:this.script.id}
            })
        }
    }
}
</script>
