import axios from 'axios'
const API_URL = 'http://localhost:8080/api/v1/consult';

export const consult = {
    state: {
      waitingConsultList: [],
    },

    getters: {
      waitingConsultList: state => state.waitingConsultList,
    },

    mutations: {
        SET_WAITINGCONSULTLIST: (state, waitingConsultList) => state.waitingConsultList = waitingConsultList,
    },

    actions: {
        fetchWaitingConsultList( { commit } ) {
            axios.get(API_URL)
            .then(res =>{
              commit('SET_WAITINGCONSULTLIST', res.data.consultList)
              console.log(res.data.consultList)
              
          } ).catch(error => console.log(error.resonse))
            
            
        },
    },

  }