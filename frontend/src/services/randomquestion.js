import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/question/random';
class RandomQuestion {
  getQuestion(topic,level) {
    console.log(topic)
    console.log(level)
    return axios.get(API_URL, {
      params: {
        topic: topic,
        level: level
      }
    })
      .then(response => {
        console.log(response)
        // return response.data;
      });
  }
}
export default new RandomQuestion();