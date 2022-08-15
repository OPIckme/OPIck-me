import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const script = {
    state: {
        scriptList: [],
        script: {},
        selectTopicIdx:'',
        category:"",
        complet:false
    },

    getters: {
        scriptList: state => state.scriptList.slice().reverse(),
        script: state => state.script,
        selectTopicIdx: state => state.selectTopicIdx,
        category: state => state.category,
        complet: state => state.complet,
    },

    mutations: {
        SET_SCRIPTLIST: (state, scriptList) => state.scriptList = scriptList,
        SET_SCRIPT: (state, script) => state.script = script,
        SET_SECECTTOPICIDX: (state, selectTopicIdx) => state.selectTopicIdx = selectTopicIdx,
        SET_CATEGORY: (state, category) => state.category = category,
        SET_COMPLET: (state, complet) => state.complet = complet,
    },

    actions: {
        fetchScriptList( { commit },username ) {
            console.log(username)
            axios.get(API_URL + `/script/${username}`)
            .then(res =>{

                commit('SET_SCRIPTLIST', res.data.scriptList.filter(script=>{
                    
                    if(this.getters.complet){                        
                        return !script.feedbacks &&(this.getters.category===""||script.question.category===this.getters.category)
                    }else{
                        return script.feedbacks &&(this.getters.category===""||script.question.category===this.getters.category)
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

        fetchSelectTopicIdx( { commit },selectTopicIdx) {
            commit('SET_SECECTTOPICIDX',selectTopicIdx)
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
        fetchCategory( { commit }, category ) {
            commit('SET_CATEGORY', category)
        },
        fetchComplet( { commit }, complet ) {
            commit('SET_COMPLET', complet)
        },
    },
}