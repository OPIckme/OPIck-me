import axios from 'axios';
import {API_URL} from '@/api/http.js';

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
            axios.get(API_URL +'/feedback')
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