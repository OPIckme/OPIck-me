import axios from 'axios';
<<<<<<< HEAD
const API_URL = 'http://localhost:8080/api/v1/';
=======
import {API_URL} from '@/api/http.js';

>>>>>>> 3ff573d64d3ad8503239c16e3a1684a06aa638e2
class AuthService {
  login(user) {
    return axios.post(API_URL + '/auth/login', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          sessionStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    sessionStorage.removeItem('user');
  }
  register(user) {
    return axios.post(API_URL + '/users', {
      username: user.username,
      email: user.email,
      password: user.password,
      nickname: user.nickname
    });
  }
}
export default new AuthService();