import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const consult = {
    state: {
      waitingConsultMap: {}
    },

    getters: {
      waitingConsultMap: state => state.waitingConsultMap
    },

    mutations: {
        SET_WAITINGCONSULTMAP: (state, waitingConsultMap) => state.waitingConsultMap = waitingConsultMap
    },

    actions: {
        fetchWaitingConsultMap( { commit } ) {
          axios.get(API_URL +'/consult')
          .then(res =>{
            commit('SET_WAITINGCONSULTMAP', res.data.consultMap)
            console.log(res.data.consultMap)              
        } ).catch(error => console.log(error.resonse))         
      },
    },

  }