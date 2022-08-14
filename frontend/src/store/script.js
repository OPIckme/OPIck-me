import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const script = {
    state: {
        scriptList: [],
        script: {},
        topic:"",
        complet:false
    },

    getters: {
        scriptList: state => state.scriptList.slice().reverse(),
        script: state => state.script,
        topic: state => state.topic,
        complet: state => state.complet,
    },

    mutations: {
        SET_SCRIPTLIST: (state, scriptList) => state.scriptList = scriptList,
        SET_SCRIPT: (state, script) => state.script = script,
        SET_TOPIC: (state, topic) => state.topic = topic,
        SET_COMPLET: (state, complet) => state.complet = complet,
    },

    actions: {
        fetchScriptList( { commit },username ) {
            console.log(username)
            axios.get(API_URL + `/script/${username}`)
            .then(res =>{
                console.log(res.data.scriptList)
                commit('SET_SCRIPTLIST', res.data.scriptList.filter(script=>{
                    
                    if(this.getters.complet){                        
                        return !script.feedbacks &&(this.getters.topic===""||script.question.topic===this.getters.topic)
                    }else{
                        return script.feedbacks &&(this.getters.topic===""||script.question.topic===this.getters.topic)
                    }
                    
                }))
            }).catch(error => console.log(error.response))
        },

        fetchScript( { commit }, {username, scriptId} ) {
            axios.get(API_URL + `/script/${username}/${scriptId}`)
            .then(res => {
                commit('SET_SCRIPT', res.data.script)
                console.log(res.data.script)
            })
        },

        modifyScript( {commit}, {scriptId, scriptContent} ){
            axios({
                url: API_URL + `/script/edit/${scriptId}`,
                method: 'put',
                data: {scriptContent}
            })
            .then(res => {
                commit('SET_SCRIPT', res.data.script)
                console.log("Halo", res.data)
            })
        },
        fetchTopic( { commit }, topic ) {
            commit('SET_TOPIC', topic)
        },
        fetchComplet( { commit }, complet ) {
            commit('SET_COMPLET', complet)
        },
    },
}