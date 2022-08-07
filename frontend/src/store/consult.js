import axios from 'axios'
const API_URL = 'http://localhost:8080/api/v1/consult';

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
          axios.get(API_URL)
          .then(res =>{
            commit('SET_WAITINGCONSULTMAP', res.data.consultMap)
            console.log(res.data.consultMap)              
        } ).catch(error => console.log(error.resonse))         
      },
    },

  }