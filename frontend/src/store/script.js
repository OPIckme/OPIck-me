import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const script = {
    state: {
        scriptList: [],
        script: {},
        selectTopicIdx:'',
    },

    getters: {
        scriptList: state => state.scriptList.slice().reverse(),
        script: state => state.script,
        selectTopicIdx: state => state.selectTopicIdx,
    },

    mutations: {
        SET_SCRIPTLIST: (state, scriptList) => state.scriptList = scriptList,
        SET_SCRIPT: (state, script) => state.script = script,
        SET_SECECTTOPICIDX: (state, selectTopicIdx) => state.selectTopicIdx = selectTopicIdx,
    },

    actions: {
        fetchScriptList( { commit }, username ) {
            console.log(username)
            axios.get(API_URL + `/script/${username}`)
            .then(res =>{
                console.log("HERE!!", res.data)
              commit('SET_SCRIPTLIST', res.data.scriptList)
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
        }
    },
}