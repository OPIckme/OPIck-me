<template>
<p>[Script to Edit]</p>
<form @submit.prevent="onSubmit">
    <textarea v-model="content" cols="50" rows="5" id="content"></textarea>
    <button class="btn"><i class="bi bi-save2-fill"></i>Save</button>
</form>

</template>

<script>
import { mapActions } from 'vuex';
import axios from 'axios';
import {API_URL} from '@/api/http.js';

export default {
    name: "ScriptEditForm",
    props: {
        script: Object
    },
    data() {
        return {
            content: this.script.scriptContent,
            username: this.$store.state.auth.user.username
        }
    },
    methods: {
        ...mapActions(['modifyScript']),
        getScript(){
          axios.get(API_URL + `/script/${this.username}/${this.script.id}`)
          .then(res => {
            console.log(res.data)
            }
          )
        },
        onSubmit() {
            const payload = {
            scriptId: this.script.id,
            scriptContent: this.content
            }
            this.modifyScript(payload)
            console.log(this.username, this.script.scriptContent)
            this.getScript()
            this.$router.push({
            name:'scriptdetail',
            params:{scriptId:this.script.id}
            })
        }
    }
}
</script>

<style scoped>
i{
  margin-right: 1rem;
}
textarea{
    width: 100%;
    height: 20rem;
    margin-top: 2rem;
    font-size: 18px;
    letter-spacing: 1px;
    line-height: 1.5rem;
    /* border: none; */
    resize: none;
}
p{
  width: 100%;
  margin-top: 2rem;
}
form{
    margin-bottom: 4rem;
}
button {
  margin-top: 1.2rem;
  background-color: #F2CB05;
  border-radius: 12px;
  color: white;
  cursor: pointer;
  letter-spacing: 3px;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  transition: 200ms;
  width: 100%;
  box-sizing: border-box;
  border: 0;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

button:not(:disabled):hover,
button:not(:disabled):focus {
  outline: 0;
  background: #F2CB05;
  box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
}

button:disabled {
  filter: saturate(0.2) opacity(0.5);
  -webkit-filter: saturate(0.2) opacity(0.5);
  cursor: not-allowed;
}
</style>