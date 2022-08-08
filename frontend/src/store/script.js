import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const script = {
    state: {
        scriptList: [],
        script: {},
    },

    getters: {
        scriptList: state => state.scriptList,
        script: state => state.script,
    },

    mutations: {
        SET_SCRIPTLIST: (state, scriptList) => state.scriptList = scriptList,
        SET_SCRIPT: (state, script) => state.script = script,
    },

    actions: {
        fetchScriptList( { commit } ) {
            axios.get(API_URL + '/script')
            .then(res =>{
              commit('SET_SCRIPTLIST', res.data.scriptList)
              console.log(res.data.scriptList)
            }).catch(error => console.log(error.response))
        },

        fetchScript( { commit }, scriptId ) {
            axios.get(API_URL + `/script/${scriptId}`)
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
        }
    },
}