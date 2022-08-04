// import axios from 'axios'
// const API_URL = 'http://localhost:8080/api/v1/script';

// export const script = {
//     state: {
//         scriptList: [],
//         script: {},
//     },

//     getters: {
//         scriptList: state => state.scriptList,
//         script: state => state.script,
//     },

//     mutations: {
//         SET_SCRIPTLIST: (state, scriptList) => state.scriptList = scriptList,
//         SET_SCRIPT: (state, script) => state.script = script,
//     },

//     actions: {
//         fetchScriptList( { commit } ) {
//             axios.get(API_URL)
//             .then(res => commit('SET_SCRIPTLIST', res.data.scriptList))
//         },
//         // fetchScript( { commit, scriptId } ) {
//         //     axios.get(API_URL + )
//         // }

//     },


// }