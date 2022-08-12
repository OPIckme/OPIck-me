import axios from 'axios';
import {API_URL} from '@/api/http.js';

export const consult = {
  state: {
    waitingConsultMap: {},
    consultId:""
  },

  getters: {
    waitingConsultMap: state => state.waitingConsultMap,
    consultId: state => state.consultId,
  },

  mutations: {
      SET_WAITINGCONSULTMAP: (state, waitingConsultMap) => state.waitingConsultMap = waitingConsultMap,
      SET_CONSULTID: (state, consultId) => state.consultId = consultId
  },

  actions: {
    fetchWaitingConsultMap( { commit } ) {
      axios.get(API_URL +'/consult')
      .then(res =>{
        commit('SET_WAITINGCONSULTMAP', res.data.consultMap)   
      })
      .catch(error => console.log(error.resonse))         
    },
    fetchConsultId( { commit },consultId ) {
      commit('SET_CONSULTID', consultId)          
    },
  },
}