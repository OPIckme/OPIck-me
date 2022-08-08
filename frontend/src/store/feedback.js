import axios from 'axios'
const API_URL = 'http://i7B202.p.ssafy.io:8080/api/v1/feedback';

export const feedback = {
    state: {
        feedbackList: [],
        // script: {},
    },

    getters: {
      feedbackList: state => state.feedbackList,
        // script: state => state.script,
    },

    mutations: {
        SET_FEEDBACKLIST: (state, feedbackList) => state.feedbackList = feedbackList,
        // SET_SCRIPT: (state, script) => state.script = script,
    },

    actions: {
        fetchFeedbackList( { commit } ) {
            axios.get(API_URL)
            .then(res =>{
              commit('SET_FEEDBACKLIST', res.data)
              console.log(res.data)
              
          } ).catch(error => console.log(error.resonse))
            
            
        },

        
        
          // fetchScript( { commit, scriptId } ) {
        //     axios.get(API_URL + )
        // }

    },

  }