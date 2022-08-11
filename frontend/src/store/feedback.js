import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const feedback = {
    state: {
        feedbackList: [],
        feedback: {},
    },

    getters: {
      feedbackList: state => state.feedbackList.slice().reverse(),
      feedback: state => state.feedback,
    },

    mutations: {
        SET_FEEDBACKLIST: (state, feedbackList) => state.feedbackList = feedbackList,
        SET_FEEDBACK: (state, feedback) => state.feedback = feedback,
    },

    actions: {
        fetchFeedbackList( { commit }, username ) {
            axios.get(API_URL +`/feedback/${username}`)
            .then(res =>{
              commit('SET_FEEDBACKLIST', res.data)
              console.log(res.data)
          } ).catch(error => console.log(error.resonse))
        },

        fetchFeedback( { commit }, {username, feedbackId} ) {
          axios.get(API_URL + `/feedback/${username}/${feedbackId}`)
          .then(res => {
              commit('SET_FEEDBACK', res.data.feedback)
              console.log("HERE!!!", res.data.feedback)
          })
      },

    }
  }